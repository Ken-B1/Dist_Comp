/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import Entities.Account;
import Entities.Statistics;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    public Account getAccount() {
        Account returnvalue = (Account)em.createNamedQuery("Account.findByUsername").setParameter("username", "ken123").getSingleResult();
        return returnvalue;
    }
    
    public void updateAccount(Account account) {
        // TODO: Update these to correct stuff
        Account toChange = getAccount();
        
        toChange.setUsername(account.getUsername());
        toChange.setFname(account.getFname());
        toChange.setLname(account.getLname());
        toChange.setGender(account.getGender());
        toChange.setCountry(account.getCountry());
    }
    
    public void createAccount(Account account){
        em.persist(account);
    }
    
    public Boolean login(String username, String password) {
        Account returnvalue = (Account)em.createNamedQuery("Account.findByUsername").setParameter("username", username).getSingleResult();
        if(password.equals(returnvalue.getPassword())) {
            //Log login to statistics
            statsfacade.log(username);
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
    
}
