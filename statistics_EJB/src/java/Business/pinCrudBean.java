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
    
    /**
     * Creates a pin. Pin won't be created if name == null or "" or if recipe == null or "". If url == null or "" the url will be default.jpg
     * @param name
     * @param recipe
     * @param board
     * @param url 
     */
    @Override
    public void createPin(String name, String recipe, Board board, String url){
        if("".equals(name) || name == null || "".equals(recipe) || recipe == null){
            return;
        }
        
        if("".equals(url) || url == null){
            url = "default.jpg";
        }
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
    /**
     *
     * Creates a pin. Pin won't be created if name == null or "" or if recipe == null or "". If url == null or "" the url will be default.jpg
     * @param name
     * @param recipe
     * @param boardId
     * @param url 
     */
    @Override
    public void createPin(String name, String recipe, int boardId, String url){
        createPin(name, recipe, em.find(Board.class, boardId), url);
    }   
    
    /**
     * Returns a pin
     * @param id
     * @return 
     */
    @Override
    public Pin getPin(int id){
        return em.find(Pin.class, id);
    }
    
    /**
     * Returns all pins in a board
     * @param board
     * @return 
     */
    @Override
    public List<Pin> getPinsForBoard(Board board){
        List<Pin> resultlist = em.createNamedQuery("Pin.findByBoard").setParameter("board", board).getResultList();
        return resultlist;
    }
    
    /**
     * Updates a pin. Pin won't be updated if name == null or "" or if recipe == null or "". If url == null or "" the url will be default.jpg
     * @param id
     * @param name
     * @param recipe
     */
    @Override
    public void updatePin(int id, String name, String recipe){
        Pin toUpdate = em.find(Pin.class, id);
        if(toUpdate == null || "".equals(name) || name == null || "".equals(recipe) || recipe == null){
            return;
        }
        
        toUpdate.setRecipe(recipe);
        toUpdate.setRecipeName(name);
        em.flush();
    }
    
    /**
     * Deletes the corresponding pin
     * @param pinId 
     */
    @Override
    public void deletePin(int pinId){
        Pin toDelete = em.find(Pin.class, pinId);
        if(toDelete == null){
            return;
        }
        em.remove(toDelete);
        em.flush();
    }
}
