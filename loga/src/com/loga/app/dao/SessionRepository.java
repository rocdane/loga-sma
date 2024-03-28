package com.loga.app.dao;

import com.loga.model.Session;
import com.loga.vendor.Repository;

public class SessionRepository extends Repository<Session,Long> {

    public SessionRepository() {
        super(Session.class);
    }
}
