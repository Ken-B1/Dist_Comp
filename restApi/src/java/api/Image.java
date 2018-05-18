/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import configuration.setRemote;
import java.io.IOException;
import java.io.OutputStream;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import services.AccountBeanInterface;
import services.ImageBeanInterface;
/**
 *
 * @author ken
 */
@Path("image")
public class Image {
    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;

    private ImageBeanInterface img;
    
    @Context
    private HttpServletResponse response;
    
    public Image(){
        try{
            ic = new InitialContext(setRemote.setProperties());
            img = (ImageBeanInterface)ic.lookup("java:global/statistics_EJB/ImageBean!services.ImageBeanInterface");
        }catch(NamingException e){
            System.out.println("imageAPI error:");
            System.out.println(e.getMessage());
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getHtml() {
        String url = "0qkaVYr.jpg";
        
        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream output) throws IOException {
              try {
                  output.write(img.getImage(url));
              } catch (Exception e) {
                 e.printStackTrace();
              }
            }
          };
 
        return Response.ok(stream, "image/png") //TODO: set content-type of your file
            .header("content-disposition", "attachment; filename = "+ url)
            .build();
    }
}
