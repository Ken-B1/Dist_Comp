/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import services.LoginBeanInterface;

/**
 *
 * @author ken
 */
@Stateless
public class LoginBean implements LoginBeanInterface{

    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;

    // EJB Used to log logins
    @EJB
    private StatisticsBean statistics;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public int login(String username, String password) {
        if((long)em.createNamedQuery("Account.existsName").setParameter("username", username).getSingleResult() == 0){
            // Account does not exist
            return -1;
        }
        
        Account returnvalue = (Account)em.createNamedQuery("Account.findByUsername").setParameter("username", username).getSingleResult();
        if(password.equals(returnvalue.getPassword())) {
            // Log login to statistics
            statistics.log(returnvalue);
            InitialContext ic;
            return returnvalue.getId();

        }else{
            // Password is incorrect
            return -1;
        }
    }
    
}
