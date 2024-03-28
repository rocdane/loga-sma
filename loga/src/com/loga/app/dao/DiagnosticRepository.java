package com.loga.app.dao;

import com.loga.model.Diagnostic;
import com.loga.vendor.Repository;

public class DiagnosticRepository extends Repository<Diagnostic,Long>{
    public DiagnosticRepository() {
        super(Diagnostic.class);
    }
}
