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
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ken
 */
@Stateless
@LocalBean
public class boardCrudBean {
    @PersistenceContext(unitName = "statistics_EJBPU")
    EntityManager em;
    
    @EJB
    private StatisticsBean stats;
    
    public boardCrudBean() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void createBoard(String name, Account owner){
        Board newboard = new Board();
        newboard.setBoardname(name);
        newboard.setOwner(owner);
        em.persist(newboard);
        stats.createBoard(owner, newboard);
    }
    
    public void createBoard(String name, Categories category, Account owner){
        Board newboard = new Board();
        newboard.setBoardname(name);
        newboard.setOwner(owner);
        newboard.setCategory(category);
        em.persist(newboard);
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
        Board toUpdate = em.find(Board.class, newName);
        toUpdate.setBoardname(newName);
        toUpdate.setCategory(newCategory);
        em.flush();
        stats.updateBoard(toUpdate.getOwner(), toUpdate);
    }
    
    public void deleteBoard(int boardId){
        Board toDelete = em.find(Board.class, boardId);
        em.remove(toDelete);
        em.flush();
        stats.removeBoard(toDelete.getOwner(), toDelete);
    }
}
