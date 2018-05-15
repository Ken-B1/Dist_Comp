/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ejb.Stateless;
import javax.servlet.http.Part;
import services.ImageBeanInterface;

/**
 *
 * @author ken
 */
@Stateless
public class ImageBean implements ImageBeanInterface{

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public String storeImage(String fileName, byte[] data){
        String dirString = System.getProperty("user.home") + File.separator + "p4foodPictures";
        File dir = new File(fileName);
        dir.mkdirs();
        
        OutputStream out = null;

        try {
            out = new FileOutputStream(new File(dirString + File.separator
                    + fileName));
            

            out.write(data);
            
            System.out.println("New file " + fileName + " created at " + dirString);
            out.close();
            return dirString + File.separator + fileName;
        } catch (FileNotFoundException fne) {
            System.out.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            System.out.println("<br/> ERROR: " + fne.getMessage());
        } catch ( IOException e){
            
        }
        return null;
    }
    
    @Override
    public File getImage(String imageUrl){
        File file = new File(imageUrl);
        
        return file;
    }
}
