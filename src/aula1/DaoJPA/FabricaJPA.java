package aula1.DaoJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaJPA {
    private static EntityManagerFactory factory;
    
    public static EntityManager getEntityManager() {
        if (factory == null)
            factory = Persistence.createEntityManagerFactory("Aula1PU");
        
        return factory.createEntityManager();
    }
    
    private FabricaJPA() {
    }
}
