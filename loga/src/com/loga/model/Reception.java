package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reception")
public class Reception implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "ouvrier")
    private String ouvrier;

    @Column(name = "compteur")
    private Integer compteur;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "observation")
    private String observation;

    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @ManyToOne
    @JoinColumn(name="profile",referencedColumnName="id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name="dossier",referencedColumnName="id",nullable=false)
    private Dossier dossier;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "reception")
    private List<Etat> etats = new ArrayList<>();

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

    public Integer getCompteur() {
        return compteur;
    }

    public void setCompteur(Integer compteur) {
        this.compteur = compteur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    public Dossier getDossier() {
        return dossier;
    }
    
    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }
    
    public List<Etat> getEtats() {
        return this.etats;
    }
    
    public void setEtats(List<Etat> etats) {
        this.etats = etats;
    }

    public void addEtat(Etat etat){
        etat.setReception(this);
        this.etats.add(etat);
    }

    public void removeEtat(Etat etat){
        this.etats.remove(etat);
    }
}
