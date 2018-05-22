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
import javax.persistence.FetchType;
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
@Table(name = "pin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pin.findAll", query = "SELECT p FROM Pin p")
    , @NamedQuery(name = "Pin.findById", query = "SELECT p FROM Pin p WHERE p.id = :id")
    , @NamedQuery(name = "Pin.findByRecipeName", query = "SELECT p FROM Pin p WHERE p.recipeName = :recipeName")
    , @NamedQuery(name = "Pin.findByRecipe", query = "SELECT p FROM Pin p WHERE p.recipe = :recipe")
    , @NamedQuery(name = "Pin.findByBoard", query = "SELECT p FROM Pin p WHERE p.board = :board")
    , @NamedQuery(name = "Pin.findByExpression", query = "SELECT p FROM Pin p WHERE p.recipeName LIKE :expression")})
public class Pin implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meal1")
    private Collection<Suggestions> suggestionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meal2")
    private Collection<Suggestions> suggestionsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meal3")
    private Collection<Suggestions> suggestionsCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meal4")
    private Collection<Suggestions> suggestionsCollection3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meal5")
    private Collection<Suggestions> suggestionsCollection4;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "location")
    private String location;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "recipeName")
    private String recipeName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "recipe")
    private String recipe;
    @JoinColumn(name = "board", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Board board;

    public Pin() {
    }

    public Pin(Integer id) {
        this.id = id;
    }

    public Pin(Integer id, String recipeName, String recipe) {
        this.id = id;
        this.recipeName = recipeName;
        this.recipe = recipe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
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
        if (!(object instanceof Pin)) {
            return false;
        }
        Pin other = (Pin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Pin[ id=" + id + " ]";
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlTransient
    public Collection<Suggestions> getSuggestionsCollection() {
        return suggestionsCollection;
    }

    public void setSuggestionsCollection(Collection<Suggestions> suggestionsCollection) {
        this.suggestionsCollection = suggestionsCollection;
    }

    @XmlTransient
    public Collection<Suggestions> getSuggestionsCollection1() {
        return suggestionsCollection1;
    }

    public void setSuggestionsCollection1(Collection<Suggestions> suggestionsCollection1) {
        this.suggestionsCollection1 = suggestionsCollection1;
    }

    @XmlTransient
    public Collection<Suggestions> getSuggestionsCollection2() {
        return suggestionsCollection2;
    }

    public void setSuggestionsCollection2(Collection<Suggestions> suggestionsCollection2) {
        this.suggestionsCollection2 = suggestionsCollection2;
    }

    @XmlTransient
    public Collection<Suggestions> getSuggestionsCollection3() {
        return suggestionsCollection3;
    }

    public void setSuggestionsCollection3(Collection<Suggestions> suggestionsCollection3) {
        this.suggestionsCollection3 = suggestionsCollection3;
    }

    @XmlTransient
    public Collection<Suggestions> getSuggestionsCollection4() {
        return suggestionsCollection4;
    }

    public void setSuggestionsCollection4(Collection<Suggestions> suggestionsCollection4) {
        this.suggestionsCollection4 = suggestionsCollection4;
    }
    
}
