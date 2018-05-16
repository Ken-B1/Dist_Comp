/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Categories;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import services.databaseConnectorInterface;

/**
 *
 * @author ken
 * This is a class that will deliver database content which does not nessecarily have to be processed
 * This class is used to maintain the separation of presentation/business/data interactions
 */
@Stateless
public class databaseConnector implements databaseConnectorInterface{
    @PersistenceContext(unitName = "statistics_EJBPU")
    EntityManager em;
    public databaseConnector() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Categories> getAllCategories(){
        return em.createNamedQuery("Categories.findAll").getResultList();
    }
    
}
