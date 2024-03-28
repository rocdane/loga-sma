package com.loga.app.dao;

import com.loga.model.Recette;
import com.loga.vendor.Repository;

public class RecetteRepository extends Repository<Recette,Long>{
    public RecetteRepository() {
        super(Recette.class);
    }
}
