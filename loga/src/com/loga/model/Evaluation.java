package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "evaluation")
public class Evaluation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "date_evaluation")
    private Date date;

    @Column(name = "note")
    private int note;

    @Column(name = "observation")
    private String observation;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="profile",referencedColumnName="id")
    private Profile profile;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "evaluation")
    private List<Critere> criteres = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Critere> getCriteres() {
        return criteres;
    }

    public void setCriteres(List<Critere> criteres) {
        for (Critere critere:criteres) {
            critere.setEvaluation(this);
        }
        this.criteres = criteres;
    }

    public void addCritere(Critere critere){
        critere.setEvaluation(this);
        this.criteres.add(critere);
    }
}
