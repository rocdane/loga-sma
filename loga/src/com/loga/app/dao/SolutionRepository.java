package com.loga.app.dao;

import com.loga.model.Solution;
import com.loga.vendor.Repository;

public class SolutionRepository extends Repository<Solution,Long>{
    public SolutionRepository() {
        super(Solution.class);
    }
}
