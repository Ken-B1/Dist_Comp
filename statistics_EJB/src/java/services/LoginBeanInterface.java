/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Remote;

/**
 *
 * @author ken
 */
@Remote
public interface LoginBeanInterface {
    public int login(String username, String password);
}
