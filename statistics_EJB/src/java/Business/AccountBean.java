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
import Entities.Suggestions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        Account user = em.find(Account.class, currentUser);
        Status result = new Status(0, "");
        if(!account.getEmail().equals(user.getEmail())){
            result = reg.validate(account.getEmail(), "");   
        }
        
        if(result.getStatusCode() == 0 && !account.getUsername().equals(user.getUsername())){
            result=reg.validate("", account.getUsername());
        }
        
        if(result.getStatusCode() == 0){
            user.setEmail(account.getEmail());
            user.setFname(account.getFname());
            user.setLname(account.getLname());
            user.setPassword(account.getPassword());
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
    
    /**
     * Follow a board
     * @param toFollow 
     */
    @Override
    public void followBoard(Board toFollow){
        System.out.println("called");
        if(followsBoard(toFollow.getId())){
            // User is already following board
            return;
        }

        Query quer = em.createNativeQuery("INSERT INTO boardfollowers(boardid, userid, isblocked) VALUES (?1, ?2, 0)");
        quer.setParameter(2, currentUser);
        quer.setParameter(1, toFollow.getId());
        quer.executeUpdate();
    }
    
    /**
     * Unfollow a board
     * @param toUnFollow 
     */
    @Override
    public void unfollowBoard(Board toUnFollow){
        Query quer = em.createNativeQuery("DELETE FROM boardfollowers WHERE (Userid = ?1 and Boardid = ?2)");
        quer.setParameter(1, currentUser);
        quer.setParameter(2, toUnFollow.getId());
        quer.executeUpdate();
    }
    
    /**
     * Check if the current user is following a board
     * @param boardId
     * @return 
     */
    @Override
    public boolean followsBoard(int boardId){
        Query check = em.createNativeQuery("SELECT count(*) FROM boardfollowers WHERE (boardid = ?2 and userid = ?1)");
        check.setParameter(1, currentUser);
        check.setParameter(2, boardId);  
        return (int)check.getSingleResult() != 0;
    }
    /**
     * Follow a person. If the currentUser or the toFollow user dont exist, this method does nothing.
     * @param toFollow the id of the person to be followed
     */
    @Override
    public void followPerson(int toFollow){
        Peoplefollower newFollower = new Peoplefollower(currentUser, toFollow);
        Account currentUsr = em.find(Account.class, currentUser);
        Account followedUsr = em.find(Account.class, toFollow);

        if(currentUsr == null || followedUsr == null){
            return;
        }
   
        if(followsUser(followedUsr)){
            // User is already following toFollow
            return;
        }
       
        newFollower.setAccount(currentUsr);
        newFollower.setAccount1(followedUsr);
        System.out.println(currentUsr.getId());
        System.out.println(followedUsr.getId());
        System.out.println("______________");
        em.persist(newFollower);
        
        stats.follow(em.find(Account.class, currentUser), em.find(Account.class, toFollow));
    }    
    
    /**
     * Unfollow a person. If the person to unfollow doesnt exist, this method does nothing. 
     * @param toUnFollow 
     */
    @Override
    public void unfollowPerson(int toUnFollow){
        Peoplefollower toRemove = em.find(Peoplefollower.class, new PeoplefollowerPK(currentUser, toUnFollow));
        if(toRemove != null){
            em.remove(toRemove);
        }else{
            return;
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
    
    /**
     * Get messages for the current user
     * @return 
     */
    @Override
    public Collection<Messages> getMessages(){
        Account user = em.find(Account.class, currentUser);
        return user.getMessagesCollection();
    }
    
    /**
     * Get the number of followers for current user
     * @return 
     */
    @Override
    public int getNumFollowers(){
        Account user =  em.find(Account.class, currentUser);
        return user.getPeoplefollowerCollection().size();
    }
    
    /**
     * Get the number of people following the current user
     * @return 
     */
    @Override
    public int getNumFollowing(){
        Account user =  em.find(Account.class, currentUser);
        return user.getPeoplefollowerCollection1().size();
    }
    // Getters and setters for account entity
    
    
    /**
     * Set the currently managed account for this accountBean
     * @param acc 
     */
    @Override
    public void setAccount(int acc){
        currentUser = acc;
        
    }
    
    @Override
    public Account getAccount(){
        Account currentAcc = em.find(Account.class, currentUser);
        em.refresh(currentAcc);
        return currentAcc;
    }
    
    @Override
    public Account getAccount(String username){
        Account account = null;
        try{
            account = (Account)em.createNamedQuery("Account.findByUsername").setParameter("username", username).getSingleResult();
            em.refresh(account);
            System.out.println(account.getIsBlocked());
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
        return getTailoredPins(5, 5);
    }
    
    /**
     * Returns numFromBoard + numFromCategories pins based on the categories which the current user follows and the boards which this user follows.
     * @param numFromBoard
     * @param numFromCategories
     * @return 
     */
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
                continue;
            }
            int maxIdForCat = (int)res;
            int boardId = rand.nextInt(maxIdForCat + 1);
            Board selectedBoard = (Board)em.createNamedQuery("Board.firstOccurencesAfterId").setParameter("id", boardId).setParameter("category", currentCategory).setMaxResults(1).getSingleResult();
            Collection<Pin> allPins = selectedBoard.getPinCollection();
            if(allPins.isEmpty()){
                continue;
            }
            int pinIndex = rand.nextInt(allPins.size());
            resultList.add((Pin)allPins.toArray()[pinIndex]);            
        }
        
        Collections.shuffle(resultList);
        return resultList;
    }
    
    /**
     * Check if current user follows an account.
     * @param acc
     * @return 
     */
    @Override
    public boolean followsUser(Account acc){
        Account currentAcc = em.find(Account.class, currentUser);
        em.refresh(currentAcc);
        return (long)em.createNamedQuery("Peoplefollower.checkIfExists").setParameter("follower", currentAcc.getId()).setParameter("followed", acc.getId()).getSingleResult() > 0;
    }
    
    /**
     * Check if user is friends with other user
     * @param acc
     * @return 
     */
    @Override
    public boolean isFriends(Account acc){
        Account currentAcc = em.find(Account.class, currentUser);
        em.refresh(currentAcc);
        return currentAcc.getAccountCollection2().contains(acc);
    }
    
    /**
     * Gets 5 pins as recommentations. If the user did not get any recommendations yet, or recommendations are outdated, create 5 new
     * @return 
     */
    @Override
    public List<Pin> getRecommendations(){      
        Account user = em.find(Account.class, currentUser);
        List<Pin> ret = new ArrayList<>();
        Suggestions sug;
        if(user == null){
            return null;
        }
        List<Suggestions> res = em.createNamedQuery("Suggestions.findByUserid").setParameter("userid", currentUser).getResultList();
        
        if(res.isEmpty()){
            List<Pin> listOfPins = getTailoredPins(50, 50);
            Set<Pin> UniqueSet = new HashSet<Pin>(listOfPins);
            List<Pin> uniquePins = new ArrayList<Pin>(UniqueSet);
            Collections.shuffle(uniquePins);
            uniquePins = uniquePins.subList(0,5);
            if(uniquePins.isEmpty()){
                return new ArrayList<Pin>();
            }
            while(uniquePins.size() < 5){
                // Repeat first pin because not enough content
                uniquePins.add(uniquePins.get(0));
            }
            Query quer = em.createNativeQuery("INSERT INTO suggestions(userid, meal1, meal2, meal3, meal4, meal5, timestamp) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)");
            quer.setParameter(1, currentUser);
            quer.setParameter(2, uniquePins.get(0).getId());
            quer.setParameter(3, uniquePins.get(1).getId());
            quer.setParameter(4, uniquePins.get(2).getId());
            quer.setParameter(5, uniquePins.get(3).getId());
            quer.setParameter(6, uniquePins.get(4).getId());
            quer.setParameter(7, new Date(System.currentTimeMillis()));
            quer.executeUpdate();
            sug = em.find(Suggestions.class, currentUser);
        }else{
            sug = em.find(Suggestions.class, currentUser);
            Date today = new Date(System.currentTimeMillis());
            long diff = today.getTime() - sug.getTimestamp().getTime();
            if(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) > 5){
                List<Pin> listOfPins = getTailoredPins(50, 50);
                Set<Pin> UniqueSet = new HashSet<Pin>(listOfPins);
                List<Pin> uniquePins = new ArrayList<Pin>(UniqueSet);
                Collections.shuffle(uniquePins);
                uniquePins = uniquePins.subList(0,5);
                if(uniquePins.isEmpty()){
                    return new ArrayList<Pin>();
                }
                while(uniquePins.size() < 5){
                    // Repeat first pin because not enough content
                    uniquePins.add(uniquePins.get(0));
                }
                sug.setMeal1(uniquePins.get(0));
                sug.setMeal2(uniquePins.get(1));
                sug.setMeal3(uniquePins.get(2));
                sug.setMeal4(uniquePins.get(3));
                sug.setMeal5(uniquePins.get(4));
                sug.setTimestamp(new Date(System.currentTimeMillis()));
                em.flush();
            }
        }
        ret.add(sug.getMeal1());
        ret.add(sug.getMeal2());
        ret.add(sug.getMeal3());
        ret.add(sug.getMeal4());
        ret.add(sug.getMeal5());
        return ret;
    }
}
