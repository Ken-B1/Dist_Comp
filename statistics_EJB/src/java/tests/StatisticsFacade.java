/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import Entities.Statistics;
import java.util.Date;
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
    
    public void test() {
        System.out.println("Called test");
        
        Statistics stats = new Statistics();
        stats.setName("Ken2");
        stats.setTimestamp(new Date(System.currentTimeMillis()));
        getEntityManager().persist(stats);
    }
    
}
