package com.loga.api;

import com.loga.app.dao.AchatRepository;
import com.loga.app.dao.ArticleRepository;
import com.loga.app.dao.VenteRepository;
import com.loga.model.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * TODO:application de l'injection de dépendance
 * Implémentation de l'interface IMarket représentant les opérations du service Vente
 * @author rochdane sabi rocdanesabi@gmail.com
 */
public class Market extends RecursiveAction implements IMarket{
    private static Market market;

    private Market(){
    }

    @Override
    protected void compute() {
    }

    public static Market getInstance(){
        if(market==null){
            market = new Market();
        }
        return market;
    }

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Fournisseur dans la base de données.
     * @param fournisseur
     * @return Fournisseur
     */
    @Override
    public Fournisseur enregistrerFournisseur(Fournisseur fournisseur) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner un objet Fournisseur de la base de données.
     * @param id
     * @return Fournisseur
     */
    @Override
    public Fournisseur afficherFournisseur(long id) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de supprimer un objet Fournisseur de la base de données.
     * @param fournisseur
     */
    @Override
    public boolean supprimerFournisseur(Fournisseur fournisseur) {
        return false;
    }

    /**
     * TODO:Cette méthode permet de sélectionner un objet Fourniture de la base de données.
     * @param id
     * @return Fourniture
     */
    @Override
    public Fourniture afficherFourniture(long id) {
        return null;
    }

    /**
     * TODO : Cette méthode permet de modifier un objet Fourniture dans la base de données.
     * @param fourniture
     */
    @Override
    public boolean modifierFourniture(Fourniture fourniture) {
        return false;
    }

    /**
     * TODO : Cette méthode permet d'enregistrer un objet Commande dans la base de donnnées.
     * @param commande
     * @return Commande
     * @see Fourniture
     */
    @Override
    public Commande enregistrerCommande(Commande commande) {
        return null;
    }

    /**
     * TODO : Cette méthode permet de sélectionner tous les objets Commande de la base de données dans une collection.
     * @return
     */
    @Override
    public List<Commande> listerCommande() {
        return null;
    }

    @Override
    public List<Commande> chercherCommande(String immatriculation) {
        return null;
    }

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Livraison dans la base de données.
     * @param livraison
     * @return Livraison
     * @see Fourniture
     */
    @Override
    public Livraison enregistrerLivraison(Livraison livraison) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Livraison de la base de données.
     * @return List
     */
    @Override
    public List<Livraison> listerLivraison() {
        return null;
    }

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Article dans la base de données.
     * @param article
     * @return Article
     */
    @Override
    public Article enregistrerArticle(Article article) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Article de la base de données.
     * @return List
     */
    @Override
    public List<Article> listerArticle() {
        ArticleRepository articleRepository = new ArticleRepository();
        return articleRepository.findAll();
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Article de la base de données.
     * @return List
     */
    @Override
    public List<Article> chercherArticle(String text) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner un objet Article de la base de données.
     * @param id
     * @return Article
     */
    @Override
    public Article afficherArticle(long id) {
        return null;
    }

    @Override
    public Article afficherArticle(String txt) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de mettre à jour un objet Article de la base de données.
     * @param up
     */
    @Override
    public boolean modifierArticle(Article up) {
        return false;
    }

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Achat dans la base de données.
     * @param achat
     * @return Achat
     * @see Article
     */
    @Override
    public Achat enregistrerAchat(Achat achat) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Achat de la base de données.
     * @return List
     */
    @Override
    public List<Achat> listerAchat() {
        AchatRepository achatRepository = new AchatRepository();
        return achatRepository.findAll();
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Achat de la base de données.
     * @param date
     * @return List
     */
    @Override
    public List<Achat> listerAchat(Date date) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Achat de la base de données.
     * @param debut,fin
     * @return List
     */
    @Override
    public List<Achat> listerAchat(Date debut, Date fin) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner un objet Achat de la base de données.
     * @param id
     * @return Achat
     */
    @Override
    public Achat afficherAchat(long id) {
        return null;
    }

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Vente de la base de données.
     * @param vente
     * @return Vente
     * @see Article
     */
    @Override
    public Vente enregistrerVente(Vente vente) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Vente de la base de données.
     * @return List
     */
    @Override
    public List<Vente> listerVente() {
        VenteRepository venteRepository = new VenteRepository();
        return venteRepository.findAll();
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Vente de la base de données.
     * @param date
     * @return List
     */
    @Override
    public List<Vente> listerVente(Date date) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Vente de la base de données.
     * @param debut,fin
     * @return List
     */
    @Override
    public List<Vente> listerVente(Date debut, Date fin) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner un objet Vente de la base de données.
     * @param id
     * @return Vente
     */
    @Override
    public Vente afficherVente(long id) {
        return null;
    }
}
