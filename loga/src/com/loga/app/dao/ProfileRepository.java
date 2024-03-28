package com.loga.app.dao;

import com.loga.model.Profile;
import com.loga.vendor.Factory;
import com.loga.vendor.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileRepository extends Repository<Profile,Long>{

    public ProfileRepository() {
        super(Profile.class);
    }

    public List<Profile> search(String text){
        List<Profile> list = new ArrayList<>();
        try {
            ResultSet rs = Factory.getInstance().get("select * from profile where nom like '%"+text+"%' or prenom like '%"+text+"%'");
            while (rs.next()){
                Profile profile = findById(rs.getLong("id"));
                list.add(profile);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Profile> findOuvrier(){
        Session session = getSessionFactory().openSession();
        List<Profile> profiles = null;
        Query query = session.createQuery("select p from Profile p,Titre t where p.titre=t.id and t.designation in (:titres)");
        query.setParameter("titres", Arrays.asList("CHEF ATELIER","CHEF EQUIPE","COMPAGNON","STAGIAIRE"));
        profiles = query.list();
        session.close();
        return profiles;
    }
}
