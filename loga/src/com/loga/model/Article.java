package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "article")
public class Article implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "designation")
    private String designation;

    @Column(name = "description")
    private String description;

    @Column(name = "prix")
    private double prix;

    @Column(name = "prix_achat")
    private double prixAchat;

    @Column(name = "stock")
    private int stock;

    @ManyToOne
    @JoinColumn(name="atelier",referencedColumnName="id",nullable=false)
    private Atelier atelier;

    public enum Categorie{
        CAROSSERIE,
        CHASSIS,
        DIVERS,
        FILTRE,
        LIQUIDE,
        LUBRIFIANT,
        MOTEUR
    }

    public Article() {
        this.id = 0;
        this.reference = "reference";
        this.categorie = "categorie";
        this.designation = "designation";
        this.description = "description";
        this.prix = 0;
        this.prixAchat = 0;
        this.stock = 0;
        this.atelier = new Atelier();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public static Categorie[] getCategories(){
        return Categorie.values();
    }

    public Atelier getAtelier() {
        return atelier;
    }

    public void setAtelier(Atelier atelier) {
        this.atelier = atelier;
    }

    public String toString(){
        return designation;
    }
}
