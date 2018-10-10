package com.revolut.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;


import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.UndertowOptions;

public class RestServer {
    private UndertowJaxrsServer server;
    public RestServer() {
    }

    public void start() throws Exception {
        server = new UndertowJaxrsServer().start(buildServer());
        server.deploy(RestApplication.class, "/");
        Logger.getLogger(RestServer.class.getName()).log(Level.INFO, String.format("=============== Server started"));
        Logger.getLogger(RestServer.class.getName()).log(Level.INFO, String.format("=============== Listening on: %s port {%d}", "127.0.0.1", 8888));
       
        System.out.println();
    }

    public void stop() {
        if (server != null) {
            server.stop();
        }
    }

    protected Undertow.Builder buildServer() throws Exception {


        return Undertow.builder()
                .setServerOption(UndertowOptions.ENABLE_HTTP2, true)
                .addHttpListener( 8888,"127.0.0.1")
                .setHandler(Handlers.gracefulShutdown(Handlers.path()));
    }



}
