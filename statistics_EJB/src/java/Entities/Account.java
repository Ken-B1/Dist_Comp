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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id")
    , @NamedQuery(name = "Account.findByUsername", query = "SELECT a FROM Account a WHERE a.username = :username")
    , @NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password")
    , @NamedQuery(name = "Account.findByFname", query = "SELECT a FROM Account a WHERE a.fname = :fname")
    , @NamedQuery(name = "Account.findByLname", query = "SELECT a FROM Account a WHERE a.lname = :lname")
    , @NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email")
    , @NamedQuery(name = "Account.findByGender", query = "SELECT a FROM Account a WHERE a.gender = :gender")
    , @NamedQuery(name = "Account.findByCountry", query = "SELECT a FROM Account a WHERE a.country = :country")
    , @NamedQuery(name = "Account.findByAdmin", query = "SELECT a FROM Account a WHERE a.admin = :admin")
    , @NamedQuery(name = "Account.existsName", query = "SELECT COUNT(a) FROM Account a WHERE a.username = :username")
    , @NamedQuery(name = "Account.existsEmail", query = "SELECT COUNT(a) FROM Account a WHERE a.email = :email")
    , @NamedQuery(name = "Account.findByExpression", query = "SELECT p FROM Account p WHERE p.username LIKE :expression")})
public class Account implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Useractions> useractionsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator", fetch = FetchType.EAGER)
    private Collection<Notifications> notificationsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver", fetch = FetchType.EAGER)
    private Collection<Notifications> notificationsCollection1;

    @Size(max = 255)
    @Column(name = "gmailId")
    private String gmailId;

    @JoinTable(name = "friendrequests", joinColumns = {
        @JoinColumn(name = "requested", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "requester", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Account> accountCollection; // People requesting this user
    @ManyToMany(mappedBy = "accountCollection",fetch = FetchType.EAGER)
    private Collection<Account> accountCollection1; // People requested by this user
    
    @JoinTable(name = "friends", joinColumns = {
        @JoinColumn(name = "user1", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "user2", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Account> accountCollection2; // Friends of this user
    @ManyToMany(mappedBy = "accountCollection2",fetch = FetchType.EAGER)
    private Collection<Account> accountCollection3; // Users of which this user is a friend

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    private Collection<Messages> messagesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private Collection<Messages> messagesCollection1;

    @Basic(optional = false)
    @NotNull
    @Column(name = "isBlocked")
    private short isBlocked;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Collection<Boardfollowers> boardfollowersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Collection<Peoplefollower> peoplefollowerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account1")
    private Collection<Peoplefollower> peoplefollowerCollection1;

    @Basic(optional = false)
    @NotNull
    @Column(name = "admin")
    private boolean admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner",fetch = FetchType.EAGER)
    private Collection<Board> boardCollection;
    @JoinTable(name = "accountcategoryjunction", joinColumns = {
        @JoinColumn(name = "accountid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "categoryid", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Categories> categoriesCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fname")
    private String fname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lname")
    private String lname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @Size(max = 45)
    @Column(name = "Country")
    private String country;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Statistics> statisticsCollection;

    public Account() {
    }

    public Account(Integer id) {
        this.id = id;
    }

    public Account(Integer id, String username, String password, String fname, String lname, String email, String country, boolean admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.country = country;
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @XmlTransient
    public Collection<Statistics> getStatisticsCollection() {
        return statisticsCollection;
    }

    public void setStatisticsCollection(Collection<Statistics> statisticsCollection) {
        this.statisticsCollection = statisticsCollection;
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
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Account[ id=" + id + " ]";
    }


    @XmlTransient
    public Collection<Categories> getCategoriesCollection() {
        return categoriesCollection;
    }

    public void setCategoriesCollection(Collection<Categories> categoriesCollection) {
        this.categoriesCollection = categoriesCollection;
    }


    @XmlTransient
    public Collection<Board> getBoardCollection() {
        return boardCollection;
    }

    public void setBoardCollection(Collection<Board> boardCollection) {
        this.boardCollection = boardCollection;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @XmlTransient
    public Collection<Boardfollowers> getBoardfollowersCollection() {
        return boardfollowersCollection;
    }

    public void setBoardfollowersCollection(Collection<Boardfollowers> boardfollowersCollection) {
        this.boardfollowersCollection = boardfollowersCollection;
    }

    @XmlTransient
    public Collection<Peoplefollower> getPeoplefollowerCollection() {
        return peoplefollowerCollection;
    }

    public void setPeoplefollowerCollection(Collection<Peoplefollower> peoplefollowerCollection) {
        this.peoplefollowerCollection = peoplefollowerCollection;
    }

    @XmlTransient
    public Collection<Peoplefollower> getPeoplefollowerCollection1() {
        return peoplefollowerCollection1;
    }

    public void setPeoplefollowerCollection1(Collection<Peoplefollower> peoplefollowerCollection1) {
        this.peoplefollowerCollection1 = peoplefollowerCollection1;
    }
    
    public short getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(short isBlocked) {
        this.isBlocked = isBlocked;
    }

    @XmlTransient
    public Collection<Messages> getMessagesCollection() {
        messagesCollection.isEmpty();
        return messagesCollection;
    }

    public void setMessagesCollection(Collection<Messages> messagesCollection) {
        this.messagesCollection = messagesCollection;
    }

    @XmlTransient
    public Collection<Messages> getMessagesCollection1() {
        return messagesCollection1;
    }

    public void setMessagesCollection1(Collection<Messages> messagesCollection1) {
        this.messagesCollection1 = messagesCollection1;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection1() {
        return accountCollection1;
    }

    public void setAccountCollection1(Collection<Account> accountCollection1) {
        this.accountCollection1 = accountCollection1;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection2() {
        return accountCollection2;
    }

    public void setAccountCollection2(Collection<Account> accountCollection2) {
        this.accountCollection2 = accountCollection2;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection3() {
        return accountCollection3;
    }

    public void setAccountCollection3(Collection<Account> accountCollection3) {
        this.accountCollection3 = accountCollection3;
    }

    public String getGmailId() {
        return gmailId;
    }

    public void setGmailId(String gmailId) {
        this.gmailId = gmailId;
    }

    @XmlTransient
    public Collection<Notifications> getNotificationsCollection() {
        return notificationsCollection;
    }

    public void setNotificationsCollection(Collection<Notifications> notificationsCollection) {
        this.notificationsCollection = notificationsCollection;
    }

    @XmlTransient
    public Collection<Notifications> getNotificationsCollection1() {
        return notificationsCollection1;
    }

    public void setNotificationsCollection1(Collection<Notifications> notificationsCollection1) {
        this.notificationsCollection1 = notificationsCollection1;
    }

    @XmlTransient
    public Collection<Useractions> getUseractionsCollection() {
        return useractionsCollection;
    }

    public void setUseractionsCollection(Collection<Useractions> useractionsCollection) {
        this.useractionsCollection = useractionsCollection;
    }
    
}
