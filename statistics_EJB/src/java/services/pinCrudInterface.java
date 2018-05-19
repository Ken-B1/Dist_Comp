/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Board;
import Entities.Pin;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ken
 */
@Remote
public interface pinCrudInterface {
    public void createPin(String name, String recipe, Board board, String url);
    
    public void createPin(String name, String recipe, int boardId, String url);
    
    public Pin getPin(int id);
    
    public List<Pin> getPinsForBoard(Board board);
    
    public void updatePin(int id, String name, String recipe);
    
    public void deletePin(int pinId);
}
