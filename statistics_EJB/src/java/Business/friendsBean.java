/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import java.util.Collection;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ken
 */
@Stateless
@LocalBean
public class friendsBean{

    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;
    
    /**
     * Method that sends a friendRequest to a user.
     * Friend request won't be sent if already friends or if requester is blocked
     * 
     * @param requesterId
     * @param requestedId 
     */
    public void sendFriendRequest(int requesterId, int requestedId){
        Account requester = em.find(Account.class, requesterId);
        Account requested = em.find(Account.class, requestedId);
        if(requester == null || requested == null){
            // Incorrect account requested
            return;
        }
        
        // Needs to refresh, because for some reason the entity manager persists between calls even though this is stateless
        em.refresh(requester);
        em.refresh(requested);

        if(requester.getAccountCollection1().contains(requested)){
            // If user is already requested or requester is blocked, do nothing
            return;
        }
        
        Query query = em.createNativeQuery("INSERT INTO friendrequests VALUES(?1, ?2)");
        query.setParameter(1,requesterId);
        query.setParameter(2,requestedId);
        query.executeUpdate();
        em.flush();
    }
    
    /**
     * Method that requests to unFriend user
     * 
     * @param requesterId
     * @param requestedId 
     */
    public void sendunFriendRequest(int requesterId, int requestedId){
        Account requester = em.find(Account.class, requesterId);
        Account requested = em.find(Account.class, requestedId);
        if(requester == null || requested == null){
            // Incorrect account requested
            return;
        }
        // Needs to refresh, because for some reason the entity manager persists between calls even though this is stateless
        em.refresh(requester);
        em.refresh(requested);
        
        if(!requester.getAccountCollection2().contains(requested)){
            // These people are no longer friends
            return;
        }

        Query query = em.createNativeQuery("DELETE FROM friends WHERE ((user1 = ?1 AND user2 =  ?2) or (user1 = ?2 AND user2 =  ?1))");
        query.setParameter(1,requesterId);
        query.setParameter(2,requestedId);
        query.executeUpdate();
        em.flush();        
    }
    
    /**
     * Method to accept friend request
     * 
     * @param requesterId the id of the person that sent the original request
     * @param requestedId the id of the person that wants to accept the request
     */
    public void acceptFriendRequest(int requesterId, int requestedId){
        Account requester = em.find(Account.class, requesterId);
        Account requested = em.find(Account.class, requestedId);

        if(requester == null || requested == null){
            // Incorrect account requested
            return;
        }  
        
        // Needs to refresh, because for some reason the entity manager persists between calls even though this is stateless
        em.refresh(requester);
        em.refresh(requested);

        if(!requester.getAccountCollection1().contains(requested)){
            // Friend request no longer exists
            return;
        }

        // Remove friend request and add friends to table
        removeFriendRequest(requesterId, requestedId);
        Query query = em.createNativeQuery("INSERT INTO friends VALUES(?1, ?2), (?2, ?1)");
        query.setParameter(1,requesterId);
        query.setParameter(2,requestedId);
        query.executeUpdate();
        em.flush();           
    }
    
    /**
     * Method to remove friend request
     * 
     * @param requesterId
     * @param requestedId 
     */
    public void removeFriendRequest(int requesterId, int requestedId){
        Account requester = em.find(Account.class, requesterId);
        Account requested = em.find(Account.class, requestedId);
        if(requester == null || requested == null){
            // Incorrect account requested
            return;
        }
        
        // Needs to refresh, because for some reason the entity manager persists between calls even though this is stateless
        em.refresh(requester);
        em.refresh(requested);
        
        if(!requester.getAccountCollection1().contains(requested)){
            // Friend request no longer exists
            return;
        }
        
        Query query = em.createNativeQuery("DELETE FROM friendrequests WHERE ((requester = ?1 AND requested =  ?2) or (requester = ?2 AND requested =  ?1))");
        query.setParameter(1,requesterId);
        query.setParameter(2,requestedId);
        query.executeUpdate();
        em.flush();        
    }
    
    public Collection<Account> getFriends(int userId){
        Account currentUser = em.find(Account.class, userId);
        return currentUser.getAccountCollection2();
    }
    
    public Collection<Account> getFriendRequests(int userId){
        Account currentUser = em.find(Account.class, userId);
        return currentUser.getAccountCollection();
    }
}
