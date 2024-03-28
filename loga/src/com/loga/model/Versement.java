package com.loga.model;

import com.loga.api.ICashFlow;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "versement")
public class Versement implements ICashFlow, Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "date_versement")
    @Temporal(TemporalType.DATE)
    private Date dateVersement;

    @Column(name = "mode")
    private String mode;

    @Column(name = "reference")
    private String reference;

    @Column(name = "versement")
    private Double versement;

    @ManyToOne
    @JoinColumn(name="reparation",referencedColumnName="id")
    private Reparation reparation;

    @Override
    public String getLibelle() {
        return "Versement : "+getReference();
    }

    @Override
    public double getCost() {
        return getVersement();
    }

    @Override
    public Date getTemporal() {
        return getDateVersement();
    }

    public enum Mode{
        ESPECE,CHEQUE,MOBILE
    }

    public static Mode[] getModes(){
        return Mode.values();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateVersement() {
        return dateVersement;
    }

    public void setDateVersement(Date dateVersement) {
        this.dateVersement = dateVersement;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getVersement() {
        return versement;
    }

    public void setVersement(Double versement) {
        this.versement = versement;
    }

    public Reparation getReparation() {
        return reparation;
    }

    public void setReparation(Reparation reparation) {
        this.reparation = reparation;
    }
}
