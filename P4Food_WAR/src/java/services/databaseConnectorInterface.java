/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Categories;
import java.util.List;

/**
 *
 * @author ken
 */
public interface databaseConnectorInterface {
    public List<Categories> getAllCategories();
}
