/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.entity;

import java.io.Serializable;
import java.util.List;
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
 * Class for the products data
 * 
 * @author Julen
 */
@Entity
@Table(name="product",schema="jampdb")
@NamedQueries({
    @NamedQuery(name="findProductsByIdByTxoko",
            query="SELECT p FROM Product p JOIN p.txokos t WHERE p.idProduct = :idProduct AND t.idTxoko = :idTxoko"
    ),
    @NamedQuery(name="findAllProducts",
            query="SELECT p FROM Product p"
    ),
    @NamedQuery(name="findProductByName",
            query="SELECT p FROM Product p join p.txokos t WHERE p.name = :name AND t.idTxoko = :idTxoko"
    ),
    @NamedQuery(name="findAllProductByTxoko",
            query="SELECT p FROM Product p join p.txokos t WHERE t.idTxoko = :idTxoko"
    )
    })


@XmlRootElement
public class Product implements Serializable {

    private static long serialVersionUID = 1L;
  
    /**
     * id for the product. is the primary key of the table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProduct;
    
    /**
     * the stock of the product
     */
    private Integer stock;
   
    /**
     * the name of the product
     */
    private String name;
   
    /**
     * The price of the product
     */
    private Float price;
    
    /**
     * the description of the product
     */
    private String description;

    /**
     * The list of txoko have pthe prodcut
     */
    @ManyToMany(cascade={MERGE}, fetch=FetchType.EAGER)
    @JoinTable(name = "TxokoProduct", schema="jampdb")
    private List<Txoko> txokos;
    
    /**
     * Getter of idProdcut
     * @return the id product
     */
    public Integer getIdProduct() {
        return idProduct;
    }

    /**
     * Setter of idProduct
     * @param idProduct 
     */
    public void setIdProduct(Integer idProduct) {
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
     * Getter of Sock
     * 
     * @return the stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * Setter of Stock
     * 
     * @param stock the stock to set
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * Getter of name of product
     * 
     * @return the name of product
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of Price
     * 
     * @return the price of product
     */
    public Float getPrice() {
        return price;
    }

    /**
     * Setter of price
     * 
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * Getter of Description
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Seter of Description
     * 
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Getter of list of txokos
     * 
     * @return the txokos
     */
    //@XmlTransient
    public List<Txoko> getTxokos() {
        return txokos;
    }

    /**
     * Seter of list of products
     * 
     * @param txokos the txokos to set
     */
    public void setTxokos(List<Txoko> txokos) {
        this.txokos = txokos;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdProduct() != null ? getIdProduct().hashCode() : 0);
        return hash;
    }

    /**
     * 
     * @param object
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.getIdProduct() == null && other.getIdProduct() != null) || (this.getIdProduct() != null && !this.idProduct.equals(other.idProduct))) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "jampserverside.entity.Product[ id=" + getIdProduct() + " ]";
    }

    
}