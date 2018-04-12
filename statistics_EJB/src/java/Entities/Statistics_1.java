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
@Table(name = "statistics")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statistics_1.findAll", query = "SELECT s FROM Statistics_1 s")
    , @NamedQuery(name = "Statistics_1.findById", query = "SELECT s FROM Statistics_1 s WHERE s.id = :id")
    , @NamedQuery(name = "Statistics_1.findByTimestamp", query = "SELECT s FROM Statistics_1 s WHERE s.timestamp = :timestamp")})
public class Statistics_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account_1 userid;

    public Statistics_1() {
    }

    public Statistics_1(Integer id) {
        this.id = id;
    }

    public Statistics_1(Integer id, Date timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Account_1 getUserid() {
        return userid;
    }

    public void setUserid(Account_1 userid) {
        this.userid = userid;
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
        if (!(object instanceof Statistics_1)) {
            return false;
        }
        Statistics_1 other = (Statistics_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Statistics_1[ id=" + id + " ]";
    }
    
}
