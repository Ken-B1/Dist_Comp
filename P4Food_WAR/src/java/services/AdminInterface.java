/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Business_Utility.Status;
import Entities.Categories;
import java.util.List;

/**
 *
 * @author ken
 */
public interface AdminInterface {
    public Status createAdminAccount(String email,String userName,String password,String fname,String lname,String country,String gender);

    public void makeAdmin(String username);
    
    public void addCategory(String name);
    
    public List<String> getCategoryNames();
    
    public List<Categories> getCategories();
    
    public void blockUser(int userId);
    
    public void unblockUser(int userId);
    
    public void removeUser(int userId);
}
