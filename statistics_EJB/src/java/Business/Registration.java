/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ken
 * Stateless because after creating an account, state can be thrown away
 */
@Stateless
@LocalBean
public class Registration {
    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Registration(){};
    
    /*
        Method that creates a new account. 
        Returns -1 if account could not be created because of already existing values
        Returns account id otherwise
    */
    public int createAccount(String email,String userName,String password,String fname,String lname,String country,String gender){
        if(checkValues(email, userName) != 0){
            //Email or username already exists
            return -1;
        }
        Account newAccount = new Account();
        newAccount.setEmail(email);
        newAccount.setUsername(userName);
        newAccount.setPassword(password);
        newAccount.setFname(fname);
        newAccount.setLname(lname);
        newAccount.setCountry(country);
        newAccount.setGender(gender);
        
        em.persist(newAccount);
        
        Account createdAcc = (Account)em.createNamedQuery("Account.findByUsername").setParameter("username", userName).getSingleResult();
        return createdAcc.getId();
    };
    
    /*
    *   Check if an account with values already exists
    *   Returns an id based on the result
    */
    public int checkValues(String email, String userName){
        long usernameexists = (long)em.createNamedQuery("Account.existsName").setParameter("username", userName).getSingleResult();
        if(usernameexists != 0){
            return 7;
        }
        long emailexists = (long)em.createNamedQuery("Account.existsEmail").setParameter("email", userName).getSingleResult();
        if(emailexists != 0){
            return 8;
        }
        return 0;
    }
}
