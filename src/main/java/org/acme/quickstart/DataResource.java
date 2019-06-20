package org.acme.quickstart;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/dbtest")
public class DataResource {
    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sample> getSample() {
        return this.em.createNativeQuery("select * from sample", Sample.class).getResultList();
    }
}
