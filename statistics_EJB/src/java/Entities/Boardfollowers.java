/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ken
 */
@Entity
@Table(name = "boardfollowers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boardfollowers.findAll", query = "SELECT b FROM Boardfollowers b")
    , @NamedQuery(name = "Boardfollowers.findByUserid", query = "SELECT b FROM Boardfollowers b WHERE b.boardfollowersPK.userid = :userid")
    , @NamedQuery(name = "Boardfollowers.findByBoardid", query = "SELECT b FROM Boardfollowers b WHERE b.boardfollowersPK.boardid = :boardid")
    , @NamedQuery(name = "Boardfollowers.findByIsBlocked", query = "SELECT b FROM Boardfollowers b WHERE b.isBlocked = :isBlocked")})
public class Boardfollowers implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BoardfollowersPK boardfollowersPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isBlocked")
    private short isBlocked;
    @JoinColumn(name = "Boardid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Board board;
    @JoinColumn(name = "Userid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Account account;

    public Boardfollowers() {
    }

    public Boardfollowers(BoardfollowersPK boardfollowersPK) {
        this.boardfollowersPK = boardfollowersPK;
    }

    public Boardfollowers(BoardfollowersPK boardfollowersPK, short isBlocked) {
        this.boardfollowersPK = boardfollowersPK;
        this.isBlocked = isBlocked;
    }

    public Boardfollowers(int userid, int boardid) {
        this.boardfollowersPK = new BoardfollowersPK(userid, boardid);
    }

    public BoardfollowersPK getBoardfollowersPK() {
        return boardfollowersPK;
    }

    public void setBoardfollowersPK(BoardfollowersPK boardfollowersPK) {
        this.boardfollowersPK = boardfollowersPK;
    }

    public short getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(short isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (boardfollowersPK != null ? boardfollowersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boardfollowers)) {
            return false;
        }
        Boardfollowers other = (Boardfollowers) object;
        if ((this.boardfollowersPK == null && other.boardfollowersPK != null) || (this.boardfollowersPK != null && !this.boardfollowersPK.equals(other.boardfollowersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Boardfollowers[ boardfollowersPK=" + boardfollowersPK + " ]";
    }
    
}
