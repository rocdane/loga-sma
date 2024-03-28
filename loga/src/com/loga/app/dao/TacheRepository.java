package com.loga.app.dao;

import com.loga.model.Tache;
import com.loga.vendor.Factory;
import com.loga.vendor.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TacheRepository extends Repository<Tache,Long> {

    public TacheRepository() {
        super(Tache.class);
    }

    public List<String> search(){
        List<String> list = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        query.append("select distinct description");
        query.append(" from tache");
        try {
            ResultSet rs = Factory.getInstance().get(query.toString());
            while (rs.next()) {
                list.add(rs.getString("description"));
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Tache> find(){
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("select distinct t from Tache t");
        session.close();
        return query.list();
    }
}
