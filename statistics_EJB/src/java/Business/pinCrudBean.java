/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Board;
import Entities.Pin;
import java.util.List;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import services.StatisticsBeanInterface;
import services.pinCrudInterface;

/**
 *
 * @author ken
 */
@Stateless
public class pinCrudBean implements pinCrudInterface{
    @PersistenceContext(unitName = "statistics_EJBPU")
    EntityManager em;
    
    private StatisticsBeanInterface stats;

    private static InitialContext ic;
    
    public pinCrudBean(){
         try{
            ic = new InitialContext();
            stats = (StatisticsBeanInterface)ic.lookup("java:module/StatisticsBean");
        }catch(NamingException e){
            System.out.println("pinCrudBean error:");
            System.out.println(e.getMessage());
        }            
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void createPin(String name, String recipe, Board board, String url){
        Pin newpin = new Pin();

        newpin.setRecipeName(name);
        newpin.setBoard(board);
        newpin.setRecipe(recipe);
        newpin.setLocation(url);

        em.persist(newpin);
        em.flush();
        em.refresh(newpin);

        stats.createPin(board.getOwner(), newpin);
    }
    
    @Override
    public void createPin(String name, String recipe, int boardId, String url){
        createPin(name, recipe, em.find(Board.class, boardId), url);
    }   
    
    @Override
    public Pin getPin(int id){
        return em.find(Pin.class, id);
    }
    
    @Override
    public List<Pin> getPinsForBoard(Board board){
        List<Pin> resultlist = em.createNamedQuery("Pin.findByBoard").setParameter("board", board).getResultList();
        return resultlist;
    }
    
    @Override
    public void updatePin(int id, String name, String recipe, int boardId){
        Pin toUpdate = em.find(Pin.class, id);
        toUpdate.setRecipe(recipe);
        toUpdate.setRecipeName(name);
        toUpdate.setBoard(em.find(Board.class, boardId));
        em.flush();
    }
    
    @Override
    public void deletePin(int pinId){
        Pin toDelete = em.find(Pin.class, pinId);
        em.remove(toDelete);
        em.flush();
    }
}
