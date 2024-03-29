package org.acme.quickstart;

import io.vertx.core.Vertx;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/vertx")
public class VertxResource {
    @Inject
    Vertx vertx;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}")
    public CompletionStage<String> greeting(@PathParam("name") String name) {
        CompletableFuture<String> future = new CompletableFuture<>();
        long start = System.nanoTime();

        vertx.setTimer(10, l -> {
            long duration = TimeUnit.MILLISECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS);
            String message = String.format("Hello %s! (%d ms)%n", name, duration);
            future.complete(message);
        });

        return future;
    }
}
