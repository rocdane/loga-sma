package com.loga.app.dao;

import com.loga.model.Tresor;
import com.loga.ui.AlertError;
import com.loga.vendor.Factory;
import com.loga.vendor.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TresorRepository extends Repository<Tresor,Long>{

    public TresorRepository() {
        super(Tresor.class);
    }

    public Tresor find(String date){
        Tresor tresor=null;
        try {
            ResultSet rs = Factory.getInstance().get("select id from tresor where exercice = convert(datetime,'"+date+"',121)");
            if(rs.next()){
                tresor = findById(rs.getLong("id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            AlertError.getInstance().setContentText(e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
        return tresor;
    }

    public List<Tresor> find(String debut, String fin){
        List<Tresor> tresors = new ArrayList<>();
        try {
            ResultSet rs = Factory.getInstance().get("select id from tresor where exercice between convert(datetime,'"+debut+"',121) and convert(datetime,'"+fin+"',121)");
            while(rs.next()){
                Tresor tresor = findById(rs.getLong("id"));
                tresors.add(tresor);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tresors;
    }
}
