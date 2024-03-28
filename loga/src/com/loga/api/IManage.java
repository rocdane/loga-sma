package com.loga.api;

import com.loga.model.*;

import java.util.Date;
import java.util.List;

/**
 * Interface de l'application LoGa représentant les opérations du service Direction
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 * @since 2.0
 */
public interface IManage {

    /**
     * Regroupement des opérations de planification et ressources humaines
     * */
    User enregistrerUtilisateur(User user) throws Exception;
    List<User> listerUtilisateur();
    User afficherUtilisateur(long id);
    User afficherUtilisateur(String txt);
    void supprimerUtilisateur(User user) throws Exception;

    Atelier enregistrerAtelier(Atelier atelier) throws Exception;
    Atelier afficherAtelier(long id);
    Service enregistrerService(Service service) throws Exception;
    List<Service> listerService();
    Service afficherService(long id);
    Service afficherService(String txt);
    void supprimerService(Service service) throws Exception;
    Titre enregistrerTitre(Titre titre) throws Exception;
    List<Titre> listerTitre();
    Titre afficherTitre(long id);
    Titre afficherTitre(String txt);
    void supprimerTitre(Titre titre) throws Exception;
    Espace enregistrerEspace(Espace espace) throws Exception;
    List<Espace> listerEspace();
    Espace afficherEspace(long id);
    Espace afficherEspace(String txt);
    void supprimerEspace(Espace espace) throws Exception;
    Profile enregistrerProfile(Profile profile) throws Exception;
    List<Profile> listerProfile();
    List<Profile> chercherProfile(String txt);
    List<Profile> chercherProfileAtelier(String txt);
    Profile afficherProfile(long id);
    Profile afficherProfile(String txt);
    void modifierProfile(Profile up) throws Exception;
    void supprimerProfile(Profile profile) throws Exception;
    Evaluation enregistrerEvaluation(Evaluation evaluation) throws Exception;
    Calendrier enregistrerCalendrier(Calendrier calendrier) throws Exception;
    List<Calendrier> listerCalendrier();
    Calendrier afficherCalendrier(long id);

    /**
     * Regroupement des opérations de trésoreries.
     * */
    Tresor enregistrerTresor(Tresor tresor) throws Exception;
    Tresor afficherTresor(long id);
    Tresor afficherTresor(Date date);
    List<Tresor> listerTresor(Date debut, Date fin);
    Versement enregistrerVersement(Versement versement) throws Exception;
    List<Versement> listerVersement();
    Salaire enregistrerSalaire(Salaire salaire) throws Exception;
    List<Salaire> listerSalaire();

    /**
     * Regroupement des opérations de clientèle
     * */
    Client enregistrerClient(Client client) throws Exception;
    List<Client> listerClient();
    List<Client> chercherClient(String txt);
    Client afficherClient(long id);
    Client afficherClient(String txt);
    void modifierClient(Client up) throws Exception;
    void supprimerClient(Client client) throws Exception;
    Automobile enregistrerAutomobile(Automobile automobile) throws Exception;
    List<Automobile> listerAutomobile();
    List<Automobile> chercherAutomobile(String txt);
    Automobile afficherAutomobile(long id);
    Automobile afficherAutomobile(String txt);
    void modifierAutomobile(Automobile up) throws Exception;
    void supprimerAutomobile(Automobile automobile) throws Exception;
    Dossier enregistrerDossier(Dossier dossier) throws Exception;
    List<Dossier> listerDossier();
    List<Dossier> chercherDossier(String txt);
    Dossier afficherDossier(long id);
    void supprimerDossier(Dossier dossier) throws Exception;
}
