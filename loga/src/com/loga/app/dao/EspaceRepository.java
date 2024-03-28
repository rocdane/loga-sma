package com.loga.app.dao;

import com.loga.model.Espace;
import com.loga.vendor.Repository;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class EspaceRepository extends Repository<Espace,Long>{

    public EspaceRepository() {
        super(Espace.class);
    }

    public ObservableList<Espace> search(String text){
        return null;
    }

    public Espace find(String text){
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Espace e where e.designation=:designation");
        query.setParameter("designation",text);
        Espace espace = (Espace) query.getSingleResult();
        session.close();
        return espace;
    }
}
