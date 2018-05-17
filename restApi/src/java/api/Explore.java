/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import configuration.setRemote;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import services.categoryBeanInterface;
/**
 *
 * @author ken
 */
@Path("/explore")
public class Explore {
    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;

    private categoryBeanInterface explorer;
    
    public Explore(){
        try{
            ic = new InitialContext(setRemote.setProperties());
            explorer = (categoryBeanInterface)ic.lookup("java:global/statistics_EJB/categoryBean!services.categoryBeanInterface");
        }catch(NamingException e){
            System.out.println("ExploreAPI error:");
            System.out.println(e.getMessage());
        }
    }
    
    @GET
    @Path("/top")
    @Produces("application/json")
    public String getTopCategories() {
        List<Entities.Categories> topCats = explorer.getTopCategories(10);
        JsonObjectBuilder x = Json.createObjectBuilder();
        JsonArrayBuilder array = Json.createArrayBuilder();
        
        for(Entities.Categories cat: topCats){
            array.add(Json.createObjectBuilder().add("id", cat.getId()).add("name", cat.getName()));
        }
        x.add("TopCategories",array);
        return x.build().toString();
    }
    
    @GET
    @Path("/trending")
    @Produces("application/json")
    public String getRisingCategories() {
        List<Entities.Categories> risingCats = explorer.getRisingCategories(10);
        JsonObjectBuilder x = Json.createObjectBuilder();
        JsonArrayBuilder array = Json.createArrayBuilder();
        
        for(Entities.Categories cat: risingCats){
            array.add(Json.createObjectBuilder().add("id", cat.getId()).add("name", cat.getName()));
        }
        x.add("RisingCategories",array);
        return x.build().toString();
    }
}
