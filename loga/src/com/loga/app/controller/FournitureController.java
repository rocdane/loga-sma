package com.loga.app.controller;

import com.loga.app.dao.*;
import com.loga.model.*;
import com.loga.ui.AlertConfirm;
import com.loga.ui.AlertError;
import com.loga.ui.AlertInfo;
import com.loga.vendor.Report;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class FournitureController implements Initializable
{
    long commandeID;
    ObservableList<Fourniture> fournitures;
    ObservableList<Fourniture> lignecommandes;
    ObservableList<Fourniture> lignelivraisons;
    ObservableList<Fourniture> fournitureCommandes;
    List<Fournisseur> allFournisseur;
    List<Reparation> allReparation;
    List<Commande> allCommande;
    Fourniture fournitureCopy;

    @FXML
    private AnchorPane content;

    @FXML
    private TextField f_id;

    @FXML
    private TextField f_raisonSociale;

    @FXML
    private TextField f_contact;

    @FXML
    private TextField f_adresse;

    @FXML
    private TableView<Fournisseur> table_fournisseur;

    @FXML
    private TableColumn<Fournisseur, Long> t_id;

    @FXML
    private TableColumn<Fournisseur, String> t_raisonSociale;

    @FXML
    private TableColumn<Fournisseur, String> t_contact;

    @FXML
    private TableColumn<Fournisseur, String> t_adresse;

    @FXML
    private TabPane article_tab;

    @FXML
    private Tab tab_commande;

    @FXML
    private Tab tab_fournisseur;

    @FXML
    private AnchorPane commande_tab_content;

    @FXML
    private ComboBox<Reparation> reparation;

    @FXML
    private ComboBox<Fournisseur> fournisseur;

    @FXML
    private Button create_commande;

    @FXML
    private Button cancel_commande;

    @FXML
    private TableView<Fourniture> table_fourniture;

    @FXML
    private TableColumn<Fourniture, Long> id;

    @FXML
    private TableColumn<Fourniture, String> designation;

    @FXML
    private TableColumn<Fourniture, Double> prix;

    @FXML
    private TableColumn<Fourniture, Integer> quantite;

    @FXML
    private TableColumn<Fourniture, Double> montant;

    @FXML
    private GridPane new_article_form;

    @FXML
    private TextField article_designation;

    @FXML
    private TextField article_prix;

    @FXML
    private TextField article_quantite;

    @FXML
    private TextField article_montant;

    @FXML
    private Button commander;

    @FXML
    private TableView<Fourniture> ligne_commande;

    @FXML
    private TableColumn<Fourniture, Long> ligne_id;

    @FXML
    private TableColumn<Fourniture, String> ligne_designation;

    @FXML
    private TableColumn<Fourniture, Double> ligne_prix;

    @FXML
    private TableColumn<Fourniture, Integer> ligne_quantite;

    @FXML
    private TableColumn<Fourniture, Double> ligne_montant;

    @FXML
    private Tab tab_livraison;

    @FXML
    private AnchorPane livraison_tab_content;

    @FXML
    private ComboBox<Commande> commande;

    @FXML
    private Button create_livraison;

    @FXML
    private Button cancel_livraison;

    @FXML
    private TableView<Fourniture> table_article;

    @FXML
    private TableColumn<Fourniture, Long> commande_id;

    @FXML
    private TableColumn<Fourniture, String> commande_designation;

    @FXML
    private TableColumn<Fourniture, Double> commande_prix;

    @FXML
    private TableColumn<Fourniture, Integer> commande_quantite;

    @FXML
    private Button reception;

    @FXML
    private TableView<Fourniture> ligne_livraison;

    @FXML
    private TableColumn<Fourniture, Long> ligne_livraison_id;

    @FXML
    private TableColumn<Fourniture, String> ligne_livraison_designation;

    @FXML
    private TableColumn<Fourniture, Double> ligne_livraison_prix;

    @FXML
    private TableColumn<Fourniture, Integer> ligne_livraison_quantite;

    public FournitureController() {
        fournitures = FXCollections.observableArrayList();
        lignecommandes = FXCollections.observableArrayList();
        lignelivraisons = FXCollections.observableArrayList();
        fournitureCommandes = FXCollections.observableArrayList();
    }

    @FXML
    void saveFournisseur(ActionEvent event){
        if(!f_id.getText().equals("") && !f_raisonSociale.getText().equals("") && !f_contact.getText().equals("")){
            FournisseurRepository fournisseurRepository = new FournisseurRepository();
            Fournisseur fournisseur = fournisseurRepository.findById(Long.parseLong(f_id.getText().trim()));
            fournisseur.setRaisonSociale(f_raisonSociale.getText().trim());
            fournisseur.setContact(f_contact.getText().trim());
            fournisseur.setAdresse(f_adresse.getText().trim());

            try {
                fournisseurRepository.update(fournisseur);
                AlertInfo.getInstance().setTitle("Info");
                AlertInfo.getInstance().setHeaderText("Enregistrement fournisseur");
                AlertInfo.getInstance().setContentText("Fournisseur mis à jour avec succès");
                AlertInfo.getInstance().show();
                emptyFournisseurForm();
                table_fournisseur.setItems(FXCollections.observableArrayList(fournisseurRepository.findAll()));
            } catch (Exception e) {
                AlertError.getInstance().setTitle("Erreur");
                AlertError.getInstance().setHeaderText("Enregistrement fournisseur");
                AlertError.getInstance().setContentText("Echec lors de la mise à jour du Fournisseur.");
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }
        }else if(f_id.getText().equals("") && !f_raisonSociale.getText().equals("") && !f_contact.getText().equals("")){
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setRaisonSociale(f_raisonSociale.getText().trim());
            fournisseur.setContact(f_contact.getText().trim());
            fournisseur.setAdresse(f_adresse.getText().trim());
            try {
                FournisseurRepository fournisseurRepository = new FournisseurRepository();
                fournisseurRepository.save(fournisseur);
                AlertInfo.getInstance().setTitle("Info");
                AlertInfo.getInstance().setHeaderText("Enregistrement fournisseur");
                AlertInfo.getInstance().setContentText("Nouveau fournisseur enregistré avec succès.");
                AlertInfo.getInstance().show();
                emptyFournisseurForm();
                table_fournisseur.setItems(FXCollections.observableArrayList(fournisseurRepository.findAll()));
            } catch (Exception e) {
                AlertError.getInstance().setTitle("Erreur");
                AlertError.getInstance().setHeaderText("Enregistrement fournisseur");
                AlertError.getInstance().setContentText("Echec lors de l'enregistrement du fournisseur.\n"+e.getMessage());
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }
        }
    }

    void emptyFournisseurForm(){
        f_raisonSociale.setText("");
        f_contact.setText("");
        f_adresse.setText("");
        f_id.setText("");
    }

    @FXML
    void cancelFournisseur(ActionEvent event){
        emptyFournisseurForm();
    }

    @FXML
    void deleteFournisseur(ActionEvent event){
        AlertConfirm.getInstance().setHeaderText("Fournisseur");
        AlertConfirm.getInstance().setContentText("Voulez-vous supprimer le fournisseur? Cliquer sur OK pour supprimer le fournisseur sinon cliquer sur annuler.");
        if(AlertConfirm.getInstance().showAndWait().get().equals(ButtonType.OK)){
            FournisseurRepository fournisseurRepository = new FournisseurRepository();
            try {
                fournisseurRepository.delete(table_fournisseur.getSelectionModel().getSelectedItem());
                table_fournisseur.setItems(FXCollections.observableArrayList(fournisseurRepository.findAll()));
                AlertInfo.getInstance().setHeaderText("Fournisseur");
                AlertInfo.getInstance().setContentText("Fournisseur supprimé avec succès");
                AlertInfo.getInstance().show();
            } catch (Exception e) {
                AlertError.getInstance().setHeaderText("Fournisseur");
                AlertError.getInstance().setContentText("Ce fournisseur ne peut pas être supprimé. Contactez l'administrateur de votre système.");
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }
        }
    }

    @FXML
    void article_montant(KeyEvent event) {
        if(!article_quantite.getText().isEmpty())
            article_prix.setText(String.valueOf(Double.parseDouble(article_montant.getText().trim()) / Integer.parseInt(article_quantite.getText().trim())));
    }

    @FXML
    void article_prix(KeyEvent event) {
        if(!article_quantite.getText().isEmpty())
            article_montant.setText(String.valueOf(Integer.parseInt(article_quantite.getText().trim()) * Double.parseDouble(article_prix.getText().trim())));
    }

    @FXML
    void article_quantite(KeyEvent event) {
        if(!article_prix.getText().isEmpty()) {
            article_montant.setText(String.valueOf(Integer.parseInt(article_quantite.getText().trim()) * Double.parseDouble(article_prix.getText().trim())));
        }else if(!article_montant.getText().isEmpty()){
            article_prix.setText(String.valueOf(Double.parseDouble(article_montant.getText().trim()) / Integer.parseInt(article_quantite.getText().trim())));
        }
    }

    void emptyField() {
        article_designation.setText("");
        article_prix.setText("");
        article_quantite.setText("");
        article_montant.setText("");
    }
    
    void reset_commande_form() {
        emptyField();
        table_fourniture.setItems(null);
        reparation.setItems(null);
        fournisseur.setItems(null);
        ligne_commande.setItems(null);
        fournitures.clear();
        lignecommandes.clear();
    }
    
    void reset_livraison_form() {
        emptyField();
        table_article.setItems(null);
        commande.setItems(null);
        ligne_livraison.setItems(null);
        fournitureCommandes.clear();
        lignelivraisons.clear();
    }
    
    @FXML
    void cancel_commande(ActionEvent event) {
        reset_commande_form();
        AlertInfo.getInstance().setTitle("Info");
        AlertInfo.getInstance().setHeaderText("Commande annulée");
        AlertInfo.getInstance().show();
    }
    
    @FXML
    void cancel_livraison(ActionEvent event) {
        reset_livraison_form();
    }
    
    @FXML
    void commander(ActionEvent event) {
        Fourniture fourniture = fournitureCopy;
        fourniture.setDesignation(article_designation.getText().trim());
        fourniture.setPrix(Double.parseDouble(article_prix.getText().trim()));
        fourniture.setQuantite(Integer.parseInt(article_quantite.getText().trim()));
        fourniture.setMontant(Double.parseDouble(article_montant.getText().trim()));

        lignecommandes.add(fourniture);

        if (table_fourniture.getItems().contains(fournitureCopy)) {
            ligne_commande.setItems(lignecommandes);
            table_fourniture.getItems().remove(fournitureCopy);
        }

        emptyField();
        AlertInfo.getInstance().setTitle("Info");
        AlertInfo.getInstance().setHeaderText("Ajout d'une ligne commande.");
        AlertInfo.getInstance().show();
    }
    
    @FXML
    void create_commande(ActionEvent event) {
        Commande cmd = new Commande();
        cmd.setFournitures(fournitureCommandes);
        cmd.setDateCreation(new Date());
        cmd.setFournisseur(fournisseur.getValue());
        cmd.setFournitures(lignecommandes);

        try {
            CommandeRepository commandeRepository = new CommandeRepository();
            long id = commandeRepository.save(cmd);
            cmd = commandeRepository.findById(id);

            FournitureRepository fournitureRepository = new FournitureRepository();

            for (Fourniture fourniture:lignecommandes) {
                fourniture.setOrdered(true);
                fourniture.setCommande(cmd);
                fournitureRepository.update(fourniture);
            }

            Report.getInstance().createReport("/jrxml/commande.jrxml",(int)id);
            AlertInfo.getInstance().setTitle("Info");
            AlertInfo.getInstance().setHeaderText("Commande de fourniture");
            AlertInfo.getInstance().setContentText("Nouvelle commande enregistrée avec succès.");
            AlertInfo.getInstance().show();
            reset_commande_form();

        } catch (Exception e) {
            AlertError.getInstance().setTitle("Erreur");
            AlertError.getInstance().setHeaderText("Commande de fourniture");
            AlertError.getInstance().setContentText("Echec d'enregistrement de la commande.\n"+e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
    }
    
    @FXML
    void create_livraison(ActionEvent event) {
        Livraison livraison = new Livraison();
        livraison.setCommande(commande.getValue());
        livraison.setDateCreation(new Date());

        try {
            LivraisonRepository livraisonRepository = new LivraisonRepository();
            long id = livraisonRepository.save(livraison);
            livraison = livraisonRepository.findById(id);

            FournitureRepository fournitureRepository = new FournitureRepository();

            for (Fourniture fourniture:lignelivraisons) {
                fourniture.setDelivered(true);
                fourniture.setLivraison(livraison);
                fournitureRepository.update(fourniture);
            }
            Report.getInstance().createReport("/jrxml/livraison.jrxml",(int)id);
            AlertInfo.getInstance().setTitle("Info");
            AlertInfo.getInstance().setHeaderText("Livraison enregistrée avec succès.");
            AlertInfo.getInstance().show();
            reset_livraison_form();
        } catch (Exception e) {
            AlertError.getInstance().setTitle("Erreur");
            AlertError.getInstance().setHeaderText("Echec d'enregistrement de la commande.");
            AlertError.getInstance().setContentText(e.getMessage());
            AlertError.getInstance().show();
            e.printStackTrace();
        }
    }
    
    @FXML
    void receptionner(ActionEvent event) {
        FournitureRepository fournitureRepository = new FournitureRepository();
        Fourniture fourniture = fournitureRepository.findById(fournitureCopy.getId());
        lignelivraisons.add(fourniture);

        if (table_article.getItems().contains(fournitureCopy)) {
            ligne_livraison.setItems(lignelivraisons);
            table_article.getItems().remove(fournitureCopy);
        }
        emptyField();
        AlertInfo.getInstance().setTitle("Info");
        AlertInfo.getInstance().setHeaderText("Ajout d'une ligne livraison.");
        AlertInfo.getInstance().show();
    }
    
    @FXML
    void search_commande(KeyEvent event) {
        FilteredList<Commande> items = new FilteredList<>(FXCollections.observableArrayList(allCommande));
        items.setPredicate(item ->{
            String lower = commande.getEditor().getText().toLowerCase();
            String upper = commande.getEditor().getText().toUpperCase();

            if(item.getFournitures().get(0).getReparation().getDossier().getAutomobile().getImmatriculation().contains(upper) || item.getFournitures().get(0).getReparation().getDossier().getAutomobile().getImmatriculation().contains(lower))
                return true;
            else
                return item.getFournitures().get(0).getReparation().getDossier().getAutomobile().getImmatriculation().contains(upper) || item.getFournitures().get(0).getReparation().getDossier().getAutomobile().getImmatriculation().contains(lower);
        });
        SortedList<Commande> sorted = new SortedList<>(items);
        commande.setItems(FXCollections.observableArrayList(sorted));
    }
    
    @FXML
    void search_fournisseur(KeyEvent event) {
        FilteredList<Fournisseur> items = new FilteredList<>(FXCollections.observableArrayList(allFournisseur));
        items.setPredicate(item ->{
            String lower = fournisseur.getEditor().getText().toLowerCase();
            String upper = fournisseur.getEditor().getText().toUpperCase();
            if(item.getRaisonSociale().contains(lower))
                return true;
            else
                return item.getRaisonSociale().contains(upper);
        });
        SortedList<Fournisseur> sorted = new SortedList<>(items);
        fournisseur.setItems(sorted);
    }
    
    @FXML
    void search_reparation(KeyEvent event) {
        FilteredList<Reparation> items = new FilteredList<>(FXCollections.observableArrayList(allReparation));
        items.setPredicate(item ->{
            String lower = reparation.getEditor().getText().toLowerCase();
            String upper = reparation.getEditor().getText().toUpperCase();
            if(item.getDossier().getAutomobile().getImmatriculation().contains(lower))
                return true;
            else
                return item.getDossier().getAutomobile().getImmatriculation().contains(upper);
        });
        SortedList<Reparation> sorted = new SortedList<>(items);
        reparation.setItems(sorted);
    }
    
    @FXML
    void select_article(MouseEvent event) {
        fournitureCopy = table_article.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    void select_fourniture(MouseEvent event) {
        fournitureCopy = table_fourniture.getSelectionModel().getSelectedItem();
        article_designation.setText(fournitureCopy.getDesignation());
        article_prix.setText(String.valueOf(fournitureCopy.getPrix()));
        article_quantite.setText(String.valueOf(fournitureCopy.getQuantite()));
        article_montant.setText(String.valueOf(fournitureCopy.getMontant()));
    }

    public void selectTab(int i) {
        article_tab.getSelectionModel().select(getTab(i));
    }

    public Tab getTab(int i) {
        Tab tab = null;
        switch (i) {
            case 1: {
                tab = tab_fournisseur;
                break;
            }
            case 2: {
                tab = tab_commande;
                break;
            }
            case 3: {
                tab = tab_livraison;
                break;
            }
        }
        return tab;
    }

    void read_fournisseur(){
        FournisseurRepository fournisseurRepository = new FournisseurRepository();
        allFournisseur = fournisseurRepository.findAll();
        table_fournisseur.setItems(FXCollections.observableArrayList(allFournisseur));
        fournisseur.setItems(FXCollections.observableArrayList(allFournisseur));
    }

    void read_commande(){
        CommandeRepository commandeRepository = new CommandeRepository();
        allCommande = commandeRepository.findAll();
        commande.setItems(FXCollections.observableArrayList(allCommande));
    }

    void read_reparation(){
        ReparationRepository reparationRepository = new ReparationRepository();
        allReparation = reparationRepository.findAll();
        reparation.setItems(FXCollections.observableArrayList(allReparation));
    }

    public void initialize(URL location, ResourceBundle resources) {

        read_fournisseur();

        read_reparation();

        read_commande();

        reparation.setConverter(new StringConverter<Reparation>() {
            @Override
            public String toString(Reparation object) {
                if(object==null){
                    return null;
                }
                return object.toString();
            }

            @Override
            public Reparation fromString(String string) {
                String[] data = string.split("\\s");
                String id = data[0].trim();
                ReparationRepository reparationRepository = new ReparationRepository();
                return reparationRepository.findById(Long.parseLong(id));
            }
        });

        commande.setConverter(new StringConverter<Commande>() {
            @Override
            public String toString(Commande object) {
                if(object==null){
                    return null;
                }
                return object.toString();
            }

            @Override
            public Commande fromString(String string) {
                String[] data = string.split("\\s");
                String id = data[0].trim();
                CommandeRepository commandeRepository = new CommandeRepository();
                return commandeRepository.findById(Long.parseLong(id));
            }
        });

        fournisseur.setConverter(new StringConverter<Fournisseur>() {
            @Override
            public String toString(Fournisseur object) {
                if(object==null){
                    return null;
                }
                return object.toString();
            }

            @Override
            public Fournisseur fromString(String string) {
                String[] data = string.split("\\s");
                String id = data[0].trim();
                FournisseurRepository fournisseurRepository = new FournisseurRepository();
                return fournisseurRepository.findById(Long.parseLong(id));
            }
        });

        article_tab.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                if (tab.getTabPane().getSelectionModel().getSelectedIndex() == 1) {
                    read_fournisseur();
                }
            }
        });

        t_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        t_raisonSociale.setCellValueFactory(new PropertyValueFactory<>("raisonSociale"));
        t_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        t_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        montant.setCellValueFactory(new PropertyValueFactory<>("montant"));

        ligne_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ligne_designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        ligne_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        ligne_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        ligne_montant.setCellValueFactory(new PropertyValueFactory<>("montant"));

        commande_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        commande_designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        commande_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        commande_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        ligne_livraison_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ligne_livraison_designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        ligne_livraison_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        ligne_livraison_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        reparation.addEventHandler(ActionEvent.ACTION, event -> {
            if (reparation.getSelectionModel().getSelectedIndex() != -1) {
                fournitures.clear();
                for (Fourniture fourniture:reparation.getValue().getFournitures()) {
                    if(!fourniture.getOrdered()){
                        fournitures.add(fourniture);
                    }
                }
                table_fourniture.setItems(fournitures);
            }
        });

        commande.addEventHandler(ActionEvent.ACTION, event -> {
            if (commande.getSelectionModel().getSelectedIndex() != -1) {
                fournitureCommandes.clear();
                for (Fourniture fourniture:commande.getValue().getFournitures()) {
                    if(!fourniture.getDelivered()){
                        fournitureCommandes.add(fourniture);
                    }
                }
                table_article.setItems(fournitureCommandes);
            }
        });
    }
}
