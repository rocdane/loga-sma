package com.loga.app.controller;

import com.loga.app.dao.*;
import com.loga.model.*;
import com.loga.ui.AlertError;
import com.loga.ui.AlertInfo;
import com.loga.ui.AlertWarning;
import com.loga.vendor.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TresorController implements Initializable {

    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public Date debut = null;
    public Date fin = null;
    public Tresor tresor = null;
    public Date exercice = null;
    Reparation reparation;
    Profile profile;
    List<Profile> allProfile;
    List<Reparation> allReparation;

    @FXML
    private TabPane tresor_tab;

    @FXML
    private Tab tab_compte;

    @FXML
    private DatePicker compte_date;

    @FXML
    private TextField recette_description;

    @FXML
    private TextField recette_montant;

    @FXML
    private Button add_recette;

    @FXML
    private TableView<Recette> table_recette;

    @FXML
    private TableColumn<Recette, Long> t_recette_id;

    @FXML
    private TableColumn<Recette, String> t_recette_date;

    @FXML
    private TableColumn<Recette, String> t_recette_description;

    @FXML
    private TableColumn<Recette, Double> t_recette_montant;

    @FXML
    private Button remove_recette;

    @FXML
    private TextField total_recette_compte;

    @FXML
    private TextField depense_description;

    @FXML
    private TextField depense_montant;

    @FXML
    private Button add_depense;

    @FXML
    private TableView<Depense> table_depense;

    @FXML
    private TableColumn<Depense, Long> t_depense_id;

    @FXML
    private TableColumn<Depense, String> t_depense_date;

    @FXML
    private TableColumn<Depense, String> t_depense_description;

    @FXML
    private TableColumn<Depense, Double> t_depense_montant;

    @FXML
    private Button remove_depense;

    @FXML
    private TextField total_depense_compte;

    @FXML
    private Tab tab_tresor;

    @FXML
    private DatePicker compte_date_debut;

    @FXML
    private DatePicker compte_date_fin;

    @FXML
    private TableView<Recette> table_recette_tresor;

    @FXML
    private TableColumn<Recette, Long> tresor_recette_id;

    @FXML
    private TableColumn<Recette, String> tresor_recette_date;

    @FXML
    private TableColumn<Recette, String> tresor_recette_description;

    @FXML
    private TableColumn<Recette, Double> tresor_recette_montant;

    @FXML
    private TextField total_recette_tresor;

    @FXML
    private TableView<Depense> table_depense_tresor;

    @FXML
    private TableColumn<Depense, Long> tresor_depense_id;

    @FXML
    private TableColumn<Depense, String> tresor_depense_date;

    @FXML
    private TableColumn<Depense, String> tresor_depense_description;

    @FXML
    private TableColumn<Depense, Double> tresor_depense_montant;

    @FXML
    private TextField total_depense_tresor;

    @FXML
    private DatePicker bilan_date_debut;

    @FXML
    private DatePicker bilan_date_fin;

    @FXML
    private TableView<Reparation> table_bilan;

    @FXML
    private TableColumn<Reparation, String> bilan_date;

    @FXML
    private TableColumn<Reparation, String> bilan_client;

    @FXML
    private TableColumn<Reparation, String> bilan_automobile;

    @FXML
    private TableColumn<Reparation, Double> bilan_fourniture;

    @FXML
    private TableColumn<Reparation, Double> bilan_prestation;

    @FXML
    private TableColumn<Reparation, Double> bilan_total;

    @FXML
    private TextField bilan_total_fourniture;

    @FXML
    private TextField bilan_total_reparation;

    @FXML
    private TextField bilan_total_tache;

    @FXML
    private Tab tab_commerce;

    @FXML
    private DatePicker commerce_date_debut;

    @FXML
    private DatePicker commerce_date_fin;

    @FXML
    private TableView<Achat> table_achats;

    @FXML
    private TableColumn<Achat, Long> achat_id;

    @FXML
    private TableColumn<Achat, Article> achat_article;

    @FXML
    private TableColumn<Achat, Integer> achat_quantite;

    @FXML
    private TableColumn<Achat, Double> achat_montant;

    @FXML
    private TextField total_achat;

    @FXML
    private TableView<Vente> table_ventes;

    @FXML
    private TableColumn<Vente, Long> vente_id;

    @FXML
    private TableColumn<Vente, Article> vente_article;

    @FXML
    private TableColumn<Vente, Integer> vente_quantite;

    @FXML
    private TableColumn<Vente, Double> vente_montant;

    @FXML
    private TextField total_vente;

    @FXML
    private ComboBox<Reparation> reglement_reparation;

    @FXML
    private TextField reglement_montant;

    @FXML
    private TextField reglement_solde;

    @FXML
    private TextField reglement_versement;

    @FXML
    private ComboBox<Versement.Mode> reglement_mode;

    @FXML
    private TextField reglement_reference;

    @FXML
    private TableView<Versement> table_solde;

    @FXML
    private TableColumn<Versement, String> solde_date;

    @FXML
    private TableColumn<Versement, String> solde_mode;

    @FXML
    private TableColumn<Versement, Double> solde_versement;

    @FXML
    private Tab tab_salaire;

    @FXML
    private ComboBox<Profile> profile_salaire;

    @FXML
    private ComboBox<Salaire.Mode> mode_salaire;

    @FXML
    private TextField montant_salaire;

    @FXML
    private TextField base_salaire;

    @FXML
    private TextField prime_salaire;

    @FXML
    private TextField ipts_salaire;

    @FXML
    private TextField indemnite_salaire;

    @FXML
    private TextField sup_salaire;

    @FXML
    private TextField po_salaire;

    @FXML
    private TextField reference_salaire;

    @FXML
    private DatePicker date_salaire;

    @FXML
    private TableColumn<Salaire, Profile> salaire_profile;

    @FXML
    private TableColumn<Salaire, String> salaire_mode;

    @FXML
    private TableColumn<Salaire, Integer> salaire_montant;

    @FXML
    private TableColumn<Salaire,String> salaire_date;

    @FXML
    private TableView<Salaire> table_salaire;

    @FXML
    void add_depense(ActionEvent event){
        if(!(exercice==null)){
            Depense depense = new Depense();
            depense.setDateCreation(exercice);
            depense.setTresor(tresor);
            depense.setDescription(depense_description.getText());
            depense.setMontant(Double.parseDouble(depense_montant.getText()));
            try {
                DepenseRepository depenseRepository = new DepenseRepository();
                depenseRepository.save(depense);
                TresorRepository tresorRepository = new TresorRepository();
                tresor = tresorRepository.find(sdf.format(exercice));
                read_compte(tresor);
                depense_description.setText("");
                depense_montant.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void add_recette(ActionEvent event){
        if(!(exercice==null)){
            Recette recette = new Recette();
            recette.setDateCreation(exercice);
            recette.setTresor(tresor);
            recette.setDescription(recette_description.getText());
            recette.setMontant(Double.parseDouble(recette_montant.getText()));
            try {
                RecetteRepository recetteRepository = new RecetteRepository();
                recetteRepository.save(recette);
                TresorRepository tresorRepository = new TresorRepository();
                tresor = tresorRepository.find(new SimpleDateFormat("MM-dd-yyyy").format(exercice));
                read_compte(tresor);
                recette_description.setText("");
                recette_montant.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void remove_depense(ActionEvent event){
        DepenseRepository depenseRepository = new DepenseRepository();
        Depense depense = depenseRepository.findById(table_depense.getSelectionModel().getSelectedItem().getId());
        try {
            depenseRepository.delete(depense);
            TresorRepository tresorRepository = new TresorRepository();
            tresor = tresorRepository.find(sdf.format(exercice));
            read_compte(tresor);
        } catch (Exception e) {
            AlertWarning.getInstance().setHeaderText("Compte de trésorerie");
            AlertWarning.getInstance().setContentText("Impossible de supprimer une dépense vérouillée.");
            AlertWarning.getInstance().showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void remove_recette(ActionEvent event){
        RecetteRepository recetteRepository = new RecetteRepository();
        Recette recette = recetteRepository.findById(table_recette.getSelectionModel().getSelectedItem().getId());
        try {
            recetteRepository.delete(recette);
            TresorRepository tresorRepository = new TresorRepository();
            tresor = tresorRepository.find(sdf.format(exercice));
            read_compte(tresor);
        } catch (Exception e) {
            AlertWarning.getInstance().setHeaderText("Compte de trésorerie");
            AlertWarning.getInstance().setContentText("Impossible de supprimer une recette vérouillée.");
            AlertWarning.getInstance().showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void annuler_reglement(ActionEvent event) {
        reset_reglement_form();
    }

    @FXML
    void annuler_salaire(ActionEvent event) {
        reset_salaire_form();
    }

    private void reset_salaire_form() {
        table_salaire.setItems(null);
        profile_salaire.setItems(null);
        montant_salaire.setText("");
    }

    @FXML
    void cancel(ActionEvent event) {
        reset_compte();
        compte_date.getEditor().setText("");
    }

    @FXML
    void closeBilan(ActionEvent event){
        LocalDate localDate = bilan_date_fin.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        fin = Date.from(zonedDateTime.toInstant());

        if(!(debut==null) && debut.before(fin) || debut.equals(fin)){
            ReparationRepository reparationRepository = new ReparationRepository();
            List<Reparation> reparations = reparationRepository.find(sdf.format(debut),sdf.format(fin));
            read_bilan(reparations);
        }else{
            AlertWarning.getInstance().setHeaderText("Rapport périodique");
            AlertWarning.getInstance().setContentText("Une erreur s'est produite lors de la sélection des dates.\n Vérifiez et reessayez.");
            AlertWarning.getInstance().showAndWait();
        }
    }

    @FXML
    void closeCommerce(ActionEvent event) {
        LocalDate localDate = compte_date_fin.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        fin = Date.from(zonedDateTime.toInstant());

        if(!(debut==null) && debut.before(fin) || debut.equals(fin)){
            VenteRepository venteRepository = new VenteRepository();
            List<Vente> ventes = venteRepository.find(sdf.format(debut),sdf.format(fin));

            AchatRepository achatRepository = new AchatRepository();
            List<Achat> achats = achatRepository.find(sdf.format(debut),sdf.format(fin));

            read_commerce(ventes,achats);
        }else{
            AlertWarning.getInstance().setHeaderText("Rapport périodique");
            AlertWarning.getInstance().setContentText("Une erreur s'est produite lors de la sélection des dates.\n Vérifiez et reessayez.");
            AlertWarning.getInstance().showAndWait();
        }
    }

    @FXML
    void closeTresor(ActionEvent event){
        LocalDate localDate = compte_date_fin.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        fin = Date.from(zonedDateTime.toInstant());

        if(!(debut==null) && debut.before(fin) || debut.equals(fin)){
            TresorRepository tresorRepository = new TresorRepository();
            List<Tresor> tresors = tresorRepository.find(sdf.format(debut),sdf.format(fin));
            read_tresor(tresors);
        }else{
            AlertWarning.getInstance().setHeaderText("Compte de trésorerie");
            AlertWarning.getInstance().setContentText("Une erreur s'est produite lors de la sélection des dates. Vérifiez et reessayez.");
            AlertWarning.getInstance().showAndWait();
        }
    }

    @FXML
    void enregistrer_reglement(ActionEvent event) {
        if(reparation!=null && Double.parseDouble(reglement_versement.getText())>0 && Double.parseDouble(reglement_solde.getText())>=Double.parseDouble(reglement_versement.getText()) && reglement_mode.getSelectionModel().getSelectedItem()!=null){
            Versement versement = new Versement();
            versement.setDateVersement(new Date());
            versement.setReparation(reparation);
            versement.setMode(reglement_mode.getSelectionModel().getSelectedItem().name());
            versement.setVersement(Double.parseDouble(reglement_versement.getText()));
            versement.setReference(reglement_reference.getText().trim());
            VersementRepository versementRepository = new VersementRepository();

            try {
                versementRepository.save(versement);
                reglement_versement.setText("");
            } catch (Exception e) {
                AlertError.getInstance().setHeaderText("Versement");
                AlertError.getInstance().setContentText("Echec lors de l'enregistrement du versement.\n"+e.getMessage());
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }

            read_solde();
        }else{
            AlertWarning.getInstance().setHeaderText("Versement");
            AlertWarning.getInstance().setContentText("Vérifiez les informations que vous entrez.");
            AlertWarning.getInstance().showAndWait();
        }
    }

    @FXML
    void enregistrer_salaire(ActionEvent event) {
        LocalDate localDate = date_salaire.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        Date date = Date.from(zonedDateTime.toInstant());

        if(Integer.parseInt(montant_salaire.getText())>0){
            Salaire salaire = new Salaire();
            salaire.setProfile(profile_salaire.getSelectionModel().getSelectedItem());
            salaire.setMontant(Integer.parseInt(montant_salaire.getText()));
            salaire.setHeureSup(Integer.parseInt(sup_salaire.getText()));
            salaire.setIndemnite(Integer.parseInt(indemnite_salaire.getText()));
            salaire.setIpts(Integer.parseInt(ipts_salaire.getText()));
            salaire.setPartOuvriere(Integer.parseInt(po_salaire.getText()));
            salaire.setMode(mode_salaire.getEditor().getText());
            salaire.setMontant(Integer.parseInt(montant_salaire.getText()));
            salaire.setReference(reference_salaire.getText());
            salaire.setDate(date);

            SalaireRepository salaireRepository = new SalaireRepository();
            try {
                salaireRepository.save(salaire);
            } catch (Exception e) {
                AlertError.getInstance().setHeaderText("Salaire");
                AlertError.getInstance().setContentText("Echec lors de l'enregistrement du salaire.\n"+e.getMessage());
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }
            read_salaire();
        }else{
            AlertWarning.getInstance().setHeaderText("Salaire");
            AlertWarning.getInstance().setContentText("Vérifiez les informations que vous entrez.");
            AlertWarning.getInstance().showAndWait();
        }
    }

    @FXML
    void fermer(ActionEvent event) {
        reset_tresor();
    }

    @FXML
    void fermerCommerce(ActionEvent event) {
        reset_commerce();
    }

    @FXML
    void fermerBilan(ActionEvent event) {
        reset_bilan();
    }

    @FXML
    void openBilan(ActionEvent event){
        LocalDate localDate = bilan_date_debut.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        debut = Date.from(zonedDateTime.toInstant());
    }

    @FXML
    void openCommerce(ActionEvent event) {
        LocalDate localDate = commerce_date_debut.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        debut = Date.from(zonedDateTime.toInstant());
    }

    @FXML
    void openCompte(ActionEvent event){

        reset_compte();

        LocalDate localDate = compte_date.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        exercice = Date.from(zonedDateTime.toInstant());

        TresorRepository tresorRepository = new TresorRepository();

        try {
            tresor = tresorRepository.find(sdf.format(exercice));
        } catch (Exception e) {
            AlertError.getInstance().setHeaderText("Compte journalier");
            AlertError.getInstance().setContentText("Impossible de créer un compte de trésorerie.");
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }

        if(tresor==null){
            if(!exercice.after(new Date())){
                AtelierRepository atelierRepository = new AtelierRepository();
                tresor = new Tresor();
                tresor.setExercice(exercice);
                tresor.setAtelier(atelierRepository.findById(1));

                try {
                    long id = tresorRepository.save(tresor);

                    tresor = tresorRepository.findById(id);

                    AlertInfo.getInstance().setHeaderText("Compte journalier");
                    AlertInfo.getInstance().setContentText("Nouveau compte journalier ("+(new SimpleDateFormat("dd/MM/yyyy")).format(tresor.getExercice())+") créé avec succès.");
                    AlertInfo.getInstance().showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                AlertWarning.getInstance().setHeaderText("Compte journalier");
                AlertWarning.getInstance().setContentText("Impossible de créer un compte de trésorerie à l'avance.");
                AlertWarning.getInstance().showAndWait();
            }
        }else {
            AlertWarning.getInstance().setHeaderText("Compte journalier");
            AlertWarning.getInstance().setContentText("Un compte de trésorerie existe pour la date : "+new SimpleDateFormat("dd/MM/yyyy").format(exercice));
            AlertWarning.getInstance().show();
            read_compte(tresor);
        }
    }

    @FXML
    void openTresor(ActionEvent event){
        LocalDate localDate = compte_date_debut.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        debut = Date.from(zonedDateTime.toInstant());
    }

    @FXML
    void print(ActionEvent event) {
        if(!(debut==null) && !(fin==null)){
            Report.getInstance().createReport("/jrxml/tresor.jrxml",debut,fin);
        }
    }

    @FXML
    void printBilan(ActionEvent event) {
        if(!(debut==null) && !(fin==null)){
            Report.getInstance().createReport("/jrxml/bilan.jrxml",debut,fin);
        }
    }

    @FXML
    void printCommerce(ActionEvent event) {
        if(!(debut==null) && !(fin==null)){
            Report.getInstance().createReport("/jrxml/commerce.jrxml",debut,fin);
        }
    }

    @FXML
    void printCompte(ActionEvent event) {
        if(!(exercice==null)){
            Report.getInstance().createReport("/jrxml/compte.jrxml",exercice);
        }
    }

    @FXML
    void search_ordre(KeyEvent event) {
        FilteredList<Reparation> items = new FilteredList<>(FXCollections.observableArrayList(allReparation));
        items.setPredicate(item ->{
            String lower = reglement_reparation.getEditor().getText().toLowerCase();
            String upper = reglement_reparation.getEditor().getText().toUpperCase();
            if(item.getDossier().getAutomobile().getImmatriculation().contains(lower))
                return true;
            else
                return item.getDossier().getAutomobile().getImmatriculation().contains(upper);
        });
        SortedList<Reparation> sorted = new SortedList<>(items);
        reglement_reparation.setItems(sorted);
    }

    @FXML
    void search_profile(KeyEvent event) {
        FilteredList<Profile> items = new FilteredList<>(FXCollections.observableArrayList(allProfile));
        items.setPredicate(item ->{
            String lower = profile_salaire.getEditor().getText().toLowerCase();
            String upper = profile_salaire.getEditor().getText().toUpperCase();
            if(item.getNom().contains(lower) || item.getPrenom().contains(lower))
                return true;
            else
                return item.getNom().contains(upper) || item.getPrenom().contains(upper);
        });
        SortedList<Profile> sorted = new SortedList<>(items);
        profile_salaire.setItems(FXCollections.observableArrayList(sorted));
    }

    void read_bilan(List<Reparation> reparations){

        table_bilan.setItems(FXCollections.observableArrayList(reparations));

        List<Fourniture> fournitures = new ArrayList<>();

        List<Tache> taches = new ArrayList<>();

        for (Reparation reparation:reparations) {
            fournitures.addAll(reparation.getFournitures());
            taches.addAll(reparation.getTaches());
        }

        double total_fourniture=0;
        for (Fourniture fourniture:fournitures) {
            total_fourniture+=fourniture.getMontant();
        }
        bilan_total_fourniture.setText(Money.getInstance().format(total_fourniture)+" Fcfa");

        double total_tache=0;
        for (Tache tache:taches){
            total_tache+=tache.getCout();
        }
        bilan_total_tache.setText(total_tache+" Fcfa");

        double total_reparation = total_tache + total_fourniture;
        bilan_total_reparation.setText(Money.getInstance().format(total_reparation)+" Fcfa");
    }

    void read_commerce(List<Vente> ventes,List<Achat> achats){
        table_ventes.setItems(FXCollections.observableArrayList(ventes));
        double vente_total = 0;
        for (Vente vente:ventes) {
            vente_total+=vente.getMontant();
        }
        total_vente.setText(Money.getInstance().format(vente_total)+" F (CFA)");

        table_achats.setItems(FXCollections.observableArrayList(achats));
        double achat_total = 0;
        for (Achat achat:achats) {
            achat_total+=achat.getMontant();
        }
        total_achat.setText(Money.getInstance().format(achat_total)+" F (CFA)");
    }

    void read_compte(Tresor tresor){

        table_recette.setItems(FXCollections.observableArrayList(tresor.getRecettes()));

        table_recette.refresh();

        double total_recette=0;

        for (Recette recette:tresor.getRecettes()){
            AlertInfo.getInstance().setContentText(recette.getDescription()+" | "+recette.getMontant());
            total_recette+=recette.getMontant();
        }

        total_recette_compte.setText(Money.getInstance().format(total_recette)+" Fcfa");

        table_depense.setItems(FXCollections.observableArrayList(tresor.getDepenses()));

        double total_depense=0;

        for (Depense depense:tresor.getDepenses()) {
            total_depense+=depense.getMontant();
        }

        total_depense_compte.setText(Money.getInstance().format(total_depense)+" Fcfa");
    }

    void read_tresor(List<Tresor> tresors){

        List<Depense> depenses = new ArrayList<>();

        List<Recette> recettes = new ArrayList<>();

        for (Tresor tresor:tresors) {
            depenses.addAll(tresor.getDepenses());
            recettes.addAll(tresor.getRecettes());
        }

        table_depense_tresor.setItems(FXCollections.observableArrayList(depenses));

        table_recette_tresor.setItems(FXCollections.observableArrayList(recettes));

        double total_depense=0;
        for (Depense depense:depenses) {
            total_depense+=depense.getMontant();
        }
        total_depense_tresor.setText(Money.getInstance().format(total_depense)+" Fcfa");

        double total_recette=0;
        for (Recette recette:recettes){
            total_recette+=recette.getMontant();
        }
        total_recette_tresor.setText(Money.getInstance().format(total_recette)+" Fcfa");
    }

    void read_solde(){
        reparation = reglement_reparation.getSelectionModel().getSelectedItem();
        double solde=0;
        for (Versement versement:reparation.getVersements()) {
            solde+=versement.getVersement();
        }
        reglement_montant.setText(String.valueOf(reparation.getTotal()));
        reglement_solde.setText(String.valueOf(reparation.getTotal()-solde));
        table_solde.setItems(FXCollections.observableArrayList(reparation.getVersements()));
    }

    void read_salaire(){
        SalaireRepository salaireRepository = new SalaireRepository();
        table_salaire.setItems(FXCollections.observableArrayList(salaireRepository.findAll()));
    }

    void reset_reglement_form(){
        table_solde.setItems(null);
        reglement_reparation.setItems(null);
        reglement_reparation.getEditor().setText("");
        reglement_montant.setText("");
        reglement_solde.setText("");
        reglement_versement.setText("");
    }

    void reset_compte(){
        recette_description.setText("");
        recette_montant.setText("");
        total_recette_compte.setText("");
        table_recette.setItems(null);
        depense_description.setText("");
        total_depense_compte.setText("");
        table_depense.setItems(null);
        depense_montant.setText("");
    }

    void reset_tresor(){
        compte_date_debut.getEditor().setText("");
        compte_date_fin.getEditor().setText("");
        table_recette_tresor.setItems(null);
        table_recette_tresor.refresh();
        table_depense_tresor.setItems(null);
        table_depense_tresor.refresh();
        total_recette_tresor.setText("");
        total_depense_tresor.setText("");
    }

    void reset_bilan(){
        bilan_total_reparation.setText("");
        bilan_total_fourniture.setText("");
        bilan_total_tache.setText("");
        bilan_date_debut.getEditor().setText("");
        bilan_date_fin.getEditor().setText("");
        table_bilan.setItems(null);
        table_bilan.refresh();
    }

    void reset_commerce(){
        commerce_date_debut.getEditor().setText("");
        compte_date_fin.getEditor().setText("");
        table_achats.setItems(null);
        table_achats.refresh();
        table_ventes.setItems(null);
        table_ventes.refresh();
        total_achat.setText("");
        total_vente.setText("");
    }

    void read_profile(){
        ProfileRepository profileRepository = new ProfileRepository();
        allProfile = profileRepository.findAll();
        profile_salaire.setItems(FXCollections.observableArrayList(allProfile));
    }

    void read_reparation(){
        ReparationRepository reparationRepository = new ReparationRepository();
        allReparation = reparationRepository.searchOrdered();
        reglement_reparation.setItems(FXCollections.observableArrayList(allReparation));
    }

    public void initialize(URL location, ResourceBundle resources) {

        read_profile();

        read_reparation();

        reglement_reparation.setConverter(new StringConverter<Reparation>() {
            @Override
            public String toString(Reparation object) {
                if(object==null)
                    return null;
                return object.toString();
            }

            @Override
            public Reparation fromString(String string) {
                String line = reglement_reparation.getEditor().getText();
                String[] dossier = line.split("\\s");
                ReparationRepository reparationRepository = new ReparationRepository();
                return reparationRepository.findById(Long.parseLong(dossier[0].trim()));
            }
        });

        profile_salaire.setConverter(new StringConverter<Profile>() {
            @Override
            public String toString(Profile object) {
                if(object==null)
                    return null;
                return object.toString();
            }

            @Override
            public Profile fromString(String string) {
                String line = profile_salaire.getEditor().getText();
                String[] id = line.split("\\s");
                ProfileRepository profileRepository = new ProfileRepository();
                return profileRepository.findById(Long.parseLong(id[0].trim()));
            }
        });

        reglement_reparation.addEventHandler(ActionEvent.ACTION, event -> {
            if (reglement_reparation.getSelectionModel().getSelectedIndex() != -1) {
                read_solde();
            }
        });

        profile_salaire.addEventHandler(ActionEvent.ACTION, event -> {
            if (profile_salaire.getSelectionModel().getSelectedIndex() != -1) {
                profile = profile_salaire.getSelectionModel().getSelectedItem();
            }
        });

        reglement_mode.getItems().setAll(Versement.getModes());

        mode_salaire.getItems().setAll(Salaire.getModes());

        salaire_date.setCellValueFactory((TableColumn.CellDataFeatures<Salaire, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-YYYY").format(r.getValue().getDate())));
        salaire_mode.setCellValueFactory((TableColumn.CellDataFeatures<Salaire, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMode()));
        salaire_montant.setCellValueFactory((TableColumn.CellDataFeatures<Salaire, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));
        salaire_profile.setCellValueFactory((TableColumn.CellDataFeatures<Salaire, Profile> r)->new ReadOnlyObjectWrapper<>(r.getValue().getProfile()));

        solde_date.setCellValueFactory((TableColumn.CellDataFeatures<Versement, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-YYYY").format(r.getValue().getDateVersement())));
        solde_mode.setCellValueFactory((TableColumn.CellDataFeatures<Versement, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMode()));
        solde_versement.setCellValueFactory((TableColumn.CellDataFeatures<Versement, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getVersement()));

        t_recette_id.setCellValueFactory((TableColumn.CellDataFeatures<Recette, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        t_recette_date.setCellValueFactory((TableColumn.CellDataFeatures<Recette, String> r)->new ReadOnlyObjectWrapper<>((new SimpleDateFormat("dd-MM-yyyy")).format(r.getValue().getDateCreation())));
        t_recette_description.setCellValueFactory((TableColumn.CellDataFeatures<Recette, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDescription()));
        t_recette_montant.setCellValueFactory((TableColumn.CellDataFeatures<Recette, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));

        t_depense_id.setCellValueFactory((TableColumn.CellDataFeatures<Depense, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        t_depense_date.setCellValueFactory((TableColumn.CellDataFeatures<Depense, String> r)->new ReadOnlyObjectWrapper<>((new SimpleDateFormat("dd-MM-yyyy")).format(r.getValue().getDateCreation())));
        t_depense_description.setCellValueFactory((TableColumn.CellDataFeatures<Depense, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDescription()));
        t_depense_montant.setCellValueFactory((TableColumn.CellDataFeatures<Depense, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));

        tresor_recette_id.setCellValueFactory((TableColumn.CellDataFeatures<Recette, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        tresor_recette_date.setCellValueFactory((TableColumn.CellDataFeatures<Recette, String> r)->new ReadOnlyObjectWrapper<>((new SimpleDateFormat("dd-MM-yyyy")).format(r.getValue().getDateCreation())));
        tresor_recette_description.setCellValueFactory((TableColumn.CellDataFeatures<Recette, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDescription()));
        tresor_recette_montant.setCellValueFactory((TableColumn.CellDataFeatures<Recette, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));

        tresor_depense_id.setCellValueFactory((TableColumn.CellDataFeatures<Depense, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        tresor_depense_date.setCellValueFactory((TableColumn.CellDataFeatures<Depense, String> r)->new ReadOnlyObjectWrapper<>((new SimpleDateFormat("dd-MM-yyyy")).format(r.getValue().getDateCreation())));
        tresor_depense_description.setCellValueFactory((TableColumn.CellDataFeatures<Depense, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDescription()));
        tresor_depense_montant.setCellValueFactory((TableColumn.CellDataFeatures<Depense, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));

        bilan_date.setCellValueFactory((TableColumn.CellDataFeatures<Reparation, String> r)->new ReadOnlyObjectWrapper<>((new SimpleDateFormat("dd-MM-yyyy")).format(r.getValue().getDateCreation())));
        bilan_automobile.setCellValueFactory((TableColumn.CellDataFeatures<Reparation, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDossier().getAutomobile().getImmatriculation()));
        bilan_client.setCellValueFactory((TableColumn.CellDataFeatures<Reparation, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDossier().getAutomobile().getClient().getRaisonSociale()));
        bilan_fourniture.setCellValueFactory((TableColumn.CellDataFeatures<Reparation, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getTotalFourniture()));
        bilan_prestation.setCellValueFactory((TableColumn.CellDataFeatures<Reparation, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getTotalTache()));
        bilan_total.setCellValueFactory((TableColumn.CellDataFeatures<Reparation, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getTotal()));

        achat_id.setCellValueFactory((TableColumn.CellDataFeatures<Achat, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        achat_article.setCellValueFactory((TableColumn.CellDataFeatures<Achat, Article> r)->new ReadOnlyObjectWrapper<>(r.getValue().getArticle()));
        achat_quantite.setCellValueFactory((TableColumn.CellDataFeatures<Achat, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getQuantite()));
        achat_montant.setCellValueFactory((TableColumn.CellDataFeatures<Achat, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));

        vente_id.setCellValueFactory((TableColumn.CellDataFeatures<Vente, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        vente_article.setCellValueFactory((TableColumn.CellDataFeatures<Vente, Article> r)->new ReadOnlyObjectWrapper<>(r.getValue().getArticle()));
        vente_quantite.setCellValueFactory((TableColumn.CellDataFeatures<Vente, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getQuantite()));
        vente_montant.setCellValueFactory((TableColumn.CellDataFeatures<Vente, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));
    }
}
