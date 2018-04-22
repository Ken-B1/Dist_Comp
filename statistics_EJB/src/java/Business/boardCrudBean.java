/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import Entities.Board;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ken
 */
@Stateful
@LocalBean
public class boardCrudBean {
    @PersistenceContext(unitName = "statistics_EJBPU")
    EntityManager em;
    public boardCrudBean() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public void createBoard(String name, Account owner){
        Board newboard = new Board();
        newboard.setBoardname(name);
        newboard.setOwner(owner);
        em.persist(newboard);
    }
    
    public List<Board> getBoardsForUser(Account owner){
        List<Board> resultlist = em.createNamedQuery("Board.findByOwner").setParameter("userid", owner).getResultList();
        return resultlist;
    }
    
    public void deleteBoard(int boardId){
        Board toDelete = em.find(Board.class, boardId);
        em.remove(toDelete);
        em.flush();
    }
}
