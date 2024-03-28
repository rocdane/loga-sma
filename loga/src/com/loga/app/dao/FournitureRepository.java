package com.loga.app.dao;

import com.loga.model.Fourniture;
import com.loga.vendor.Factory;
import com.loga.vendor.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FournitureRepository extends Repository<Fourniture,Long>{

    public FournitureRepository() {
        super(Fourniture.class);
    }

    public List<String> search(){
        List<String> list = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        query.append("select distinct designation");
        query.append(" from fourniture");

        try {
            ResultSet rs = Factory.getInstance().get(query.toString());
            while (rs.next()) {
                list.add(rs.getString("designation"));
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Fourniture> find(){
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("select distinct f from Fourniture f");
        List<Fourniture> fournitures = query.list();
        session.close();
        return fournitures;
    }
}
