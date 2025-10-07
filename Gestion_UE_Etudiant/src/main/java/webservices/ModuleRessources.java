package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/module")
public class ModuleRessources {

    private static ModuleBusiness moduleBusiness = new ModuleBusiness();
    private static UniteEnseignementBusiness ueBusiness = new UniteEnseignementBusiness();

    // Lister tous les modules
    @GET
    @Path("/liste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response liste() {
        List<Module> modules = moduleBusiness.getAllModules();
        return Response.status(200).entity(modules).build();
    }

    // Ajouter un module
    @POST
    @Path("/ajout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response ajout(Module module) {
        if (moduleBusiness.addModule(module)) {
            return Response.status(201).entity("Module added successfully").build();
        } else {
            return Response.status(409).entity("Module could not be added").build();
        }
    }

    // Supprimer un module
    @DELETE
    @Path("/delete/{matricule}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("matricule") String matricule) {
        if (moduleBusiness.deleteModule(matricule)) {
            return Response.status(200).entity("Module deleted successfully").build();
        } else {
            return Response.status(404).entity("Module not found").build();
        }
    }

    // Mettre à jour un module
    @PUT
    @Path("/update/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(@PathParam("matricule") String matricule, Module module) {
        if (moduleBusiness.updateModule(matricule, module)) {
            return Response.status(200).entity("Module updated successfully").build();
        } else {
            return Response.status(404).entity("Module not found").build();
        }
    }

    // Récupérer un module par matricule
    @GET
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModule(@PathParam("matricule") String matricule) {
        Module module = moduleBusiness.getModuleByMatricule(matricule);
        if (module != null) {
            return Response.status(200).entity(module).build();
        } else {
            return Response.status(404).entity("Module not found").build();
        }
    }

    // Récupérer les modules par type
    @GET
    @Path("/type")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByType(@QueryParam("type") String type) {
        Module.TypeModule typeModule;
        try {
            typeModule = Module.TypeModule.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.status(400).entity("Invalid module type").build();
        }
        List<Module> modules = moduleBusiness.getModulesByType(typeModule);
        return Response.status(200).entity(modules).build();
    }

    // Récupérer les modules d'une UE
    @GET
    @Path("/ue/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUE(@PathParam("code") int code) {
        UniteEnseignement ue = ueBusiness.getUEByCode(code);
        if (ue == null) {
            return Response.status(404).entity("UE not found").build();
        }
        List<Module> modules = moduleBusiness.getModulesByUE(ue);
        return Response.status(200).entity(modules).build();
    }
}