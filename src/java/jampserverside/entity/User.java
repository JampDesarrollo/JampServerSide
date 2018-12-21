/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 2dam
 */
@Entity
@Table(name = "user", schema = "jampdb")/*
@NamedQueries({
    @NamedQuery(name = "findUserByLogin",
            query = "SELECT * FROM users WHERE users.login= :user.getLogin()"
    )
    ,
    @NamedQuery(name = "deleteUser",
            query = "DELETE FROM users WHERE users.login= :user.getLogin()"
    )
    ,
    @NamedQuery(name = "updateUser",
            query = "UPDATE users SET email=:user.getEmail(),"
            + "fullname= :user.getFullname(),"
            + "password= :user.getPassword"
            + "lastAccess= :user.getLastAccess"
            + "lastPasswordChangeuser.getLastPasswordChange"
    )
    ,
    @NamedQuery(name = "findAllTxokoUsers",
            query = "SELECT * FROM users WHERE users.id_txoko= user.getLogin()"
    )
    ,
    @NamedQuery(name = "createUser",
            query = "INSERT INTO users ('login','email','fullname','status','privileges','password')"
            + "values(':user.getLogin()',':user.getEmail()',':user.Fullname()',':user.getStatus()',':user.getPrivileges()',':user.getPassword()')"
    )
})*/
@XmlRootElement
public class User implements Serializable {

    /**
     *
     */
    @ManyToMany
    @JoinTable(name = "UserEvent")
    private List<Event> events;
    /**
     *
     */
    @OneToMany(mappedBy = "user")
    private List<Expense> expenses;
    /**
     *
     */
    @Id
    private Integer idUser;
    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "idTxoko")
    private Txoko txoko;
    /**
     *
     */
    @Column(unique = true)
    private String login;
    /**
     *
     */
    private String email;
    /**
     *
     */
    private String fullname;
    /**
     *
     */
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    /**
     *
     */
    @Enumerated(EnumType.STRING)
    private UserPrivilege privilege;
    /**
     *
     */
    private String password;
    /**
     *
     */
    private Timestamp lastAccess;
    /**
     *
     */
    private Timestamp lastPasswordChange;

    /**
     * @return the events
     */
    @XmlTransient
    public List<Event> getEvents() {
        return events;
    }

    /**
     * @param events the events to set
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * @return the expenses
     */
    @XmlTransient
    public List<Expense> getExpenses() {
        return expenses;
    }

    /**
     * @param expenses the expenses to set
     */
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    /**
     * @return the idUser
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the idTxoko
     */
    public Txoko getTxoko() {
        return txoko;
    }

    /**
     * @param idTxoko the idTxoko to set
     */
    public void setTxoko(Txoko txoko) {
        this.txoko = txoko;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the status
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * @return the privilege
     */
    public UserPrivilege getPrivilege() {
        return privilege;
    }

    /**
     * @param privilege the privilege to set
     */
    public void setPrivilege(UserPrivilege privilege) {
        this.privilege = privilege;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the lastAccess
     */
    public Timestamp getLastAccess() {
        return lastAccess;
    }

    /**
     * @param lastAccess the lastAccess to set
     */
    public void setLastAccess(Timestamp lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * @return the lastPasswordChange
     */
    public Timestamp getLastPasswordChange() {
        return lastPasswordChange;
    }

    /**
     * @param lastPasswordChange the lastPasswordChange to set
     */
    public void setLastPasswordChange(Timestamp lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.idUser);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.idUser, other.idUser)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + '}';
    }

}
