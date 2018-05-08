/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import Entities.Board;
import Entities.Pin;
import Entities.Statistics;
import Entities.Useractions;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ken
 */
@Stateless
public class StatisticsBean extends AbstractFacade<Statistics> {

    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatisticsBean() {
        super(Statistics.class);
    }
    
    public void log(final Account username) {
        Statistics stats = new Statistics();
        stats.setUserid(username);
        stats.setTimestamp(new Date(System.currentTimeMillis()));
        getEntityManager().persist(stats);
    }
    
    /*
        Log specific actions for specific users.
        Log codes are as follows:
         - createBoard: 1
         - updateBoard: 2
         - removeBoard: 3
         - createPin: 4
         - updatePin: 5
         - removePin: 6
    */
    public void createBoard(Account user, Board board){
        Useractions action = new Useractions(1, new Date(System.currentTimeMillis()));
        action.setUser(user);
        em.persist(action);
    }
    
    public void updateBoard(Account user, Board board){
        Useractions action = new Useractions(2, new Date(System.currentTimeMillis()));
        action.setUser(user);
        em.persist(action);        
    }
        
    public void removeBoard(Account user, Board board){
        Useractions action = new Useractions(3, new Date(System.currentTimeMillis()));
        action.setUser(user);
        em.persist(action);       
    }
    
    public void createPin(Account user, Pin pin){
        Useractions action = new Useractions(4, new Date(System.currentTimeMillis()));
        action.setUser(user);
        em.persist(action);        
    }
    
    public void updatePin(Account user, Pin pin){
        Useractions action = new Useractions(5, new Date(System.currentTimeMillis()));
        action.setUser(user);
        em.persist(action);        
    }
        
    public void removePin(Account user, Pin pin){
        Useractions action = new Useractions(6, new Date(System.currentTimeMillis()));
        action.setUser(user);
        em.persist(action);        
    }    
    
    public List<Statistics> getStatistics(final int id) {
        Account returnvalue = (Account)em.createNamedQuery("Account.findById").setParameter("id", id).getSingleResult();
        return em.createNamedQuery("Statistics.findByUserid").setParameter("userid", returnvalue).getResultList();
    }
    
}
