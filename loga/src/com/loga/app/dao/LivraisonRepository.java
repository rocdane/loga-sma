package com.loga.app.dao;

import com.loga.model.Livraison;
import com.loga.vendor.Repository;

public class LivraisonRepository extends Repository<Livraison,Long> {
    public LivraisonRepository() {
        super(Livraison.class);
    }
}
