package com.louis.dubbo;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * traceId 测试接口
 */
@Path("/TraceIdTestService")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_JSON})
public interface TraceIdTestService {

    @GET
    @Path("doTraceId")
    public String doTraceId(String param);

}
