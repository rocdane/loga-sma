package com.loga.app.controller;

import com.loga.api.Repair;
import com.loga.app.dao.DossierRepository;
import com.loga.app.dao.ProfileRepository;
import com.loga.app.dao.ReceptionRepository;
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
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class ReceptionController implements Initializable
{
    Reception reception = new Reception();
    Diagnostic diagnostic = new Diagnostic();
    ObservableList<Etat> newEtats;
    ObservableList<Etat> list_etat;
    AtomicInteger rowNbr;
    ObservableList<Defaut> list_defaut;
    ObservableList<Solution> list_solution;
    List<Profile> allProfile;
    List<Dossier> allDossier;
    long dossierID;
    long receptionID;

    String etat111="OK",etat112="OK",etat113="OK",etat114="OK",etat115="OK",etat116="OK",etat117="OK",etat221="OK",etat222="OK",etat223="OK",etat224="OK",etat225="OK",
            etat331="OK",etat332="OK",etat333="OK",etat334="OK",etat441="OK",etat442="OK",etat443="OK",etat444="OK",etat511="OK",etat512="OK",etat513="OK",etat514="OK",
            etat521="OK",etat522="OK",etat523="OK",etat524="OK",etat531="OK",etat532="OK",etat533="OK",etat534="OK",etat541="OK",etat542="OK",etat543="OK",etat544="OK",
            etat661="OK",etat662="OK",etat663="OK",etat664="OK",etat665="OK";

    @FXML
    private TextField etat7;

    @FXML
    private TextArea etat8;

    @FXML
    private TextArea etat9;

    @FXML
    private TextField compteur;

    @FXML
    private TitledPane etat1;

    @FXML
    private Label etat111_description;

    @FXML
    private Label etat112_description;

    @FXML
    private Label etat113_description;

    @FXML
    private Label etat114_description;

    @FXML
    private Label etat115_description;

    @FXML
    private Label etat116_description;

    @FXML
    private Label etat117_description;

    @FXML
    private RadioButton etat111_ok;

    @FXML
    private RadioButton etat111_warning;

    @FXML
    private RadioButton etat111_danger;

    @FXML
    private RadioButton etat112_ok;

    @FXML
    private RadioButton etat112_warning;

    @FXML
    private RadioButton etat112_danger;

    @FXML
    private RadioButton etat113_ok;

    @FXML
    private RadioButton etat113_warning;

    @FXML
    private RadioButton etat113_danger;

    @FXML
    private RadioButton etat114_ok;

    @FXML
    private RadioButton etat114_warning;

    @FXML
    private RadioButton etat114_danger;

    @FXML
    private RadioButton etat115_ok;

    @FXML
    private RadioButton etat115_warning;

    @FXML
    private RadioButton etat115_danger;

    @FXML
    private RadioButton etat116_ok;

    @FXML
    private RadioButton etat116_warning;

    @FXML
    private RadioButton etat116_danger;

    @FXML
    private RadioButton etat117_ok;

    @FXML
    private RadioButton etat117_warning;

    @FXML
    private RadioButton etat117_danger;

    @FXML
    private TitledPane etat2;

    @FXML
    private RadioButton etat222_ok;

    @FXML
    private RadioButton etat222_warning;

    @FXML
    private RadioButton etat222_danger;

    @FXML
    private RadioButton etat223_ok;

    @FXML
    private RadioButton etat223_warning;

    @FXML
    private RadioButton etat223_danger;

    @FXML
    private RadioButton etat224_ok;

    @FXML
    private RadioButton etat224_warning;

    @FXML
    private RadioButton etat224_danger;

    @FXML
    private RadioButton etat225_ok;

    @FXML
    private RadioButton etat225_warning;

    @FXML
    private RadioButton etat225_danger;

    @FXML
    private RadioButton etat221_ok;

    @FXML
    private RadioButton etat221_warning;

    @FXML
    private RadioButton etat221_danger;

    @FXML
    private TitledPane etat3;

    @FXML
    private RadioButton etat331_ok;

    @FXML
    private RadioButton etat331_warning;

    @FXML
    private RadioButton etat331_danger;

    @FXML
    private RadioButton etat332_ok;

    @FXML
    private RadioButton etat332_warning;

    @FXML
    private RadioButton etat332_danger;

    @FXML
    private RadioButton etat333_ok;

    @FXML
    private RadioButton etat333_warning;

    @FXML
    private RadioButton etat333_danger;

    @FXML
    private RadioButton etat334_ok;

    @FXML
    private RadioButton etat334_warning;

    @FXML
    private RadioButton etat334_danger;

    @FXML
    private TitledPane etat4;

    @FXML
    private RadioButton etat441_ok;

    @FXML
    private RadioButton etat441_warning;

    @FXML
    private RadioButton etat441_danger;

    @FXML
    private RadioButton etat442_ok;

    @FXML
    private RadioButton etat442_warning;

    @FXML
    private RadioButton etat442_danger;

    @FXML
    private RadioButton etat443_ok;

    @FXML
    private RadioButton etat443_warning;

    @FXML
    private RadioButton etat443_danger;

    @FXML
    private RadioButton etat444_ok;

    @FXML
    private RadioButton etat444_warning;

    @FXML
    private RadioButton etat444_danger;

    @FXML
    private TitledPane etat5;

    @FXML
    private RadioButton etat511_ok;

    @FXML
    private RadioButton etat511_warning;

    @FXML
    private RadioButton etat511_danger;

    @FXML
    private RadioButton etat512_ok;

    @FXML
    private RadioButton etat512_warning;

    @FXML
    private RadioButton etat512_danger;

    @FXML
    private RadioButton etat513_ok;

    @FXML
    private RadioButton etat513_warning;

    @FXML
    private RadioButton etat513_danger;

    @FXML
    private RadioButton etat514_ok;

    @FXML
    private RadioButton etat514_warning;

    @FXML
    private RadioButton etat514_danger;

    @FXML
    private RadioButton etat521_ok;

    @FXML
    private RadioButton etat521_warning;

    @FXML
    private RadioButton etat521_danger;

    @FXML
    private RadioButton etat522_ok;

    @FXML
    private RadioButton etat522_warning;

    @FXML
    private RadioButton etat522_danger;

    @FXML
    private RadioButton etat523_ok;

    @FXML
    private RadioButton etat523_warning;

    @FXML
    private RadioButton etat523_danger;

    @FXML
    private RadioButton etat524_ok;

    @FXML
    private RadioButton etat524_warning;

    @FXML
    private RadioButton etat524_danger;

    @FXML
    private RadioButton etat531_ok;

    @FXML
    private RadioButton etat531_warning;

    @FXML
    private RadioButton etat531_danger;

    @FXML
    private RadioButton etat532_ok;

    @FXML
    private RadioButton etat532_warning;

    @FXML
    private RadioButton etat532_danger;

    @FXML
    private RadioButton etat533_ok;

    @FXML
    private RadioButton etat533_warning;

    @FXML
    private RadioButton etat533_danger;

    @FXML
    private RadioButton etat534_ok;

    @FXML
    private RadioButton etat534_warning;

    @FXML
    private RadioButton etat534_danger;

    @FXML
    private RadioButton etat541_ok;

    @FXML
    private RadioButton etat541_warning;

    @FXML
    private RadioButton etat541_danger;

    @FXML
    private RadioButton etat542_ok;

    @FXML
    private RadioButton etat542_warning;

    @FXML
    private RadioButton etat542_danger;

    @FXML
    private RadioButton etat544_ok;

    @FXML
    private RadioButton etat544_warning;

    @FXML
    private RadioButton etat544_danger;

    @FXML
    private RadioButton etat543_ok;

    @FXML
    private RadioButton etat543_warning;

    @FXML
    private RadioButton etat543_danger;

    @FXML
    private TitledPane etat6;

    @FXML
    private RadioButton etat665_ok;

    @FXML
    private RadioButton etat665_warning;

    @FXML
    private RadioButton etat665_danger;

    @FXML
    private RadioButton etat664_ok;

    @FXML
    private RadioButton etat664_warning;

    @FXML
    private RadioButton etat664_danger;

    @FXML
    private RadioButton etat663_ok;

    @FXML
    private RadioButton etat663_warning;

    @FXML
    private RadioButton etat663_danger;

    @FXML
    private RadioButton etat662_ok;

    @FXML
    private RadioButton etat662_warning;

    @FXML
    private RadioButton etat662_danger;

    @FXML
    private RadioButton etat661_ok;

    @FXML
    private RadioButton etat661_warning;

    @FXML
    private RadioButton etat661_danger;

    @FXML
    private AnchorPane content;
    @FXML
    private TabPane receptionTab;
    @FXML
    private Tab tab_reception;
    @FXML
    private AnchorPane newTabContent;
    @FXML
    private ComboBox<Profile> profile_diagnostic;
    @FXML
    private ComboBox<Profile> profile_reception;
    @FXML
    private ComboBox<Dossier> dossierAutomobile;
    @FXML
    private Tab tab_diagnostic;
    @FXML
    private AnchorPane detailTabContent;
    @FXML
    private ComboBox<Dossier> reception_diagnostic;
    @FXML
    private TextArea solution_diagnostic;
    @FXML
    private TextField diagnostic_compteur;
    @FXML
    private ComboBox<Defaut.Groupe> libelle_defaut;
    @FXML
    private TextArea description_defaut;
    @FXML
    private TableView<Defaut> table_defaut;
    @FXML
    private TableColumn<Defaut, String> defaut_libelle;
    @FXML
    private TableColumn<Defaut, String> defaut_description;
    @FXML
    private ComboBox<Solution.Groupe> libelle_solution;
    @FXML
    private TextArea description_solution;
    @FXML
    private TableView<Solution> table_solution;
    @FXML
    private TableColumn<Defaut, String> solution_libelle;
    @FXML
    private TableColumn<Defaut, String> solution_description;

    public ReceptionController() {
        newEtats = FXCollections.observableArrayList();
        list_etat = FXCollections.observableArrayList();
        rowNbr = new AtomicInteger(0);
        list_defaut = FXCollections.observableArrayList();
        list_solution = FXCollections.observableArrayList();
    }
    
    @FXML
    void add_diagnostic() {
        if (list_defaut.isEmpty()) {
            AlertInfo.getInstance().setTitle("Info");
            AlertInfo.getInstance().setHeaderText("Enregistrement diagnostic");
            AlertInfo.getInstance().setContentText("Le diagnostic doit comporter au moins un défaut");
            AlertInfo.getInstance().show();
        }
        else {
            Diagnostic diagnostic = new Diagnostic();
            diagnostic.setProfile(profile_diagnostic.getValue());
            diagnostic.setOuvrier(profile_diagnostic.getEditor().getText());
            diagnostic.setDateCreation(new Date());
            diagnostic.setDiagnostic(solution_diagnostic.getText());
            diagnostic.setCompteur(Integer.parseInt(diagnostic_compteur.getText().trim()));
            diagnostic.setDossier(reception_diagnostic.getValue());
            diagnostic.setDefauts(list_defaut);
            diagnostic.setSolutions(list_solution);

            try {
                diagnostic = Repair.getInstance().enregistrerDiagnostic(diagnostic);

                AlertInfo.getInstance().setTitle("Info");
                AlertInfo.getInstance().setHeaderText("Enregistrement diagnostic.");
                AlertInfo.getInstance().setContentText("Nouveau diagnostic enregistré avec succès.");
                AlertInfo.getInstance().show();

                Report.getInstance().createReport("/jrxml/diagnostic.jrxml",(int)diagnostic.getId());

                reset_diagnostic_form();
            } catch (Exception e) {
                AlertError.getInstance().setTitle("Erreur");
                AlertError.getInstance().setHeaderText("Enregistrement diagnostic.");
                AlertError.getInstance().setContentText("Echec lors de l'enregistrement du diagnostic.\n"+e.getMessage());
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }
        }
    }
    
    void reset_diagnostic_form() {
        reception_diagnostic.getEditor().setText("");
        dossierID=0;
        diagnostic_compteur.setText("");
        solution_diagnostic.setText("");
        description_solution.setText("");
        description_solution.setText("");
        list_defaut.clear();
        table_defaut.setItems(null);
        list_solution.clear();
        table_solution.setItems(null);
    }
    
    @FXML
    void cancel_diagnostic() {
        reset_diagnostic_form();
    }
    
    @FXML
    void add_defaut() {
        Defaut defaut = new Defaut("", libelle_defaut.getValue().name(), description_defaut.getText().trim());
        diagnostic.addDefaut(defaut);
        table_defaut.setItems(FXCollections.observableArrayList(diagnostic.getDefauts()));
        description_defaut.setText("");
    }
    
    @FXML
    void remove_defaut() {
        Defaut defaut = table_defaut.getSelectionModel().getSelectedItem();
        list_defaut.remove(defaut);
        table_defaut.setItems(list_defaut);
    }
    
    @FXML
    void add_solution() {
        Solution solution = new Solution("", libelle_solution.getValue().name(), description_solution.getText().trim());
        diagnostic.addSolution(solution);
        table_solution.setItems(FXCollections.observableArrayList(diagnostic.getSolutions()));
        description_solution.setText("");
    }
    
    @FXML
    void remove_solution() {
        Solution defaut = table_solution.getSelectionModel().getSelectedItem();
        list_solution.remove(defaut);
        table_solution.setItems(list_solution);
    }

    @FXML
    void cancel_reception(ActionEvent event) {
        reset_reception_form();
    }

    @FXML
    void save_reception(ActionEvent event) {
        Etat etat = new Etat("etat111","Siège avant gauche",etat111);
        newEtats.add(etat);

        etat=new Etat("etat112","Siège arrière gauche",etat112);
        newEtats.add(etat);

        etat=new Etat("etat113","Siège Avant droit",etat113);
        newEtats.add(etat);

        etat=new Etat("etat114","Siège arrière droit",etat114);
        newEtats.add(etat);

        etat=new Etat("etat115","Banquettes",etat115);
        newEtats.add(etat);

        etat=new Etat("etat116","Moquettes",etat116);
        newEtats.add(etat);

        etat=new Etat("etat117","Ciel de toit",etat117);
        newEtats.add(etat);

        etat=new Etat("etat221","Livret de bord",etat221);
        newEtats.add(etat);

        etat=new Etat("etat222","Kit de secours",etat222);
        newEtats.add(etat);

        etat=new Etat("etat223","Roue de secours",etat223);
        newEtats.add(etat);

        etat=new Etat("etat224","Triangle de signalisation",etat224);
        newEtats.add(etat);

        etat=new Etat("etat225","Extincteur",etat225);
        newEtats.add(etat);

        etat=new Etat("etat331","Courroies d'entraînement",etat331);
        newEtats.add(etat);

        etat=new Etat("etat332","Batterie / Câbles / Colliers",etat331);
        newEtats.add(etat);

        etat=new Etat("etat333","Etat du liquide de refroidissement",etat333);
        newEtats.add(etat);

        etat=new Etat("etat334","Radiateur / Durites / Colliers",etat334);
        newEtats.add(etat);

        etat=new Etat("etat441","Huile à moteur",etat441);
        newEtats.add(etat);

        etat=new Etat("etat442","Huile boîte de vitesses",etat442);
        newEtats.add(etat);

        etat=new Etat("etat443","Liquide de frein",etat443);
        newEtats.add(etat);

        etat=new Etat("etat444","Huile de direction assistée",etat444);
        newEtats.add(etat);

        etat=new Etat("etat511","Pneu Roue Avant Gauche",etat511);
        newEtats.add(etat);

        etat=new Etat("etat512","Disque Roue Avant Gauche",etat512);
        newEtats.add(etat);

        etat=new Etat("etat513","Etrier Roue Avant Gauche",etat513);
        newEtats.add(etat);

        etat=new Etat("etat514","Canalisation / flexibles Roue Avant Gauche",etat514);
        newEtats.add(etat);

        etat=new Etat("etat521","Pneu Roue Arrière Gauche",etat521);
        newEtats.add(etat);

        etat=new Etat("etat522","Disque Roue Arrière Gauche",etat522);
        newEtats.add(etat);

        etat=new Etat("etat523","Etrier Roue Arrière Gauche",etat523);
        newEtats.add(etat);

        etat=new Etat("etat524","Canalisation / flexibles Roue Arrière Gauche",etat524);
        newEtats.add(etat);

        etat=new Etat("etat531","Pneu Roue Avant Droit",etat531);
        newEtats.add(etat);

        etat=new Etat("etat532","Disque Roue Avant Droit",etat532);
        newEtats.add(etat);

        etat=new Etat("etat533","Etrier Roue Avant Droit",etat533);
        newEtats.add(etat);

        etat=new Etat("etat534","Canalisation / flexibles Roue Avant Droit",etat534);
        newEtats.add(etat);

        etat=new Etat("etat541","Pneu Roue Arrière Droit",etat541);
        newEtats.add(etat);

        etat=new Etat("etat542","Disque Roue Arrière Droit",etat542);
        newEtats.add(etat);

        etat=new Etat("etat543","Etrier Roue Arrière Droit",etat543);
        newEtats.add(etat);

        etat=new Etat("etat544","Canalisation / flexibles Roue Arrière Droit",etat544);
        newEtats.add(etat);

        etat=new Etat("etat661","Elements de la directions",etat661);
        newEtats.add(etat);

        etat=new Etat("etat662","Elements de la suspension",etat662);
        newEtats.add(etat);

        etat=new Etat("etat663","Amortisseurs",etat663);
        newEtats.add(etat);

        etat=new Etat("etat664","Jambes de suspension",etat664);
        newEtats.add(etat);

        etat=new Etat("etat665","Système d'échappement",etat665);
        newEtats.add(etat);

        etat = new Etat("etat7","Niveau de carburant",etat7.getText());
        newEtats.add(etat);

        Reception reception = new Reception();
        reception.setOuvrier(profile_reception.getEditor().getText());
        reception.setProfile(profile_reception.getValue());
        reception.setDateCreation(new Date());
        reception.setDossier(dossierAutomobile.getValue());
        reception.setEtats(newEtats);

        if(!etat8.getText().isEmpty())
            reception.setObservation(etat8.getText());

        if(!etat9.getText().isEmpty())
            reception.setCommentaire(etat9.getText());

        if(!profile_reception.getEditor().getText().isEmpty())
            reception.setOuvrier(profile_reception.getEditor().getText());

        if(!compteur.getText().isEmpty())
            reception.setCompteur(Integer.parseInt(compteur.getText().trim()));

        try {
            reception = Repair.getInstance().enregistrerReception(reception);
            AlertInfo.getInstance().setTitle("Info");
            AlertInfo.getInstance().setHeaderText("Réception automobile");
            AlertInfo.getInstance().setContentText("Réception enregistrée avec succès.");
            AlertInfo.getInstance().show();
            reset_reception_form();

            Report.getInstance().createReport("/jrxml/reception.jrxml",(int)reception.getId());

            AlertConfirm.getInstance().setTitle("Confirmation");
            AlertConfirm.getInstance().setHeaderText("Réception automobile");
            AlertConfirm.getInstance().setContentText("Réception enregistrée avec succès. Souhaitez-vous enregistrer un contrôle diagnostic pour le même véhicule?");
            if(AlertConfirm.getInstance().showAndWait().get().equals(ButtonType.OK)){
                diagnostic_compteur.setText(String.valueOf(reception.getCompteur()));
                profile_diagnostic.setValue(reception.getProfile());
                reception_diagnostic.setValue(reception.getDossier());
                selectTab(2);
            }else {
                reset_reception_form();
            }
        } catch (Exception e) {
            AlertInfo.getInstance().setTitle("Erreur");
            AlertInfo.getInstance().setHeaderText("Réception automobile");
            AlertInfo.getInstance().setContentText("Echec lors de l'enregistrement de la réception.\n"+e.getMessage());
            AlertInfo.getInstance().showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void search_dossier_reception(KeyEvent event){
        FilteredList<Dossier> items = new FilteredList<>(FXCollections.observableArrayList(allDossier));
        items.setPredicate(item ->{
            String lower = dossierAutomobile.getEditor().getText().toLowerCase();
            String upper = dossierAutomobile.getEditor().getText().toUpperCase();
            if(item.getAutomobile().getImmatriculation().contains(lower))
                return true;
            else
                return item.getAutomobile().getImmatriculation().contains(upper);
        });
        SortedList<Dossier> sorted = new SortedList<>(items);
        dossierAutomobile.setItems(FXCollections.observableArrayList(sorted));
    }

    @FXML
    void search_dossier_diagnostic(KeyEvent event){
        FilteredList<Dossier> items = new FilteredList<>(FXCollections.observableArrayList(allDossier));
        items.setPredicate(item ->{
            String lower = reception_diagnostic.getEditor().getText().toLowerCase();
            String upper = reception_diagnostic.getEditor().getText().toUpperCase();
            if(item.getAutomobile().getImmatriculation().contains(lower))
                return true;
            else
                return item.getAutomobile().getImmatriculation().contains(upper);
        });
        SortedList<Dossier> sorted = new SortedList<>(items);
        reception_diagnostic.setItems(FXCollections.observableArrayList(sorted));
    }

    @FXML
    void search_profile_diagnostic(KeyEvent event){
        FilteredList<Profile> items = new FilteredList<>(FXCollections.observableArrayList(allProfile));
        items.setPredicate(item ->{
            String lower = profile_diagnostic.getEditor().getText().toLowerCase();
            String upper = profile_diagnostic.getEditor().getText().toUpperCase();
            if(item.getNom().contains(lower) || item.getPrenom().contains(lower))
                return true;
            else
                return item.getNom().contains(upper) || item.getPrenom().contains(upper);
        });
        SortedList<Profile> sorted = new SortedList<>(items);
        profile_diagnostic.setItems(FXCollections.observableArrayList(sorted));
    }

    @FXML
    void search_profile_reception(KeyEvent event){
        FilteredList<Profile> items = new FilteredList<>(FXCollections.observableArrayList(allProfile));
        items.setPredicate(item ->{
            String lower = profile_reception.getEditor().getText().toLowerCase();
            String upper = profile_reception.getEditor().getText().toUpperCase();
            if(item.getNom().contains(lower) || item.getPrenom().contains(lower))
                return true;
            else
                return item.getNom().contains(upper) || item.getPrenom().contains(upper);
        });
        SortedList<Profile> sorted = new SortedList<>(items);
        profile_reception.setItems(FXCollections.observableArrayList(sorted));
    }

    @FXML
    void etat111_danger(MouseEvent event) {
        if(etat111_danger.isSelected()){
            etat111_ok.setSelected(false);
            etat111_warning.setSelected(false);
            etat111="MAUVAIS";
        }
    }

    @FXML
    void etat111_ok(MouseEvent event) {
        if(etat111_ok.isSelected()){
            etat111_danger.setSelected(false);
            etat111_warning.setSelected(false);
            etat111="OK";
        }
    }

    @FXML
    void etat111_warning(MouseEvent event) {
        if(etat111_warning.isSelected()){
            etat111_danger.setSelected(false);
            etat111_ok.setSelected(false);
            etat111="ATTENTION";
        }
    }

    @FXML
    void etat112_danger(MouseEvent event) {
        if(etat112_danger.isSelected()){
            etat112_ok.setSelected(false);
            etat112_warning.setSelected(false);
            etat112="MAUVAIS";
        }
    }

    @FXML
    void etat112_ok(MouseEvent event) {
        if(etat112_ok.isSelected()){
            etat112_danger.setSelected(false);
            etat112_warning.setSelected(false);
            etat112="OK";
        }
    }

    @FXML
    void etat112_warning(MouseEvent event) {
        if(etat112_warning.isSelected()){
            etat112_danger.setSelected(false);
            etat112_ok.setSelected(false);
            etat112="ATTENTION";
        }
    }

    @FXML
    void etat113_danger(MouseEvent event) {
        if(etat113_danger.isSelected()){
            etat113_ok.setSelected(false);
            etat113_warning.setSelected(false);
            etat113="MAUVAIS";
        }
    }

    @FXML
    void etat113_ok(MouseEvent event) {
        if(etat113_ok.isSelected()){
            etat113_danger.setSelected(false);
            etat113_warning.setSelected(false);
            etat113="OK";
        }
    }

    @FXML
    void etat113_warning(MouseEvent event) {
        if(etat113_warning.isSelected()){
            etat113_danger.setSelected(false);
            etat113_ok.setSelected(false);
            etat113="ATTENTION";
        }
    }

    @FXML
    void etat114_danger(MouseEvent event) {
        if(etat114_danger.isSelected()){
            etat114_ok.setSelected(false);
            etat114_warning.setSelected(false);
            etat114="MAUVAIS";
        }
    }

    @FXML
    void etat114_ok(MouseEvent event) {
        if(etat114_ok.isSelected()){
            etat114_danger.setSelected(false);
            etat114_warning.setSelected(false);
            etat114="OK";
        }
    }

    @FXML
    void etat114_warning(MouseEvent event) {
        if(etat114_warning.isSelected()){
            etat114_danger.setSelected(false);
            etat114_ok.setSelected(false);
            etat114="ATTENTION";
        }
    }

    @FXML
    void etat115_danger(MouseEvent event) {
        if(etat115_danger.isSelected()){
            etat115_ok.setSelected(false);
            etat115_warning.setSelected(false);
            etat115="MAUVAIS";
        }
    }

    @FXML
    void etat115_ok(MouseEvent event) {
        if(etat115_ok.isSelected()){
            etat115_danger.setSelected(false);
            etat115_warning.setSelected(false);
            etat115="OK";
        }
    }

    @FXML
    void etat115_warning(MouseEvent event) {
        if(etat115_warning.isSelected()){
            etat115_danger.setSelected(false);
            etat115_ok.setSelected(false);
            etat115="ATTENTION";
        }
    }

    @FXML
    void etat116_danger(MouseEvent event) {
        if(etat116_danger.isSelected()){
            etat116_ok.setSelected(false);
            etat116_warning.setSelected(false);
            etat116="MAUVAIS";
        }
    }

    @FXML
    void etat116_ok(MouseEvent event) {
        if(etat116_ok.isSelected()){
            etat116_danger.setSelected(false);
            etat116_warning.setSelected(false);
            etat116="OK";
        }
    }

    @FXML
    void etat116_warning(MouseEvent event) {
        if(etat116_warning.isSelected()){
            etat116_danger.setSelected(false);
            etat116_ok.setSelected(false);
            etat116="ATTENTION";
        }
    }

    @FXML
    void etat117_danger(MouseEvent event) {
        if(etat117_danger.isSelected()){
            etat117_ok.setSelected(false);
            etat117_warning.setSelected(false);
            etat117="MAUVAIS";
        }
    }

    @FXML
    void etat117_ok(MouseEvent event) {
        if(etat117_ok.isSelected()){
            etat117_danger.setSelected(false);
            etat117_warning.setSelected(false);
            etat117="OK";
        }
    }

    @FXML
    void etat117_warning(MouseEvent event) {
        if(etat117_warning.isSelected()){
            etat117_danger.setSelected(false);
            etat117_ok.setSelected(false);
            etat117="ATTENTION";
        }
    }

    @FXML
    void etat221_danger(MouseEvent event) {
        if(etat221_danger.isSelected()){
            etat221_ok.setSelected(false);
            etat221_warning.setSelected(false);
            etat221="MAUVAIS";
        }
    }

    @FXML
    void etat221_ok(MouseEvent event) {
        if(etat221_ok.isSelected()){
            etat221_danger.setSelected(false);
            etat221_warning.setSelected(false);
            etat221="OK";
        }
    }

    @FXML
    void etat221_warning(MouseEvent event) {
        if(etat221_warning.isSelected()){
            etat221_danger.setSelected(false);
            etat221_ok.setSelected(false);
            etat221="ATTENTION";
        }
    }

    @FXML
    void etat222_danger(MouseEvent event) {
        if(etat222_danger.isSelected()){
            etat222_ok.setSelected(false);
            etat222_warning.setSelected(false);
            etat222="MAUVAIS";
        }
    }

    @FXML
    void etat222_ok(MouseEvent event) {
        if(etat222_ok.isSelected()){
            etat222_danger.setSelected(false);
            etat222_warning.setSelected(false);
            etat222="OK";
        }
    }

    @FXML
    void etat222_warning(MouseEvent event) {
        if(etat222_warning.isSelected()){
            etat222_danger.setSelected(false);
            etat222_ok.setSelected(false);
            etat222="ATTENTION";
        }
    }

    @FXML
    void etat223_danger(MouseEvent event) {
        if(etat223_danger.isSelected()){
            etat223_ok.setSelected(false);
            etat223_warning.setSelected(false);
            etat223="MAUVAIS";
        }
    }

    @FXML
    void etat223_ok(MouseEvent event) {
        if(etat223_ok.isSelected()){
            etat223_danger.setSelected(false);
            etat223_warning.setSelected(false);
            etat223="OK";
        }
    }

    @FXML
    void etat223_warning(MouseEvent event) {
        if(etat223_warning.isSelected()){
            etat223_danger.setSelected(false);
            etat223_ok.setSelected(false);
            etat223="ATTENTION";
        }
    }

    @FXML
    void etat224_danger(MouseEvent event) {
        if(etat224_danger.isSelected()){
            etat224_ok.setSelected(false);
            etat224_warning.setSelected(false);
            etat224="MAUVAIS";
        }
    }

    @FXML
    void etat224_ok(MouseEvent event) {
        if(etat224_ok.isSelected()){
            etat224_danger.setSelected(false);
            etat224_warning.setSelected(false);
            etat224="OK";
        }
    }

    @FXML
    void etat224_warning(MouseEvent event) {
        if(etat224_warning.isSelected()){
            etat224_danger.setSelected(false);
            etat224_ok.setSelected(false);
            etat224="ATTENTION";
        }
    }

    @FXML
    void etat225_danger(MouseEvent event) {
        if(etat225_danger.isSelected()){
            etat225_ok.setSelected(false);
            etat225_warning.setSelected(false);
            etat225="MAUVAIS";
        }
    }

    @FXML
    void etat225_ok(MouseEvent event) {
        if(etat225_ok.isSelected()){
            etat225_danger.setSelected(false);
            etat225_warning.setSelected(false);
            etat225="OK";
        }
    }

    @FXML
    void etat225_warning(MouseEvent event) {
        if(etat225_warning.isSelected()){
            etat225_danger.setSelected(false);
            etat225_ok.setSelected(false);
            etat225="ATTENTION";
        }
    }

    @FXML
    void etat331_danger(MouseEvent event) {
        if(etat331_danger.isSelected()){
            etat331_warning.setSelected(false);
            etat331_ok.setSelected(false);
            etat331="MAUVAIS";
        }
    }

    @FXML
    void etat331_ok(MouseEvent event) {
        if(etat331_ok.isSelected()){
            etat331_danger.setSelected(false);
            etat331_warning.setSelected(false);
            etat331="OK";
        }
    }

    @FXML
    void etat331_warning(MouseEvent event) {
        if(etat331_warning.isSelected()){
            etat331_danger.setSelected(false);
            etat331_ok.setSelected(false);
            etat331="ATTENTION";
        }
    }

    @FXML
    void etat332_danger(MouseEvent event) {
        if(etat332_danger.isSelected()){
            etat332_warning.setSelected(false);
            etat332_ok.setSelected(false);
            etat332="MAUVAIS";
        }
    }

    @FXML
    void etat332_ok(MouseEvent event) {
        if(etat332_ok.isSelected()){
            etat332_warning.setSelected(false);
            etat332_danger.setSelected(false);
            etat332="OK";
        }
    }

    @FXML
    void etat332_warning(MouseEvent event) {
        if(etat332_warning.isSelected()){
            etat332_danger.setSelected(false);
            etat332_ok.setSelected(false);
            etat332="ATTENTION";
        }
    }

    @FXML
    void etat333_danger(MouseEvent event) {
        if(etat333_danger.isSelected()){
            etat333_warning.setSelected(false);
            etat333_ok.setSelected(false);
            etat333="MAUVAIS";
        }
    }

    @FXML
    void etat333_ok(MouseEvent event) {
        if(etat333_ok.isSelected()){
            etat333_warning.setSelected(false);
            etat333_danger.setSelected(false);
            etat333="OK";
        }
    }

    @FXML
    void etat333_warning(MouseEvent event) {
        if(etat333_warning.isSelected()){
            etat333_danger.setSelected(false);
            etat333_ok.setSelected(false);
            etat333="ATTENTION";
        }
    }

    @FXML
    void etat334_danger(MouseEvent event) {
        if(etat334_danger.isSelected()){
            etat334_warning.setSelected(false);
            etat334_ok.setSelected(false);
            etat334="MAUVAIS";
        }
    }

    @FXML
    void etat334_ok(MouseEvent event) {
        if(etat334_ok.isSelected()){
            etat334_danger.setSelected(false);
            etat334_warning.setSelected(false);
            etat334="OK";
        }
    }

    @FXML
    void etat334_warning(MouseEvent event) {
        if(etat334_warning.isSelected()){
            etat334_danger.setSelected(false);
            etat334_ok.setSelected(false);
            etat334="ATTENTION";
        }
    }

    @FXML
    void etat441_danger(MouseEvent event) {
        if(etat441_danger.isSelected()){
            etat441_warning.setSelected(false);
            etat441_ok.setSelected(false);
            etat441="MAUVAIS";
        }
    }

    @FXML
    void etat441_ok(MouseEvent event) {
        if(etat441_ok.isSelected()){
            etat441_warning.setSelected(false);
            etat441_danger.setSelected(false);
            etat441="OK";
        }
    }

    @FXML
    void etat441_warning(MouseEvent event) {
        if(etat441_warning.isSelected()){
            etat441_ok.setSelected(false);
            etat441_danger.setSelected(false);
            etat441="ATTENTION";
        }
    }

    @FXML
    void etat442_danger(MouseEvent event) {
        if(etat442_danger.isSelected()){
            etat442_warning.setSelected(false);
            etat442_ok.setSelected(false);
            etat442="MAUVAIS";
        }
    }

    @FXML
    void etat442_ok(MouseEvent event) {
        if(etat442_ok.isSelected()){
            etat442_warning.setSelected(false);
            etat442_danger.setSelected(false);
            etat442="OK";
        }
    }

    @FXML
    void etat442_warning(MouseEvent event) {
        if(etat442_warning.isSelected()){
            etat442_ok.setSelected(false);
            etat442_danger.setSelected(false);
            etat442="ATTENTION";
        }
    }

    @FXML
    void etat443_danger(MouseEvent event) {
        if(etat443_danger.isSelected()){
            etat443_warning.setSelected(false);
            etat443_ok.setSelected(false);
            etat443="MAUVAIS";
        }
    }

    @FXML
    void etat443_ok(MouseEvent event) {
        if(etat443_ok.isSelected()){
            etat443_warning.setSelected(false);
            etat443_danger.setSelected(false);
            etat443="OK";
        }
    }

    @FXML
    void etat443_warning(MouseEvent event) {
        if(etat443_warning.isSelected()){
            etat443_danger.setSelected(false);
            etat443_ok.setSelected(false);
            etat443="ATTENTION";
        }
    }

    @FXML
    void etat444_danger(MouseEvent event) {
        if(etat444_danger.isSelected()){
            etat444_warning.setSelected(false);
            etat444_ok.setSelected(false);
            etat444="MAUVAIS";
        }
    }

    @FXML
    void etat444_ok(MouseEvent event) {
        if(etat444_ok.isSelected()){
            etat444_warning.setSelected(false);
            etat444_danger.setSelected(false);
            etat444="OK";
        }
    }

    @FXML
    void etat444_warning(MouseEvent event) {
        if(etat444_warning.isSelected()){
            etat444_danger.setSelected(false);
            etat444_ok.setSelected(false);
            etat444="ATTENTION";
        }
    }

    @FXML
    void etat511_danger(MouseEvent event) {
        if(etat511_danger.isSelected()){
            etat511_warning.setSelected(false);
            etat511_ok.setSelected(false);
            etat511="MAUVAIS";
        }
    }

    @FXML
    void etat511_ok(MouseEvent event) {
        if(etat511_ok.isSelected()){
            etat511_warning.setSelected(false);
            etat511_danger.setSelected(false);
            etat511="OK";
        }
    }

    @FXML
    void etat511_warning(MouseEvent event) {
        if(etat511_warning.isSelected()){
            etat511_ok.setSelected(false);
            etat511_danger.setSelected(false);
            etat511="ATTENTION";
        }
    }

    @FXML
    void etat512_danger(MouseEvent event) {
        if(etat512_danger.isSelected()){
            etat512_warning.setSelected(false);
            etat512_ok.setSelected(false);
            etat512="MAUVAIS";
        }
    }

    @FXML
    void etat512_ok(MouseEvent event) {
        if(etat512_ok.isSelected()){
            etat512_warning.setSelected(false);
            etat512_danger.setSelected(false);
            etat512="OK";
        }
    }

    @FXML
    void etat512_warning(MouseEvent event) {
        if(etat512_warning.isSelected()){
            etat512_ok.setSelected(false);
            etat512_danger.setSelected(false);
            etat512="ATTENTION";
        }
    }

    @FXML
    void etat513_danger(MouseEvent event) {
        if(etat513_danger.isSelected()){
            etat513_warning.setSelected(false);
            etat513_ok.setSelected(false);
            etat513="MAUVAIS";
        }
    }

    @FXML
    void etat513_ok(MouseEvent event) {
        if(etat513_ok.isSelected()){
            etat513_warning.setSelected(false);
            etat513_danger.setSelected(false);
            etat513="OK";
        }
    }

    @FXML
    void etat513_warning(MouseEvent event) {
        if(etat513_warning.isSelected()){
            etat513_ok.setSelected(false);
            etat513_danger.setSelected(false);
            etat513="ATTENTION";
        }
    }

    @FXML
    void etat514_danger(MouseEvent event) {
        if(etat514_danger.isSelected()){
            etat514_warning.setSelected(false);
            etat514_ok.setSelected(false);
            etat514="MAUVAIS";
        }
    }

    @FXML
    void etat514_ok(MouseEvent event) {
        if(etat514_ok.isSelected()){
            etat514_warning.setSelected(false);
            etat514_danger.setSelected(false);
            etat514="OK";
        }
    }

    @FXML
    void etat514_warning(MouseEvent event) {
        if(etat514_warning.isSelected()){
            etat514_ok.setSelected(false);
            etat514_danger.setSelected(false);
            etat514="ATTENTION";
        }
    }

    @FXML
    void etat521_danger(MouseEvent event) {
        if(etat521_danger.isSelected()){
            etat521_warning.setSelected(false);
            etat521_ok.setSelected(false);
            etat521="MAUVAIS";
        }
    }

    @FXML
    void etat521_ok(MouseEvent event) {
        if(etat521_ok.isSelected()){
            etat521_warning.setSelected(false);
            etat521_danger.setSelected(false);
            etat521="OK";
        }
    }

    @FXML
    void etat521_warning(MouseEvent event) {
        if(etat521_warning.isSelected()){
            etat521_ok.setSelected(false);
            etat521_danger.setSelected(false);
            etat521="ATTENTION";
        }
    }

    @FXML
    void etat522_danger(MouseEvent event) {
        if(etat522_danger.isSelected()){
            etat522_warning.setSelected(false);
            etat522_ok.setSelected(false);
            etat522="MAUVAIS";
        }
    }

    @FXML
    void etat522_ok(MouseEvent event) {
        if(etat522_ok.isSelected()){
            etat522_warning.setSelected(false);
            etat522_danger.setSelected(false);
            etat522="OK";
        }
    }

    @FXML
    void etat522_warning(MouseEvent event) {
        if(etat522_warning.isSelected()){
            etat522_ok.setSelected(false);
            etat522_danger.setSelected(false);
            etat522="ATTENTION";
        }
    }

    @FXML
    void etat523_danger(MouseEvent event) {
        if(etat523_danger.isSelected()){
            etat523_warning.setSelected(false);
            etat523_ok.setSelected(false);
            etat523="MAUVAIS";
        }
    }

    @FXML
    void etat523_ok(MouseEvent event) {
        if(etat523_ok.isSelected()){
            etat523_warning.setSelected(false);
            etat523_danger.setSelected(false);
            etat523="OK";
        }
    }

    @FXML
    void etat523_warning(MouseEvent event) {
        if(etat523_warning.isSelected()){
            etat523_ok.setSelected(false);
            etat523_danger.setSelected(false);
            etat523="ATTENTION";
        }
    }

    @FXML
    void etat524_danger(MouseEvent event) {
        if(etat524_danger.isSelected()){
            etat524_warning.setSelected(false);
            etat524_ok.setSelected(false);
            etat524="MAUVAIS";
        }
    }

    @FXML
    void etat524_ok(MouseEvent event) {
        if(etat524_ok.isSelected()){
            etat524_warning.setSelected(false);
            etat524_danger.setSelected(false);
            etat524="OK";
        }
    }

    @FXML
    void etat524_warning(MouseEvent event) {
        if(etat524_warning.isSelected()){
            etat524_ok.setSelected(false);
            etat524_danger.setSelected(false);
            etat524="ATTENTION";
        }
    }

    @FXML
    void etat531_danger(MouseEvent event) {
        if(etat531_danger.isSelected()){
            etat531_warning.setSelected(false);
            etat531_ok.setSelected(false);
            etat531="MAUVAIS";
        }
    }

    @FXML
    void etat531_ok(MouseEvent event) {
        if(etat531_ok.isSelected()){
            etat531_warning.setSelected(false);
            etat531_danger.setSelected(false);
            etat531="OK";
        }
    }

    @FXML
    void etat531_warning(MouseEvent event) {
        if(etat531_warning.isSelected()){
            etat531_ok.setSelected(false);
            etat531_danger.setSelected(false);
            etat531="ATTENTION";
        }
    }

    @FXML
    void etat532_danger(MouseEvent event) {
        if(etat532_danger.isSelected()){
            etat532_warning.setSelected(false);
            etat532_ok.setSelected(false);
            etat532="MAUVAIS";
        }
    }

    @FXML
    void etat532_ok(MouseEvent event) {
        if(etat532_ok.isSelected()){
            etat532_warning.setSelected(false);
            etat532_danger.setSelected(false);
            etat532="OK";
        }
    }

    @FXML
    void etat532_warning(MouseEvent event) {
        if(etat532_warning.isSelected()){
            etat532_ok.setSelected(false);
            etat532_danger.setSelected(false);
            etat532="ATTENTION";
        }
    }

    @FXML
    void etat533_danger(MouseEvent event) {
        if(etat533_danger.isSelected()){
            etat533_warning.setSelected(false);
            etat533_ok.setSelected(false);
            etat533="MAUVAIS";
        }
    }

    @FXML
    void etat533_ok(MouseEvent event) {
        if(etat533_ok.isSelected()){
            etat533_warning.setSelected(false);
            etat533_danger.setSelected(false);
            etat533="OK";
        }
    }

    @FXML
    void etat533_warning(MouseEvent event) {
        if(etat533_warning.isSelected()){
            etat533_ok.setSelected(false);
            etat533_danger.setSelected(false);
            etat533="ATTENTION";
        }
    }

    @FXML
    void etat534_danger(MouseEvent event) {
        if(etat534_danger.isSelected()){
            etat534_warning.setSelected(false);
            etat534_ok.setSelected(false);
            etat534="MAUVAIS";
        }
    }

    @FXML
    void etat534_ok(MouseEvent event) {
        if(etat534_ok.isSelected()){
            etat534_warning.setSelected(false);
            etat534_danger.setSelected(false);
            etat534="OK";
        }
    }

    @FXML
    void etat534_warning(MouseEvent event) {
        if(etat534_warning.isSelected()){
            etat534_ok.setSelected(false);
            etat534_danger.setSelected(false);
            etat534="ATTENTION";
        }
    }

    @FXML
    void etat541_danger(MouseEvent event) {
        if(etat541_danger.isSelected()){
            etat541_warning.setSelected(false);
            etat541_ok.setSelected(false);
            etat541="MAUVAIS";
        }
    }

    @FXML
    void etat541_ok(MouseEvent event) {
        if(etat541_ok.isSelected()){
            etat541_warning.setSelected(false);
            etat541_danger.setSelected(false);
            etat541="OK";
        }
    }

    @FXML
    void etat541_warning(MouseEvent event) {
        if(etat541_warning.isSelected()){
            etat541_ok.setSelected(false);
            etat541_danger.setSelected(false);
            etat541="ATTENTION";
        }
    }

    @FXML
    void etat542_danger(MouseEvent event) {
        if(etat542_danger.isSelected()){
            etat542_warning.setSelected(false);
            etat542_ok.setSelected(false);
            etat542="MAUVAIS";
        }
    }

    @FXML
    void etat542_ok(MouseEvent event) {
        if(etat542_ok.isSelected()){
            etat542_warning.setSelected(false);
            etat542_danger.setSelected(false);
            etat542="OK";
        }
    }

    @FXML
    void etat542_warning(MouseEvent event) {
        if(etat542_warning.isSelected()){
            etat542_ok.setSelected(false);
            etat542_danger.setSelected(false);
            etat542="ATTENTION";
        }
    }

    @FXML
    void etat543_danger(MouseEvent event) {
        if(etat543_danger.isSelected()){
            etat543_warning.setSelected(false);
            etat543_ok.setSelected(false);
            etat543="MAUVAIS";
        }
    }

    @FXML
    void etat543_ok(MouseEvent event) {
        if(etat543_ok.isSelected()){
            etat543_warning.setSelected(false);
            etat543_danger.setSelected(false);
            etat543="OK";
        }
    }

    @FXML
    void etat543_warning(MouseEvent event) {
        if(etat543_warning.isSelected()){
            etat543_ok.setSelected(false);
            etat543_danger.setSelected(false);
            etat543="ATTENTION";
        }
    }

    @FXML
    void etat544_danger(MouseEvent event) {
        if(etat544_danger.isSelected()){
            etat544_warning.setSelected(false);
            etat544_ok.setSelected(false);
            etat544="MAUVAIS";
        }
    }

    @FXML
    void etat544_ok(MouseEvent event) {
        if(etat544_ok.isSelected()){
            etat544_warning.setSelected(false);
            etat544_danger.setSelected(false);
            etat544="OK";
        }
    }

    @FXML
    void etat544_warning(MouseEvent event) {
        if(etat544_warning.isSelected()){
            etat544_ok.setSelected(false);
            etat544_danger.setSelected(false);
            etat544="ATTENTION";
        }
    }

    @FXML
    void etat661_danger(MouseEvent event) {
        if(etat661_danger.isSelected()){
            etat661_warning.setSelected(false);
            etat661_ok.setSelected(false);
            etat661="MAUVAIS";
        }
    }

    @FXML
    void etat661_ok(MouseEvent event) {
        if(etat661_ok.isSelected()){
            etat661_warning.setSelected(false);
            etat661_danger.setSelected(false);
            etat661="OK";
        }
    }

    @FXML
    void etat661_warning(MouseEvent event) {
        if(etat661_warning.isSelected()){
            etat661_ok.setSelected(false);
            etat661_danger.setSelected(false);
            etat661="ATTENTION";
        }
    }

    @FXML
    void etat662_danger(MouseEvent event) {
        if(etat662_danger.isSelected()){
            etat662_warning.setSelected(false);
            etat662_ok.setSelected(false);
            etat662="MAUVAIS";
        }
    }

    @FXML
    void etat662_ok(MouseEvent event) {
        if(etat662_ok.isSelected()){
            etat662_warning.setSelected(false);
            etat662_danger.setSelected(false);
            etat662="OK";
        }
    }

    @FXML
    void etat662_warning(MouseEvent event) {
        if(etat662_warning.isSelected()){
            etat662_ok.setSelected(false);
            etat662_danger.setSelected(false);
            etat662="ATTENTION";
        }
    }

    @FXML
    void etat663_danger(MouseEvent event) {
        if(etat663_danger.isSelected()){
            etat663_warning.setSelected(false);
            etat663_ok.setSelected(false);
            etat663="MAUVAIS";
        }
    }

    @FXML
    void etat663_ok(MouseEvent event) {
        if(etat663_ok.isSelected()){
            etat663_warning.setSelected(false);
            etat663_danger.setSelected(false);
            etat663="OK";
        }
    }

    @FXML
    void etat663_warning(MouseEvent event) {
        if(etat663_warning.isSelected()){
            etat663_ok.setSelected(false);
            etat663_danger.setSelected(false);
            etat663="ATTENTION";
        }
    }

    @FXML
    void etat664_danger(MouseEvent event) {
        if(etat664_danger.isSelected()){
            etat664_warning.setSelected(false);
            etat664_ok.setSelected(false);
            etat664="MAUVAIS";
        }
    }

    @FXML
    void etat664_ok(MouseEvent event) {
        if(etat664_ok.isSelected()){
            etat664_warning.setSelected(false);
            etat664_danger.setSelected(false);
            etat664="OK";
        }
    }

    @FXML
    void etat664_warning(MouseEvent event) {
        if(etat664_warning.isSelected()){
            etat664_ok.setSelected(false);
            etat664_danger.setSelected(false);
            etat664="ATTENTION";
        }
    }
    @FXML
    void etat665_danger(MouseEvent event) {
        if(etat665_danger.isSelected()){
            etat665_warning.setSelected(false);
            etat665_ok.setSelected(false);
            etat665="MAUVAIS";
        }
    }

    @FXML
    void etat665_ok(MouseEvent event) {
        if(etat665_ok.isSelected()){
            etat665_warning.setSelected(false);
            etat665_danger.setSelected(false);
            etat665="OK";
        }
    }

    @FXML
    void etat665_warning(MouseEvent event) {
        if(etat665_warning.isSelected()){
            etat665_ok.setSelected(false);
            etat665_danger.setSelected(false);
            etat665="ATTENTION";
        }
    }

    void reset_reception_form() {
        list_etat.removeAll();
        dossierID = 0;
        receptionID = 0;
    }
    
    void readAll() {
        ReceptionRepository receptionRepository = new ReceptionRepository();
        Reception reception = receptionRepository.findById(receptionID);
        list_etat = FXCollections.observableArrayList(reception.getEtats());
    }

    public void selectTab(int i) {
        receptionTab.getSelectionModel().select(getTab(i));
    }

    public Tab getTab(int i) {
        Tab tab = null;
        switch (i) {
            case 1: {
                tab = tab_reception;
                break;
            }
            case 2: {
                tab = tab_diagnostic;
                break;
            }
        }
        return tab;
    }

    public void getDossiers(){
        DossierRepository dossierRepository = new DossierRepository();
        this.allDossier = dossierRepository.findAll();
        dossierAutomobile.setItems(FXCollections.observableArrayList(allDossier));
        reception_diagnostic.setItems(FXCollections.observableArrayList(allDossier));
    }

    public void getProfiles(){
        ProfileRepository profileRepository = new ProfileRepository();
        this.allProfile = profileRepository.findOuvrier();
        profile_reception.setItems(FXCollections.observableArrayList(allProfile));
        profile_diagnostic.setItems(FXCollections.observableArrayList(allProfile));
    }

    public void initialize(URL location, ResourceBundle resources) {

        getDossiers();

        getProfiles();

        libelle_defaut.setItems(FXCollections.observableArrayList(Defaut.getGroupe()));
        libelle_solution.setItems(FXCollections.observableArrayList(Solution.getGroupes()));

        defaut_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        defaut_description.setCellValueFactory(new PropertyValueFactory<>("description"));

        solution_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        solution_description.setCellValueFactory(new PropertyValueFactory<>("description"));

        receptionTab.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                if (tab.getTabPane().getSelectionModel().getSelectedIndex() == 2) {
                    readAll();
                }
            }
        });

        profile_diagnostic.setConverter(new StringConverter<Profile>() {
            @Override
            public String toString(Profile object) {
                if(object==null){
                    return null;
                }
                return object.toString();
            }

            @Override
            public Profile fromString(String string) {
                ProfileRepository profileRepository = new ProfileRepository();
                String[] data = string.split("\\s");
                String id = data[0].trim();
                return profileRepository.findById(Long.parseLong(id));
            }
        });

        profile_reception.setConverter(new StringConverter<Profile>() {
            @Override
            public String toString(Profile object) {
                if(object==null){
                    return null;
                }
                return object.toString();
            }

            @Override
            public Profile fromString(String string) {
                ProfileRepository profileRepository = new ProfileRepository();
                String[] data = profile_reception.getEditor().getText().split("\\s");
                String id = data[0].trim();
                return profileRepository.findById(Long.parseLong(id));
            }
        });

        dossierAutomobile.setConverter(new StringConverter<Dossier>() {
            @Override
            public String toString(Dossier object) {
                if(object==null){
                    return null;
                }
                return object.toString();
            }

            @Override
            public Dossier fromString(String string) {
                DossierRepository dossierRepository = new DossierRepository();
                String[] data = string.split("/");
                String id = data[0].trim();
                return dossierRepository.findById(Long.parseLong(id));
            }
        });

        reception_diagnostic.setConverter(new StringConverter<Dossier>() {
            @Override
            public String toString(Dossier object) {
                if(object==null){
                    return null;
                }
                return object.toString();
            }

            @Override
            public Dossier fromString(String string) {
                DossierRepository dossierRepository = new DossierRepository();
                String[] data = string.split("/");
                String id = data[0].trim();
                return dossierRepository.findById(Long.parseLong(id));
            }
        });
    }
}
