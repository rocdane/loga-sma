package com.loga.app.dao;

import com.loga.model.Salaire;
import com.loga.vendor.Repository;

public class SalaireRepository extends Repository<Salaire,Long> {
    public SalaireRepository() {
        super(Salaire.class);
    }
}
