package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "espace")
public class Espace implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "code",unique = true,length = 50)
    private String code;

    @Column(name = "intitule")
    private String intitule;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="atelier",referencedColumnName="id")
    private Atelier atelier;

    public String toString(){
        return id+" "+intitule;
    }

    public Espace() {
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
    
    public String getIntitule() {
        return this.intitule;
    }
    
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Atelier getAtelier() {
        return atelier;
    }

    public void setAtelier(Atelier atelier) {
        this.atelier = atelier;
    }
}
