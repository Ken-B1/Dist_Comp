/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import Entities.Board;
import Entities.Categories;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import services.StatisticsBeanInterface;

/**
 *
 * @author ken
 */
@Stateless
@LocalBean
public class boardCrudBean{
    @PersistenceContext(unitName = "statistics_EJBPU")
    EntityManager em;
    
    private StatisticsBeanInterface stats;

    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;
    
    public boardCrudBean(){
         try{
            ic = new InitialContext();
            stats = (StatisticsBeanInterface)ic.lookup("java:module/StatisticsBean");
        }catch(NamingException e){
            System.out.println("boardCrudBean error:");
            System.out.println(e.getMessage());
        }            
    }
    
    public void createBoard(String name, Account owner){
        Board newboard = new Board();
        newboard.setBoardname(name);
        newboard.setOwner(owner);
        em.persist(newboard);
        em.flush();
        em.refresh(newboard);
        stats.createBoard(owner, newboard);
    }
    
    public void createBoard(String name, Categories category, Account owner){
        Board newboard = new Board();
        newboard.setBoardname(name);
        newboard.setOwner(owner);
        newboard.setCategory(category);
        em.persist(newboard);
        em.flush();
        em.refresh(newboard);
        stats.createBoard(owner, newboard);
    }
    
    public void createBoard(String name, int categoryId, Account owner){
        createBoard(name, em.find(Categories.class, categoryId), owner);
    }
    
    public Board getBoard(int boardId){
        Board returnboard = em.find(Board.class, boardId);
        return returnboard;
    }
    
    public List<Board> getBoardsForUser(Account owner){
        List<Board> resultlist = em.createNamedQuery("Board.findByOwner").setParameter("userid", owner).getResultList();
        return resultlist;
    }
    
    public void updateBoard(int boardId, String newName, Categories newCategory){
        Board toUpdate = em.find(Board.class, boardId);
        toUpdate.setBoardname(newName);
        toUpdate.setCategory(newCategory);
        em.flush();
    }
 
    public void updateBoard(int boardId, String newName, int newCategory){
        updateBoard(boardId, newName, em.find(Categories.class, newCategory));
    }
    
    public void makePrivate(int boardId, boolean isPrivate){
        Board toUpdate = em.find(Board.class, boardId);
        if(isPrivate){
            toUpdate.setIsprivate((short)1);
        }else{
            toUpdate.setIsprivate((short)0);
        }
        em.flush();
    }
    
    public void deleteBoard(int boardId){
        Board toDelete = em.find(Board.class, boardId);
        em.remove(toDelete);
        em.flush();
    }
}
