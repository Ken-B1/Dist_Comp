/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import configuration.setRemote;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import services.AccountBeanInterface;
/**
 *
 * @author ken
 */
@Path("Login")
public class Login {
    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;

    private AccountBeanInterface account;
    
    public Login(){
        try{
            ic = new InitialContext(setRemote.setProperties());
            System.out.println("1");
            account = (AccountBeanInterface)ic.lookup("java:global/statistics_EJB/AccountBean!services.AccountBeanInterface");
            account.setAccount(1);
        }catch(NamingException e){
            System.out.println("UserCategoriesAPI error:");
            System.out.println(e.getMessage());
        }
    }
    @GET
    @Produces("application/json")
    public String getHtml() {
        JsonObjectBuilder x = Json.createObjectBuilder();
        x.add("test", "a");
        return x.build().toString();
    }
}
