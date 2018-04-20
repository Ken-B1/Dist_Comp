/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import Entities.Statistics;
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
public class StatisticsFacade extends AbstractFacade<Statistics> {

    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatisticsFacade() {
        super(Statistics.class);
    }
    
    public void log(final Account username) {
        System.out.println("Called test");
        
        Statistics stats = new Statistics();
        stats.setUserid(username);
        stats.setTimestamp(new Date(System.currentTimeMillis()));
        getEntityManager().persist(stats);
    }
    
    public List<Statistics> getStatistics(final int id) {
        Account returnvalue = (Account)em.createNamedQuery("Account.findById").setParameter("id", id).getSingleResult();
        return em.createNamedQuery("Statistics.findByUserid").setParameter("userid", returnvalue).getResultList();
    }
    
}
