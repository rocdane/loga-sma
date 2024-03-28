package com.loga.api;

import com.loga.model.*;

import java.util.List;

/**
 * Interface de l'application LoGa représentant les opérations du service Atelier.
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 * @since 2.0
 */
public interface IRepair {
    // Reception
    Reception enregistrerReception(Reception reception) throws Exception;
    Diagnostic enregistrerDiagnostic(Diagnostic diagnostic) throws Exception;

    // Reparation
    Reparation enregistrerReparation(Reparation reparation);
    List<Reparation> listerReparation();
    List<Reparation> chercherReparation(String txt);
    Reparation afficherReparation(long id);
    boolean mofifierReparation(Reparation up);
    boolean supprimerReparation(Reparation reparation);
    Qualite enregistrerControleQualite(Qualite qualite);
}
