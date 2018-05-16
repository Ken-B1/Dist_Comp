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
import Entities.Categories;
import Entities.Messages;
import Entities.Peoplefollower;
import Entities.PeoplefollowerPK;
import Entities.Pin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import services.AccountBeanInterface;
import services.RegistrationBeanInterface;
import services.StatisticsBeanInterface;

/**
 *
 * @author Ken
 */
@Stateful
public class AccountBean implements AccountBeanInterface{
    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;
    
    // Stateless bean used for registration and validation
    private RegistrationBeanInterface reg;
    
    // Stateless bean used to log actions
    private StatisticsBeanInterface stats;
    
    // Entity object representing current user in database
    private int currentUser;
  
    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;

    public AccountBean(){
        try{
            ic = new InitialContext();
            reg = (RegistrationBeanInterface)ic.lookup("java:global/statistics_EJB/Registration!services.RegistrationBeanInterface");
            stats = (StatisticsBeanInterface)ic.lookup("java:global/statistics_EJB/StatisticsBean!services.StatisticsBeanInterface");
        }catch(NamingException e){
            System.out.println("Accountbean error:");
            System.out.println(e.getMessage());
        }
    }
    
    public AccountBean(Account acc){
        currentUser = acc.getId();
        em.persist(currentUser);
    }
    
    // Put all crud functionalities here?
    @Override
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
    
    @Override
    public boolean hasCategories(){
        return !em.find(Account.class, currentUser).getCategoriesCollection().isEmpty();
    }
    
    @Override
    public Collection<Categories> getUserCategories(){
        return em.find(Account.class, currentUser).getCategoriesCollection();
    }
    
    @Override
    public void addUserCategory(Categories newCategory){
        Account user = em.find(Account.class, currentUser);
        Collection<Categories> categories = user.getCategoriesCollection();
        categories.add(newCategory);
        user.setCategoriesCollection(categories);
        em.flush();
    }
    
    // Follow a board
    @Override
    public void followBoard(Board toFollow){
        Account currentAccount = em.find(Account.class, currentUser);
        Collection<Board> boardcollection = currentAccount.getBoardCollection();
        boardcollection.add(toFollow);
        currentAccount.setBoardCollection(boardcollection);
    }
    
    // Unfollow a board
    @Override
    public void unfollowBoard(Board toUnFollow){
        Account currentAccount = em.find(Account.class, currentUser);
        Collection<Board> boardcollection = currentAccount.getBoardCollection();
        boardcollection.remove(toUnFollow);
        currentAccount.setBoardCollection(boardcollection);
    }
    
    // Follow a person
    @Override
    public void followPerson(int toFollow){
        Peoplefollower newFollower = new Peoplefollower(currentUser, toFollow);
        newFollower.setAccount(em.find(Account.class, currentUser));
        newFollower.setAccount1(em.find(Account.class, toFollow));
        em.persist(newFollower);
        stats.follow(em.find(Account.class, currentUser), em.find(Account.class, toFollow));
    }    
    
    // Unfollow a person
    @Override
    public void unfollowPerson(int toUnFollow){
        Peoplefollower toRemove = em.find(Peoplefollower.class, new PeoplefollowerPK(currentUser, toUnFollow));
        if(toRemove != null){
            em.remove(toRemove);
        }else{
            // Something went wrong
            throw new NullPointerException("Tried to unfollow a person that could not be found");
        }
    }    
    
    // Block a follower
    @Override
    public void blockPerson(int toBlock){
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
    @Override
    public void unblockPerson(int tounBlock){
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
    
    // Get this user's messages
    @Override
    public Collection<Messages> getMessages(){
        Account user = em.find(Account.class, currentUser);
        return user.getMessagesCollection();
    }
    
    // Get number of followers and following
    @Override
    public int getNumFollowers(){
        Account user =  em.find(Account.class, currentUser);
        return user.getPeoplefollowerCollection().size();
    }
    
    @Override
    public int getNumFollowing(){
        Account user =  em.find(Account.class, currentUser);
        return user.getPeoplefollowerCollection1().size();
    }
    // Getters and setters for account entity
    
    // Add an account and make it managed by entity manager
    @Override
    public void setAccount(int acc){
        currentUser = acc;
        
    }
    
    @Override
    public Account getAccount(){
        return em.find(Account.class, currentUser);
    }
    
    @Override
    public Account getAccount(String username){
        Account account = null;
        try{
            account = (Account)em.createNamedQuery("Account.findByUsername").setParameter("username", username).getSingleResult();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return account;
    }
    
    @Override
    public Account getAccountForId(int id){
        return em.find(Account.class, id);
    }
    
    @Override
    public List<Pin> getTailoredPins(){
        return getTailoredPins(20, 30);
    }
    
    @Override
    public List<Pin> getTailoredPins(int numFromBoard, int numFromCategories){
        List<Pin> resultList = new ArrayList();
        Account acc = getAccount();
        em.refresh(acc);
        List<Boardfollowers> listofBoards = em.createNamedQuery("Boardfollowers.findByUserid").setParameter("userid", acc.getId()).getResultList();
        Collection<Categories> listofCategories = acc.getCategoriesCollection();
        Random rand = new Random(System.currentTimeMillis());
        
        for(int x = 0; x < numFromBoard ; x++){
            if(listofBoards.isEmpty()){
                break;
            }
            // Keep adding a random pin from a random board
            int currentIndex = rand.nextInt(listofBoards.size());
            Board currentBoard = listofBoards.get(currentIndex).getBoard();
            Collection<Pin> allPins = currentBoard.getPinCollection();
            int pinIndex = rand.nextInt(allPins.size());
            resultList.add((Pin)allPins.toArray()[pinIndex]);
        }
        
        for(int x = 0; x < numFromCategories ; x++){
            if(listofCategories.isEmpty()){
                break;
            }
            // Add random pins from selected categories
            int currentIndex = rand.nextInt(listofCategories.size());
            Categories currentCategory = (Categories)listofCategories.toArray()[currentIndex];
            Object res = em.createNamedQuery("Board.maxIdForCategory").setParameter("category", currentCategory).getSingleResult();
            if(res == null){
                return resultList;
            }
            int maxIdForCat = (int)res;
            int boardId = rand.nextInt(maxIdForCat + 1);
            Board selectedBoard = (Board)em.createNamedQuery("Board.firstOccurencesAfterId").setParameter("id", boardId).setMaxResults(1).getSingleResult();
            Collection<Pin> allPins = selectedBoard.getPinCollection();
            if(allPins.size() == 0){
                continue;
            }
            int pinIndex = rand.nextInt(allPins.size());
            resultList.add((Pin)allPins.toArray()[pinIndex]);            
        }
        
        return resultList;
    }
    
}
