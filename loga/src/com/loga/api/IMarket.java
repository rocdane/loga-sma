package com.loga.api;

import com.loga.model.*;

import java.util.Date;
import java.util.List;

/**
 * Interface de l'application LoGa représentant les opérations du service Vente
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 * @since 2.0
 */
public interface IMarket {

    /**
     * Regroupement des opérations de logistique
     * */
    Fournisseur enregistrerFournisseur(Fournisseur fournisseur);
    Fournisseur afficherFournisseur(long id);
    boolean supprimerFournisseur(Fournisseur fournisseur);
    Fourniture afficherFourniture(long id);
    boolean modifierFourniture(Fourniture fourniture);

    Commande enregistrerCommande(Commande commande);
    List<Commande> listerCommande();
    List<Commande> chercherCommande(String immatriculation);
    Livraison enregistrerLivraison(Livraison livraison);
    List<Livraison> listerLivraison();

    /**
     * Regroupement des opérations de stock
     * */
    Article enregistrerArticle(Article article);
    List<Article> listerArticle();
    List<Article> chercherArticle(String text);
    Article afficherArticle(long id);
    Article afficherArticle(String txt);
    boolean modifierArticle(Article up);

    Achat enregistrerAchat(Achat achat);
    List<Achat> listerAchat();
    List<Achat> listerAchat(Date date);
    List<Achat> listerAchat(Date debut,Date fin);
    Achat afficherAchat(long id);

    Vente enregistrerVente(Vente vente);
    List<Vente> listerVente();
    List<Vente> listerVente(Date date);
    List<Vente> listerVente(Date debut,Date fin);
    Vente afficherVente(long id);
}
