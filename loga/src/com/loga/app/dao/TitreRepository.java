package com.loga.app.dao;

import com.loga.model.Titre;
import com.loga.vendor.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class TitreRepository extends Repository<Titre,Long> {
    public TitreRepository() {
        super(Titre.class);
    }

    public Titre find(String text){
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Titre t where t.designation=:titre");
        query.setParameter("titre",text);
        Titre titre = (Titre) query.getSingleResult();
        session.close();
        return titre;
    }
}
