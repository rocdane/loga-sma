package com.loga.app.dao;

import com.loga.model.Calendrier;
import com.loga.vendor.Repository;

public class CalendrierRepository extends Repository<Calendrier,Long> {
    public CalendrierRepository() {
        super(Calendrier.class);
    }
}
