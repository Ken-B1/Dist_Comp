/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Account;
import Entities.Board;
import Entities.Pin;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ken
 */
@Remote
public interface searchBeanInterface {
    public List<Pin> findPinsForSearch(String searchFunction);
    
    public List<Pin> findPinsForSearch(String searchFunction, int limit);
    
    public List<Board> findBoardsForSearch(String searchFunction);
    
    public List<Board> findBoardsForSearch(String searchFunction, int limit);
    
    public List<Account> findUsersForSearch(String searchFunction);
    
    public List<Account> findUsersForSearch(String searchFunction, int limit);
}
