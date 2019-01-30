/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity JPA class for Expenses. The properties of this class are idUser ,
 * date, type, description and price.
 *
 * @author ander
 */
@Entity
@Table(name = "expense", schema = "jampdb")
@NamedQueries({
    @NamedQuery(name = "findAllExpensesUsers",
            query = "SELECT u FROM Expense u WHERE u.user.idUser IN ("
            + "SELECT u FROM User u WHERE u.txoko.idTxoko = :idTxoko) ORDER BY u.dateExpense ASC"
    )
    ,
    @NamedQuery(name = "findMonthExpensesUsers",
            query = "SELECT u FROM Expense u WHERE MONTH(u.dateExpense) = MONTH(:current) AND u.user.idUser IN ("
            + "SELECT u FROM User u WHERE u.txoko.idTxoko = :idTxoko) ORDER BY u.user.idUser ASC"
    )
        ,
    @NamedQuery(name = "findMonthExpensesSingleUser",
            query = "SELECT SUM(u.price) FROM Expense u WHERE MONTH(u.dateExpense) = MONTH(:current) "
                    + "AND u.user.idUser = :idUser"
    )
})
@XmlRootElement
public class Expense implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idExpense;
    /**
     * Id of the user.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;
    /**
     * Date of the expense.
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateExpense;
    /**
     * Type of expense.
     */
    private String type;
    /**
     * Long description of the expense.
     */
    private String description;
    /**
     * Price of the expense.
     */
    private Float price;

    /**
     * @return the idExpense
     */
    public Integer getIdExpense() {
        return idExpense;
    }

    /**
     * @param idExpense the idExpense to set
     */
    public void setIdExpense(Integer idExpense) {
        this.idExpense = idExpense;
    }

    /**
     * @return the idUser
     */
    public User getUser() {
        return user;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the dateExpense
     */
    public Date getDateExpense() {
        return dateExpense;
    }

    /**
     * @param dateExpense the dateExpense to set
     */
    public void setDateExpense(Date dateExpense) {
        this.dateExpense = dateExpense;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.idExpense);
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
        final Expense other = (Expense) obj;
        if (!Objects.equals(this.idExpense, other.idExpense)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Expense{" + "idExpense=" + idExpense + '}';
    }

}
