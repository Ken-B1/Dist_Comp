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
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import services.AdminInterface;
import services.RegistrationBeanInterface;

/**
 *
 * @author Ken
 * Class that facilitates some admin actions
 * Gives an error, but this might be a netbeans problem. It should work when running
 */
@Stateful
public class Admin implements AdminInterface{
    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;
    
    RegistrationBeanInterface regBean;
    
    public Admin(){
        InitialContext ic;
            try{
                // Use jndi lookup to create a container managed instance of the accountbean
                ic = new InitialContext();
                regBean = (RegistrationBeanInterface)ic.lookup("java:global/statistics_EJB/Registration!services.RegistrationBeanInterface");
            }catch(NamingException e){
                System.out.println("Admin error:");
                System.out.println(e.getMessage());
            }
    }
    
    /*
    * Create a new account then set to admin
    */
    @Override
    public Status createAdminAccount(String email,String userName,String password,String fname,String lname,String country,String gender){
        Status result = regBean.createAccount(email, userName, password, fname, lname, country, gender);
        
        if(result.getStatusCode() == 0){
            makeAdmin(userName);
        }
        return result;
    }
    
    /**
     * Method to turn a existing user into an admin
     * @param username 
     */
    @Override
    public void makeAdmin(String username){
        Account toChange = (Account) em.createNamedQuery("Account.findByUsername").setParameter("username", username).getSingleResult();
        if(toChange == null){
            return;
        }
        toChange.setAdmin(true);
        em.flush();
    }
    
    /**
     * Creates a new category with name name.
     * @param name If name is null or the empty string, category won't be added
     */
    @Override
    public void addCategory(String name){
        Categories newcategory = new Categories();
        if(name == null){
            return;
        }
        newcategory.setName(name);
        em.persist(newcategory);
    }
    
    /**
     * Returns all existing names of categories.
     * @return 
     */
    @Override
    public List<String> getCategoryNames(){
        List<Categories> categorieslist = getCategories();
        List<String> returnlist = new ArrayList<String>();
        for(Categories category: categorieslist){
            returnlist.add(category.getName());
        }
        return returnlist;
    }
    
    @Override
    public List<Categories> getCategories(){
        return em.createNamedQuery("Categories.findAll").getResultList();
    }
    
    // Block, unblock or remove users
    @Override
    public void blockUser(int userId){
        Account toBlock = em.find(Account.class, userId);
        toBlock.setIsBlocked((short)1);
        em.flush();
    }
    
    @Override
    public void unblockUser(int userId){
        Account toUnBlock = em.find(Account.class, userId);
        toUnBlock.setIsBlocked((short)0);
        em.flush();
    }
    
    @Override
    public void removeUser(int userId){
        Account toRemove = em.find(Account.class, userId);
        em.remove(toRemove);
        em.flush();   
    }
}
