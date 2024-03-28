package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "atelier")
public class Atelier implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Column(name = "mention_legale")
    private String mentionLegale;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "contact")
    private String contact;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "atelier")
    private List<Espace> espaces = new ArrayList<>();

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "atelier")
    private List<Service> services = new ArrayList<>();

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "atelier")
    private List<Tresor> tresors = new ArrayList<>();

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "atelier")
    private List<Article> articles = new ArrayList<>();

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "atelier")
    private List<Immobilisation> immobilisations = new ArrayList<>();

    public Atelier() {
        this.id = 0;
        this.raisonSociale = "raisonSociale";
        this.mentionLegale = "mentionLegale";
        this.adresse = "adresse";
        this.contact = "contact";
        this.espaces = new ArrayList<>();
        this.services = new ArrayList<>();
        this.tresors = new ArrayList<>();
        this.articles = new ArrayList<>();
        this.immobilisations = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getMentionLegale() {
        return mentionLegale;
    }

    public void setMentionLegale(String mentionLegale) {
        this.mentionLegale = mentionLegale;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<Espace> getEspaces() {
        return espaces;
    }

    public void setEspaces(List<Espace> espaces) {
        this.espaces = espaces;
    }

    public void addEspace(Espace espace){
        espace.setAtelier(this);
        this.espaces.add(espace);
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void addService(Service service){
        service.setAtelier(this);
        this.services.add(service);
    }

    public List<Tresor> getTresors() {
        return tresors;
    }

    public void setTresors(List<Tresor> tresors) {
        this.tresors = tresors;
    }

    public void addTresor(Tresor tresor){
        tresor.setAtelier(this);
        this.tresors.add(tresor);
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void addArticle(Article article){
        article.setAtelier(this);
        this.articles.add(article);
    }

    public List<Immobilisation> getImmobilisations() {
        return immobilisations;
    }

    public void setImmobilisations(List<Immobilisation> immobilisations) {
        this.immobilisations = immobilisations;
    }

    public void addImmobilisation(Immobilisation immobilisation){
        immobilisation.setAtelier(this);
        this.immobilisations.add(immobilisation);
    }
}
