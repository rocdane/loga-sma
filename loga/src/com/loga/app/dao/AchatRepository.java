package com.loga.app.dao;

import com.loga.model.Achat;
import com.loga.ui.AlertError;
import com.loga.vendor.Factory;
import com.loga.vendor.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AchatRepository extends Repository<Achat,Long> {
    public AchatRepository() {
        super(Achat.class);
    }

    public List<Achat> find(String date){
        List<Achat> achats = new ArrayList<>();
        try {
            ResultSet rs = Factory.getInstance().get("select id from achat where date_achat = convert(datetime,'"+date+"',121)");
            while(rs.next()){
                Achat achat = findById(rs.getLong("id"));
                achats.add(achat);
            }
        } catch (SQLException | ClassNotFoundException e) {
            AlertError.getInstance().setContentText(e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
        return achats;
    }

    public List<Achat> find(String debut, String fin){
        List<Achat> achats = new ArrayList<>();
        try {
            ResultSet rs = Factory.getInstance().get("select id from achat where date_achat between convert(datetime,'"+debut+"',121) and convert(datetime,'"+fin+"',121)");
            while(rs.next()){
                Achat achat = findById(rs.getLong("id"));
                achats.add(achat);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return achats;
    }
}
