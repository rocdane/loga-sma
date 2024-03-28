package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tresor")
public class Tresor implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "exercice",unique = true)
    @Temporal(TemporalType.DATE)
    private Date exercice;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="atelier",referencedColumnName="id",nullable=false)
    private Atelier atelier;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "tresor")
    private List<Recette> recettes=new ArrayList<>();

    @Column(name = "total_recette")
    private double totalRecette;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "tresor")
    private List<Depense> depenses=new ArrayList<>();

    @Column(name = "total_depense")
    private double totalDepense;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getExercice() {
        return exercice;
    }

    public void setExercice(Date exercice) {
        this.exercice = exercice;
    }

    public Atelier getAtelier() {
        return atelier;
    }

    public void setAtelier(Atelier atelier) {
        this.atelier = atelier;
    }

    public List<Recette> getRecettes() {
        return recettes;
    }

    public void setRecettes(List<Recette> recettes) {
        this.recettes = recettes;
    }

    public void addRecette(Recette recette){
        recette.setTresor(this);
        this.recettes.add(recette);
    }

    public double getTotalRecette(){
        double total = 0;
        for (Recette recette:getRecettes()) {
            total+=recette.getMontant();
        }
        return total;
    }

    public void setTotalRecette(double totalRecette){
        this.totalRecette = totalRecette;
    }

    public List<Depense> getDepenses() {
        return depenses;
    }

    public void setDepenses(List<Depense> depenses) {
        this.depenses = depenses;
    }

    public void addDepense(Depense depense){
        depense.setTresor(this);
        this.depenses.add(depense);
    }

    public double getTotalDepense(){
        double total=0;
        for (Depense depense:getDepenses()) {
            total+=depense.getMontant();
        }
        return total;
    }

    public void setTotalDepense(double totalDepense){
        this.totalDepense = totalDepense;
    }
}
