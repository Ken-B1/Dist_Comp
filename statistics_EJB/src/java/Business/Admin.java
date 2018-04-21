/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ken
 * Class that facilitates some admin actions
 */
@Stateful
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
    
    
}
