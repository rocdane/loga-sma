package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "commande")
public class Commande implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name="fournisseur",referencedColumnName="id",nullable=false)
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "commande")
    private List<Fourniture> fournitures;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "montant")
    private double montant;

    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    public Commande() {
    }

    public String toString(){
        return getId()+" "+fournisseur.getRaisonSociale()+" "+new SimpleDateFormat("dd-MM-yyyy").format(getDateCreation());
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<Fourniture> getFournitures() {
        return fournitures;
    }

    public void setFournitures(List<Fourniture> fournitures) {
        this.fournitures = fournitures;
    }

    public int getQuantite() {
        int total = 0;
        for (Fourniture fourniture :fournitures) {
            total+=fourniture.getQuantite();
        }
        quantite = total;
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getMontant() {
        double total=0;
        for (Fourniture fourniture:fournitures) {
            total+=fourniture.getMontant();
        }
        return total;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
