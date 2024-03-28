package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "qualite")
public class Qualite implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "compteur")
    private Integer compteur;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="reparation",referencedColumnName="id",nullable=false)
    private Reparation reparation;

    @OneToMany(mappedBy = "qualite")
    private List<Controle> controles;

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public Integer getCompteur() {
        return compteur;
    }

    public void setCompteur(Integer compteur) {
        this.compteur = compteur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Reparation getReparation() {
        return reparation;
    }

    public void setReparation(Reparation reparation) {
        this.reparation = reparation;
    }

    public List<Controle> getControles() {
        return this.controles;
    }
    
    public void setControles(List<Controle> controles) {
        this.controles = controles;
    }

    public void addControle(Controle controle){
        controle.setQualite(this);
        this.controles.add(controle);
    }
    
    public Date getDateCreation() {
        return this.dateCreation;
    }
    
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
