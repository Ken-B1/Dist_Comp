/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import configuration.setRemote;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import services.LoginBeanInterface;
/**
 *
 * @author ken
 */
@Path("login")
public class Login {
    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;

    private LoginBeanInterface login;
    
    public Login(){
        try{
            ic = new InitialContext(setRemote.setProperties());
            login = (LoginBeanInterface)ic.lookup("java:global/statistics_EJB/LoginBean!services.LoginBeanInterface");
        }catch(NamingException e){
            System.out.println("UserCategoriesAPI error:");
            System.out.println(e.getMessage());
        }
    }
    
    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public String log(String toTest) {
        StringReader strReader = new StringReader(toTest);
        JsonObjectBuilder x = Json.createObjectBuilder();        
        JsonReader reader = Json.createReader(strReader);
        JsonObject jsonObject = reader.readObject();
        if(!jsonObject.containsKey("username") || !jsonObject.containsKey("password")){
            x.add("status", "400");
            x.add("reason", "Incorrect request body (missing username or password)");   
            return x.build().toString();
        }
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        
        if(username==null && password==null){
            x.add("status", "400");
            x.add("reason", "Incorrect request body (username or password is null)");   
            return x.build().toString();
        }
        
        int result = login.login(username, password);
        if(result != -1){
            x.add("status", "200");
            x.add("id", result);
        }else{
            x.add("status", "401");
            x.add("reason", "Incorrect credentials");
        }

        return x.build().toString();
    }
}
