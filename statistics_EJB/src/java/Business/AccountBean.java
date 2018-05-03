/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ken
 */
@Stateful
@LocalBean
public class AccountBean {
    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;
    
    private Account currentUser;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public AccountBean(){}
    
    public AccountBean(Account acc){
        currentUser = acc;
    }
    
    // Put all crud functionalities here?
    
}
