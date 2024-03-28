package com.loga.app.controller;

import com.loga.api.IManage;
import com.loga.app.dao.AchatRepository;
import com.loga.app.dao.ArticleRepository;
import com.loga.app.dao.AtelierRepository;
import com.loga.app.dao.VenteRepository;
import com.loga.model.Achat;
import com.loga.model.Article;
import com.loga.model.Vente;
import com.loga.ui.AlertError;
import com.loga.ui.AlertWarning;
import com.loga.vendor.Money;
import com.loga.vendor.Report;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class StockController implements Initializable {
    private IManage manage;
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private List<Article> articles;
    private List<Achat> achats;
    private List<Vente> ventes;
    private Article article;
    private Article article_achat;
    private Article article_vente;
    private Date debut = null;
    private Date fin = null;

    public void setManage(IManage manage){
       this.manage = manage;
    }

    @FXML
    private AnchorPane content;

    @FXML
    private TabPane stock_tab;

    @FXML
    private Tab tab_achat;

    @FXML
    private AnchorPane achat_tab_content;

    @FXML
    private ComboBox<Article.Categorie> achat_categorie;

    @FXML
    private TextField achat_description;

    @FXML
    private TextField achat_prix;

    @FXML
    private ComboBox<Article> achat_designation;

    @FXML
    private Button ajouter_achat;

    @FXML
    private Button annuler_achat;

    @FXML
    private TextField achat_quantite;

    @FXML
    private TextField achat_fournisseur;

    @FXML
    private TableView<Achat> table_achat;

    @FXML
    private TableColumn<Achat, Long> table_achat_id;

    @FXML
    private TableColumn<Achat, String> table_achat_date;

    @FXML
    private TableColumn<Achat, String> table_achat_designation;

    @FXML
    private TableColumn<Achat, String> table_achat_description;

    @FXML
    private TableColumn<Achat, Integer> table_achat_quantite;

    @FXML
    private TableColumn<Achat, Double> table_achat_prix;

    @FXML
    private TableColumn<Achat, Double> table_achat_montant;

    @FXML
    private TableColumn<Achat, String> table_achat_fournisseur;

    @FXML
    private Button stocker;

    @FXML
    private Tab tab_vente;

    @FXML
    private AnchorPane vente_tab_content;

    @FXML
    private TextField vente_description;

    @FXML
    private TextField vente_prix;

    @FXML
    private TextField vente_client;

    @FXML
    private ComboBox<Article> vente_designation;

    @FXML
    private Button ajouter_vente;

    @FXML
    private Button annuler_vente;

    @FXML
    private TextField vente_quantite;

    @FXML
    private TableView<Vente> table_vente;

    @FXML
    private TableColumn<Vente, Long> table_vente_id;

    @FXML
    private TableColumn<Vente, String> table_vente_date;

    @FXML
    private TableColumn<Vente, String> table_vente_designation;

    @FXML
    private TableColumn<Vente, String> table_vente_description;

    @FXML
    private TableColumn<Vente, Integer> table_vente_quantite;

    @FXML
    private TableColumn<Vente, Double> table_vente_prix;

    @FXML
    private TableColumn<Vente, Double> table_vente_montant;

    @FXML
    private TableColumn<Vente, String> table_vente_client;

    @FXML
    private Button sortir;

    @FXML
    private Tab tab_stock;

    @FXML
    private AnchorPane stock_tab_content;

    @FXML
    private TextField filtre;

    @FXML
    private TableView<Article> table_article;

    @FXML
    private TableColumn<Article, Long> table_article_id;

    @FXML
    private TableColumn<Article, String> table_article_designation;

    @FXML
    private TableColumn<Article, String> table_article_description;

    @FXML
    private TableColumn<Article, Integer> table_article_quantite;

    @FXML
    private TableColumn<Article, Double> table_article_prix;

    @FXML
    private Tab tab_article;

    @FXML
    private TextField article_reference;

    @FXML
    private TextField article_designation;

    @FXML
    private TextField article_prix;

    @FXML
    private TextField article_quantite;

    @FXML
    private ComboBox<Article.Categorie> article_categorie;

    @FXML
    private TextArea article_description;

    @FXML
    private Tab tab_ventes;

    @FXML
    private DatePicker ventes_date_debut;

    @FXML
    private DatePicker ventes_date_fin;

    @FXML
    private TableView<Vente> table_ventes;

    @FXML
    private TableColumn<Vente, String> ventes_date;

    @FXML
    private TableColumn<Vente, Article> ventes_article;

    @FXML
    private TableColumn<Vente, String> ventes_client;

    @FXML
    private TableColumn<Vente, Double> ventes_prix;

    @FXML
    private TableColumn<Vente, Integer> ventes_quantite;

    @FXML
    private TableColumn<Vente, Double> ventes_montant;

    @FXML
    private TextField vente_total;

    @FXML
    private Tab tab_achats;

    @FXML
    private DatePicker achats_date_debut;

    @FXML
    private DatePicker achats_date_fin;

    @FXML
    private TableView<Achat> table_achats;

    @FXML
    private TableColumn<Achat, String> achats_date;

    @FXML
    private TableColumn<Achat, Article> achats_article;

    @FXML
    private TableColumn<Achat, String> achats_fournisseur;

    @FXML
    private TableColumn<Achat, Double> achats_montant;

    @FXML
    private TableColumn<Achat, Double> achats_prix;

    @FXML
    private TableColumn<Achat, Integer> achats_quantite;

    @FXML
    private TextField achat_total;

    @FXML
    void afficher_article(MouseEvent event){
        article = table_article.getSelectionModel().getSelectedItem();
        article_reference.setText(article.getReference());
        article_quantite.setText(String.valueOf(article.getStock()));
        article_prix.setText(String.valueOf(article.getPrix()));
        article_designation.setText(article.getDesignation());
        article_description.setText(article.getDescription());
        article_categorie.getSelectionModel().select(Article.Categorie.valueOf(article.getCategorie()));
        selectTab(4);
    }

    void reset_article_form(){
        article_reference.setText("");
        article_designation.setText("");
        article_description.setText("");
        article_quantite.setText("");
        article_prix.setText("");
    }

    @FXML
    void ajouter_achat(ActionEvent event) {
        if(achat_designation.getEditor().getText().equals("") || achat_prix.getText().equals("") || achat_quantite.getText().equals("")){
            AlertWarning.getInstance().setHeaderText("Approvisionnement");
            AlertWarning.getInstance().setContentText("Vérifiez les informations que vous entrez.");
            AlertWarning.getInstance().showAndWait();
        }else{
            if(achat_designation.getSelectionModel().getSelectedItem()==null && achat_categorie.getSelectionModel().getSelectedItem()!=null){

                AtelierRepository atelierRepository = new AtelierRepository();

                Article article = new Article();
                article.setDesignation(achat_designation.getEditor().getText());
                article.setDescription(achat_description.getText());
                article.setPrixAchat(Double.parseDouble(achat_prix.getText()));
                article.setStock(Integer.parseInt(achat_quantite.getText()));
                article.setCategorie(achat_categorie.getEditor().getText());
                article.setAtelier(atelierRepository.findById(1));

                ArticleRepository articleRepository = new ArticleRepository();

                try {
                    long id = articleRepository.save(article);
                    Achat achat = new Achat();
                    achat.setArticle(articleRepository.findById(id));
                    achat.setFournisseur(achat_fournisseur.getText());
                    achat.setDate(new Date());
                    achat.setPrix(Double.parseDouble(achat_prix.getText()));
                    achat.setQuantite(Integer.parseInt(achat_quantite.getText()));
                    achat.setMontant(achat.getPrix()*achat.getQuantite());
                    achats.add(achat);
                } catch (Exception e) {
                    AlertWarning.getInstance().setHeaderText("Approvisionnement");
                    AlertWarning.getInstance().setContentText("Vérifiez les informations que vous entrez.\n"+e.getMessage());
                    AlertWarning.getInstance().showAndWait();
                    e.printStackTrace();
                }
            }else{
                Achat achat = new Achat();
                achat.setArticle(article_achat);
                achat.setFournisseur(achat_fournisseur.getText());
                achat.setDate(new Date());
                achat.setPrix(Double.parseDouble(achat_prix.getText()));
                achat.setQuantite(Integer.parseInt(achat_quantite.getText()));
                achat.setMontant(achat.getPrix()*achat.getQuantite());
                achats.add(achat);
            }
            table_achat.setItems(FXCollections.observableArrayList(achats));
            reset_achat_form();
        }
    }

    @FXML
    void ajouter_vente(ActionEvent event) {
        if(article_vente == null || vente_client.getText().equals("") || vente_quantite.getText().equals("")){
            AlertWarning.getInstance().setHeaderText("Vente");
            AlertWarning.getInstance().setContentText("L'article n'existe pas pour la vente.");
            AlertWarning.getInstance().showAndWait();
            reset_vente_form();
        }else{
            if(article_vente.getStock() >= Integer.parseInt(vente_quantite.getText())){
                Vente vente = new Vente();
                vente.setClient(vente_client.getText());
                vente.setDate(new Date());
                vente.setArticle(article_vente);
                vente.setPrix(Double.parseDouble(vente_prix.getText().trim()));
                vente.setQuantite(Integer.parseInt(vente_quantite.getText()));
                vente.setMontant(vente.getPrix()*vente.getQuantite());
                ventes.add(vente);
                table_vente.setItems(FXCollections.observableArrayList(ventes));
            }else{
                AlertWarning.getInstance().setHeaderText("Vente");
                AlertWarning.getInstance().setContentText("L'article "+article_vente.toString()+" n'est pas en stock suffisant à la vente.");
                AlertWarning.getInstance().showAndWait();
            }
            reset_vente_form();
        }
    }

    public void reset_achat_form(){
        article_achat=null;
        achat_description.setText("");
        achat_designation.getEditor().setText("");
        achat_fournisseur.setText("");
        achat_prix.setText("");
        achat_quantite.setText("");
    }

    @FXML
    void annuler_achat(ActionEvent event) {
        reset_achat_form();
    }

    @FXML
    void annuler_recherche(ActionEvent event) {
        filtre.setText("");
        read_article();
    }

    @FXML
    void apply(ActionEvent event) {
        article.setPrix(Double.parseDouble(article_prix.getText()));
        article.setCategorie(article_categorie.getEditor().getText());
        article.setReference(article_reference.getText());
        article.setDescription(article_description.getText());
        article.setDesignation(article_designation.getText());
        ArticleRepository articleRepository = new ArticleRepository();
        try {
            articleRepository.update(article);
            reset_article_form();
            read_article();
            selectTab(3);
        } catch (Exception e) {
            AlertError.getInstance().setHeaderText("Article : Mise à jour d'information");
            AlertError.getInstance().setContentText("Impossible de mettre à jour l'article.\n"+e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void cancel_edit(ActionEvent event) {
        reset_article_form();
        selectTab(3);
    }

    public void reset_vente_form(){
        vente_client.setText("");
        vente_description.setText("");
        vente_designation.getEditor().setText("");
        vente_quantite.setText("");
        article_vente=null;
    }

    @FXML
    void annuler_vente(ActionEvent event) {
        reset_vente_form();
    }

    @FXML
    void chercher_article_achat(KeyEvent event) {
        FilteredList<Article> items = new FilteredList<>(FXCollections.observableArrayList(articles));
        items.setPredicate(item ->{
            String lower = achat_designation.getEditor().getText().toLowerCase();
            String upper = achat_designation.getEditor().getText().toUpperCase();
            if(item.getDesignation().contains(lower) || item.getDescription().contains(lower))
                return true;
            else
                return item.getDesignation().contains(upper) || item.getDescription().contains(upper);
        });
        SortedList<Article> sorted = new SortedList<>(items);
        achat_designation.setItems(sorted);
    }

    @FXML
    void chercher_article_vente(KeyEvent event) {
        FilteredList<Article> items = new FilteredList<>(FXCollections.observableArrayList(articles));
        items.setPredicate(item ->{
            String lower = vente_designation.getEditor().getText().toLowerCase();
            String upper = vente_designation.getEditor().getText().toUpperCase();
            if(item.getDesignation().contains(lower) || item.getDescription().contains(lower))
                return true;
            else
                return item.getDesignation().contains(upper) || item.getDescription().contains(upper);
        });
        SortedList<Article> sorted = new SortedList<>(items);
        vente_designation.setItems(sorted);
    }

    @FXML
    void ventes_date_debut(ActionEvent event) {
        LocalDate localDate = ventes_date_debut.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        debut = Date.from(zonedDateTime.toInstant());

        VenteRepository venteRepository = new VenteRepository();
        List<Vente> ventes = venteRepository.find(sdf.format(debut));
        read_vente(ventes);
    }

    @FXML
    void ventes_date_fin(ActionEvent event) {
        LocalDate localDate = ventes_date_fin.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        fin = Date.from(zonedDateTime.toInstant());

        if(!(debut==null) && debut.before(fin) || Objects.equals(debut, fin)){
            VenteRepository venteRepository = new VenteRepository();
            List<Vente> ventes = venteRepository.find(sdf.format(debut),sdf.format(fin));
            read_vente(ventes);
        }else{
            AlertWarning.getInstance().setHeaderText("Ventes");
            AlertWarning.getInstance().setContentText("Une erreur s'est produite lors de la sélection des dates.\n Vérifiez et reessayez.");
            AlertWarning.getInstance().showAndWait();
        }
    }

    @FXML
    void achats_date_debut(ActionEvent event) {
        LocalDate localDate = achats_date_debut.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        debut = Date.from(zonedDateTime.toInstant());

        AchatRepository achatRepository = new AchatRepository();
        List<Achat> achats = achatRepository.find(sdf.format(debut));
        read_achat(achats);
    }

    @FXML
    void achats_date_fin(ActionEvent event) {
        LocalDate localDate = achats_date_fin.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        fin = Date.from(zonedDateTime.toInstant());

        if(!(debut==null) && debut.before(fin) || Objects.equals(debut, fin)){
            AchatRepository achatRepository = new AchatRepository();
            List<Achat> achats = achatRepository.find(sdf.format(debut),sdf.format(fin));
            read_achat(achats);
        }else{
            AlertWarning.getInstance().setHeaderText("Approvisionnements");
            AlertWarning.getInstance().setContentText("Une erreur s'est produite lors de la sélection des dates.\n Vérifiez et reessayez.");
            AlertWarning.getInstance().showAndWait();
        }
    }

    @FXML
    void fermerAchats(ActionEvent event) {
        achats_date_debut.getEditor().setText("");
        achats_date_fin.getEditor().setText("");
        AchatRepository achatRepository = new AchatRepository();
        List<Achat> achats = achatRepository.find(sdf.format(new Date()));
        table_achats.setItems(FXCollections.observableArrayList(achats));
    }

    @FXML
    void fermerVentes(ActionEvent event) {
        ventes_date_debut.getEditor().setText("");
        ventes_date_fin.getEditor().setText("");
        VenteRepository venteRepository = new VenteRepository();
        List<Vente> ventes = venteRepository.find(sdf.format(new Date()));
        table_ventes.setItems(FXCollections.observableArrayList(ventes));
    }

    @FXML
    void filtrer(KeyEvent event) {
        FilteredList<Article> items = new FilteredList<>(FXCollections.observableArrayList(articles));
        items.setPredicate(item ->{
            String lower = filtre.getText().toLowerCase();
            String upper = filtre.getText().toUpperCase();
            if(item.getDesignation().contains(lower) || item.getDescription().contains(lower))
                return true;
            else
                return item.getDesignation().contains(upper) || item.getDescription().contains(upper);
        });
        SortedList<Article> sorted = new SortedList<>(items);
        table_article.setItems(sorted);
    }

    @FXML
    void printAchats(ActionEvent event) {
        if(!(debut==null) && !(fin==null)){
            Report.getInstance().createReport("/jrxml/achat_periodique.jrxml",debut,fin);
        }else if(!(debut==null)){
            Report.getInstance().createReport("/jrxml/achat.jrxml",debut);
        }else{
            Report.getInstance().createReport("/jrxml/achat.jrxml",new Date());
        }
    }

    @FXML
    void printVentes(ActionEvent event) {
        if(!(debut==null) && !(fin==null)){
            Report.getInstance().createReport("/jrxml/vente_periodique.jrxml",debut,fin);
        }else if(!(debut == null)){
            Report.getInstance().createReport("/jrxml/vente.jrxml",debut);
        }else{
            Report.getInstance().createReport("/jrxml/vente.jrxml",new Date());
        }
    }

    @FXML
    void printStock(ActionEvent event) {
    }

    public void read_article(){
        ArticleRepository articleRepository = new ArticleRepository();
        this.articles = articleRepository.findAll();
        table_article.setItems(FXCollections.observableArrayList(articles));
        achat_designation.setItems(FXCollections.observableArrayList(articles));
        vente_designation.setItems(FXCollections.observableArrayList(articles));
    }

    @FXML
    void sortir(ActionEvent event) {
        ArticleRepository articleRepository = new ArticleRepository();
        VenteRepository venteRepository = new VenteRepository();
        for(Vente vente : ventes){
            try {
                venteRepository.save(vente);
                Article up = vente.getArticle();
                up.setStock(up.getStock() - vente.getQuantite());
                up.setPrix(vente.getPrix());
                articleRepository.update(up);
            } catch (Exception e) {
                AlertError.getInstance().setHeaderText("Vente");
                AlertError.getInstance().setContentText("Une erreur est survenue.\n"+e.getMessage());
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }
        }
        table_vente.setItems(null);
        table_vente.refresh();
    }

    @FXML
    void stocker(ActionEvent event) {
        ArticleRepository articleRepository = new ArticleRepository();

        AchatRepository achatRepository = new AchatRepository();

        for(Achat achat : achats){
            try {
                achatRepository.save(achat);
                Article up = achat.getArticle();
                up.setStock(achat.getQuantite() + up.getStock());
                up.setPrixAchat(up.getPrix());
                articleRepository.update(up);
            } catch (Exception e) {
                AlertError.getInstance().setHeaderText("Approvisionnement");
                AlertError.getInstance().setContentText("Une erreur est survenue.\n"+e.getMessage());
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }
        }
        table_achat.setItems(null);
        table_achat.refresh();
    }

    void read_vente(List<Vente> ventes){
        table_ventes.setItems(FXCollections.observableArrayList(ventes));
        double total = 0;
        for (Vente vente:ventes) {
            total+=vente.getMontant();
        }
        vente_total.setText(Money.getInstance().format(total)+" F (CFA)");
    }

    void read_achat(List<Achat> achats){
        table_achats.setItems(FXCollections.observableArrayList(achats));
        double total = 0;
        for (Achat achat:achats) {
            total+=achat.getMontant();
        }
        achat_total.setText(Money.getInstance().format(total)+" F (CFA)");
    }

    public void selectTab(int i) {
        stock_tab.getSelectionModel().select(getTab(i));
    }

    public Tab getTab(int i) {
        Tab tab = null;
        switch (i) {
            case 1: {
                tab = tab_achat;
                break;
            }
            case 2: {
                tab = tab_vente;
                break;
            }
            case 3: {
                tab = tab_stock;
                break;
            }
            case 4:{
                tab = tab_article;
            }
        }
        return tab;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        achats = new ArrayList<>();
        ventes = new ArrayList<>();

        read_article();

        stock_tab.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                switch (tab.getTabPane().getSelectionModel().getSelectedIndex()) {
                    case 0:{
                        table_achat.setItems(null);
                        reset_achat_form();
                        break;
                    }
                    case 1:{
                        table_vente.setItems(null);
                        reset_vente_form();
                        break;
                    }
                    case 2:{
                        table_article.setItems(FXCollections.observableArrayList(articles));
                        break;
                    }
                    case 4:{
                        VenteRepository venteRepository = new VenteRepository();
                        List<Vente> ventes = venteRepository.find(sdf.format(new Date()));
                        read_vente(ventes);
                        break;
                    }
                    case 5:{
                        AchatRepository achatRepository = new AchatRepository();
                        List<Achat> achats = achatRepository.find(sdf.format(new Date()));
                        read_achat(achats);
                        break;
                    }
                }
            }
        });

        achat_categorie.setItems(FXCollections.observableArrayList(Article.getCategories()));

        article_categorie.setItems(FXCollections.observableArrayList(Article.getCategories()));

        achat_designation.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Article>() {
            @Override
            public void changed(ObservableValue ov, Article old, Article current) {
                article_achat = current;
                achat_description.setText(current.getDescription());
                achat_prix.setText(String.valueOf(current.getPrixAchat()));
            }
        });

        achat_designation.setConverter(new StringConverter<Article>() {
            @Override
            public String toString(Article object) {
                if(object==null){
                    return null;
                }
                return object.toString();
            }

            @Override
            public Article fromString(String string) {
                ArticleRepository articleRepository = new ArticleRepository();
                return articleRepository.find(achat_designation.getEditor().getText().trim());
            }
        });

        vente_designation.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Article>() {
            @Override
            public void changed(ObservableValue ov, Article old, Article current) {
                article_vente = current;
                vente_description.setText(current.getDescription());
                vente_prix.setText(String.valueOf(current.getPrix()));
            }
        });

        vente_designation.setConverter(new StringConverter<Article>() {
            @Override
            public String toString(Article object) {
                if(object==null){
                    return null;
                }
                return object.toString();
            }

            @Override
            public Article fromString(String string) {
                ArticleRepository articleRepository = new ArticleRepository();
                return articleRepository.find(vente_designation.getEditor().getText().trim());
            }
        });

        table_achat_id.setCellValueFactory((TableColumn.CellDataFeatures<Achat, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getArticle().getId()));
        table_achat_date.setCellValueFactory((TableColumn.CellDataFeatures<Achat, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-yyyy").format(r.getValue().getDate())));
        table_achat_description.setCellValueFactory((TableColumn.CellDataFeatures<Achat, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getArticle().getDescription()));
        table_achat_designation.setCellValueFactory((TableColumn.CellDataFeatures<Achat, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getArticle().getDesignation()));
        table_achat_fournisseur.setCellValueFactory((TableColumn.CellDataFeatures<Achat, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getFournisseur()));
        table_achat_quantite.setCellValueFactory((TableColumn.CellDataFeatures<Achat, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getQuantite()));
        table_achat_montant.setCellValueFactory((TableColumn.CellDataFeatures<Achat, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));
        table_achat_prix.setCellValueFactory((TableColumn.CellDataFeatures<Achat, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getPrix()));

        table_vente_id.setCellValueFactory((TableColumn.CellDataFeatures<Vente, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getArticle().getId()));
        table_vente_client.setCellValueFactory((TableColumn.CellDataFeatures<Vente, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getClient()));
        table_vente_date.setCellValueFactory((TableColumn.CellDataFeatures<Vente, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-yyyy").format(r.getValue().getDate())));
        table_vente_description.setCellValueFactory((TableColumn.CellDataFeatures<Vente, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getArticle().getDescription()));
        table_vente_designation.setCellValueFactory((TableColumn.CellDataFeatures<Vente, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getArticle().getDesignation()));
        table_vente_prix.setCellValueFactory((TableColumn.CellDataFeatures<Vente, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getPrix()));
        table_vente_quantite.setCellValueFactory((TableColumn.CellDataFeatures<Vente, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getQuantite()));
        table_vente_montant.setCellValueFactory((TableColumn.CellDataFeatures<Vente, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));

        table_article_id.setCellValueFactory((TableColumn.CellDataFeatures<Article, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        table_article_description.setCellValueFactory((TableColumn.CellDataFeatures<Article, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDescription()));
        table_article_designation.setCellValueFactory((TableColumn.CellDataFeatures<Article, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDesignation()));
        table_article_prix.setCellValueFactory((TableColumn.CellDataFeatures<Article, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getPrix()));
        table_article_quantite.setCellValueFactory((TableColumn.CellDataFeatures<Article, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getStock()));

        achats_date.setCellValueFactory((TableColumn.CellDataFeatures<Achat, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-yyyy").format(r.getValue().getDate())));
        achats_article.setCellValueFactory((TableColumn.CellDataFeatures<Achat, Article> r)->new ReadOnlyObjectWrapper<>(r.getValue().getArticle()));
        achats_fournisseur.setCellValueFactory((TableColumn.CellDataFeatures<Achat, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getFournisseur()));
        achats_quantite.setCellValueFactory((TableColumn.CellDataFeatures<Achat, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getQuantite()));
        achats_montant.setCellValueFactory((TableColumn.CellDataFeatures<Achat, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));
        achats_prix.setCellValueFactory((TableColumn.CellDataFeatures<Achat, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getPrix()));

        ventes_date.setCellValueFactory((TableColumn.CellDataFeatures<Vente, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-yyyy").format(r.getValue().getDate())));
        ventes_article.setCellValueFactory((TableColumn.CellDataFeatures<Vente, Article> r)->new ReadOnlyObjectWrapper<>(r.getValue().getArticle()));
        ventes_client.setCellValueFactory((TableColumn.CellDataFeatures<Vente, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getClient()));
        ventes_quantite.setCellValueFactory((TableColumn.CellDataFeatures<Vente, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getQuantite()));
        ventes_montant.setCellValueFactory((TableColumn.CellDataFeatures<Vente, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));
        ventes_prix.setCellValueFactory((TableColumn.CellDataFeatures<Vente, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getPrix()));
    }
}

