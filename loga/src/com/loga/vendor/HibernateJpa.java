package com.loga.vendor;

import com.loga.ui.AlertError;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Classe de configuration de hibernate/jpa.
 * TODO:Faire en sorte de réaliser une injection de dépendance à partir de cette classe.
 * @author rochdane sabi
 */
public class HibernateJpa {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            AlertError.getInstance().setTitle("Erreur");
            AlertError.getInstance().setHeaderText("Connexion avec la base de donnée");
            AlertError.getInstance().setContentText("Problème à l'ouverture de la session : \n" + e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
