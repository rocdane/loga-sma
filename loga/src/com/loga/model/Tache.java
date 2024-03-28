package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tache")
public class Tache implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "cout")
    private float cout;

    @Column(name = "temps")
    private float temps;

    @Column(name = "taux_horaire")
    private float tauxHoraire;

    @ManyToOne
    @JoinColumn(name="reparation",referencedColumnName="id",nullable=false)
    private Reparation reparation;

    public Tache() {
    }

    public Tache(String description, float cout) {
        this.description = description;
        this.cout = cout;
    }

    public String toString(){
        return description;
    }
    
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public float getTemps() {
        return this.temps;
    }
    
    public void setTemps(float temps) {
        this.temps = temps;
    }
    
    public float getTauxHoraire() {
        return this.tauxHoraire;
    }
    
    public void setTauxHoraire(float tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }
    
    public double getCout() {
        return this.cout;
    }
    
    public void setCout(float cout) {
        this.cout = cout;
    }

    public Reparation getReparation() {
        return reparation;
    }

    public void setReparation(Reparation reparation) {
        this.reparation = reparation;
    }
}
