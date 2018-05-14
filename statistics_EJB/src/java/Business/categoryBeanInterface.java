/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Categories;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author ken
 */
@Remote
public interface categoryBeanInterface {
    public List<Categories> getTopCategories();
    
    public List<Categories> getTopCategories(int amount);
    
    public List<Categories> getRisingCategories(int amount);
}
