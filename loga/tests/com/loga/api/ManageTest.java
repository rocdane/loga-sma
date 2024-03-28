package com.loga.api;

import com.loga.model.*;
import org.junit.Assert;
import org.junit.Test;
import java.util.Date;

public class ManageTest {

    @Test
    public void enregistrerCalendrier() {
    }

    @Test
    public void enregistrerUtilisateur() {
    }

    @Test
    public void enregistrerAtelier() throws Exception {
        Atelier atelier = new Atelier();
        atelier.setRaisonSociale("GARAGE");
        atelier.setMentionLegale("ENTREPRISE D'EXPLOITATION AUTOMOBILE");
        atelier.setContact("contact@loga.com");
        atelier.setAdresse("rue 00229, Porto-novo, Bénin");
        Atelier added = Manage.getInstance().enregistrerAtelier(atelier);
        Assert.assertSame(added,atelier);
    }

    @Test
    public void enregistrerService() throws Exception {
        Service service = new Service();
        service.setDesignation("DIRECTION");

        Atelier atelier = new Atelier();
        atelier.setRaisonSociale("GARAGE");
        atelier.setMentionLegale("ENTREPRISE D'EXPLOITATION AUTOMOBILE");
        atelier.setContact("contact@loga.com");
        atelier.setAdresse("rue 00229, Porto-novo, Bénin");
        atelier.addService(service);

        Service added = Manage.getInstance().enregistrerService(service);

        Assert.assertSame(added,service);
    }

    @Test
    public void enregistrerTitre() throws Exception {
        Titre titre = new Titre();
        titre.setDesignation("Direction");

        Service service = new Service();
        service.setDesignation("DIRECTION");
        service.addTitre(titre);

        Atelier atelier = new Atelier();
        atelier.setRaisonSociale("GARAGE");
        atelier.setMentionLegale("ENTREPRISE D'EXPLOITATION AUTOMOBILE");
        atelier.setContact("contact@loga.com");
        atelier.setAdresse("rue 00229, Porto-novo, Bénin");
        atelier.addService(service);

        Titre added = Manage.getInstance().enregistrerTitre(titre);

        Assert.assertSame(added,titre);
    }

    @Test
    public void enregistrerEspace() throws Exception {
        Espace espace = new Espace();
        espace.setCode("M1");
        espace.setIntitule("Maintenance 1");

        Atelier atelier = new Atelier();
        atelier.setRaisonSociale("GARAGE");
        atelier.setMentionLegale("ENTREPRISE D'EXPLOITATION AUTOMOBILE");
        atelier.setContact("contact@loga.com");
        atelier.setAdresse("rue 00229, Porto-novo, Bénin");
        atelier.addEspace(espace);

        Espace added = Manage.getInstance().enregistrerEspace(espace);

        Assert.assertSame(added,espace);
    }

    @Test
    public void enregistrerTresor() throws Exception {
        Depense depense = new Depense();
        depense.setDescription("Achat imprévu");
        depense.setMontant(2000);
        depense.setDateCreation(new Date());

        Recette recette = new Recette();
        recette.setDescription("Avance sur devis");
        recette.setMontant(10000);
        recette.setDateCreation(new Date());

        Tresor tresor = new Tresor();
        tresor.setExercice(new Date());
        tresor.addDepense(depense);
        tresor.setTotalDepense(tresor.getTotalDepense());
        tresor.addRecette(recette);
        tresor.setTotalRecette(tresor.getTotalRecette());

        Atelier atelier = new Atelier();
        atelier.setRaisonSociale("GARAGE");
        atelier.setMentionLegale("ENTREPRISE D'EXPLOITATION AUTOMOBILE");
        atelier.setContact("contact@loga.com");
        atelier.setAdresse("rue 00229, Porto-novo, Bénin");
        atelier.addTresor(tresor);

        Tresor added = Manage.getInstance().enregistrerTresor(tresor);

        Assert.assertSame(added,tresor);
    }

    @Test
    public void enregistrerVersement() {
    }

    @Test
    public void enregistrerSalaire() throws Exception {
        Salaire salaire = new Salaire();
        salaire.setMontant(30000);
        salaire.setDate(new Date());
        salaire.setHeureSup(5000);
        salaire.setIndemnite(2000);
        salaire.setIpts(1000);
        salaire.setPartOuvriere(1000);
        salaire.setPrime(10000);
        salaire.setMode(Salaire.Mode.ESPECE.name());
        salaire.setReference("aucune");

        Profile profile = new Profile();
        profile.setNom("sabi");
        profile.setPrenom("rochdane");
        profile.setContact("rochdanesabi@gmcplus.com");
        profile.setPoste("IT");
        profile.setDateAjout(new Date());

        User user = new User();
        user.setUsername(profile.getPrenom().trim().toLowerCase());
        user.setPassword(profile.getNom().trim().toUpperCase());
        user.setRole(User.Role.UTILISATEUR.name());
        user.setActive(true);
        user.setProfile(profile);
        profile.setUser(user);
        profile.addSalaire(salaire);

        Salaire added = Manage.getInstance().enregistrerSalaire(salaire);

        Assert.assertSame(added,salaire);

    }

    @Test
    public void enregistrerProfile() throws Exception {
        Profile profile = new Profile();
        profile.setNom("sabi");
        profile.setPrenom("rochdane");
        profile.setContact("rochdanesabi@loga.com");
        profile.setPoste("IT");
        profile.setDateAjout(new Date());

        User user = new User();
        user.setUsername(profile.getPrenom().trim().toLowerCase());
        user.setPassword(profile.getNom().trim().toUpperCase());
        user.setRole(User.Role.UTILISATEUR.name());
        user.setActive(true);
        user.setProfile(profile);
        profile.setUser(user);

        Profile added = Manage.getInstance().enregistrerProfile(profile);
        Assert.assertSame(added,profile);
    }

    @Test
    public void enregistrerEvaluation() throws Exception {
        Critere critere = new Critere();
        critere.setCode("APT1");
        critere.setDesignation("Aptitude 1");
        critere.setBareme(3);
        critere.setNote(1);

        Evaluation evaluation = new Evaluation();
        evaluation.setObservation("Formation continue");
        evaluation.setDate(new Date());
        evaluation.setNote(14);
        evaluation.addCritere(critere);

        Profile profile = new Profile();
        profile.setNom("sabi");
        profile.setPrenom("rochdane");
        profile.setContact("rochdanesabi@loga.com");
        profile.setPoste("IT");
        profile.setDateAjout(new Date());

        User user = new User();
        user.setUsername(profile.getPrenom().trim().toLowerCase());
        user.setPassword(profile.getNom().trim().toUpperCase());
        user.setRole(User.Role.UTILISATEUR.name());
        user.setActive(true);
        user.setProfile(profile);
        profile.setUser(user);
        profile.addEvaluation(evaluation);

        Evaluation added = Manage.getInstance().enregistrerEvaluation(evaluation);
        Assert.assertSame(added,evaluation);
    }

    @Test
    public void enregistrerClient() throws Exception {
        Client client = new Client();
        client.setRaisonSociale("rochdane sabi");
        client.setAdresse("Bénin");
        client.setVille("Cotonou");
        client.setContact("rocdanesabi@loga.com");

        Client added = Manage.getInstance().enregistrerClient(client);

        Assert.assertSame(client,added);
    }

    @Test
    public void enregistrerAutomobile() throws Exception {
        Automobile automobile = new Automobile();
        automobile.setImmatriculation("CC9223RB");
        automobile.setMarque("RENAULT");
        automobile.setModele("DUSTER");
        automobile.setChassis("ABCDEFGHIJKLMNOPQ");
        automobile.setCylindre("2.0 L");
        automobile.setPmec("01/01/2019");
        automobile.setPuissance("9CV");
        automobile.setTransmission("MANUELLE");
        automobile.setCompteur(923);
        automobile.setTypeCompteur(Automobile.Compteur.KM.name());

        Client client = new Client();
        client.setRaisonSociale("rochdane sabi");
        client.setAdresse("Bénin");
        client.setVille("Cotonou");
        client.setContact("rocdanesabi@loga.com");
        client.addAutomobile(automobile);

        Automobile added = Manage.getInstance().enregistrerAutomobile(automobile);

        Assert.assertSame(automobile,added);
    }

    @Test
    public void enregistrerDossier() throws Exception {
        Client client = new Client();
        client.setRaisonSociale("rochdane sabi");
        client.setAdresse("Bénin");
        client.setVille("Cotonou");
        client.setContact("rocdanesabi@loga.com");

        Automobile automobile = new Automobile();
        automobile.setImmatriculation("CC9223RB");
        automobile.setMarque("RENAULT");
        automobile.setModele("DUSTER");
        automobile.setChassis("ABCDEFGHIJKLMNOPQ");
        automobile.setCylindre("2.0 L");
        automobile.setPmec("01/01/2019");
        automobile.setPuissance("9CV");
        automobile.setTransmission("MANUELLE");
        automobile.setCompteur(923);
        automobile.setTypeCompteur(Automobile.Compteur.KM.name());
        automobile.setClient(client);
        client.addAutomobile(automobile);

        Dossier dossier = new Dossier();
        dossier.setDateOuverture(new Date());
        dossier.setDateMaj(new Date());
        dossier.setReference("GMCPLUS/"+automobile.getImmatriculation()+"/"+client.getRaisonSociale());
        dossier.setAutomobile(automobile);
        dossier.setClient(client);
        automobile.setDossier(dossier);
        client.addDossier(dossier);

        Dossier added = Manage.getInstance().enregistrerDossier(dossier);

        Assert.assertSame(added,dossier);
    }
}