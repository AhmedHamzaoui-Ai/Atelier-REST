package ressources;

import entities.Option;
import metiers.OptionBusiness;
import utilities.Metier;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("options")
public class OptionRessource {

    private final OptionBusiness business = Metier.OPTIONS;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOption(Option option) {
        if (option != null && business.addOption(option)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOptions(@QueryParam("domaine") String domaine) {
        List<Option> result = (domaine == null || domaine.isEmpty())
                ? business.getListeOptions()
                : business.getOptionsByDomaine(domaine);
        return Response.ok(result).build();
    }

    @GET
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOption(@PathParam("code") int code) {
        Option option = business.getOptionByCode(code);
        if (option == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(option).build();
    }

    @PUT
    @Path("{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOption(@PathParam("code") int code, Option option) {
        if (option != null && business.updateOption(code, option)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{code}")
    public Response deleteOption(@PathParam("code") int code) {
        if (business.deleteOption(code)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
