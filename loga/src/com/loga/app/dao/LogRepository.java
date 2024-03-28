package com.loga.app.dao;

import com.loga.app.controller.GuiController;
import com.loga.model.Log;
import com.loga.model.User;
import com.loga.vendor.Repository;

import java.util.Date;
import java.util.List;

public class LogRepository extends Repository<Log,Long> {
    private static LogRepository instance;

    private LogRepository() {
        super(Log.class);
    }

    public static LogRepository getInstance() {
        if (instance == null){
            instance = new LogRepository();
        }
        return instance;
    }

    public void write(String event, String description, User user) {
        Log log = new Log();
        log.setEvent(event);
        log.setDescription(description);
        log.setAt(new Date());
        log.setSession(GuiController.getSession());
        try {
            save(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Log> read() {
        return findAll();
    }
}
