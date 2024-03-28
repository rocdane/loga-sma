package com.loga.app.dao;

import com.loga.model.Versement;
import com.loga.vendor.Repository;

public class VersementRepository extends Repository<Versement,Long> {
    public VersementRepository() {
        super(Versement.class);
    }
}
