package com.loga.vendor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static Logger logger;
    private String log;

    private Logger(){
        log = new String();
    }

    public static Logger getInstance(){
        if(logger==null){
            logger = new Logger();
        }
        return logger;
    }

    public void ajouterLog(String log) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH'h'mm");
        this.log+="["+dateFormat.format(date)+"]"+log+"\n";
    }

    public String afficherLog() {
        return log;
    }
}
