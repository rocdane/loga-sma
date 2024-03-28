package com.loga.app.dao;

import com.loga.model.Controle;
import com.loga.vendor.Repository;

public class ControleRepository extends Repository<Controle,Long>{
    public ControleRepository() {
        super(Controle.class);
    }
}
