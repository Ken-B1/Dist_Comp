/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Account;
import Entities.Board;
import Entities.Categories;
import Entities.Notifications;
import Entities.Pin;
import Entities.Statistics;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ken
 */
@Remote
public interface StatisticsBeanInterface {
    public void log(final Account username);
    
    public void createBoard(Account user, Board board);
    
    public void createPin(Account user, Pin pin);
    
    public void follow( Account follower, Account followed);
    
    public void friend( Account requester, Account requested);
    
    public void boardCategory(Account user, Categories category);
    
    public void markAsRead(int notificationId);
    
    public Notifications getNotification(int notificationId);
    
    public List<Statistics> getStatistics(final int id);
}
