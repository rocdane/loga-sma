package com.loga.api;

import com.loga.app.dao.*;
import com.loga.model.*;
import com.loga.ui.AlertInfo;
import com.loga.vendor.HibernateJpa;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TemporalType;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * TODO:application de l'injection de dépendance
 * Implémentation de l'interface IManage représentant les opérations du service Direction
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 * @since 2.0
 */
public class Manage implements IManage {

    private static Manage manage;

    private Manage(){
    }

    public static Manage getInstance(){
        if(manage==null){
            manage = new Manage();
        }
        return manage;
    }


    /**
    * TODO : Cette méthode permet d'enregistrer un nouveau calendrier dans la base de données. Elle retourne l'objet ainsi créé.
    * @param calendrier
    * @return Calendrier
    * */
    @Override
    public Calendrier enregistrerCalendrier(Calendrier calendrier) throws Exception {
        CalendrierRepository calendrierRepository = new CalendrierRepository();
        calendrierRepository.persist(calendrier);
        return calendrier;
    }

    /**
    * TODO : Cette méthode permet de sélectionner et de retourner tous les calendriers de la base de données dans une collection.
    * @return List
    * */
    @Override
    public List<Calendrier> listerCalendrier() {
        CalendrierRepository calendrierRepository = new CalendrierRepository();
        return calendrierRepository.findAll();
    }

    /**
    * TODO:cette méthode permet de sélectionner un calendrier de la base de données à partir de son identifiant
    * @param id
    * @return Calendrier
    * */
    @Override
    public Calendrier afficherCalendrier(long id) {
        CalendrierRepository calendrierRepository = new CalendrierRepository();
        return calendrierRepository.findById(id);
    }

    /**
    * TODO:Cette méthode permet d'enregistrer un nouveau utilisateur dans la base de donnée. Elle retourne l'objet ainsi créé.
    * @param user
    * @return User
    * */
    @Override
    public User enregistrerUtilisateur(User user) throws Exception {
        UserRepository userRepository = new UserRepository();
        userRepository.save(user);
        return user;
    }

    /**
    * TODO:Cette méthode permet de sélectionner un utilisateur de la base de donnée
    * @param id
    * @return User
    * */
    @Override
    public User afficherUtilisateur(long id) {
        UserRepository userRepository = new UserRepository();
        return userRepository.findById(id);
    }

    /**
     * TODO:Cette méthode permet de sélectionner un utilisateur de la base de données
     * @param txt
     * @return User
     * */
    @Override
    public User afficherUtilisateur(String txt) {
        Session session = HibernateJpa.getSessionFactory().openSession();
        Query query = session.createQuery("from User where username =:usr");
        query.setParameter("usr",txt);
        return (User) query.getSingleResult();
    }

    /**
     * Cette méthode permet de sélectionner tous les utilisateurs de la base de données dans une collection.
     * @return List
     * */
    public List<User> listerUtilisateur(){
        UserRepository userRepository = new UserRepository();
        return userRepository.findAll();
    }

    /**
     * Cette méthode permet de supprimer un utilisateur de la base de données.
     * @param user
     */
    @Override
    public void supprimerUtilisateur(User user) throws Exception {
        UserRepository userRepository = new UserRepository();
        userRepository.delete(user);
    }

    /**
     * Cette méthode permet d'enregistrer ou de mettre à jour un atelier dans la base de données.
     * @param atelier
     * @return Atelier
     */
    @Override
    public Atelier enregistrerAtelier(Atelier atelier) throws Exception {
        AtelierRepository atelierRepository = new AtelierRepository();
        atelierRepository.persist(atelier);
        return atelier;
    }

    /**
     * Cette méthode permet de sélectionner un Atelier de la base de données.
     * @param id
     * @return
     */
    @Override
    public Atelier afficherAtelier(long id) {
        AtelierRepository atelierRepository = new AtelierRepository();
        return atelierRepository.findById(id);
    }

    /**
     * Cette méthode permet d'enregistrer ou de mettre à jour un service dans la base de données.
     * @param service
     * @return Service
     */
    @Override
    public Service enregistrerService(Service service) throws Exception {
        ServiceRepository serviceRepository = new ServiceRepository();
        serviceRepository.persist(service);
        return service;
    }

    /**
     * Cette méthode permet de sélectionner tous les services de la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Service> listerService() {
        ServiceRepository serviceRepository = new ServiceRepository();
        return serviceRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner un service de la base de données.
     * @param id
     * @return Service;
     */
    @Override
    public Service afficherService(long id) {
        ServiceRepository serviceRepository = new ServiceRepository();
        return serviceRepository.findById(id);
    }

    /**
     * Cette méthode permet de sélectionner un service de la base de données.
     * @param txt
     * @return Service;
     */
    @Override
    public Service afficherService(String txt) {
        Session session = HibernateJpa.getSessionFactory().openSession();
        Query query = session.createQuery("from Service where designation =:designation");
        query.setParameter("designation",txt);
        return (Service) query.getSingleResult();
    }

    /**
     * Cette méthode permet de supprimer un service de la base de données.
     * @param service
     */
    @Override
    public void supprimerService(Service service) throws Exception {
        ServiceRepository serviceRepository = new ServiceRepository();
        serviceRepository.delete(service);
    }

    /**
     * Cette méthode permet d'enregistrer ou de mettre à jour un titre dans la base de données.
     * @param titre
     * @return Titre
     */
    @Override
    public Titre enregistrerTitre(Titre titre) throws Exception {
        TitreRepository titreRepository = new TitreRepository();
        titreRepository.persist(titre);
        return titre;
    }

    /**
     * Cette méthode permet de sélectionner tous les titres de la base de données dans une collection.
     * @return Titre
     */
    @Override
    public List<Titre> listerTitre() {
        TitreRepository titreRepository = new TitreRepository();
        return titreRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner un titre de la base de données
     * @param id
     * @return Titre
     */
    @Override
    public Titre afficherTitre(long id) {
        TitreRepository titreRepository = new TitreRepository();
        return titreRepository.findById(id);
    }

    /**
     * Cette méthode permet de sélectionner un titre de la base de données
     * @param txt
     * @return Titre
     */
    @Override
    public Titre afficherTitre(String txt) {
        Session session = HibernateJpa.getSessionFactory().openSession();
        Query query = session.createQuery("from Titre where designation =:designation");
        query.setParameter("designation",txt);
        return (Titre) query.getSingleResult();
    }

    /**
     * Cette méthode permet de supprimer un titre de la base de données
     * @param titre
     */
    @Override
    public void supprimerTitre(Titre titre) throws Exception {
        TitreRepository titreRepository = new TitreRepository();
        titreRepository.delete(titre);
    }

    /**
     * Cette méthode permet d'enregistrer ou de mettre à jour un titre de la base de données.
     * @param espace
     * @return Espace
     */
    @Override
    public Espace enregistrerEspace(Espace espace) throws Exception {
        EspaceRepository espaceRepository = new EspaceRepository();
        espaceRepository.persist(espace);
        return espace;
    }

    /**
     * Cette méthode permet de sélectionner tous les Espace de la bases de données dans une collection.
     * @return List
     */
    @Override
    public List<Espace> listerEspace() {
        EspaceRepository espaceRepository = new EspaceRepository();
        return espaceRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner un Espace de la base de données.
     * @param id
     * @return Espace
     */
    @Override
    public Espace afficherEspace(long id) {
        EspaceRepository espaceRepository = new EspaceRepository();
        return espaceRepository.findById(id);
    }

    /**
     * Cette méthode permet de sélectionner un Espace de la base de données.
     * @param txt
     * @return Espace
     */
    @Override
    public Espace afficherEspace(String txt) {
        Session session = HibernateJpa.getSessionFactory().openSession();
        Query query = session.createQuery("from Espace where designation =:designation");
        query.setParameter("designation",txt);
        return (Espace) query.getSingleResult();
    }

    /**
     * Cette méthode permet de supprimer un Espace de la base de données
     * @param espace
     */
    @Override
    public void supprimerEspace(Espace espace) throws Exception {
        EspaceRepository espaceRepository = new EspaceRepository();
        espaceRepository.delete(espace);
    }

    /**
     * Cette méthode permet d'enregistrer un objet Tresor dans la base de données. Elle retourne l'objet Tresor ainsi créé
     * @param tresor
     * @return Tresor
     */
    @Override
    public Tresor enregistrerTresor(Tresor tresor) throws Exception {
        TresorRepository tresorRepository = new TresorRepository();
        tresorRepository.persist(tresor);
        return tresor;
    }

    /**
     * Cette méthode permet de sélectionner un objet Tresor de la base de données
     * @param id
     * @return Tresor
     */
    @Override
    public Tresor afficherTresor(long id) {
        TresorRepository tresorRepository = new TresorRepository();
        return tresorRepository.findById(id);
    }

    /**
     * Cette méthode permet de sélectionner un objet Tresor de la base de données
     * @param date
     * @return Tresor
     */
    @Override
    public Tresor afficherTresor(Date date) {
        Session session = HibernateJpa.getSessionFactory().openSession();
        Query query = session.createQuery("from Tresor where exercice=:date");
        query.setParameter("date",date, TemporalType.DATE);
        return (Tresor) query.getSingleResult();
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Tresor de la base de données à partir dans une collection.
     * @return List
     */
    @Override
    public List<Tresor> listerTresor(Date debut, Date fin) {
        Session session = HibernateJpa.getSessionFactory().openSession();
        Query query = session.createQuery("from Tresor where exercice=:date");
        query.setParameter("debut",debut,TemporalType.DATE);
        query.setParameter("fin",fin,TemporalType.DATE);
        return query.list();
    }

    /**
     * Cette méthode permet d'enregistrer un objet Versement dans la base de données
     * @param versement
     * @return Versement
     */
    @Override
    public Versement enregistrerVersement(Versement versement) throws Exception {
        VersementRepository versementRepository = new VersementRepository();
        long id = versementRepository.save(versement);
        return versementRepository.findById(id);
    }

    /**
     * Cette méthode permet de lister les objets Versement de la base de données
     * @return List
     */
    @Override
    public List<Versement> listerVersement() {
        VersementRepository versementRepository = new VersementRepository();
        return versementRepository.findAll();
    }

    /**
     * Cette méthode permet d'enregistrer un objet Salaire dans la base de données.
     * @param salaire
     * @return Salaire
     */
    @Override
    public Salaire enregistrerSalaire(Salaire salaire) throws Exception {
        SalaireRepository salaireRepository = new SalaireRepository();
        salaireRepository.persist(salaire);
        return salaire;
    }

    /**
     * Cette méthode permet d'enregistrer un objet Profile dans la base de données.
     * @param profile
     * @return Profile
     */
    @Override
    public Profile enregistrerProfile(Profile profile) throws Exception {
        ProfileRepository profileRepository = new ProfileRepository();
        profileRepository.persist(profile);
        return profile;
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Profile de la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Profile> listerProfile() {
        ProfileRepository profileRepository = new ProfileRepository();
        return profileRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Profile de la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Profile> chercherProfile(String txt) {
        Session session = HibernateJpa.getSessionFactory().openSession();
        List<Profile> profiles = null;
        Query query = session.createQuery("from Profile where nom like :txt or prenom like :txt");
        query.setParameter("txt","%"+txt+"%");
        profiles = query.list();
        session.close();
        return profiles;
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Profile de la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Profile> chercherProfileAtelier(String txt) {
        Session session = HibernateJpa.getSessionFactory().openSession();
        List<Profile> profiles = null;
        Query query = session.createQuery("select p from Profile p,Titre t where p.titre=t.id and t.designation in (:titres)");
        query.setParameter("titres", Arrays.asList("CHEF ATELIER","CHEF EQUIPE","COMPAGNON","STAGIAIRE"));
        profiles = query.list();
        session.close();
        return profiles;
    }

    /**
     * Cette méthode permet de sélectionner un objet Profile de la base de données.
     * @param id
     * @return Profile
     */
    @Override
    public Profile afficherProfile(long id) {
        ProfileRepository profileRepository = new ProfileRepository();
        return profileRepository.findById(id);
    }

    /**
     * Cette méthode permet de sélectionner un objet Profile de la base de données.
     * @param txt
     * @return Profile
     */
    @Override
    public Profile afficherProfile(String txt) {
        Session session = HibernateJpa.getSessionFactory().openSession();
        Query query = session.createQuery("from Profile p,User u where p.users=u.id and u.username=:txt");
        query.setParameter("txt",txt);
        return (Profile) query.getSingleResult();
    }

    /**
     * Cette méthode permet de mettre à jour un objet Profile.
     * @param up
     */
    @Override
    public void modifierProfile(Profile up) throws Exception {
        ProfileRepository profileRepository = new ProfileRepository();
        profileRepository.update(up);
    }

    /**
     * Cette méthode permet de supprimer un objet Profile de la base de données.
     * @param profile
     */
    @Override
    public void supprimerProfile(Profile profile) throws Exception {
        ProfileRepository profileRepository = new ProfileRepository();
        profileRepository.delete(profile);
    }

    /**
     * Cette méthode permet d'enregistrer un objet Evaluation dans la base de données.
     * @param evaluation
     * @return Evaluation
     */
    @Override
    public Evaluation enregistrerEvaluation(Evaluation evaluation) throws Exception {
        EvaluationRepository evaluationRepository = new EvaluationRepository();
        evaluationRepository.persist(evaluation);
        return evaluation;
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Salaire de la base de données
     * @return List
     */
    @Override
    public List<Salaire> listerSalaire() {
        SalaireRepository salaireRepository = new SalaireRepository();
        return salaireRepository.findAll();
    }

    /**
     * Cette méthode permet d'enregistrer un objet Client dans la base de données. Elle retourne l'objet Client ainsi créé.
     * @param client
     * @return Client
     */
    @Override
    public Client enregistrerClient(Client client) throws Exception {
        ClientRepository clientRepository = new ClientRepository();
        clientRepository.persist(client);
        return client;
    }

    /**
     * Cette méthode permet de sélectionner les objets Client de la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Client> listerClient() {
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner les objets Client de la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Client> chercherClient(String txt) {
        Session session = HibernateJpa.getSessionFactory().openSession();
        Query query = session.createQuery("from Client where raisonSociale like :raison");
        query.setParameter("raison","%"+txt+"%");
        return query.list();
    }

    /**
     * Cette méthode permet de sélectionner un objet Client de la base de données.
     * @param id
     * @return Client
     */
    @Override
    public Client afficherClient(long id) {
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.findById(id);
    }

    /**
     * Cette méthode permet de sélectionner un objet Client de la base de données.
     * @param txt
     * @return Client
     */
    @Override
    public Client afficherClient(String txt) {
        Client client = null;
        Session session = HibernateJpa.getSessionFactory().openSession();
        Query query = session.createQuery("from Client where raisonSociale =: raison");
        query.setParameter("raison",txt);
        client = (Client) query.getSingleResult();
        return client;
    }

    /**
     * Cette méthode permet de mettre à jour un objet Client dans la base de données.
     * @param up
     */
    @Override
    public void modifierClient(Client up) throws Exception {
        ClientRepository clientRepository = new ClientRepository();
        clientRepository.update(up);
    }

    /**
     * Cette méthode permet de supprimer un objet Client de la base de données.
     * @param client
     */
    @Override
    public void supprimerClient(Client client) throws Exception {
        ClientRepository clientRepository = new ClientRepository();
        clientRepository.delete(client);
    }

    /**
     * Cette méthode permet d'enregistrer un objet Automobile dans la base de données. Elle retourne l'objet Automobile ainsi créé.
     * @param automobile
     * @return Automobile
     */
    @Override
    public Automobile enregistrerAutomobile(Automobile automobile) throws Exception {
        AutomobileRepository automobileRepository = new AutomobileRepository();
        automobileRepository.persist(automobile);
        return automobile;
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Automobile dans la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Automobile> listerAutomobile() {
        AutomobileRepository automobileRepository = new AutomobileRepository();
        return automobileRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Automobile dans la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Automobile> chercherAutomobile(String txt) {
        Session session = HibernateJpa.getSessionFactory().openSession();
        Query query = session.createQuery("from Automobile where immatriculation like :txt");
        query.setParameter("txt","%"+txt+"%");
        return query.list();
    }

    /**
     * Cette méthode permet de sélectionner un objet Automobile de la base de données.
     * @param id
     * @return Automobile
     */
    @Override
    public Automobile afficherAutomobile(long id) {
        AutomobileRepository automobileRepository = new AutomobileRepository();
        return automobileRepository.findById(id);
    }

    /**
     * Cette méthode permet de sélectionner un objet Automobile de la base de données.
     * @param txt
     * @return Automobile
     */
    @Override
    public Automobile afficherAutomobile(String txt) {
        Session session = HibernateJpa.getSessionFactory().openSession();
        Query query = session.createQuery("from Automobile where immtriculation=:txt");
        query.setParameter("txt",txt);
        return (Automobile) query.getSingleResult();
    }

    /**
     * Cette méthode permet de mettre à jour un objet Automobile de la base de données.
     * @param up
     */
    @Override
    public void modifierAutomobile(Automobile up) throws Exception {
        AutomobileRepository automobileRepository = new AutomobileRepository();
        automobileRepository.update(up);
    }

    /**
     * Cette méthode permet de supprimer un objet Automobile de la base de données.
     * @param automobile
     */
    @Override
    public void supprimerAutomobile(Automobile automobile) throws Exception {
        AutomobileRepository automobileRepository = new AutomobileRepository();
        automobileRepository.delete(automobile);
    }

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Dossier dans la base de données.
     * @param dossier
     * @return Dossier
     * @see Automobile,Client
     */
    @Override
    public Dossier enregistrerDossier(Dossier dossier) throws Exception {
        DossierRepository dossierRepository = new DossierRepository();
        dossierRepository.persist(dossier);
        return dossier;
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Dossier de la base de données.
     * @return List
     */
    @Override
    public List<Dossier> listerDossier() {
        DossierRepository dossierRepository = new DossierRepository();
        return dossierRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Dossier de la base de données.
     * @return List
     */
    @Override
    public List<Dossier> chercherDossier(String txt) {
        Session session = HibernateJpa.getSessionFactory().openSession();
        Query query = session.createQuery("from Dossier d, Automobile a where d.automobile=a.id and a.immatriculation like :txt");
        query.setParameter("txt","%"+txt+"%");
        return query.list();
    }

    /**
     * Cette méthode permet de sélectionner un objet Dossier de la base de données.
     * @param id
     * @return Dossier
     */
    @Override
    public Dossier afficherDossier(long id) {
        DossierRepository dossierRepository = new DossierRepository();
        return dossierRepository.findById(id);
    }

    /**
     * Cette méthode permet de supprimer un objet Dossier de la base de données.
     * @param dossier
     */
    @Override
    public void supprimerDossier(Dossier dossier) throws Exception {
        DossierRepository dossierRepository = new DossierRepository();
        dossierRepository.delete(dossier);
    }
}
