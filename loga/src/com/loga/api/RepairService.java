package com.loga.api;

import com.loga.app.dao.ReceptionRepository;
import com.loga.model.Etat;
import com.loga.model.Reception;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "Repair")
public class RepairService {

    @WebMethod
    public Reception enregistrerReception(@WebParam(name = "reception") Reception reception) throws Exception {
        ReceptionRepository receptionRepository = new ReceptionRepository();
        long id = receptionRepository.save(reception);

        Reception saved = receptionRepository.findById(id);

        for (Etat line:reception.getEtats()){
            line.setReception(reception);
        }

        receptionRepository.persist(saved);

        return receptionRepository.findById(id);
    }
}
