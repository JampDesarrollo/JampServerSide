/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import static javax.persistence.CascadeType.MERGE;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class for the events data.
 *
 * @author paula
 */
@Entity
//LAS SELECTS
@Table(name = "event", schema = "jampdb")
@NamedQueries({
    @NamedQuery(name="findAll",
        query="SELECT e FROM Event e"
        ),
    @NamedQuery(name = "findAllEvents",
            query = "SELECT e FROM Event e JOIN e.txokos t WHERE t.idTxoko = :idTxoko"
    )
    ,
    @NamedQuery(name = "findEventByIdByTxoko",
            query = "SELECT e FROM Event e JOIN e.txokos t WHERE t.idTxoko = :idTxoko AND e.idEvent = :idEvent"
    )
    ,
    @NamedQuery(name = "findEventByName",
            query = "SELECT e FROM Event e JOIN e.txokos t WHERE t.idTxoko = :idTxoko AND e.name = :name"
    )
})
@XmlRootElement
public class Event implements Serializable {

    private static long serialVersionUID = 1L;
    //atributos

    //clave primaria
    /**
     * id for the event. Is the primary key of the table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEvent;

    /**
     * The description of the event
     */
    private String description;

    /**
     * The date of the event
     */
    
    private Date date;

    /**
     * The price of the event
     */
    private Float price;

    /**
     * The name of the event
     */
    private String name;

    /**
     * The name of the image of the event
     */
    private String img;

    /**
     *
     */
    @ManyToMany(cascade={MERGE},fetch=FetchType.EAGER)
    @JoinTable(name = "TxokoEvent", schema="jampdb")
    private List<Txoko> txokos;

    /**
     *
     */

    @ManyToMany(cascade={MERGE},fetch=FetchType.EAGER)
    @JoinTable(name = "UserEvent", schema = "jampdb")
    private List<User> users;

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
     * @return the idEvent
     */
    public Integer getIdEvent() {
        return idEvent;
    }

    /**
     * @param idEvent the idEvent to set
     */
    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the date
     */
   
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price = price;
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
     * @return the img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return the txokos
     */
 //   @XmlTransient
    public List<Txoko> getTxokos() {
        return txokos;
    }

    /**
     * @param txokos the txokos to set
     */
    public void setTxokos(List<Txoko> txokos) {
        this.txokos = txokos;
    }

    /**
     * @return the users
     */
 //  @XmlTransient
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.idEvent);
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
        final Event other = (Event) obj;
        if (!Objects.equals(this.idEvent, other.idEvent)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Event{" + "idEvent=" + idEvent + '}';
    }

}