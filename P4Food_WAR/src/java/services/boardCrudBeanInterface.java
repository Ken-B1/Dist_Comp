/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Account;
import Entities.Board;
import Entities.Categories;
import java.util.List;

/**
 *
 * @author ken
 */
public interface boardCrudBeanInterface {
    public void createBoard(String name, Account owner);
    
    public void createBoard(String name, Categories category, Account owner);
    
    public void createBoard(String name, int categoryId, Account owner);
    
    public Board getBoard(int boardId);
    
    public List<Board> getBoardsForUser(Account owner);
    
    public void updateBoard(int boardId, String newName, Categories newCategory);
    
    public void updateBoard(int boardId, String newName, int newCategory);
    
    public void makePrivate(int boardId, boolean isPrivate);
    
    public void deleteBoard(int boardId);
    
}
