 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Julen
 */
@Entity
@Table(name="product",schema="jampdb")
@NamedQueries({
    @NamedQuery(name="findProductById",
            query="SELECT p FROM Product p JOIN Txoko t WHERE p.id = :id AND t.id = :txokoId ORDER BY u.id DESC"
    ),
    @NamedQuery(name="findProductByName",
            query="SELECT u FROM User u WHERE u.profile = :profile"
    ),
    @NamedQuery(name="findAllProduct",
            query="SELECT u FROM User u WHERE u.profile = :profile"
    )
})


public class Product implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
  
    /**
     * 
     */
    private Integer idProduct;
    /**
     * 
     */
    private Integer stock;
    /**
     * 
     */
    private String name;
    /**
     * 
     */
    private Float price;
    /**
     * 
     */
    private String description;

    @ManyToMany(mappedBy="idProduct")
    private List<Txoko>txokos;
    
    public Integer getId() {
        return idProduct;
    }

    public void setId(Integer id) {
        this.idProduct = idProduct;
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
     * @return the stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(Integer stock) {
        this.stock = stock;
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
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.idProduct.equals(other.idProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jampserverside.entity.Product[ id=" + getId() + " ]";
    }
}