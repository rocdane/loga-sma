package com.loga.api;

import com.loga.model.Fournisseur;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "Market")
public class MarketService {
    @WebMethod
    public Fournisseur enregistrerFournisseur(@WebParam(name = "fournisseur") Fournisseur fournisseur) {
        return null;
    }
}

