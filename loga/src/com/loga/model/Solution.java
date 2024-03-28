package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "solution")
public class Solution implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="diagnostic",referencedColumnName="id",nullable=false)
    private Diagnostic diagnostic;

    /**
     * énumération des groupes de libelle::Solution
     * HYPOTHESE : Toute cause liée à l'apparition d'un défaut
     * CONTROLE : Toute démarche liée à la résolution d'un défaut
     */
    public enum Groupe{
        HYPOTHESE,CONTROLE
    }

    public Solution() {
    }

    public Solution(String code, String libelle, String description) {
        this.code = code;
        this.libelle = libelle;
        this.description = description;
    }
    
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
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

    public Diagnostic getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(Diagnostic diagnostic) {
        this.diagnostic = diagnostic;
    }

    public static Groupe[] getGroupes(){
        return Groupe.values();
    }
}
