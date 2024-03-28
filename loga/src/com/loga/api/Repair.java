package com.loga.api;

import com.loga.app.dao.*;
import com.loga.model.*;

import java.util.List;

/**
 * TODO:application de l'injection de dépendance
 * Implémentation de l'interface IRepair représentant les opérations du service Atelier.
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 * @since 2.0
 */
public class Repair implements IRepair{
    private static Repair repair;

    private Repair(){
    }

    public static Repair getInstance(){
        if (repair==null){
            repair = new Repair();
        }
        return repair;
    }

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Reception dans la base de données.
     * @param reception
     * @return Reception
     * @see Etat
     * */
    @Override
    public Reception enregistrerReception(Reception reception) throws Exception {
        ReceptionRepository receptionRepository = new ReceptionRepository();
        long id = receptionRepository.save(reception);

        Reception saved = receptionRepository.findById(id);

        for (Etat line:reception.getEtats()){
            line.setReception(reception);
        }

        receptionRepository.persist(saved);

        return receptionRepository.findById(id);
    }

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Diagnostic dans la base de données.
     * @param diagnostic
     * @return Diagnostic
     * @see Defaut,Solution
     */
    @Override
    public Diagnostic enregistrerDiagnostic(Diagnostic diagnostic) throws Exception {
        DiagnosticRepository diagnosticRepository = new DiagnosticRepository();

        long id = diagnosticRepository.save(diagnostic);

        Diagnostic saved = diagnostic = diagnosticRepository.findById(id);
        saved.setDefauts(diagnostic.getDefauts());
        saved.setSolutions(diagnostic.getSolutions());

        DefautRepository defautRepository = new DefautRepository();
        for (Defaut defaut : saved.getDefauts()) {
            defaut.setDiagnostic(saved);
            defautRepository.save(defaut);
        }

        SolutionRepository solutionRepository = new SolutionRepository();
        for (Solution solution : saved.getSolutions()) {
            solution.setDiagnostic(saved);
            solutionRepository.save(solution);
        }

        diagnosticRepository.persist(saved);

        return diagnosticRepository.findById(id);
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Reparation dans une collection
     * @return List
     */
    @Override
    public List<Reparation> listerReparation() {
        ReparationRepository reparationRepository = new ReparationRepository();
        return reparationRepository.findAll();
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Reparation dans une collection
     * @return List
     */
    @Override
    public List<Reparation> chercherReparation(String txt) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner un objet Reparation dans la base de données.
     * @param id
     * @return Reparation
     */
    @Override
    public Reparation afficherReparation(long id) {
        return null;
    }

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Reparation
     * @param reparation
     * @return Reparation
     * @see Tache,Fourniture
     */
    @Override
    public Reparation enregistrerReparation(Reparation reparation) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de mettre à jour un objet Reparation dans la base de données.
     * @param up
     * @return Reparation
     */
    @Override
    public boolean mofifierReparation(Reparation up) {
        return false;
    }

    /**
     * TODO:Cette méthode permet de supprimer un objet Reparation de la base de données.
     * @param reparation
     */
    @Override
    public boolean supprimerReparation(Reparation reparation) {
        return false;
    }

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Qualite dans la base de données.
     * @param qualite
     * @return Qualite
     * @see Reparation,Controle
     */
    @Override
    public Qualite enregistrerControleQualite(Qualite qualite) {
        return null;
    }
}
