package com.loga.app.dao;

import com.loga.model.Reparation;
import com.loga.vendor.Factory;
import com.loga.vendor.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReparationRepository extends Repository<Reparation,Long>{

    public ReparationRepository() {
        super(Reparation.class);
    }

    public List<String> search(String text){
        List<String> result = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        query.append("select reparation.id,reparation.date_creation,automobile.immatriculation");
        query.append(" from reparation,dossier,automobile,client");
        query.append(" where reparation.dossier=dossier.id and reparation.autorisation=0");
        query.append(" and dossier.automobile=automobile.id");
        query.append(" and automobile.immatriculation like '%"+text+"%'");
        query.append(" group by reparation.id,reparation.date_creation,automobile.immatriculation order by reparation.id desc;");

        try {
            ResultSet rs = Factory.getInstance().get(query.toString());
            while (rs.next()){
                StringBuilder line = new StringBuilder();
                line.append(rs.getLong("id"));
                line.append(" / ");
                line.append(rs.getString("immatriculation"));
                line.append(" / ");
                line.append(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("date_creation")));
                result.add(line.toString());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Reparation> searchOrdered(){
        List<Reparation> result = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        query.append("select reparation.id");
        query.append(" from reparation,dossier,automobile");
        query.append(" where reparation.dossier=dossier.id and reparation.autorisation=1");
        query.append(" and dossier.automobile=automobile.id");

        try {
            ResultSet rs = Factory.getInstance().get(query.toString());
            while (rs.next()){
                Reparation reparation = findById(rs.getLong("id"));
                result.add(reparation);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Reparation> searchCommande(String text){
        List<Reparation> reparations = new ArrayList<>();

        try {
            StringBuilder query = new StringBuilder();
            query.append("select distinct reparation.id as id from automobile,dossier,reparation,fourniture,article,commande ");
            query.append(" where automobile.immatriculation like '%" + text + "%' and");
            query.append(" dossier.id=reparation.dossier and");
            query.append(" reparation.id=fourniture.reparation and");
            query.append(" commande.id=fourniture.commande");

            ResultSet rs = Factory.getInstance().get(query.toString());

            while (rs.next()) {
                reparations.add(findById(rs.getLong("id")));
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return reparations;
    }

    public List<Reparation> find(String text){
        List<Reparation> reparations = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        query.append("select reparation.id as id");
        query.append(" from reparation,dossier,automobile");
        query.append(" where reparation.dossier=dossier.id");
        query.append(" and dossier.automobile=automobile.id");
        query.append(" and automobile.immatriculation like '%"+text+"%'");
        query.append(" group by reparation.id,dossier.id,automobile.id order by reparation.id desc;");
        try {
            ResultSet rs = Factory.getInstance().get(query.toString());

            while (rs.next()) {
                reparations.add(findById(rs.getLong("id")));
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reparations;
    }

    public List<Reparation> find(String debut, String fin){
        List<Reparation> reparations = new ArrayList<>();

        StringBuilder bilan = new StringBuilder();
        bilan.append("SELECT reparation.id FROM ordre, reparation, dossier,automobile,client");
        bilan.append(" WHERE ordre.reparation = reparation.id AND reparation.autorisation = 1");
        bilan.append(" AND reparation.dossier = dossier.id AND dossier.automobile = automobile.id");
        bilan.append(" AND automobile.client = client.id AND reparation.date_creation BETWEEN convert(datetime,'"+debut+"',121) and convert(datetime,'"+fin+"',121)");

        try {
            ResultSet rs = Factory.getInstance().get(bilan.toString());
            while (rs.next()) {
                Reparation reparation = findById(rs.getLong("id"));
                reparations.add(reparation);
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reparations;
    }
}
