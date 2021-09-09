package com.louis.dubbo;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/DubboService2")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_JSON })
public interface DubboService2 {

    @GET
    @Path("DubboService2")
    String dubboService2();
}
