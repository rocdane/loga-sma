package com.loga.model;

import com.loga.api.ICashFlow;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "achat")
@XmlRootElement
public class Achat implements ICashFlow, Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_achat")
    private Date date;

    @Column(name = "fournisseur")
    private String fournisseur;

    @OneToOne
    @JoinColumn(name="article",referencedColumnName="id")
    private Article article;

    @Column(name = "prix")
    private double prix;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "montant")
    private double montant;

    public Achat() {
        this.id = 0;
        this.date = new Date();
        this.fournisseur = "";
        this.article = new Article();
        this.prix = 0;
        this.quantite = 0;
        this.montant = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getLibelle() {
        return "Achat : "+article.getDesignation();
    }

    @Override
    public double getCost() {
        return -getMontant();
    }

    @Override
    public Date getTemporal(){
        return getDate();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
