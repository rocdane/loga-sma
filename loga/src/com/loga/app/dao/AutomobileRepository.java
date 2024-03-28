package com.loga.app.dao;

import com.loga.model.Automobile;
import com.loga.vendor.Factory;
import com.loga.vendor.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AutomobileRepository extends Repository<Automobile,Long>{

    public AutomobileRepository() {
        super(Automobile.class);
    }

    public Automobile find(String text){
        Automobile auto = null;
        try {
            ResultSet rs = Factory.getInstance().get("select id from automobile where automobile.immatriculation='" + text + "'");
            if (rs.next()) {
                auto = findById(rs.getLong("id"));
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return auto;
    }
}
