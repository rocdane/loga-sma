package com.loga.app.dao;

import com.loga.model.Service;
import com.loga.vendor.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ServiceRepository extends Repository<Service,Long> {
    public ServiceRepository() {
        super(Service.class);
    }

    public Service find(String text){
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Service s where s.designation=:service");
        query.setParameter("service",text);
        Service service = (Service) query.getSingleResult();
        session.close();
        return service;
    }
}
