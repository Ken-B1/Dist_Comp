/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Categories;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ken
 */
@Stateless
@LocalBean
public class categoryBean {

    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    /**
     * Returns the 10 most followed categories.
     * @return 
     */
    public List<Categories> getTopCategories(){
        return getTopCategories(10);
    }
    
    /**
     * Returns the most followed categories 
     * @param amount Amount of categories to be retrieved. Can return more if there are multiple categories with same amount of popularities
     * @return 
     */
    public List<Categories> getTopCategories(int amount){      
        List<Integer> popularities = new ArrayList<Integer>();
        List<Categories> allcats = em.createNamedQuery("Categories.findAll").getResultList();
        List<Categories> resultList = new ArrayList<Categories>();
        
        for(Categories x: allcats){
            popularities.add(x.getAccountCollection().size());
        }
        
        Collections.sort(popularities, Collections.reverseOrder());
        
        int sizeLimit = popularities.get(amount-1);
        
        for(Categories x: allcats){
            if(x.getAccountCollection().size() >= sizeLimit){
                resultList.add(x);
            }
        }
        
        return resultList;
    }
    
    /**
     * Returns the most followed categories 
     * @param amount Amount of categories to be retrieved. Can return more if there are multiple categories with same amount of popularities
     * @return 
     */
    public List<Categories> getRisingCategories(int amount){      
        List<Integer> popularities = new ArrayList<Integer>();
        List<String> allcats = em.createNamedQuery("Useractions.findCategoryFollows").setParameter("timestamp",  new Date(System.currentTimeMillis()-82800000)).getResultList();
        List<Categories> returnList = new ArrayList();
        
        for(String x: allcats.subList(0, amount)){
            returnList.add(em.find(Categories.class, Integer.parseInt(x)));
        }
        
        return returnList;
    }
}
