package com.loga.app.dao;

import com.loga.model.Dossier;
import com.loga.vendor.Factory;
import com.loga.vendor.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DossierRepository extends Repository<Dossier,Long>{

    public DossierRepository() {
        super(Dossier.class);
    }

    public List<String> search(String text){
        List<String> result = new ArrayList<>();

        StringBuilder string = new StringBuilder();
        string.append("select dossier.id,automobile.immatriculation,client.raison_sociale");
        string.append(" from dossier,automobile,client");
        string.append(" where automobile.immatriculation like '%"+text+"%'");
        string.append(" and dossier.automobile=automobile.id and automobile.client=client.id");
        string.append(" group by dossier.id,automobile.immatriculation,client.raison_sociale");

        try {
            ResultSet rs = Factory.getInstance().get(string.toString());

            while (rs.next()){
                StringBuilder line = new StringBuilder();
                line.append(rs.getLong("id"));
                line.append(" / ");
                line.append(rs.getString("immatriculation"));
                line.append(" / ");
                line.append(rs.getString("raison_sociale"));
                result.add(line.toString());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ObservableList<Dossier> find(String text){
        ObservableList<Dossier> dossiers = FXCollections.observableArrayList();

        StringBuilder string = new StringBuilder();
        string.append("select dossier.id,automobile.immatriculation,client.raison_sociale");
        string.append(" from dossier,automobile,client");
        string.append(" where automobile.immatriculation like '%"+text+"%'");
        string.append(" and dossier.automobile=automobile.id and automobile.client=client.id");
        string.append(" groupe by dossier.id,automobile.id,client.id");

        String query = "select dossier.id from dossier,automobile where automobile.immatriculation like '%" + text + "%' and dossier.automobile=automobile.id";

        try {
            ResultSet rs = Factory.getInstance().get(query);
            while (rs.next()) {
                Dossier dossier = findById(rs.getLong("id"));
                dossiers.add(dossier);
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dossiers;
    }

    public Dossier findByAuto(String auto){
        Dossier dossier = null;
        String query = "select dossier.id from dossier,automobile where automobile.id=dossier.automobile and automobile.immatriculation like '"+auto+"';";
        try {
            ResultSet rs = Factory.getInstance().get(query);
            if (rs.next()) {
                dossier = findById(rs.getLong("id"));
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dossier;
    }
}
