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
 * This class that implements the interface
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
     * This method find product by id and idTxoko
     * 
     * @param id the id of the prodct
     * @param idTxoko the id of the txoko
     * @return ProductBean that we found
     * @throws ReadException throw exception is something is wrong
     */
    @Override
    public Product find(String id, Integer idTxoko) throws ReadException {
        Product product=null;
        try{
            LOGGER.info("ProductEJB: Finding product by id.");
            product=(Product) em.createNamedQuery("findProductById")
                    .setParameter("id", id)
                    .setParameter("idTxoko", idTxoko)
                    .getResultList();
            LOGGER.log(Level.INFO,"ProductEJB: User found {0}",product.getIdProduct());
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductEJB: Exception Finding product by idProduct and idTxoko:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return product;
    }
    

    /**
     * This method find product by name
     * 
     * @param name the name of the product
     * @param idTxoko the id of the txoko
     * @return List of productBean that found
     * @throws ReadException throw exception is something is wrong
     */
    @Override
    public List<Product> findProductByName(String name, Integer idTxoko) throws ReadException {
        List<Product> product=null;
        try{
            LOGGER.info("ProductEJB: Finding product by name.");
            product=(List<Product>)em.createNamedQuery("findProductByName")
                    .setParameter("name", name)
                    .setParameter("idTxoko", idTxoko)
                    .getResultList();
            LOGGER.log(Level.INFO,"ProductEJB: Product found {0}");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductEJB: Exception Finding product by idToko and name:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return product;
    }

    /**
     * This method find all products by txoko
     * 
     * @param idTxoko the id of the txoko
     * @return listof productBean of one txoko
     * @throws ReadException throw exception is something is wrong
     */
    @Override
    public List<Product> findAllProductsByTxoko(Integer idTxoko) throws ReadException {
        List<Product> product=null;
        try{
            LOGGER.info("ProductEJB: Finding all products By Txoko.");
            product=(List<Product>)em.createNamedQuery("findAllProductByTxoko")
                    .setParameter("idTxoko", idTxoko)
                    .getResultList();
            LOGGER.log(Level.INFO,"ProductEJB: finding products by txoko {0}");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductEJB: Exception Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return product;    }
    
        /**
        * This method is for find all products
        * 
        * @return List of prodcuts
        * @throws ReadException throw exception is something is wrong
        */
        @Override
        public List<Product> findAllProducts() throws ReadException {
        List<Product> product=null;
        try{
            LOGGER.info("ProductEJB: Finding all products.");
            product=(List<Product>)em.createNamedQuery("findAllProducts").getResultList();
            //LOGGER.log(Level.INFO,"ProductEJB: User found {0}",product.get(idTxoko));
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductEJB: Exception Finding all txokos b:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return product;    
        }
        
        /**
         * This method found products by id
         * 
         * @param idProduct the id of the product
         * @return product foud by id
         * @throws ReadException throw exception is something is wrong
         */
        @Override
        public Product findProductsById(Integer idProduct) throws ReadException {
        Product product=null;
        try{
            LOGGER.info("ProductEJB: Finding products by Id.");
            
            product = em.find(Product.class, idProduct);
            //LOGGER.log(Level.INFO,"ProductEJB: User found {0}",product.get(idTxoko));
        }catch(IllegalArgumentException il){
            LOGGER.log(Level.SEVERE, "ProductEJB: Exception Finding user by login:",
                    il.getMessage());
            throw new ReadException(il.getMessage());
        }
        return product;    
        }
        
    /**
     * This method found product by id by txoko
     * 
     * @param idProduct the id of the product
     * @param idTxoko the id of the txoko
     * @return a product of one txoko and id
     * @throws ReadException throw exception is something is wrong
     */
    @Override
    public Product findProductByIdByTxoko(Integer idProduct, Integer idTxoko) throws ReadException {
        Product product = null;
        try {
            LOGGER.info("ProductManager: Finding product by id.");
            product = (Product) em.createNamedQuery("findProductsByIdByTxoko")
                    .setParameter("idProduct", idProduct)
                    .setParameter("idTxoko", idTxoko)
                    .getSingleResult();
            LOGGER.log(Level.INFO, "ProductManager: User found {0}");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ProductManager: Exception Finding product by idProduct and idTxoko:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return product;
    }

    /**
     * This method create a product
     * 
     * @param product the product we wat to create
     * @throws CreateException throw create is something is wrong
     */
    @Override
    public void createProduct(Product product) throws CreateException {
        LOGGER.info("ProductEJB: Creating product.");
        product.setIdProduct(null);
        try{
            em.persist(product);
            LOGGER.info("ProductEJB: Product created.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductEJB: Exception creating product.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }    
    }

    /**
     * This method update a product
     * 
     * @param product the product we want to update
     * @throws UpdateException throw exception is update is wrong
     */
    @Override
    public void updateProduct(Product product) throws UpdateException {
        LOGGER.info("ProductEJB: Updating product.");
        try{
            
            em.merge(product);
            em.flush();
            
            LOGGER.info("ProductEJB: Product updated.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductEJB: Exception updating product.{0}",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }  
    }

    /**
     * This method is for delete a product
     * 
     * @param product the product we want to delete
     * @throws DeleteException throw exception is delete is wrong
     */
    @Override
    public void deleteProduct(Product product) throws DeleteException {
        LOGGER.info("ProductEJB: Deleting product.");
        try{
            if(!em.contains(product))
                product = em.merge(product);
            em.remove(product);
            LOGGER.info("ProductEJB: Product deleted.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductEJB: Exception deleting product.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }  
    }
    
}