/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Categories;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ken
 * This is a class that will deliver database content which does not nessecarily have to be processed
 * This class is used to maintain the separation of presentation/business/data interactions
 */
@Stateless
@LocalBean
public class databaseConnector {
    @PersistenceContext(unitName = "statistics_EJBPU")
    EntityManager em;
    public databaseConnector() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<Categories> getAllCategories(){
        return em.createNamedQuery("Categories.findAll").getResultList();
    }
    
}
