package com.loga.api;

import com.loga.app.dao.CalendrierRepository;
import com.loga.model.Calendrier;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "Manage")
public class ManageService {

    @WebMethod
    public Calendrier enregistrerCalendrier(@WebParam(name = "calendrier") Calendrier calendrier) throws Exception {
        CalendrierRepository calendrierRepository = new CalendrierRepository();
        calendrierRepository.persist(calendrier);
        return calendrier;
    }
}
