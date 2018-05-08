/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ken
 */
@Embeddable
public class PeoplefollowerPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "User")
    private int user;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FollowedUser")
    private int followedUser;

    public PeoplefollowerPK() {
    }

    public PeoplefollowerPK(int user, int followedUser) {
        this.user = user;
        this.followedUser = followedUser;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(int followedUser) {
        this.followedUser = followedUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) user;
        hash += (int) followedUser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeoplefollowerPK)) {
            return false;
        }
        PeoplefollowerPK other = (PeoplefollowerPK) object;
        if (this.user != other.user) {
            return false;
        }
        if (this.followedUser != other.followedUser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PeoplefollowerPK[ user=" + user + ", followedUser=" + followedUser + " ]";
    }
    
}
