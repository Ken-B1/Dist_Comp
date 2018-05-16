/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import Entities.Board;
import Entities.Categories;
import Entities.Notifications;
import Entities.Peoplefollower;
import Entities.Pin;
import Entities.Statistics;
import Entities.Useractions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import services.StatisticsBeanInterface;

/**
 *
 * @author ken
 */
@Stateless
public class StatisticsBean implements StatisticsBeanInterface{

    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;

    @Override
    public void log(final Account username) {
        Statistics stats = new Statistics();
        stats.setUserid(username);
        stats.setTimestamp(new Date(System.currentTimeMillis()));
        em.persist(stats);
    }
    
    /*
        Log specific actions for specific users.
        Log codes are as follows:
         - createBoard: 1
         - createPin: 2
         - follow: 3
    */
    
    @Override
    public void createBoard(Account user, Board board){
        try{
            System.out.println("1");
            for(Account receiver: user.getAccountCollection2()){
                System.out.println("2");
                // Add a notification for each friend
                Notifications newnot = new Notifications();
                newnot.setCreator(user);
                newnot.setReceiver(receiver);
                newnot.setType(1);
                newnot.setDescription(board.getId().toString());
                em.persist(newnot);
            }

            List<Peoplefollower> followers = new ArrayList(user.getPeoplefollowerCollection1());

            for(Peoplefollower receiver: followers){
                System.out.println("3");
                Account actualRec = receiver.getAccount();
                Notifications newnot = new Notifications();
                newnot.setCreator(user);
                newnot.setReceiver(actualRec);
                newnot.setType(1);
                newnot.setDescription(board.getId().toString());
                em.persist(newnot);            
            }
            
            boardCategory(user, board.getCategory());
        }catch(ConstraintViolationException e){
            System.out.println("Violations:");
            for(ConstraintViolation u: e.getConstraintViolations()){
                System.out.println(u.getMessage());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void createPin(Account user, Pin pin){   

        for(Account receiver: user.getAccountCollection2()){
            // Add a notification for each friend
            Notifications newnot = new Notifications();
            newnot.setCreator(user);
            newnot.setReceiver(receiver);
            newnot.setType(2);
            newnot.setDescription(pin.getId().toString());
            em.persist(newnot);
        }
        
        List<Peoplefollower> followers = em.createNamedQuery("Peoplefollower.findByFollowedUser").setParameter("followedUser", user.getId()).getResultList();
        
        for(Peoplefollower receiver: followers){
            Account actualRec = receiver.getAccount1();
            Notifications newnot = new Notifications();
            newnot.setCreator(user);
            newnot.setReceiver(actualRec);
            newnot.setType(2);
            newnot.setDescription(pin.getId().toString());
            em.persist(newnot);            
        }
    }
    
    @Override
    public void follow( Account follower, Account followed){
        Notifications newnot = new Notifications();
        newnot.setCreator(follower);
        newnot.setReceiver(followed);
        newnot.setType(3);
        em.persist(newnot);         
    }
    
    @Override
    public void boardCategory(Account user, Categories category){
        Useractions action = new Useractions();
        action.setCategory(category);
        action.setUser(user);
        action.setTimestamp(new Date(System.currentTimeMillis()));
        em.persist(action);
    }   
    
    @Override
    public void markAsRead(int notificationId){
        Notifications not = em.find(Notifications.class, notificationId);
        not.setIsread((short)1);
        em.flush();
    }
    
    @Override
    public Notifications getNotification(int notificationId){
        return em.find(Notifications.class, notificationId);
    }
    
    @Override
    public List<Statistics> getStatistics(final int id) {
        Account returnvalue = (Account)em.createNamedQuery("Account.findById").setParameter("id", id).getSingleResult();
        return em.createNamedQuery("Statistics.findByUserid").setParameter("userid", returnvalue).getResultList();
    }
    
}
