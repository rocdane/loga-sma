package com.loga.app.dao;

import com.loga.model.Client;
import com.loga.vendor.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ClientRepository extends Repository<Client,Long>{

    public ClientRepository() {
        super(Client.class);
    }

    public Client find(String text){
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Client c where c.name=:raison");
        query.setParameter("raison",text);
        Client client = (Client) query.getSingleResult();
        session.close();
        return client;
    }
}
