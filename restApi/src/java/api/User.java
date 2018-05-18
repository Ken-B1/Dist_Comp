/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import Entities.Account;
import configuration.setRemote;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import services.AccountBeanInterface;
import services.messageCrudInterface;
/**
 *
 * @author ken
 */
@Path("user/{username}")
public class User {
    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;

    private AccountBeanInterface account;
    
    private messageCrudInterface messages;
    
    public User(){
        try{
            ic = new InitialContext(setRemote.setProperties());
            account = (AccountBeanInterface)ic.lookup("java:global/statistics_EJB/AccountBean!services.AccountBeanInterface");
            messages = (messageCrudInterface)ic.lookup("java:global/statistics_EJB/messageCrud!services.messageCrudInterface");
        }catch(NamingException e){
            System.out.println("UserCategoriesAPI error:");
            System.out.println(e.getMessage());
        }
    }
    @GET
    @Produces("application/json")
    public String getSettings(@PathParam("username") String username) {
        Account user = account.getAccount(username);
        JsonObjectBuilder x = Json.createObjectBuilder();
        x.add("id", user.getId());
        x.add("username", user.getUsername());
        x.add("fname", user.getFname());
        x.add("lname", user.getLname());
        x.add("country", user.getCountry() == null? "" : user.getCountry());
        x.add("gender", user.getGender());
        x.add("gmailid", user.getGmailId() == null? "" : user.getGmailId());
        x.add("isBlocked", user.getIsBlocked());
        return x.build().toString();
    }
    
    @GET
    @Path("categories")
    @Produces("application/json")
    public String getCategories(@PathParam("username") String username) {
        account.setAccount(account.getAccount(username).getId());
        Collection<Entities.Categories> userCats = account.getUserCategories();
        
        JsonObjectBuilder x = Json.createObjectBuilder();
        JsonArrayBuilder array = Json.createArrayBuilder();
        
        for(Entities.Categories cat: userCats){
            array.add(Json.createObjectBuilder().add("id", cat.getId()).add("name", cat.getName()));
        }

        x.add("categories", array);
        return x.build().toString();
    }
    
    @GET
    @Path("messages")
    @Produces("application/json")
    public String getMessages(@PathParam("username") String username) {
        account.setAccount(account.getAccount(username).getId());
        Collection<Entities.Messages> userMessages = account.getMessages();
        
        JsonObjectBuilder x = Json.createObjectBuilder();
        JsonArrayBuilder array = Json.createArrayBuilder();
        for(Entities.Messages message: userMessages){
            array.add(Json.createObjectBuilder()
                    .add("id", message.getId())
                    .add("subject", message.getSubject())
                    .add("content", message.getContent())
                    .add("isRead", message.getIsRead())
                    .add("sender", message.getSender().getUsername())
                    .add("receiver",username)
                    .add("timestamp", message.getTimestamp().toString()));
        }

        x.add("messages", array);
        return x.build().toString();
    }
    
    @GET
    @Path("notifications")
    @Produces("application/json")
    public String getNotifications(@PathParam("username") String username) {
        account.setAccount(account.getAccount(username).getId());
        List<Entities.Notifications> notifications = new ArrayList(account.getAccount().getNotificationsCollection1());
        
        JsonObjectBuilder x = Json.createObjectBuilder();
        JsonArrayBuilder array = Json.createArrayBuilder();
        
        for(Entities.Notifications nots: notifications){
            array.add(Json.createObjectBuilder()
                    .add("id", nots.getIdnotifications())
                    .add("sender", nots.getCreator().getUsername())
                    .add("description", nots.getDescription())
                    .add("isread", nots.getIsread())
                    .add("type", nots.getType())
                    .add("receiver", nots.getReceiver().getUsername()));
        }

        x.add("notification", array);
        return x.build().toString();
    }
    
    @GET
    @Path("userpins")
    @Produces("application/json")
    public String getUserPins(@PathParam("username") String username) {
        account.setAccount(account.getAccount(username).getId());
        List<Entities.Pin> pins = account.getTailoredPins();
        
        JsonObjectBuilder x = Json.createObjectBuilder();
        JsonArrayBuilder array = Json.createArrayBuilder();
        
        for(Entities.Pin pin: pins){
            array.add(Json.createObjectBuilder()
                    .add("id", pin.getId())
                    .add("name", pin.getRecipeName())
                    .add("recipe", pin.getRecipe())
                    .add("boardid", pin.getBoard().getId())
                    .add("imagelocation", pin.getLocation()));
        }

        x.add("tailoredPins", array);
        return x.build().toString();
    }
}
