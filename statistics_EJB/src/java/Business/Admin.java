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
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
public class Admin{
    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;
    
    Registration regBean;
    
    public Admin(){
        InitialContext ic;
            try{
                // Use jndi lookup to create a container managed instance of the accountbean
                ic = new InitialContext();
                regBean = (Registration)ic.lookup("java:global/statistics_EJB/Registration!services.RegistrationBeanInterface");
            }catch(NamingException e){
                System.out.println("Admin error:");
                System.out.println(e.getMessage());
            }
    }
    
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
    
    // Block, unblock or remove users
    public void blockUser(int userId){
        Account toBlock = em.find(Account.class, userId);
        toBlock.setIsBlocked((short)1);
        em.flush();
    }
    
    public void unblockUser(int userId){
        Account toUnBlock = em.find(Account.class, userId);
        toUnBlock.setIsBlocked((short)0);
        em.flush();
    }
    
    public void removeUser(int userId){
        Account toRemove = em.find(Account.class, userId);
        em.remove(toRemove);
        em.flush();   
    }
}
