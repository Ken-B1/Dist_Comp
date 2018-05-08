/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business_Utility.Status;
import Entities.Account;
import Entities.Categories;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ken
 * Class that facilitates some admin actions
 * Gives an error, but this might be a netbeans problem. It should work when running
 */
@Stateful
@LocalBean
public class Admin extends AccountBean{
    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;
    
    @EJB
    Registration regBean;
    public Admin(){};
    
    /*
    * Create a new account then set to admin
    */
    public Status createAdminAccount(String email,String userName,String password,String fname,String lname,String country,String gender){

        Status result = regBean.createAccount(email, userName, password, fname, lname, country, gender);
        
        if(result.getStatusCode() == 0){
            makeAdmin(userName);
        }
        return result;
    }
    
    public void makeAdmin(String username){
        Account toChange = (Account) em.createNamedQuery("Account.findByUsername").setParameter("username", username).getSingleResult();
        toChange.setAdmin(true);
        em.flush();
    }
    
    /*
    * Temporary methods
    * Will likely be moved to category class later
    */
    
    public void addCategory(String name){
        Categories newcategory = new Categories();
        newcategory.setName(name);
        em.persist(newcategory);
    }
    
    public List<String> getCategoryNames(){
        List<Categories> categorieslist = getCategories();
        List<String> returnlist = new ArrayList<String>();
        for(Categories category: categorieslist){
            returnlist.add(category.getName());
        }
        return returnlist;
    }
    
    public List<Categories> getCategories(){
        return em.createNamedQuery("Categories.findAll").getResultList();
    }
    
}
