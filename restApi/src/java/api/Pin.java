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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import services.boardCrudBeanInterface;
import services.pinCrudInterface;
/**
 *
 * @author ken
 */
@Path("pin")
public class Pin {
    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;

    private pinCrudInterface pinCrud;
    
    private boardCrudBeanInterface boardCrud;
    
    public Pin(){
        try{
            ic = new InitialContext(setRemote.setProperties());
            pinCrud = (pinCrudInterface)ic.lookup("java:global/statistics_EJB/pinCrudBean!services.pinCrudInterface");
            boardCrud = (boardCrudBeanInterface)ic.lookup("java:global/statistics_EJB/boardCrudBean!services.boardCrudBeanInterface");
        }catch(NamingException e){
            System.out.println("pinAPI error:");
            System.out.println(e.getMessage());
        }
    }
    @GET
    @Path("board/{boardid}")
    @Produces("application/json")
    public String getPinsForBoard(@PathParam("boardid") int boardId) {
        List<Entities.Pin> pins = pinCrud.getPinsForBoard(boardCrud.getBoard(boardId));
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

        x.add("pins", array);
        return x.build().toString();
    }
    
    @GET
    @Path("{pinid}")
    @Produces("application/json")
    public String getFullPin(@PathParam("pinid") int pinId) {
        Entities.Pin fullPin = pinCrud.getPin(pinId);
        
        JsonObjectBuilder x = Json.createObjectBuilder();
        x.add("id", fullPin.getId());
        x.add("name", fullPin.getRecipeName());
        x.add("recipe", fullPin.getRecipe());
        x.add("location", fullPin.getLocation());
        return x.build().toString();
    }
}
