/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import Entities.Categories;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ken
 * Class that facilitates some admin actions
 */
@Stateless
@LocalBean
public class Admin {
    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;
    
    @EJB
    Registration regBean;
    public Admin(){};
    
    /*
    * Create a new account then set to admin
    */
    public int createAdminAccount(String email,String userName,String password,String fname,String lname,String country,String gender){

        int result = regBean.createAccount(email, userName, password, fname, lname, country, gender);
        
        if(result != -1){
            makeAdmin(result);
        }
        return result;
    }
    
    public void makeAdmin(int id){
        Account toChange = em.find(Account.class, id);
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
