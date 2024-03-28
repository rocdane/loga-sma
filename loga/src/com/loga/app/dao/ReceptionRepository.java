package com.loga.app.dao;

import com.loga.model.Reception;
import com.loga.vendor.Repository;

public class ReceptionRepository extends Repository<Reception,Long>{
    public ReceptionRepository() {
        super(Reception.class);
    }
}
