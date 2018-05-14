/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.File;
import javax.ejb.Remote;
import javax.servlet.http.Part;

/**
 *
 * @author ken
 */
@Remote
public interface ImageBeanInterface {
    public String storeImage(String fileName, Part filePart);
    
    public File getImage(String imageUrl);
}
