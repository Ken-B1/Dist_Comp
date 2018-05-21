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
@Table(name = "suggestions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suggestions.findAll", query = "SELECT s FROM Suggestions s")
    , @NamedQuery(name = "Suggestions.findByUserid", query = "SELECT s FROM Suggestions s WHERE s.userid = :userid")
    , @NamedQuery(name = "Suggestions.findByTimestamp", query = "SELECT s FROM Suggestions s WHERE s.timestamp = :timestamp")})
public class Suggestions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "userid")
    private Integer userid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "meal1", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pin meal1;
    @JoinColumn(name = "meal2", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pin meal2;
    @JoinColumn(name = "meal3", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pin meal3;
    @JoinColumn(name = "meal4", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pin meal4;
    @JoinColumn(name = "meal5", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pin meal5;

    public Suggestions() {
    }

    public Suggestions(Integer userid) {
        this.userid = userid;
    }

    public Suggestions(Integer userid, Date timestamp) {
        this.userid = userid;
        this.timestamp = timestamp;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Pin getMeal1() {
        return meal1;
    }

    public void setMeal1(Pin meal1) {
        this.meal1 = meal1;
    }

    public Pin getMeal2() {
        return meal2;
    }

    public void setMeal2(Pin meal2) {
        this.meal2 = meal2;
    }

    public Pin getMeal3() {
        return meal3;
    }

    public void setMeal3(Pin meal3) {
        this.meal3 = meal3;
    }

    public Pin getMeal4() {
        return meal4;
    }

    public void setMeal4(Pin meal4) {
        this.meal4 = meal4;
    }

    public Pin getMeal5() {
        return meal5;
    }

    public void setMeal5(Pin meal5) {
        this.meal5 = meal5;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suggestions)) {
            return false;
        }
        Suggestions other = (Suggestions) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Suggestions[ userid=" + userid + " ]";
    }
    
}
