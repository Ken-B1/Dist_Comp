/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

import javax.ejb.Stateless;

/**
 *
 * @author ken
 */
@Stateless
public class NewSessionBean implements testInterface{

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public NewSessionBean(){};
    
    @Override
    public void print(){
        System.out.println("Hello World");
    }
}
