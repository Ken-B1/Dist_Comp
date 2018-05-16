/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Account;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author ken
 */
@Remote
public interface friendsBeanInterface {
    public void sendFriendRequest(int requesterId, int requestedId);
    
    public void sendunFriendRequest(int requesterId, int requestedId);
    
    public void acceptFriendRequest(int requesterId, int requestedId);
    
    public void removeFriendRequest(int requesterId, int requestedId);
    
    public Collection<Account> getFriends(int userId);
    
    public Collection<Account> getFriendRequests(int userId);
}
