/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Business_Utility.Status;

/**
 *
 * @author ken
 */
public interface RegistrationBeanInterface {
    public Status createAccount(String email,String userName,String password,String fname,String lname,String country,String gender);
       
    public Status validate(String email, String userName);
}
