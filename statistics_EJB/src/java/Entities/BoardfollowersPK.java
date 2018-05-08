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
public class BoardfollowersPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Userid")
    private int userid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Boardid")
    private int boardid;

    public BoardfollowersPK() {
    }

    public BoardfollowersPK(int userid, int boardid) {
        this.userid = userid;
        this.boardid = boardid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getBoardid() {
        return boardid;
    }

    public void setBoardid(int boardid) {
        this.boardid = boardid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userid;
        hash += (int) boardid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BoardfollowersPK)) {
            return false;
        }
        BoardfollowersPK other = (BoardfollowersPK) object;
        if (this.userid != other.userid) {
            return false;
        }
        if (this.boardid != other.boardid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.BoardfollowersPK[ userid=" + userid + ", boardid=" + boardid + " ]";
    }
    
}
