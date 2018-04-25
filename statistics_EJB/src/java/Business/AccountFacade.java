/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import Entities.Statistics;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ken
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> {

    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;
    
    @EJB
    private StatisticsFacade statsfacade;
    private Statistics statistic;

    public Account getAccountByUsername(String username) {
        Account returnvalue = (Account)em.createNamedQuery("Account.findByUsername").setParameter("username", username).getSingleResult();
        return returnvalue;
    }
    
    public Account getAccountById(int id) {
        Account returnvalue = (Account)em.createNamedQuery("Account.findById").setParameter("id", id).getSingleResult();
        return returnvalue;
    }
    
    
    public void updateAccount(Account account, int currentUserId) {
        Account acc = (Account)em.createNamedQuery("Account.findById").setParameter("id", currentUserId).getSingleResult();
        acc.setEmail(account.getEmail());
        acc.setFname(account.getFname());
        acc.setLname(account.getLname());
        acc.setCountry(account.getCountry());
        acc.setGender(account.getGender());
        acc.setUsername(account.getUsername());
        em.flush();
    }
    
    public int createAccount(String email, String userName, String password, String fname, String lname, String country, String gender){
        long usernameexists = (long)em.createNamedQuery("Account.existsName").setParameter("username", userName).getSingleResult();
        if(usernameexists != 0){
            return 7;
        }
        long emailexists = (long)em.createNamedQuery("Account.existsEmail").setParameter("email", userName).getSingleResult();
        if(emailexists != 0){
            return 8;
        }

        Account account = new Account();
        if(email.equals("")){
            return 1;
        }else if(userName.equals("")){
            return 2;
        }else if(password.equals("")){
            return 3;
        }else if(fname.equals("")){
            return 4;
        }else if(lname.equals("")){
            return 5;
        }else if(gender.equals("")){
            return 6;
        }
        account.setEmail(email);
        account.setUsername(userName);
        account.setPassword(password);
        account.setFname(fname);
        account.setLname(lname);
        account.setCountry(country);
        account.setGender(gender);
        em.persist(account);
        return 0;
    }
    
    public void makeAdmin(Account account){
        account.setAdmin(true);
        em.flush();
    }
    
    public Boolean login(String username, String password) {
        if((long)em.createNamedQuery("Account.existsName").setParameter("username", username).getSingleResult() == 0){
            return false;
        }
        Account returnvalue = (Account)em.createNamedQuery("Account.findByUsername").setParameter("username", username).getSingleResult();
        if(password.equals(returnvalue.getPassword())) {
            //Log login to statistics
            statsfacade.log(returnvalue);
            return true;
        }else{
            return false;
        }
    }
    
    public int getId(String username){
        try{
            return this.getAccountByUsername(username).getId();
        } catch(Exception E){
            System.out.println("Something went wrong when getting ID");
        }
        return -1;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
    
    public boolean hasCategories(int accountId){
        return em.find(Account.class, accountId).getCategoriesCollection().size() == 0;
    }
}
