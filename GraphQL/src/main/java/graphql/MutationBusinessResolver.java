package graphql;

import business.ModuleBusiness;
import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entities.Module;
import entities.UniteEnseignement;

/**
 * GraphQL Mutation Resolver for Module entity.
 * Handles all write operations (create, update, delete) for Modules.
 */
public class MutationBusinessResolver implements GraphQLRootResolver {

    private ModuleBusiness moduleBusiness;

    public MutationBusinessResolver() {
        moduleBusiness = new ModuleBusiness();
    }

    /**
     * GraphQL Mutation: addModule
     * Adds a new module to the system.
     */
    public boolean addModule(String matricule, String libelle, int coefficient, int volumeHoraire,
                             Module.TypeModule type, UniteEnseignement uniteEnseignement) {

        Module module = new Module(matricule, libelle, coefficient, volumeHoraire, type, uniteEnseignement);
        return moduleBusiness.addModule(module);
    }

    /**
     * GraphQL Mutation: updateModule
     * Updates an existing module.
     */
    public boolean updateModule(String matricule, String libelle, int coefficient, int volumeHoraire,
                                Module.TypeModule type, UniteEnseignement uniteEnseignement) {

        Module updatedModule = new Module(matricule, libelle, coefficient, volumeHoraire, type, uniteEnseignement);
        return moduleBusiness.updateModule(matricule, updatedModule);
    }

    /**
     * GraphQL Mutation: deleteModule
     * Deletes a module by its matricule.
     */
    public boolean deleteModule(String matricule) {
        return moduleBusiness.deleteModule(matricule);
    }
}
