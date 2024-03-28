package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reparation")
public class Reparation implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "compteur")
    private Integer compteur;

    @Column(name = "numero")
    private String numero;

    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @Column(name = "autorisation")
    private Boolean autorisation;

    @Column(name = "ouvrier")
    private String ouvrier;

    @Column(name = "total_fourniture")
    private double totalFourniture;

    @Column(name = "total_tache")
    private double totalTache;

    @Column(name = "total")
    private double total;

    @Column(name = "total_lettre")
    private String totalLettre;

    @Column(name = "is_billed")
    private Boolean billed;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="dossier",referencedColumnName="id",nullable=false)
    private Dossier dossier;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="profile",referencedColumnName="id")
    private Profile profile;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy="reparation")
    private List<Tache> taches = new ArrayList<>();

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy="reparation")
    private List<Fourniture> fournitures = new ArrayList<>();

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy="reparation")
    private List<Versement> versements = new ArrayList<>();

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy="reparation")
    private Qualite qualite;

    public String toString(){
        return getId()+" "+getDossier().getAutomobile().getImmatriculation()+" "+getDossier().getAutomobile().getClient().getRaisonSociale()+" "+ new SimpleDateFormat("dd-MM-yyyy").format(getDateCreation());
    }

    public Reparation() {
        this.id = 0;
        this.description = "";
        this.compteur = 0;
        this.numero = "";
        this.dateCreation = new Date();
        this.autorisation = false;
        this.ouvrier = "";
        this.totalFourniture = 0;
        this.totalTache = 0;
        this.total = 0;
        this.totalLettre = "";
        this.billed = false;
        this.dossier = new Dossier();
        this.profile = new Profile();
        this.taches = new ArrayList<>();
        this.fournitures = new ArrayList<>();
        this.versements = new ArrayList<>();
        this.qualite = new Qualite();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCompteur() {
        return compteur;
    }

    public void setCompteur(Integer compteur) {
        this.compteur = compteur;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(Boolean autorisation) {
        this.autorisation = autorisation;
    }

    public String getOuvrier() {
        return ouvrier;
    }

    public void setOuvrier(String ouvrier) {
        this.ouvrier = ouvrier;
    }

    public double getTotalFourniture() {
        this.totalFourniture=0;
        for (Fourniture fourniture : getFournitures()) {
            this.totalFourniture += fourniture.getMontant();
        }
        return this.totalFourniture;
    }

    public void setTotalFourniture(double totalFourniture) {
        this.totalFourniture = totalFourniture;
    }

    public double getTotalTache() {
        this.totalTache=0;
        for (Tache tache : this.getTaches()) {
            this.totalTache += tache.getCout();
        }
        return this.totalTache;
    }

    public void setTotalTache(double totalTache) {
        this.totalTache = totalTache;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public void addTache(Tache tache){
        tache.setReparation(this);
        this.taches.add(tache);
    }

    public List<Fourniture> getFournitures() {
        return fournitures;
    }

    public void setFournitures(List<Fourniture> fournitures) {
        this.fournitures = fournitures;
    }

    public void addFourniture(Fourniture fourniture){
        fourniture.setReparation(this);
        this.fournitures.add(fourniture);
    }

    public Qualite getQualite() {
        return qualite;
    }

    public void setQualite(Qualite qualite) {
        this.qualite = qualite;
    }

    public double getSolde(){
        double solde=0;
        for (Versement versement:versements) {
            solde+=versement.getVersement();
        }
        return solde;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTotalLettre() {
        return totalLettre;
    }

    public void setTotalLettre(String totalLettre) {
        this.totalLettre = totalLettre;
    }

    public Boolean getBilled() {
        return billed;
    }

    public void setBilled(Boolean billed) {
        this.billed = billed;
    }

    public List<Versement> getVersements() {
        return versements;
    }

    public void setVersements(List<Versement> versements) {
        this.versements = versements;
    }

    public void addVersement(Versement versement){
        versement.setReparation(this);
        this.versements.add(versement);
    }
}
