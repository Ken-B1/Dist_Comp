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
    , @NamedQuery(name = "Useractions.findByIduseractions", query = "SELECT u FROM Useractions u WHERE u.iduseractions = :iduseractions")
    , @NamedQuery(name = "Useractions.findByTimestamp", query = "SELECT u FROM Useractions u WHERE u.timestamp = :timestamp")
    , @NamedQuery(name = "Useractions.findCategoryFollows", query = "SELECT u.category FROM Useractions u WHERE u.timestamp >= :timestamp GROUP BY u.category ORDER BY count(u.category) DESC")})
public class Useractions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iduseractions")
    private Integer iduseractions;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "category", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Categories category;
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account user;

    public Useractions() {
    }

    public Useractions(Integer iduseractions) {
        this.iduseractions = iduseractions;
    }

    public Useractions(Integer iduseractions, Date timestamp) {
        this.iduseractions = iduseractions;
        this.timestamp = timestamp;
    }

    public Integer getIduseractions() {
        return iduseractions;
    }

    public void setIduseractions(Integer iduseractions) {
        this.iduseractions = iduseractions;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
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
        hash += (iduseractions != null ? iduseractions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Useractions)) {
            return false;
        }
        Useractions other = (Useractions) object;
        if ((this.iduseractions == null && other.iduseractions != null) || (this.iduseractions != null && !this.iduseractions.equals(other.iduseractions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Useractions[ iduseractions=" + iduseractions + " ]";
    }
    
}
