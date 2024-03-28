package com.loga.app.dao;

import com.loga.model.Fournisseur;
import com.loga.vendor.Factory;
import com.loga.vendor.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FournisseurRepository extends Repository<Fournisseur,Long>{

    public FournisseurRepository() {
        super(Fournisseur.class);
    }

    public ObservableList<String> search(String text){
        ObservableList<String> fournisseurs = FXCollections.observableArrayList();
        try {
            ResultSet rs = Factory.getInstance().get("select * from fournisseur where raison_sociale like '%" + text + "%'");
            while (rs.next()) {
                StringBuilder txt = new StringBuilder();
                txt.append(rs.getLong("id") + "/");
                txt.append(rs.getString("raison_sociale"));
                fournisseurs.add(txt.toString());
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return fournisseurs;
    }
}
