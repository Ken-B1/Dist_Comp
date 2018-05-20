/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Business_Utility.Status;
import Entities.Account;
import Entities.Board;
import Entities.Categories;
import Entities.Messages;
import Entities.Pin;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ken
 */
@Remote
public interface AccountBeanInterface {
    public Status updateAccount(Account account);
    
    public boolean hasCategories();
    
    public Collection<Categories> getUserCategories();
    
    public void addUserCategory(Categories newCategory);
    
    public void followBoard(Board toFollow);
    
    public void unfollowBoard(Board toUnFollow);
    
    public void followPerson(int toFollow);
    
    public void unfollowPerson(int toUnFollow);
    
    public void blockPerson(int toBlock);
    
    public void unblockPerson(int tounBlock);
    
    public Collection<Messages> getMessages();
    
    public int getNumFollowers();
    
    public int getNumFollowing();
    
    public void setAccount(int acc);
    
    public Account getAccount();
    
    public Account getAccount(String username);
    
    public Account getAccountForId(int id);
     
    public List<Pin> getTailoredPins();
    
    public List<Pin> getTailoredPins(int numFromBoard, int numFromCategories);
    
    public boolean followsUser(Account acc);
    
    public boolean isFriends(Account acc);
}
