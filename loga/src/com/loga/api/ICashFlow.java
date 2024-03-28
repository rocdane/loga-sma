package com.loga.api;

import java.util.Date;

/**
 * Interface de l'application LoGa représentant les opérations du flux financier.
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 * @since 2.0
 */
public interface ICashFlow {
    String getLibelle();
    double getCost();
    Date getTemporal();
}
