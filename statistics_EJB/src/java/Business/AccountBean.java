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
import Entities.Peoplefollower;
import Entities.PeoplefollowerPK;
import java.util.Collection;
import javax.ejb.EJB;
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
    
    public Collection<Categories> getUserCategories(int accountId){
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
    // Getters and setters for account entity
    
    // Add an account and make it managed by entity manager
    public void setAccount(Account acc){
        currentUser = em.merge(acc).getId();
        
    }
    
    public Account getAccount(){
        return em.find(Account.class, currentUser);
    }
    
}
