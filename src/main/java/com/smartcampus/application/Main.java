package com.smartcampus.application;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

/**
 *
 * @author anudhi
 */


@ApplicationPath("/api/v1")
public class Main extends Application {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static final String BASE_URI = "http://localhost:8080/";

    public static void main(String[] args) throws IOException, InterruptedException {
        final ResourceConfig config = new ResourceConfig()
                .packages("com.smartcampus.resources",
                          "com.smartcampus.exceptions",
                          "com.smartcampus.filters")
                .register(JacksonFeature.class);

        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
                URI.create("http://localhost:8080/api/v1/"), config);

        LOGGER.info("Smart Campus API started.");
        LOGGER.info("Discovery endpoint: http://localhost:8080/api/v1");
        LOGGER.info("Press Ctrl+C to stop.");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Stopping server...");
            server.shutdownNow();
        }));
        Thread.currentThread().join();
    }
}