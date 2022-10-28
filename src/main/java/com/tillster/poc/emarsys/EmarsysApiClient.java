package com.tillster.poc.emarsys;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import java.util.List;

public interface EmarsysApiClient {

    @POST
    @Consumes("text/csv")
    @Produces("text/plain")
    String createOrder(List<SampleObject> orders);
}
