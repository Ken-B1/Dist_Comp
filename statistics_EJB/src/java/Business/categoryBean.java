/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import services.categoryBeanInterface;
import Entities.Categories;
import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ken
 */
@Stateless
public class categoryBean implements categoryBeanInterface{

    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    /**
     * Returns the 10 most followed categories.
     * @return 
     */
    @Override
    public List<Categories> getTopCategories(){
        return getTopCategories(10);
    }
    
    /**
     * Returns the most followed categories 
     * @param amount Amount of categories to be retrieved. Can return more if there are multiple categories with same amount of popularities
     * @return 
     */
    @Override
    public List<Categories> getTopCategories(int amount){      
        List<Integer> popularities = new ArrayList<>();
        List<Categories> allcats = em.createNamedQuery("Categories.findAll").getResultList();
        List<Categories> resultList = new ArrayList<>();
        
        for(Categories x: allcats){
            popularities.add(x.getAccountCollection().size());
        }
        
        Collections.sort(popularities, Collections.reverseOrder());
        amount = min(amount, popularities.size());
        int sizeLimit = popularities.get(amount-1);
        for(Categories x: allcats){
            if(x.getAccountCollection().size() >= sizeLimit){
                resultList.add(x);
            }
        }
        
        return resultList;
    }
    
    /**
     * Returns the most followed categories during the last week
     * @param amount Amount of categories to be retrieved. Can return more if there are multiple categories with same amount of popularities
     * @return 
     */
    @Override
    public List<Categories> getRisingCategories(int amount){      
        return em.createNamedQuery("Useractions.findCategoryFollows").setParameter("timestamp",  new Date(System.currentTimeMillis()-604800000)).setMaxResults(amount).getResultList();
    }
}
