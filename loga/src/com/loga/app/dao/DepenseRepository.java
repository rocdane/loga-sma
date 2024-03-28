package com.loga.app.dao;

import com.loga.model.Depense;
import com.loga.vendor.Repository;

public class DepenseRepository extends Repository<Depense,Long>{
    public DepenseRepository() {
        super(Depense.class);
    }
}
