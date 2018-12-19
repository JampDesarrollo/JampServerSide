/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author 2dam
 */
@Entity
@Table(name = "user", schema = "jampdb")
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
})
public class User implements Serializable {
    /**
     * 
     */
    @ManyToMany
    @JoinTable(name="UserEvent")
    private List<Event> events;
    /**
     * 
     */
    @OneToMany(mappedBy = "idUser")
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
    @Column(unique=true)
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
    private String status;
    /**
     * 
     */
    private String privilege;
    /**
     * 
     */
    private String password;
    /**
     * 
     */
    private Date lastAccess;
    /**
     * 
     */
    private Date lastPasswordChange;

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
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the privilege
     */
    public String getPrivilege() {
        return privilege;
    }

    /**
     * @param privilege the privilege to set
     */
    public void setPrivilege(String privilege) {
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
    public Date getLastAccess() {
        return lastAccess;
    }

    /**
     * @param lastAccess the lastAccess to set
     */
    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * @return the lastPasswordChange
     */
    public Date getLastPasswordChange() {
        return lastPasswordChange;
    }

    /**
     * @param lastPasswordChange the lastPasswordChange to set
     */
    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

}