package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "fournisseur")
public class Fournisseur implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "raison_sociale",unique = true)
    private String raisonSociale;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "contact",unique = true)
    private String contact;

    @OneToMany(mappedBy = "fournisseur")
    private List<Commande> commandes;

    public Fournisseur() {
    }

    public String toString(){
        return getId()+" "+getRaisonSociale();
    }

    public Fournisseur(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }
    
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getRaisonSociale() {
        return this.raisonSociale;
    }
    
    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }
    
    public String getAdresse() {
        return this.adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getContact() {
        return this.contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
}
