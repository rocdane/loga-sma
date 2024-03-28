package com.loga.app.controller;

import com.loga.app.dao.DossierRepository;
import com.loga.model.*;
import com.loga.vendor.Report;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class DossierController implements Initializable
{
    List<Dossier> allDossier;

    Dossier dossierCurrent;

    @FXML
    private ComboBox<Dossier> dossiers;

    @FXML
    private TextField immatriculation;

    @FXML
    private TextField marque;

    @FXML
    private TextField compteur;

    @FXML
    private TextField chassis;

    @FXML
    private TextField client;

    @FXML
    private TextField reference;

    @FXML
    private TextField pmec;

    @FXML
    private TextField puissance;

    @FXML
    private TextField cylindre;

    @FXML
    private TextField transmission;

    @FXML
    private TableView<Reception> table_reception;

    @FXML
    private TableColumn<Reception, Long> reception_id;

    @FXML
    private TableColumn<Reception, String> reception_date;

    @FXML
    private TableColumn<Reception, String> reception_observation;

    @FXML
    private TableColumn<Reception, String> reception_commentaire;

    @FXML
    private TableColumn<Reception, Integer> reception_compteur;

    @FXML
    private TableView<Diagnostic> table_diagnostic;

    @FXML
    private TableColumn<Diagnostic, Long> diagnostic_id;

    @FXML
    private TableColumn<Diagnostic, String> diagnostic_date;

    @FXML
    private TableColumn<Diagnostic, String> diagnostic_diagnostic;

    @FXML
    private TableColumn<Diagnostic, Integer> diagnostic_compteur;

    @FXML
    private TableView<Reparation> table_reparation;

    @FXML
    private TableColumn<Reparation, Long> reparation_id;

    @FXML
    private TableColumn<Reparation, String> reparation_date;

    @FXML
    private TableColumn<Reparation, String> reparation_numero;

    @FXML
    private TableColumn<Reparation, String> reparation_description;

    @FXML
    private TableColumn<Reparation, Integer> reparation_compteur;

    @FXML
    private TableView<Commande> table_commande;

    @FXML
    private TableColumn<Commande, Long> commande_id;

    @FXML
    private TableColumn<Commande, String> commande_date;

    @FXML
    private TableColumn<Commande, String> commande_fournisseur;

    @FXML
    private TableColumn<Commande, Integer> commande_quantite;

    @FXML
    private TableColumn<Commande, Double> commande_montant;

    @FXML
    private TableView<Qualite> table_qualite;

    @FXML
    private TableColumn<Qualite, Long> qualite_id;

    @FXML
    private TableColumn<Qualite, Date> qualite_date;

    @FXML
    private TableColumn<Qualite, Integer> qualite_compteur;

    @FXML
    private TableColumn<Qualite, String> qualite_commentaire;

    @FXML
    private TableView<Reparation> table_solde;

    @FXML
    private TableColumn<Reparation, String> solde_reparation;

    @FXML
    private TableColumn<Reparation, String> solde_description;

    @FXML
    private TableColumn<Reparation, Double> solde_montant;

    @FXML
    private TableColumn<Reparation, Double> solde_balance;

    @FXML
    void print_diagnostic(MouseEvent event) {
        Report.getInstance().createReport("/jrxml/diagnostic.jrxml", (int) table_diagnostic.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    void print_commande(MouseEvent event) {
        Report.getInstance().createReport("/jrxml/commande.jrxml",(int)table_commande.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    void print_qualite(MouseEvent event) {

    }

    @FXML
    void print_reception(MouseEvent event) {
        Report.getInstance().createReport("/jrxml/reception.jrxml",(int) table_reception.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    void print_reparation(MouseEvent event) {
        Report.getInstance().createReport("/jrxml/reparation.jrxml",(int) table_reparation.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    void search_dossier(KeyEvent event) {
        FilteredList<Dossier> items = new FilteredList<>(FXCollections.observableArrayList(allDossier));
        items.setPredicate(item ->{
            String lower = dossiers.getEditor().getText().toLowerCase();
            String upper = dossiers.getEditor().getText().toUpperCase();
            if(item.getAutomobile().getImmatriculation().contains(lower))
                return true;
            else
                return item.getAutomobile().getImmatriculation().contains(upper);
        });
        SortedList<Dossier> sorted = new SortedList<>(items);
        dossiers.setItems(sorted);
    }

    void readInformation(Dossier dossier) {
        client.setText(dossier.getAutomobile().getClient().getRaisonSociale()+" | "+dossier.getAutomobile().getClient().getContact());
        immatriculation.setText(dossier.getAutomobile().getImmatriculation());
        marque.setText(dossier.getAutomobile().getMarque() + " / " + dossier.getAutomobile().getModele());
        compteur.setText(dossier.getAutomobile().getCompteur() + " [" + dossier.getAutomobile().getTypeCompteur() + "]");
        chassis.setText(dossier.getAutomobile().getChassis());
        reference.setText(dossier.getReference());
        pmec.setText(dossier.getAutomobile().getPmec());
        transmission.setText(dossier.getAutomobile().getTransmission());
        puissance.setText(dossier.getAutomobile().getPuissance());
        cylindre.setText(dossier.getAutomobile().getCylindre());
    }

    void readReception(Dossier dossier) {
        List<Reception> receptions = dossier.getReceptions();
        table_reception.setItems(FXCollections.observableArrayList(receptions));
    }

    void readDiagnostic(Dossier dossier) {
        List<Diagnostic> diagnostics = dossier.getDiagnostics();
        table_diagnostic.setItems(FXCollections.observableArrayList(diagnostics));
    }

    void readReparation(Dossier dossier) {
        List<Reparation> reparations = dossier.getReparations();
        table_reparation.setItems(FXCollections.observableArrayList(reparations));
    }

    void readCommande(Dossier dossier) {
        List<Commande> commandes = new ArrayList<>();
        List<Reparation> reparations = dossier.getReparations();
        for (Reparation reparation : reparations) {
            if(!reparation.getFournitures().isEmpty()){
                Fourniture fourniture = reparation.getFournitures().get(0);
                Commande commande = fourniture.getCommande();
                if(commande!=null){
                    commandes.add(commande);
                }
            }
        }
        table_commande.setItems(FXCollections.observableArrayList(commandes));
    }

    void readSolde(Dossier dossier){
        List<Reparation> reparations = new ArrayList<>();
        for (Reparation reparation : dossier.getReparations()) {
            if(reparation.getSolde() < reparation.getTotal()){
                reparations.add(reparation);
            }
        }
        table_solde.setItems(FXCollections.observableArrayList(reparations));
    }

    void readQualite(Dossier dossier) {
        List<Qualite> qualites = new ArrayList<>();
        List<Reparation> reparations = dossier.getReparations();
        for (Reparation reparation:reparations) {
            Qualite qualite = reparation.getQualite();
            if(qualite!=null){
                qualites.add(reparation.getQualite());
            }
        }
        table_qualite.setItems(FXCollections.observableArrayList(qualites));
    }

    void readDossiers(){
        DossierRepository dossierRepository = new DossierRepository();
        this.allDossier = dossierRepository.findAll();
        dossiers.setItems(FXCollections.observableArrayList(allDossier));
    }
    
    public void initialize(URL location, ResourceBundle resources) {
        dossiers.setConverter(new StringConverter<Dossier>() {
            @Override
            public String toString(Dossier object) {
                if(object==null) return null;
                return object.toString();
            }

            @Override
            public Dossier fromString(String string) {
                DossierRepository dossierRepository = new DossierRepository();
                String[] data = string.split("/");
                String auto = data[0].trim();
                return dossierRepository.findByAuto(auto);
            }
        });

        readDossiers();

        reception_id.setCellValueFactory((TableColumn.CellDataFeatures<Reception, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        reception_date.setCellValueFactory((TableColumn.CellDataFeatures<Reception, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-YYYY").format(r.getValue().getDateCreation())));
        reception_commentaire.setCellValueFactory((TableColumn.CellDataFeatures<Reception, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getCommentaire()));
        reception_observation.setCellValueFactory((TableColumn.CellDataFeatures<Reception, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getObservation()));
        reception_compteur.setCellValueFactory((TableColumn.CellDataFeatures<Reception, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getCompteur()));

        diagnostic_id.setCellValueFactory((TableColumn.CellDataFeatures<Diagnostic, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        diagnostic_date.setCellValueFactory((TableColumn.CellDataFeatures<Diagnostic, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-YYYY").format(r.getValue().getDateCreation())));
        diagnostic_diagnostic.setCellValueFactory((TableColumn.CellDataFeatures<Diagnostic, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDiagnostic()));
        diagnostic_compteur.setCellValueFactory((TableColumn.CellDataFeatures<Diagnostic, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getCompteur()));

        reparation_id.setCellValueFactory((TableColumn.CellDataFeatures<Reparation, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        reparation_date.setCellValueFactory((TableColumn.CellDataFeatures<Reparation, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-YYYY").format(r.getValue().getDateCreation())));
        reparation_description.setCellValueFactory((TableColumn.CellDataFeatures<Reparation, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDescription()));
        reparation_numero.setCellValueFactory((TableColumn.CellDataFeatures<Reparation, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getNumero()));
        reparation_compteur.setCellValueFactory((TableColumn.CellDataFeatures<Reparation, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getCompteur()));

        commande_id.setCellValueFactory((TableColumn.CellDataFeatures<Commande, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        commande_date.setCellValueFactory((TableColumn.CellDataFeatures<Commande, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-YYYY").format(r.getValue().getDateCreation())));
        commande_fournisseur.setCellValueFactory((TableColumn.CellDataFeatures<Commande, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getFournisseur().getRaisonSociale()));
        commande_quantite.setCellValueFactory((TableColumn.CellDataFeatures<Commande, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getQuantite()));
        commande_montant.setCellValueFactory((TableColumn.CellDataFeatures<Commande, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));

        solde_reparation.setCellValueFactory((TableColumn.CellDataFeatures<Reparation, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-YYYY").format(r.getValue().getDateCreation())));
        solde_description.setCellValueFactory((TableColumn.CellDataFeatures<Reparation, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDescription()));
        solde_montant.setCellValueFactory((TableColumn.CellDataFeatures<Reparation, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getTotal()));
        solde_balance.setCellValueFactory((TableColumn.CellDataFeatures<Reparation, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getSolde()-r.getValue().getTotal()));

        qualite_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        qualite_commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        qualite_compteur.setCellValueFactory(new PropertyValueFactory<>("compteur"));
        qualite_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        dossiers.addEventHandler(ActionEvent.ACTION, event -> {
            if (dossiers.getSelectionModel().getSelectedIndex() != -1) {
                dossierCurrent = dossiers.getValue();
                readInformation(dossierCurrent);
                readReception(dossierCurrent);
                readDiagnostic(dossierCurrent);
                readReparation(dossierCurrent);
                readCommande(dossierCurrent);
                readSolde(dossierCurrent);
                readQualite(dossierCurrent);
            }
        });
    }
}
