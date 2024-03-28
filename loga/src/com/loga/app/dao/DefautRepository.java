package com.loga.app.dao;

import com.loga.model.Defaut;
import com.loga.vendor.Repository;

public class DefautRepository extends Repository<Defaut,Long>{
    public DefautRepository() {
        super(Defaut.class);
    }
}
