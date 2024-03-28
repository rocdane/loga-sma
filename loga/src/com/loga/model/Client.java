package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client implements Serializable
{
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Column(name = "type")
    private String type;

    @Column(name = "mention_legale")
    private String mentionLegale;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "ville")
    private String ville;

    @Column(name = "contact")
    private String contact;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "client")
    protected List<Automobile> automobiles = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "client")
    protected List<Dossier> dossiers = new ArrayList<>();

    public enum Type{
        HOMME,FEMME,SOCIETE
    }

    public static Type[] getTypes(){
        return Type.values();
    }

    public String toString(){
        return id+raisonSociale;
    }

    public Client() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getRaisonSociale() {
        return this.raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMentionLegale() {
        return mentionLegale;
    }

    public void setMentionLegale(String mentionLegale) {
        this.mentionLegale = mentionLegale;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return this.ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<Automobile> getAutomobiles() {
        return automobiles;
    }

    public void setAutomobiles(List<Automobile> automobiles) {
        this.automobiles = automobiles;
    }

    public void addAutomobile(Automobile automobile){
        this.automobiles.add(automobile);
    }

    public List<Dossier> getDossiers() {
        return dossiers;
    }

    public void setDossiers(List<Dossier> dossiers) {
        this.dossiers = dossiers;
    }

    public void addDossier(Dossier dossier){
        dossier.setClient(this);
        this.dossiers.add(dossier);
    }
}
