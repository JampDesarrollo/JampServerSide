/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.ejb;

import jampserverside.entity.Product;
import jampserverside.exception.CreateException;
import jampserverside.exception.DeleteException;
import jampserverside.exception.ReadException;
import jampserverside.exception.UpdateException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;

/**
 *
 * @author Julen
 */
@Stateless
public class ProductEJB implements ProductEJBLocal{
    /**
     * Logger for the class.
     */
    private static final Logger LOGGER =
            Logger.getLogger("javafxserverside");
    
    /**
     * Entity manager object.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 
     * @param id
     * @param idTxoko
     * @return
     * @throws ReadException 
     */
    @Override
    public Product find(String id, int idTxoko) throws ReadException {
        Product product=null;
        try{
            LOGGER.info("ProductManager: Finding product by id.");
            product=(Product) em.createNamedQuery("findProductById")
                    .setParameter("id", id)
                    .setParameter("idTxoko", idTxoko)
                    .getResultList();
            LOGGER.log(Level.INFO,"ProductManager: User found {0}",product.getId());
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductManager: Exception Finding product by idProduct and idTxoko:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return product;
    }
    

    /**
     * 
     * @param name
     * @param idTxoko
     * @return
     * @throws ReadException 
     */
    @Override
    public List<Product> findProductByName(String name, int idTxoko) throws ReadException {
        List<Product> product=null;
        try{
            LOGGER.info("Product: Finding product by name.");
            product=(List<Product>)em.createNamedQuery("findProductByName")
                    .setParameter("name", name)
                    .setParameter("idTxoko", idTxoko)
                    .getResultList();
           // LOGGER.log(Level.INFO,"ProductManager: User found {0}",product.get(idTxoko);
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductManager: Exception Finding product by idToko and name:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return product;
    }

    /**
     * 
     * @param idTxoko
     * @return
     * @throws ReadException 
     */
    @Override
    public List<Product> findAllProductsByTxoko(int idTxoko) throws ReadException {
        List<Product> product=null;
        try{
            LOGGER.info("Product: Finding all products.");
            product=(List<Product>)em.createNamedQuery("findProductByTxoko")
                    .setParameter("idTxoko", idTxoko)
                    .getResultList();
           // LOGGER.log(Level.INFO,"ProductManager: User found {0}",product.get(idTxoko);
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "UserManager: Exception Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return product;    }
    
        @Override
        public List<Product> findAllProducts() throws ReadException {
        List<Product> product=null;
        try{
            LOGGER.info("Product: Finding all products.");
            product=(List<Product>)em.createNamedQuery("findAllProducts").getResultList();
           // LOGGER.log(Level.INFO,"ProductManager: User found {0}",product.get(idTxoko);
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "UserManager: Exception Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return product;    }

    /**
     * 
     * @param product
     * @throws CreateException 
     */
    @Override
    public void createProduct(Product product) throws CreateException {
        LOGGER.info("ProductManager: Creating product.");
        try{
            em.persist(product);
            LOGGER.info("ProductManager: Product created.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductManager: Exception creating product.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }    
    }

    /**
     * 
     * @param product
     * @throws UpdateException 
     */
    @Override
    public void updateProduct(Product product) throws UpdateException {
        LOGGER.info("ProductManager: Updating product.");
        try{
            em.merge(product);
            LOGGER.info("ProductManager: Product updated.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductManager: Exception updating product.{0}",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }  
    }

    /**
     * 
     * @param product
     * @throws DeleteException 
     */
    @Override
    public void deleteProduct(Product product) throws DeleteException {
        LOGGER.info("ProductManager: Deleting product.");
        try{
            product = em.merge(product);
            em.remove(product);
            LOGGER.info("ProductManager: Product deleted.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductManager: Exception deleting product.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }  
    }
    
}
