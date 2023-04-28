package com.dt180g.laboration_3.support;


import java.util.logging.Logger;


public class HanoiLogger {
    private static HanoiLogger instance;
    private Logger logger;


    public static HanoiLogger getInstance() {
        if (instance == null) {
            instance = new HanoiLogger();
        }

        return instance;
    }

//    public closeLogger() {
//
//    }

    private void initializeLogger() {

    }

    private HanoiLogger() { initializeLogger(); }


}
