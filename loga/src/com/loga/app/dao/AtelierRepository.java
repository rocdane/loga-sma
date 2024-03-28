package com.loga.app.dao;

import com.loga.model.Atelier;
import com.loga.vendor.Repository;

public class AtelierRepository extends Repository<Atelier,Long>{
    public AtelierRepository() {
        super(Atelier.class);
    }
}
