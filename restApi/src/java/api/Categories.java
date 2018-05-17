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
import javax.ws.rs.Produces;
import services.databaseConnectorInterface;
/**
 * Class that represents a restful api for category retrieval.
 * @author ken
 */
@Path("Categories")
public class Categories {
    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;

    private databaseConnectorInterface connector;
    
    public Categories(){
        try{
            ic = new InitialContext(setRemote.setProperties());
            System.out.println("1");
            connector = (databaseConnectorInterface)ic.lookup("java:global/statistics_EJB/databaseConnector!services.databaseConnectorInterface");
        }catch(NamingException e){
            System.out.println("UserCategoriesAPI error:");
            System.out.println(e.getMessage());
        }
    }
    @GET
    @Produces("application/json")
    public String getHtml() {
        List<Entities.Categories> allCats = connector.getAllCategories();
        JsonObjectBuilder x = Json.createObjectBuilder();
        JsonArrayBuilder array = Json.createArrayBuilder();
        
        for(Entities.Categories cat: allCats){
            array.add(Json.createObjectBuilder().add("id", cat.getId()).add("name", cat.getName()));
        }
        x.add("Pins",array);
        return x.build().toString();
    }
}
