package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "profile")
public class Profile implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "contact",length = 50,unique = true)
    private String contact;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "poste")
    private String poste;

    @Column(name = "archived")
    private Boolean archived;

    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name = "profile_path")
    private String profilePath;

    @Column(name = "date_ajout")
    @Temporal(TemporalType.DATE)
    private Date dateAjout;

    @Column(name = "date_archive")
    @Temporal(TemporalType.DATE)
    private Date dateArchive;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "titre",referencedColumnName = "id")
    private Titre titre;

    @Column(name = "salaire")
    private int salaire;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "users",referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "profile")
    private List<Evaluation> evaluations = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "profile")
    private List<Salaire> salaires = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "profile")
    private List<Reception> receptions = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "profile")
    private List<Diagnostic> diagnostics = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "profile")
    private List<Reparation> reparations = new ArrayList<>();

    public Profile() {
    }

    public String toString(){
        return id+" "+nom+" "+prenom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public Date getDateArchive() {
        return dateArchive;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public void setDateArchive(Date dateArchive) {
        this.dateArchive = dateArchive;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public void addEvaluation(Evaluation evaluation){
        evaluation.setProfile(this);
        this.evaluations.add(evaluation);
    }

    public List<Salaire> getSalaires() {
        return salaires;
    }

    public void setSalaires(List<Salaire> salaires) {
        this.salaires = salaires;
    }

    public void addSalaire(Salaire salaire){
        salaire.setProfile(this);
        this.salaires.add(salaire);
    }

    public List<Reception> getReceptions() {
        return receptions;
    }

    public void setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
    }

    public void addReception(Reception reception){
        reception.setProfile(this);
        this.receptions.add(reception);
    }

    public List<Diagnostic> getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(List<Diagnostic> diagnostics) {
        this.diagnostics = diagnostics;
    }

    public void addDiagnostic(Diagnostic diagnostic){
        diagnostic.setProfile(this);
        this.diagnostics.add(diagnostic);
    }

    public List<Reparation> getReparations() {
        return reparations;
    }

    public void setReparations(List<Reparation> reparations) {
        this.reparations = reparations;
    }

    public void addReparation(Reparation reparation){
        reparation.setProfile(this);
        this.reparations.add(reparation);
    }

    public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
