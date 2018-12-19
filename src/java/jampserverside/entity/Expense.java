 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
            query = "SELECT u FROM Expense u WHERE u.idUser=("
            + "SELECT u FROM User u WHERE u.idtxoko='idTxoko') ORDER BY u.idUser ASC"
    )
    ,
    @NamedQuery(name = "findMonthExpensesUsers",
            query = "SELECT u FROM Expense u WHERE u.date=sysdatetime() AND u.idUser=("
            + "SELECT u FROM User u WHERE u.idtxoko='idTxoko') ORDER BY u.idUser ASC"
    )
})
public class Expense implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    private Integer idExpense;
    /**
     * Id of the user.
     */
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
    /**
     * Date of the expense.
     */
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
