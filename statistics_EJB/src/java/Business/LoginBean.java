/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import remotesettings.setRemote;

/**
 *
 * @author ken
 */
@Stateless
@LocalBean
public class LoginBean{

    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;

    // EJB Used to log logins
    @EJB
    private StatisticsBean statistics;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
   public AccountBean login(String username, String password) {
        if((long)em.createNamedQuery("Account.existsName").setParameter("username", username).getSingleResult() == 0){
            // Account does not exist
            return null;
        }
        
        Account returnvalue = (Account)em.createNamedQuery("Account.findByUsername").setParameter("username", username).getSingleResult();
        if(password.equals(returnvalue.getPassword())) {
            // Log login to statistics
            statistics.log(returnvalue);
            InitialContext ic;
            try{
                // Use jndi lookup to create a container managed instance of the accountbean
                ic = new InitialContext();
                AccountBean accountbean = (AccountBean)ic.lookup("java:global/P4Food/statistics_EJB/AccountBean!Business.AccountBean");
                accountbean.setAccount(returnvalue);
                return accountbean;
            } catch(NamingException e){
                System.out.println(e.getMessage());
                return null;
            }
        }else{
            // Password is incorrect
            return null;
        }
    }
    
}
