package com.loga.app.dao;

import com.loga.model.Critere;
import com.loga.vendor.Repository;

public class CritereRepository extends Repository<Critere,Long> {
    public CritereRepository() {
        super(Critere.class);
    }
}
