package com.loga.model;

import com.loga.api.ICashFlow;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "depense")
public class Depense implements ICashFlow, Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "montant")
    private double montant;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="tresor",referencedColumnName="id",nullable=false)
    private Tresor tresor;

    public Depense(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMontant() {
        return montant;
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

    public Tresor getTresor() {
        return tresor;
    }

    public void setTresor(Tresor tresor) {
        this.tresor = tresor;
    }

    @Override
    public String getLibelle() {
        return "Dépense : "+description;
    }

    @Override
    public double getCost() {
        return -getMontant();
    }

    @Override
    public Date getTemporal() {
        return getDateCreation();
    }
}
