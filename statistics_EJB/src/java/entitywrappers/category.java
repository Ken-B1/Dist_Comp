/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityWrapper;

import java.io.Serializable;

/**
 *
 * @author ken
 */
public class category implements Serializable{
    int id;
    String name;
    String imageLocation;

    public category() {
    }

    public category(int id, String name, String imageLocation) {
        this.id = id;
        this.name = name;
        this.imageLocation = imageLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }
}
