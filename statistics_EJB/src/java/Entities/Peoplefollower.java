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
@Table(name = "peoplefollower")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peoplefollower.findAll", query = "SELECT p FROM Peoplefollower p")
    , @NamedQuery(name = "Peoplefollower.findByUser", query = "SELECT p FROM Peoplefollower p WHERE p.peoplefollowerPK.user = :user")
    , @NamedQuery(name = "Peoplefollower.findByFollowedUser", query = "SELECT p FROM Peoplefollower p WHERE p.peoplefollowerPK.followedUser = :followedUser")
    , @NamedQuery(name = "Peoplefollower.findByIsBlocked", query = "SELECT p FROM Peoplefollower p WHERE p.isBlocked = :isBlocked")})
public class Peoplefollower implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeoplefollowerPK peoplefollowerPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isBlocked")
    private short isBlocked;
    @JoinColumn(name = "FollowedUser", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Account account;
    @JoinColumn(name = "User", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Account account1;

    public Peoplefollower() {
    }

    public Peoplefollower(PeoplefollowerPK peoplefollowerPK) {
        this.peoplefollowerPK = peoplefollowerPK;
    }

    public Peoplefollower(PeoplefollowerPK peoplefollowerPK, short isBlocked) {
        this.peoplefollowerPK = peoplefollowerPK;
        this.isBlocked = isBlocked;
    }

    public Peoplefollower(int user, int followedUser) {
        this.peoplefollowerPK = new PeoplefollowerPK(user, followedUser);
    }

    public PeoplefollowerPK getPeoplefollowerPK() {
        return peoplefollowerPK;
    }

    public void setPeoplefollowerPK(PeoplefollowerPK peoplefollowerPK) {
        this.peoplefollowerPK = peoplefollowerPK;
    }

    public short getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(short isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount1() {
        return account1;
    }

    public void setAccount1(Account account1) {
        this.account1 = account1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peoplefollowerPK != null ? peoplefollowerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peoplefollower)) {
            return false;
        }
        Peoplefollower other = (Peoplefollower) object;
        if ((this.peoplefollowerPK == null && other.peoplefollowerPK != null) || (this.peoplefollowerPK != null && !this.peoplefollowerPK.equals(other.peoplefollowerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Peoplefollower[ peoplefollowerPK=" + peoplefollowerPK + " ]";
    }
    
}
