package com.loga.app.dao;

import com.loga.model.Immobilisation;
import com.loga.vendor.Repository;

public class ImmobilisationRepository extends Repository<Immobilisation,Long>{
    public ImmobilisationRepository() {
        super(Immobilisation.class);
    }
}
