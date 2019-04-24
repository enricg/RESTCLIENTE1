/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restisdcm;

import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import modelo.Video;

/**
 * REST Web Service
 *
 * @author enric
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Sample of GET method
     *
     * @param info
     * @param fecha
     * @return
     */
    @Path("getInfo")
    @GET
    @Produces("text/html")
    public String freeSeats(@QueryParam("info") String info,
            @QueryParam("fecha") String fecha) {

        return "<html><head></head> <body> Info " + info + " en fecha " + fecha + " </body></html>";
    }

    /**
     * Sample of POST method
     *
     * @param info
     * @param fecha
     * @return
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/html")
    public Response  postInfo(@FormParam("videoID") String videoID) throws SQLException {
        Video v = new Video();
        Boolean isUpdate = v.addNumRep(Integer.parseInt(videoID));
        if (isUpdate){
            return Response.ok().build();
        }
            return Response.status(Status.NOT_ACCEPTABLE).build();   
    }
}
