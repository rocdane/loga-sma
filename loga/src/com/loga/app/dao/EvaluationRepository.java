package com.loga.app.dao;

import com.loga.model.Evaluation;
import com.loga.vendor.Repository;

public class EvaluationRepository extends Repository<Evaluation,Long> {
    public EvaluationRepository() {
        super(Evaluation.class);
    }
}
