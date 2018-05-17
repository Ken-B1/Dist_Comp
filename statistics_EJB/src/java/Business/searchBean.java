/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import Entities.Board;
import Entities.Pin;
import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import services.searchBeanInterface;

/**
 *
 * @author ken
 * Class that presents search functionalities
 * Can search for pins, boards and users
 */
@Stateless
public class searchBean implements searchBeanInterface{

    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;
    
    /**
     * Method which returns a List of pins that contain parts of the searchFunction in it's recipename.
     * The searchfunction is split up in words and for each word recipes with names containing that word will be returned.
     * The first 20 results will be returned for each searchword
     * @param searchFunction
     * @return
     */
    @Override
    public List<Pin> findPinsForSearch(String searchFunction){
        String searchList[] = searchFunction.split(" ");
        List<Pin> resultList = new ArrayList<>();
        for(String x: searchList){
            List<Pin> currentList = em.createNamedQuery("Pin.findByExpression").setParameter("expression", "%" + x + "%").setMaxResults(20).getResultList();
            resultList.removeAll(currentList);            
            resultList.addAll(currentList);
        }
        
        return resultList;
    }
    
        /**
     * Method which returns a List of pins that contain parts of the searchFunction in it's recipename.
     * The searchfunction is split up in words and for each word recipes with names containing that word will be returned.
     * @param searchFunction
     * @param limit Indicates amount of records returned
     * @return
     */
    @Override
    public List<Pin> findPinsForSearch(String searchFunction, int limit){
        String searchList[] = searchFunction.split(" ");
        List<Pin> resultList = new ArrayList<>();
        for(String x: searchList){
            List<Pin> currentList = em.createNamedQuery("Pin.findByExpression").setParameter("expression", "%" + x + "%").setMaxResults(limit).getResultList();
            resultList.removeAll(currentList);            
            resultList.addAll(currentList);
        }
        
        return resultList.subList(0, min(resultList.size(), limit));
    }

    /**
     * Method which returns a List of boards that contain parts of the searchFunction in it's boardName or Category.
     * The searchfunction is split up in words and for each word boards with names or belonging to categories containing that word will be returned.
     * The first 20 results will be returned for each searchword
     * @param searchFunction
     * @return
     */
    @Override
    public List<Board> findBoardsForSearch(String searchFunction){
        String searchList[] = searchFunction.split(" ");
        List<Board> resultList = new ArrayList<>();
        for(String x: searchList){
            // Look for names containing word
            List<Board> currentList = em.createNamedQuery("Board.findByExpression").setParameter("expression", "%" + x + "%").setMaxResults(20).getResultList();
            resultList.removeAll(currentList);            
            resultList.addAll(currentList);
            
            // Look for categories containing word
            currentList = em.createNamedQuery("Board.findByExpressionCategory").setParameter("expression", "%" + x + "%").setMaxResults(20).getResultList();
            resultList.removeAll(currentList);            
            resultList.addAll(currentList);
        }
        
        return resultList;
    }
    
        /**
     * Method which returns a List of boards that contain parts of the searchFunction in it's boardName or Category.
     * The searchfunction is split up in words and for each word boards with names or belonging to categories containing that word will be returned.
     * @param searchFunction
     * @param limit Indicates amount of records returned
     * @return
     */
    @Override
    public List<Board> findBoardsForSearch(String searchFunction, int limit){
        String searchList[] = searchFunction.split(" ");
        List<Board> resultList = new ArrayList<>();
        for(String x: searchList){
            // Look for names containing word
            List<Board> currentList = em.createNamedQuery("Board.findByExpression").setParameter("expression", "%" + x + "%").setMaxResults(limit).getResultList();
            resultList.removeAll(currentList);            
            resultList.addAll(currentList);
            
            // Look for categories containing word
            currentList = em.createNamedQuery("Board.findByExpressionCategory").setParameter("expression", "%" + x + "%").setMaxResults(limit).getResultList();
            resultList.removeAll(currentList);            
            resultList.addAll(currentList);
        }
        return resultList.subList(0, min(resultList.size(), limit));
    }
    
    /**
     * Method which returns a List of account that contain parts of the searchFunction in it's username.
     * The searchfunction is split up in words and for each word account with usernames containing that word will be returned.
     * The first 20 results will be returned for each searchword
     * @param searchFunction
     * @return
     */
    @Override
    public List<Account> findUsersForSearch(String searchFunction){
        String searchList[] = searchFunction.split(" ");
        List<Account> resultList = new ArrayList<>();
        for(String x: searchList){
            List<Account> currentList = em.createNamedQuery("Account.findByExpression").setParameter("expression", "%" + x + "%").setMaxResults(20).getResultList();
            resultList.removeAll(currentList);            
            resultList.addAll(currentList);
        }
        
        return resultList;
    }
    
    /**
     * Method which returns a List of account that contain parts of the searchFunction in it's username.
     * The searchfunction is split up in words and for each word account with usernames containing that word will be returned.
     * @param searchFunction
     * @param limit Indicates amount of records returned 
     * @return
     */
    @Override
    public List<Account> findUsersForSearch(String searchFunction, int limit){
        String searchList[] = searchFunction.split(" ");
        List<Account> resultList = new ArrayList<>();
        for(String x: searchList){
            List<Account> currentList = em.createNamedQuery("Account.findByExpression").setParameter("expression", "%" + x + "%").setMaxResults(limit).getResultList();
            resultList.removeAll(currentList);            
            resultList.addAll(currentList);
        }
        return resultList.subList(0, min(resultList.size(), limit));
    }    
}
