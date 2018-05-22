/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ken
 */
@Entity
@Table(name = "board")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Board.findAll", query = "SELECT b FROM Board b")
    , @NamedQuery(name = "Board.findById", query = "SELECT b FROM Board b WHERE b.id = :id")
    , @NamedQuery(name = "Board.findByBoardname", query = "SELECT b FROM Board b WHERE b.boardname = :boardname")
    , @NamedQuery(name = "Board.findByOwner", query = "SELECT b FROM Board b WHERE b.owner = :userid")
    , @NamedQuery(name = "Board.findByExpression", query = "SELECT p FROM Board p WHERE (p.isprivate <> 1 and p.boardname LIKE :expression)")
    , @NamedQuery(name = "Board.findByExpressionCategory", query = "SELECT p FROM Board p WHERE (p.isprivate <> 1 and p.category.name LIKE :expression)")
    , @NamedQuery(name = "Board.maxIdForCategory", query = "SELECT max(p.id) FROM Board p WHERE p.category = :category")
    , @NamedQuery(name = "Board.firstOccurencesAfterId", query = "SELECT p FROM Board p WHERE (p.id >= :id and p.category = :category)")})
public class Board implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "isprivate")
    private short isprivate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "board")
    private Collection<Boardfollowers> boardfollowersCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "board")
    private Collection<Pin> pinCollection;

    @JoinColumn(name = "category", referencedColumnName = "id")
    @ManyToOne
    private Categories category;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "boardname")
    private String boardname;
    @JoinColumn(name = "owner", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account owner;

    public Board() {
    }

    public Board(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBoardname() {
        return boardname;
    }

    public void setBoardname(String boardname) {
        this.boardname = boardname;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
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
        if (!(object instanceof Board)) {
            return false;
        }
        Board other = (Board) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Board[ id=" + id + " ]";
    }
    
    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    @XmlTransient
    public Collection<Pin> getPinCollection() {
        return pinCollection;
    }

    public void setPinCollection(Collection<Pin> pinCollection) {
        this.pinCollection = pinCollection;
    }

    @XmlTransient
    public Collection<Boardfollowers> getBoardfollowersCollection() {
        return boardfollowersCollection;
    }

    public void setBoardfollowersCollection(Collection<Boardfollowers> boardfollowersCollection) {
        this.boardfollowersCollection = boardfollowersCollection;
    }

    public short getIsprivate() {
        return isprivate;
    }

    public void setIsprivate(short isprivate) {
        this.isprivate = isprivate;
    }
    
}
