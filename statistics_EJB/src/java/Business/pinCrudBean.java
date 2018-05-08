/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Board;
import Entities.Pin;
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
public class pinCrudBean {
    @PersistenceContext(unitName = "statistics_EJBPU")
    EntityManager em;
    
    @EJB
    private StatisticsBean stats;
    
    public pinCrudBean() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void createPin(String name, String recipe, Board board){
        Pin newpin = new Pin();
        newpin.setRecipeName(name);
        newpin.setBoard(board);
        newpin.setRecipe(recipe);
        em.persist(newpin);
        stats.createPin(board.getOwner(), newpin);
    }
    
     public void createPin(String name, String recipe, int boardId){
        createPin(name, recipe, em.find(Board.class, boardId));
    }   
    
    public Pin getPin(int id){
        return em.find(Pin.class, id);
    }
    
    public List<Pin> getPinsForBoard(Board board){
        List<Pin> resultlist = em.createNamedQuery("Pin.findByBoard").setParameter("board", board).getResultList();
        return resultlist;
    }
    
    public void updatePin(int id, String name, String recipe, int boardId){
        Pin toUpdate = em.find(Pin.class, id);
        toUpdate.setRecipe(recipe);
        toUpdate.setRecipeName(name);
        toUpdate.setBoard(em.find(Board.class, boardId));
        em.flush();
        stats.updatePin(toUpdate.getBoard().getOwner(), toUpdate);
    }
    
    public void deletePin(int pinId){
        Pin toDelete = em.find(Pin.class, pinId);
        em.remove(toDelete);
        em.flush();
        stats.removePin(toDelete.getBoard().getOwner(), toDelete);
    }
}
