package com.revolut.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.revolut.server.RestServer;

public class Runner {
    private static RestServer server;

    public static void main(String[] args) throws Exception {
        server = new RestServer();
        server.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Logger.getLogger(Runner.class.getName()).log(Level.INFO, String.format("=============== Shutting down..."));
                server.stop();
                server = null;
                Logger.getLogger(Runner.class.getName()).log(Level.INFO, String.format("=============== Server shutdown complete..."));
            }
        });
    }
}
