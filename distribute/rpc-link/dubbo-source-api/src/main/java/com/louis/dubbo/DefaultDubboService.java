package com.louis.dubbo;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * @author louis
 * <p>
 * Date: 2019/9/16
 * Description:
 */
@Path("/default")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_JSON })
public interface DefaultDubboService {


        @GET
    @Path("selectByOpenId/{name}")
    Map<String, Object> sayHello(@PathParam("name") String name);
}
