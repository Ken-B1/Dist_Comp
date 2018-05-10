/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business_Utility.Status;
import Entities.Account;
import Entities.Board;
import Entities.Boardfollowers;
import Entities.BoardfollowersPK;
import Entities.Categories;
import Entities.Messages;
import Entities.Peoplefollower;
import Entities.PeoplefollowerPK;
import Entities.Useractions;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ken
 */
@Stateful
@LocalBean
public class AccountBean {
    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;
    
    // Stateless bean used for registration and validation
    @EJB 
    private Registration reg;
    
    // Entity object representing current user in database
    private int currentUser;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public AccountBean(){}
    
    public AccountBean(Account acc){
        currentUser = acc.getId();
        em.persist(currentUser);
    }
    
    // Put all crud functionalities here?
    public Status updateAccount(Account account) {
        Status result = reg.validate(account.getEmail(), account.getUsername());
        if(result.getStatusCode() == 0){
            Account user = em.find(Account.class, currentUser);
            user.setEmail(account.getEmail());
            user.setFname(account.getFname());
            user.setLname(account.getLname());
            user.setCountry(account.getCountry());
            user.setGender(account.getGender());
            user.setUsername(account.getUsername());
            em.flush();
        }
        
        return result;
    }
    
    public boolean hasCategories(){
        return !em.find(Account.class, currentUser).getCategoriesCollection().isEmpty();
    }
    
    public Collection<Categories> getUserCategories(){
        return em.find(Account.class, currentUser).getCategoriesCollection();
    }
    
    public void addUserCategory(Categories newCategory){
        Account user = em.find(Account.class, currentUser);
        Collection<Categories> categories = user.getCategoriesCollection();
        categories.add(newCategory);
        user.setCategoriesCollection(categories);
        em.flush();
    }
    
    // Follow a board
    public void followBoard(Board toFollow){
        Boardfollowers newFollower = new Boardfollowers(currentUser, toFollow.getId());
        newFollower.setBoard(toFollow);
        newFollower.setAccount(em.find(Account.class, currentUser));
        em.persist(newFollower);
    }
    
    // Unfollow a board
    public void unfollowBoard(Board toUnFollow) throws NullPointerException{
        Boardfollowers toRemove = em.find(Boardfollowers.class, new BoardfollowersPK(currentUser, toUnFollow.getId()));
        if(toRemove != null){
            em.remove(toRemove);
        }else{
            // Something went wrong
            throw new NullPointerException("Tried to unfollow a board that could not be found");
        }
    }
    
    // Follow a person
    public void followPerson(int toFollow){
        Peoplefollower newFollower = new Peoplefollower(currentUser, toFollow);
        newFollower.setAccount(em.find(Account.class, currentUser));
        newFollower.setAccount1(em.find(Account.class, toFollow));
        em.persist(newFollower);
    }    
    
    // Unfollow a person
    public void unfollowPerson(int toUnFollow) throws NullPointerException{
        Peoplefollower toRemove = em.find(Peoplefollower.class, new PeoplefollowerPK(currentUser, toUnFollow));
        if(toRemove != null){
            em.remove(toRemove);
        }else{
            // Something went wrong
            throw new NullPointerException("Tried to unfollow a person that could not be found");
        }
    }    
    
    // Block a follower
    public void blockPerson(int toBlock) throws NullPointerException{
        Peoplefollower blockedPerson = em.find(Peoplefollower.class, new PeoplefollowerPK(toBlock, currentUser));
        if(blockedPerson != null){
            blockedPerson.setIsBlocked((short)1);
            //Check if this person is also following some of my boards
            List<Boardfollowers> allFollowedBoards =  em.createNamedQuery("Boardfollowers.findByUserid").setParameter("userid", toBlock).getResultList();
            for(Boardfollowers x: allFollowedBoards){
                x.setIsBlocked((short)1);
            }
            em.flush();
        }else{
            // Something went wrong
            throw new NullPointerException("Tried to block a person that could not be found");
        }  
    }
    
    // Unblock a follower
    public void unblockPerson(int tounBlock) throws NullPointerException{
        Peoplefollower unblockedPerson = em.find(Peoplefollower.class, new PeoplefollowerPK(tounBlock, currentUser));
        if(unblockedPerson != null){
            unblockedPerson.setIsBlocked((short)0);
            //Check if this person is also following some of my boards
            List<Boardfollowers> allFollowedBoards =  em.createNamedQuery("Boardfollowers.findByUserid").setParameter("userid", tounBlock).getResultList();
            for(Boardfollowers x: allFollowedBoards){
                x.setIsBlocked((short)0);
            }
            em.flush();
        }else{
            // Something went wrong
            throw new NullPointerException("Tried to block a person that could not be found");
        }  
        
    }
    
    /*
        Get this user's notifications
        It uses the last login time for this user to only get notifications received after this time
    */
    public List<Useractions> getNotifications(){
        Account user = em.find(Account.class, currentUser);
        Query quer = em.createNamedQuery("Useractions.findNotifications");
        quer.setParameter("user", user).setMaxResults(20);
        List<Useractions> results = quer.getResultList();
        return results;
    }
    
    // Get this user's messages
    public Collection<Messages> getMessages(){
        Account user = em.find(Account.class, currentUser);
        return user.getMessagesCollection();
    }
    
    // Get number of followers and following
    public int getNumFollowers(){
        Account user =  em.find(Account.class, currentUser);
        return user.getPeoplefollowerCollection().size();
    }
    
    public int getNumFollowing(){
        Account user =  em.find(Account.class, currentUser);
        return user.getPeoplefollowerCollection1().size();
    }
    // Getters and setters for account entity
    
    // Add an account and make it managed by entity manager
    public void setAccount(Account acc){
        currentUser = em.merge(acc).getId();
        
    }
    
    public Account getAccount(){
        return em.find(Account.class, currentUser);
    }
    
}
