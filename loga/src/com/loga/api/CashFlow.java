package com.loga.api;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Classe de représentation du flux financier
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 */
public class CashFlow implements Serializable {

    private ArrayList<ICashFlow> ICashFlow;

    public ArrayList<ICashFlow> getCashFlow() {
        return ICashFlow;
    }

    public void setCashFlow(ArrayList<ICashFlow> ICashFlow) {
        this.ICashFlow = ICashFlow;
    }

    public void addCashFlow(ICashFlow ICashFlow){
        this.ICashFlow.add(ICashFlow);
    }

    /**
     * Cette méthode permet de calculer le flux financier
     * @return Double
     */
    public double calculateCashFlow(){
        double cost = 0;
        for (ICashFlow ICashFlow :this.ICashFlow) {
            cost+= ICashFlow.getCost();
        }
        return cost;
    }

    /**
     * Cette méthode permet d'afficher le flux financier
     * @return
     */
    public StringBuilder showCashFlow(){
        StringBuilder stringBuilder = new StringBuilder();
        for (ICashFlow ICashFlow :this.ICashFlow) {
            stringBuilder.append(ICashFlow.getLibelle())
                    .append(" / ")
                    .append(ICashFlow.getCost())
                    .append(" / ")
                    .append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ICashFlow.getTemporal()))
                    .append("\n");
        }
        return stringBuilder;
    }
}
