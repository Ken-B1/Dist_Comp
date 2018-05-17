/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import configuration.setRemote;
import java.math.BigDecimal;
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
import services.searchBeanInterface;
/**
 *
 * @author ken
 */
@Path("search/{searchstring}")
public class Search {
    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;

    private searchBeanInterface search;
    
    public Search(){
        try{
            ic = new InitialContext(setRemote.setProperties());
            System.out.println("1");
            search = (searchBeanInterface)ic.lookup("java:global/statistics_EJB/searchBean!services.searchBeanInterface");
        }catch(NamingException e){
            System.out.println("searchRestAPI error:");
            System.out.println(e.getMessage());
        }
    }
    @GET
    @Produces("application/json")
    public String search(@PathParam("searchstring") String searchString) {
        List<Entities.Board> boards = search.findBoardsForSearch(searchString);
        List<Entities.Pin> pins = search.findPinsForSearch(searchString);
        List<Entities.Account> users = search.findUsersForSearch(searchString);
        JsonObjectBuilder x = Json.createObjectBuilder();        
        JsonArrayBuilder array = Json.createArrayBuilder();
        for(Entities.Board board: boards){
            array.add(Json.createObjectBuilder().add("id", board.getId()).add("name", board.getBoardname()).add("isPrivate", board.getIsprivate()));
        }
        x.add("Boards",array);
        
        array = Json.createArrayBuilder();
        for(Entities.Pin pin: pins){
            array.add(Json.createObjectBuilder().add("id", pin.getId()).add("name", pin.getRecipeName()).add("recipe", pin.getRecipe()).add("imglocation", pin.getLocation()));
        }
        x.add("Pins",array);
        
        array = Json.createArrayBuilder();
        for(Entities.Account account: users){
            array.add(Json.createObjectBuilder().add("id", account.getId())
                    .add("id", account.getId())
                    .add("username", account.getUsername())
                    .add("email", account.getEmail())
                    .add("fname", account.getFname())
                    .add("lname", account.getLname())
                    .add("gender", account.getGender())
                    .add("country", account.getCountry())
                    .add("isblocked", account.getIsBlocked())
                    .add("gmailid", account.getGmailId() == null ? "0" : account.getGmailId()));
        }
        x.add("users",array);
        return x.build().toString();
    }
}
