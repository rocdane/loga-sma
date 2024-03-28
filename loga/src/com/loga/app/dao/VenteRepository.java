package com.loga.app.dao;

import com.loga.model.Vente;
import com.loga.ui.AlertError;
import com.loga.vendor.Factory;
import com.loga.vendor.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VenteRepository extends Repository<Vente,Long> {
    public VenteRepository() {
        super(Vente.class);
    }

    public List<Vente> find(String date){
        List<Vente> ventes = new ArrayList<>();
        try {
            ResultSet rs = Factory.getInstance().get("select id from vente where date_vente = convert(datetime,'"+date+"',121)");
            while(rs.next()){
                Vente vente = findById(rs.getLong("id"));
                ventes.add(vente);
            }
        } catch (SQLException | ClassNotFoundException e) {
            AlertError.getInstance().setContentText(e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
        return ventes;
    }

    public List<Vente> find(String debut, String fin){
        List<Vente> ventes = new ArrayList<>();
        try {
            ResultSet rs = Factory.getInstance().get("select id from vente where date_vente between convert(datetime,'"+debut+"',121) and convert(datetime,'"+fin+"',121)");
            while(rs.next()){
                Vente vente = findById(rs.getLong("id"));
                ventes.add(vente);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ventes;
    }
}
