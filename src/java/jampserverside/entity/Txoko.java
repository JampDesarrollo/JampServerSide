 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author 2dam
 */
@Entity
@Table(name = "Txoko", schema = "jampdb")
public class Txoko implements Serializable {

    /**
     * List of {@link User} belonging to the txoko.
     */
    @OneToMany(mappedBy = "txoko")
    private List<User> users;
    private static long serialVersionUID = 1L;

    /**
     * The id of the txoko
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTxoko;
    /**
     * The direction of the txoko
     */
    private String direction;

    /**
     * The name of the txoko
     */
    private String name;

    /**
     * The town of the txoko
     */
    private String town;

    /**
     * The monthFee of the txoko
     */
    private Float monthFee;

    /**
     * Many to many relation with Product. Creates TxokoProduct
     */
    @ManyToMany
    @JoinTable(name = "TxokoProduct", schema="jampdb")
    private List<Product> products;

    /**
     * Many to many relation with event. Creates TxokoEvent
     */
    @ManyToMany
    @JoinTable(name = "TxokoEvent", schema="jampdb")
    private List<Event> events;

    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the idTxoko
     */
    public Integer getIdTxoko() {
        return idTxoko;
    }

    /**
     * @param idTxoko the idTxoko to set
     */
    public void setIdTxoko(Integer idTxoko) {
        this.idTxoko = idTxoko;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * @param town the town to set
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * @return the monthFee
     */
    public Float getMonthFee() {
        return monthFee;
    }

    /**
     * @param monthFee the monthFee to set
     */
    public void setMonthFee(Float monthFee) {
        this.monthFee = monthFee;
    }

    /**
     * @return the product
     */
    public List<Product> getProduct() {
        return products;
    }

    /**
     * @param products the product to set
     */
    public void setProduct(List<Product> products) {
        this.products = products;
    }

    /**
     * @return the events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * @param events the events to set
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idTxoko);
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
        final Txoko other = (Txoko) obj;
        if (!Objects.equals(this.idTxoko, other.idTxoko)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Txoko{" + "idTxoko=" + idTxoko + '}';
    }



}