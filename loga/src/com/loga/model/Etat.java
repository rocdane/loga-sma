package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "etat")
public class Etat implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "statut")
    private String statut;

    @ManyToOne
    @JoinColumn(name="reception",referencedColumnName="id",nullable=false)
    private Reception reception;

    public Etat() {
    }

    public Etat(String libelle, String description, String statut) {
        this.libelle = libelle;
        this.description = description;
        this.statut = statut;
    }
    
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getLibelle() {
        return this.libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatut(String set) {
        this.statut = set;
    }

    public String getStatut() {
        return statut;
    }

    public Reception getReception() {
        return reception;
    }

    public void setReception(Reception reception) {
        this.reception = reception;
    }
}
