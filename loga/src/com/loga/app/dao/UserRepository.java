package com.loga.app.dao;

import com.loga.model.User;
import com.loga.vendor.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.jws.soap.SOAPBinding;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository extends Repository<User,Long> {

    public UserRepository() {
        super(User.class);
    }

    public User authenticate(String usr, String pwd) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from User where username=:usr and password=:pwd");
        query.setParameter("usr",usr);
        query.setParameter("pwd",pwd);
        User user = (User)query.getSingleResult();
        session.close();
        return user;
    }

    public void update(User user) throws Exception {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from User where username=:usr");
        query.setParameter("usr",user.getUsername());
        User auth = (User)query.getSingleResult();
        if(auth!=null){
            auth.setPassword(user.getPassword());
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(auth);
            tx.commit();
        }
        session.close();
    }
}
