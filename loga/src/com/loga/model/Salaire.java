package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "salaire")
public class Salaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "prime")
    private int prime;

    @Column(name = "indemnite")
    private int indemnite;

    @Column(name = "heure_sup")
    private int heureSup;

    @Column(name = "part_ouvriere")
    private int partOuvriere;

    @Column(name = "ipts")
    private int ipts;

    @Column(name = "mode")
    private String mode;

    @Column(name = "reference")
    private String reference;

    @Column(name = "montant")
    private int montant;

    @Column(name = "date_salaire")
    private Date date;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="profile",referencedColumnName="id",nullable=false)
    private Profile profile;

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

    public int getPrime() {
        return prime;
    }

    public void setPrime(int prime) {
        this.prime = prime;
    }

    public int getIndemnite() {
        return indemnite;
    }

    public void setIndemnite(int indemnite) {
        this.indemnite = indemnite;
    }

    public int getHeureSup() {
        return heureSup;
    }

    public void setHeureSup(int heureSup) {
        this.heureSup = heureSup;
    }

    public int getPartOuvriere() {
        return partOuvriere;
    }

    public void setPartOuvriere(int partOuvriere) {
        this.partOuvriere = partOuvriere;
    }

    public int getIpts() {
        return ipts;
    }

    public void setIpts(int ipts) {
        this.ipts = ipts;
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

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
