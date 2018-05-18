/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import Entities.Account;
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
import services.AccountBeanInterface;
import services.boardCrudBeanInterface;
/**
 *
 * @author ken
 */
@Path("board")
public class Board {
    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;

    private boardCrudBeanInterface boards;
    
    private AccountBeanInterface account;
        
    public Board(){
        try{
            ic = new InitialContext(setRemote.setProperties());
            boards = (boardCrudBeanInterface)ic.lookup("java:global/statistics_EJB/boardCrudBean!services.boardCrudBeanInterface");
            account = (AccountBeanInterface)ic.lookup("java:global/statistics_EJB/AccountBean!services.AccountBeanInterface");
        }catch(NamingException e){
            System.out.println("boardAPI error:");
            System.out.println(e.getMessage());
        }
    }
    @GET
    @Path("{username}")
    @Produces("application/json")
    public String getUserBoards(@PathParam("username") String username) {
        Account requestedUser = account.getAccount(username);
        List<Entities.Board> userBoards = boards.getBoardsForUser(requestedUser);
        
        JsonArrayBuilder array = Json.createArrayBuilder();
        JsonObjectBuilder x = Json.createObjectBuilder();
        
        for(Entities.Board board: userBoards){
            array.add(Json.createObjectBuilder().add("id", board.getId()).add("boardname", board.getBoardname()).add("isPrivate", board.getIsprivate()));
        }
        
        
        x.add("boards", array);
        return x.build().toString();
    }
}
