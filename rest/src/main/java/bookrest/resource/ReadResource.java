package bookrest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aerospike.client.AerospikeException;
import com.aerospike.client.ResultCode;
import com.google.common.collect.ImmutableMap;

import bookrest.controller.ReadController;

@Path("/")
public class ReadResource {

    /**
     * Read controller we use to use aerospike
     */
    private final ReadController readController;

    /**
     * Default Constr.
     * 
     * @param readController the controller we use to read
     */
    public ReadResource(ReadController readController) {
        this.readController = readController;
    }

    /**
     * Function run when we receive a request for asset information
     * @param lineNumber the line number we are looking up
     * @return A {@link Response} that has a line in it. Or some error response
     */
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lines/{lineNumber}")
    @GET
    public Response getLineNumber(@PathParam("lineNumber") int lineNumber) {
       
        // Attempt to lookup
        try {
            ImmutableMap<String, String> outputMap = ImmutableMap.of("line", readController.getLine(lineNumber));

            return Response.status(200).entity(Entity.json(outputMap)).build();
        } catch (AerospikeException exception) {

            // If the key had no response, throw a 413
            if (exception.getResultCode() == ResultCode.KEY_NOT_FOUND_ERROR) {
                return Response.status(413).build();
            }
        }

        return Response.serverError().build();
    }
}
