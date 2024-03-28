package com.loga.model;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "automobile")
@AllArgsConstructor
public class Automobile implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "immatriculation",unique = true)
    private String immatriculation;

    @Column(name = "chassis",length = 17,unique = true)
    private String chassis;

    @Column(name = "marque")
    private String marque;

    @Column(name = "modele")
    private String modele;

    @Column(name = "type_compteur")
    private String typeCompteur;

    @Column(name = "compteur")
    private int compteur;

    @Column(name = "puissance")
    private String puissance;

    @Column(name = "cylindre")
    private String cylindre;

    @Column(name = "transmission")
    private String transmission;

    @Column(name = "pmec")
    private String pmec;

    @ManyToOne
    @JoinColumn(name="client",referencedColumnName="id")
    private Client client;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "automobile")
    private Dossier dossier;
    
    public Automobile() {

    }

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getImmatriculation() {
        return this.immatriculation;
    }
    
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }
    
    public String getChassis() {
        return this.chassis;
    }
    
    public void setChassis(String chassis) {
        this.chassis = chassis;
    }
    
    public String getMarque() {
        return this.marque;
    }
    
    public void setMarque(String marque) {
        this.marque = marque;
    }
    
    public String getModele() {
        return this.modele;
    }
    
    public void setModele(String modele) {
        this.modele = modele;
    }
    
    public String getTypeCompteur() {
        return this.typeCompteur;
    }
    
    public void setTypeCompteur(String typeCompteur) {
        this.typeCompteur = typeCompteur;
    }
    
    public int getCompteur() {
        return this.compteur;
    }
    
    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }

    public String getPuissance() {
        return puissance;
    }

    public void setPuissance(String puissance) {
        this.puissance = puissance;
    }

    public String getCylindre() {
        return cylindre;
    }

    public void setCylindre(String cylindre) {
        this.cylindre = cylindre;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getPmec() {
        return pmec;
    }

    public void setPmec(String pmec) {
        this.pmec = pmec;
    }

    public Client getClient() {
        return this.client;
    }
    
    public void setClient(Client client) {
        this.client = client;
        client.addAutomobile(this);
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public enum Compteur
    {
        KM, 
        MPH;
    }
}
