package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "immobilisation")
public class Immobilisation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "categorie")
    String categorie;

    @Column(name = "designation")
    String designation;

    @Column(name = "code")
    String code;

    @Column(name = "date_acquis")
    Date date;

    @Column(name = "taux_ammortissement")
    float ammortissement;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "atelier",referencedColumnName = "id")
    Atelier atelier;

    public Categorie[] getCategories(){
        return Categorie.values();
    }

    public enum Categorie{
        OUTILS,CONSOMMABLE,MOBILIER,IMMOBILIER
    }

    public Immobilisation(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmmortissement() {
        return ammortissement;
    }

    public void setAmmortissement(float ammortissement) {
        this.ammortissement = ammortissement;
    }

    public Atelier getAtelier() {
        return atelier;
    }

    public void setAtelier(Atelier atelier) {
        this.atelier = atelier;
    }
}
