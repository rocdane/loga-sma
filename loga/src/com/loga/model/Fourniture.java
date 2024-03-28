package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fourniture")
public class Fourniture implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prix")
    private double prix;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "montant")
    private double montant;

    @Column(name = "is_ordered")
    private Boolean ordered;

    @Column(name = "is_delivered")
    private Boolean delivered;

    @ManyToOne
    @JoinColumn(name="commande",referencedColumnName="id")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name="livraison",referencedColumnName="id")
    private Livraison livraison;

    @ManyToOne
    @JoinColumn(name="reparation",referencedColumnName="id")
    private Reparation reparation;

    public String toString(){
        return designation;
    }

    public Fourniture() {
        this.id = 0;
        this.designation = "";
        this.prix = 0;
        this.quantite = 0;
        this.montant = 0;
        this.ordered = false;
        this.delivered = false;
        this.commande = new Commande();
        this.livraison = new Livraison();
        this.reparation = new Reparation();
    }

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getDesignation() {
        return this.designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public double getPrix() {
        return this.prix;
    }
    
    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    public int getQuantite() {
        return this.quantite;
    }
    
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    public double getMontant() {
        return this.montant;
    }
    
    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }

    public Reparation getReparation() {
        return reparation;
    }

    public void setReparation(Reparation reparation) {
        this.reparation = reparation;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }
}
