package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "diagnostic")
public class Diagnostic implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "ouvrier")
    private String ouvrier;

    @Column(name = "diagnostic")
    private String diagnostic;

    @Column(name = "compteur")
    private Integer compteur;

    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @ManyToOne
    @JoinColumn(name="profile",referencedColumnName="id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name="dossier",referencedColumnName="id",nullable=false)
    private Dossier dossier;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "diagnostic")
    private List<Defaut> defauts = new ArrayList<>();

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "diagnostic")
    private List<Solution> solutions = new ArrayList<>();

    public Diagnostic() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOuvrier() {
        return ouvrier;
    }

    public void setOuvrier(String ouvrier) {
        this.ouvrier = ouvrier;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public Integer getCompteur() {
        return compteur;
    }

    public void setCompteur(Integer compteur) {
        this.compteur = compteur;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<Defaut> getDefauts() {
        return defauts;
    }

    public void setDefauts(List<Defaut> defauts) {
        this.defauts = defauts;
    }

    public void addDefaut(Defaut defaut){
        defaut.setDiagnostic(this);
        this.defauts.add(defaut);
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    public void addSolution(Solution solution){
        solution.setDiagnostic(this);
        this.solutions.add(solution);
    }
}
