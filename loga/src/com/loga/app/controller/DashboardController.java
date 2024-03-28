package com.loga.app.controller;

import com.loga.api.Manage;
import com.loga.api.Market;
import com.loga.api.Repair;
import com.loga.model.*;
import com.loga.ui.AlertWarning;
import com.loga.ui.Spinner;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable{

    DateFormat df = new SimpleDateFormat("MM-yyyy");

    @FXML
    private Text count_profil;

    @FXML
    private Text count_client;

    @FXML
    private Text count_automobile;

    @FXML
    private Text count_reparation;

    @FXML
    private Text count_article;

    @FXML
    private Text count_commande;

    @FXML
    private Text count_vente;

    @FXML
    private Text count_versement;

    @FXML
    private Text count_salaire;

    @FXML
    private Text count_depense;

    @FXML
    private BarChart<String, Double> chart_reparation;

    @FXML
    private CategoryAxis chart_reparation_x;

    @FXML
    private NumberAxis chart_reparation_y;

    @FXML
    private BarChart<String, Double> chart_vente;

    @FXML
    private CategoryAxis chart_vente_x;

    @FXML
    private NumberAxis chart_vente_y;

    @FXML
    private AreaChart<String, Double> chart_cashflow;

    @FXML
    private CategoryAxis chart_cashflow_x;

    @FXML
    private NumberAxis chart_cashflow_y;

    public void processStatistic(){
        List<Profile> profiles = Manage.getInstance().listerProfile();
        AlertWarning.getInstance().setContentText(String.valueOf(profiles.size()));
        AlertWarning.getInstance().showAndWait();
        count_profil.setText(String.valueOf(profiles.size()));

        List<Client> clients = Manage.getInstance().listerClient();
        count_client.setText(String.valueOf(clients.size()));

        List<Automobile> automobiles = Manage.getInstance().listerAutomobile();
        count_automobile.setText(String.valueOf(automobiles.size()));

        /*List<Reparation> reparations = Repair.getInstance().listerReparation();
        count_reparation.setText(String.valueOf(reparations.size()));*/

        //List<Article> articles = Market.getInstance().listerArticle();
        //count_article.setText(String.valueOf(articles.size()));

        //List<Versement> versements = Manage.getInstance().listerVersement();
        //count_versement.setText(String.valueOf(versements.size()));
    }

    public void processReparationParPeriode(){

        List<String> periodes = new ArrayList<>();

        List<Reparation> reparations = new SortedList<Reparation>(FXCollections.observableArrayList(Repair.getInstance().listerReparation()), new Comparator<Reparation>() {
            @Override
            public int compare(Reparation r1, Reparation r2) {
                return r1.getDateCreation().compareTo(r2.getDateCreation());
            }
        });

        //Séries des prestations
        XYChart.Series<String,Double> tacheParPeriode = new XYChart.Series<>();
        tacheParPeriode.setName("Prestations");

        //Séries des fournitures
        XYChart.Series<String,Double> fournitureParPeriode = new XYChart.Series<>();
        fournitureParPeriode.setName("Fournitures");

        for (Reparation reparation : reparations) {
            do {
                periodes.add(df.format(reparation.getDateCreation()));
            }while(!periodes.contains(df.format(reparation.getDateCreation())));
        }

        for (String periode:periodes) {
            double montantTache = 0;
            double montantFourniture = 0;
            for (Reparation reparation : reparations) {
                montantFourniture+=reparation.getTotalFourniture();
                montantTache+=reparation.getTotalTache();
            }
            tacheParPeriode.getData().add(new XYChart.Data<String,Double>(periode,montantTache));
            fournitureParPeriode.getData().add(new XYChart.Data<String,Double>(periode,montantFourniture));
        }
        chart_reparation.getData().add(fournitureParPeriode);
        chart_reparation.getData().add(tacheParPeriode);
    }

    public void processVenteParPeriode(){

        List<String> periodes = new ArrayList<>();

        List<Vente> ventes = new SortedList<Vente>(FXCollections.observableArrayList(Market.getInstance().listerVente()), new Comparator<com.loga.model.Vente>() {
            @Override
            public int compare(Vente o1, Vente o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        List<Achat> achats = new SortedList<Achat>(FXCollections.observableArrayList(Market.getInstance().listerAchat()), new Comparator<Achat>() {
            @Override
            public int compare(Achat o1, Achat o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        //Séries des prestations
        XYChart.Series<String,Double> venteParPeriode = new XYChart.Series<>();
        venteParPeriode.setName("Ventes");

        //Séries des fournitures
        XYChart.Series<String,Double> achatParPeriode = new XYChart.Series<>();
        achatParPeriode.setName("Achats");

        for (Achat achat : achats) {
            do {
                periodes.add(df.format(achat.getDate()));
            }while(!periodes.contains(df.format(achat.getDate())));
        }

        for (Vente vente : ventes) {
            do {
                periodes.add(df.format(vente.getDate()));
            }while(!periodes.contains(df.format(vente.getDate())));
        }

        for (String periode:periodes) {
            double montantAchat = 0;
            double montantVente = 0;
            for (Achat achat : achats) {
                montantAchat+=achat.getMontant();
            }
            for (Vente vente : ventes) {
                montantVente+=vente.getMontant();
            }
            venteParPeriode.getData().add(new XYChart.Data<String,Double>(periode,montantVente));
            achatParPeriode.getData().add(new XYChart.Data<String,Double>(periode,montantAchat));
        }

        chart_vente.getData().add(venteParPeriode);
        chart_vente.getData().add(achatParPeriode);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        processStatistic();

       /* processVenteParPeriode();

        processReparationParPeriode();*/
        /*
        Spinner.getInstance().show();

        javafx.concurrent.Service<Void> load = new javafx.concurrent.Service<Void>() {
            @Override
            protected Task<Void> createTask() {

                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        processStatistic();

                        //processVenteParPeriode();

                        //processReparationParPeriode();

                        return null;
                    }
                };
            }
        };

        load.stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) -> {
            switch (newValue){
                case FAILED:
                case CANCELLED:
                    AlertWarning.getInstance().setHeaderText("Tableau de bord");
                    AlertWarning.getInstance().setContentText("Impossible de charger les statistiques!!!");
                    AlertWarning.getInstance().show();
                case SUCCEEDED:
                    Spinner.getInstance().hide();
            }
        });

        load.start();*/
    }
}

