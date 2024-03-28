package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dossier")
public class Dossier implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference",unique = true,length = 100)
    private String reference;

    @Column(name = "date_ouverture")
    @Temporal(TemporalType.DATE)
    private Date dateOuverture;

    @Column(name = "date_maj")
    @Temporal(TemporalType.DATE)
    private Date dateMaj;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "client",referencedColumnName = "id")
    private Client client;

    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "automobile",referencedColumnName = "id")
    private Automobile automobile;

    @OneToMany(mappedBy = "dossier")
    private List<Reception> receptions = new ArrayList<>();

    @OneToMany(mappedBy = "dossier")
    private List<Diagnostic> diagnostics = new ArrayList<>();

    @OneToMany(mappedBy = "dossier")
    private List<Reparation> reparations = new ArrayList<>();

    public Dossier() {
        this.reference = "reference";
    }

    public Dossier(String reference) {
        this.reference = reference;
    }

    public String toString(){
        return automobile.getImmatriculation()+" / "+automobile.getClient().getRaisonSociale();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(Date dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public Date getDateMaj() {
        return dateMaj;
    }

    public void setDateMaj(Date dateMaj) {
        this.dateMaj = dateMaj;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public List<Reception> getReceptions() {
        return receptions;
    }

    public void setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
    }

    public List<Reparation> getReparations() {
        return reparations;
    }

    public void setReparations(List<Reparation> reparations) {
        this.reparations = reparations;
    }

    public List<Diagnostic> getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(List<Diagnostic> diagnostics) {
        this.diagnostics = diagnostics;
    }
}
