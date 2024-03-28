package com.loga.app.dao;

import com.loga.model.Commande;
import com.loga.vendor.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CommandeRepository extends Repository<Commande,Long>{

    public CommandeRepository() {
        super(Commande.class);
    }

    public List searchCommande(String text){
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("select distinct Commande c from Commande c,Fourniture f, Reparation r,Dossier D,Automobile a where c.id=f.commande and f.reparation=r.id and Dossier.id=r.dossier and d.automobile=a.id and a.immatriculation like '%':data'%'");
        query.setParameter("data",text);
        List commandes = query.list();
        session.close();
        return commandes;
    }
}
