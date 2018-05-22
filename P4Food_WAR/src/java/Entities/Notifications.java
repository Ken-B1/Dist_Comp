/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ken
 */
@Entity
@Table(name = "notifications")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notifications.findAll", query = "SELECT n FROM Notifications n")
    , @NamedQuery(name = "Notifications.findByIdnotifications", query = "SELECT n FROM Notifications n WHERE n.idnotifications = :idnotifications")
    , @NamedQuery(name = "Notifications.findByType", query = "SELECT n FROM Notifications n WHERE n.type = :type")
    , @NamedQuery(name = "Notifications.findByIsread", query = "SELECT n FROM Notifications n WHERE n.isread = :isread")
    , @NamedQuery(name = "Notifications.findByDescription", query = "SELECT n FROM Notifications n WHERE n.description = :description")})
public class Notifications implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotifications")
    private Integer idnotifications;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type")
    private int type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isread")
    private short isread;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "creator", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account creator;
    @JoinColumn(name = "receiver", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account receiver;

    public Notifications() {
    }

    public Notifications(Integer idnotifications) {
        this.idnotifications = idnotifications;
    }

    public Notifications(Integer idnotifications, int type, short isread) {
        this.idnotifications = idnotifications;
        this.type = type;
        this.isread = isread;
    }

    public Integer getIdnotifications() {
        return idnotifications;
    }

    public void setIdnotifications(Integer idnotifications) {
        this.idnotifications = idnotifications;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public short getIsread() {
        return isread;
    }

    public void setIsread(short isread) {
        this.isread = isread;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnotifications != null ? idnotifications.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notifications)) {
            return false;
        }
        Notifications other = (Notifications) object;
        if ((this.idnotifications == null && other.idnotifications != null) || (this.idnotifications != null && !this.idnotifications.equals(other.idnotifications))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Notifications[ idnotifications=" + idnotifications + " ]";
    }
    
}
