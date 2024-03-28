package com.loga.app.dao;

import com.loga.model.Etat;
import com.loga.vendor.Repository;

import java.util.List;

public class EtatRepository extends Repository<Etat,Long>{

    public EtatRepository() {
        super(Etat.class);
    }

    public List<Etat> search(String text){
        return null;
    }
}
