/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ken
 */
@Entity
@Table(name = "useractions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Useractions.findAll", query = "SELECT u FROM Useractions u")
    , @NamedQuery(name = "Useractions.findById", query = "SELECT u FROM Useractions u WHERE u.id = :id")
    , @NamedQuery(name = "Useractions.findByAction", query = "SELECT u FROM Useractions u WHERE u.action = :action")
    , @NamedQuery(name = "Useractions.findByTimestamp", query = "SELECT u FROM Useractions u WHERE u.timestamp = :timestamp")
    , @NamedQuery(name = "Useractions.findNotifications", query = "SELECT u FROM Useractions u WHERE u.timestamp >= :time and u.user = :user")})
public class Useractions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Action")
    private int action;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "User", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account user;

    public Useractions() {
    }

    public Useractions(Integer id) {
        this.id = id;
    }

    public Useractions(int action, Date timestamp) {
        this.id = id;
        this.action = action;
        this.timestamp = timestamp;
    }

    public Useractions(Integer id, int action, Date timestamp) {
        this.id = id;
        this.action = action;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Useractions)) {
            return false;
        }
        Useractions other = (Useractions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Useractions[ id=" + id + " ]";
    }
    
}
