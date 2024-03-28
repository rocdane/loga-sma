package com.loga.app.dao;

import com.loga.model.Qualite;
import com.loga.vendor.Repository;

public class QualiteRepository extends Repository<Qualite,Long>{
    public QualiteRepository() {
        super(Qualite.class);
    }
}
