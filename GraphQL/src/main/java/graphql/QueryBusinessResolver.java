package graphql;

import business.ModuleBusiness;
import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entities.Module;
import entities.UniteEnseignement;

import java.util.List;

/**
 * GraphQL Query Resolver for Module entity.
 * Handles all read operations (queries) related to Modules.
 */
public class QueryBusinessResolver implements GraphQLRootResolver {

    private ModuleBusiness moduleBusiness;

    public QueryBusinessResolver() {
        moduleBusiness = new ModuleBusiness();
    }

    /**
     * GraphQL Query: allModules
     * Returns all modules in the system.
     */
    public List<Module> allModules() {
        return moduleBusiness.getAllModules();
    }

    /**
     * GraphQL Query: moduleByMatricule
     * Returns a single module by its matricule (ID).
     */
    public Module moduleByMatricule(String matricule) {
        return moduleBusiness.getModuleByMatricule(matricule);
    }

    /**
     * GraphQL Query: modulesByType
     * Returns a list of modules filtered by their type.
     */
    public List<Module> modulesByType(Module.TypeModule type) {
        return moduleBusiness.getModulesByType(type);
    }

    /**
     * GraphQL Query: modulesByUE
     * Returns all modules associated with a specific Unite d’Enseignement (UE).
     */
    public List<Module> modulesByUE(UniteEnseignement ue) {
        return moduleBusiness.getModulesByUE(ue);
    }
}
