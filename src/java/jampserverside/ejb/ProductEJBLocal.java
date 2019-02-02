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

/**
 *
 * @author Julen
 */

public interface ProductEJBLocal {
    /**
     * This method is to find by name and idTxoko
     * 
     * @param name the nameof the txoko     
     * @param idTxoko the id of the txoko
     * @return the found product
     * @throws ReadException throw exception is something is wrong
     */
    public Product find(String name, Integer idTxoko) throws ReadException;
    
    /**
     * This method is to find by name
     * @param name the name of the product
     * @param idTxoko th id of the txoko
     * @return List of product by name
     * @throws ReadException throw exception is something is wrong
     */
    public List<Product> findProductByName(String name, Integer idTxoko) throws ReadException;
    
    /**
     * This method is for find all products by txoko
     * @param idTxoko the id of the txoko
     * @return List of Product bean with all product of the one txoko
     * @throws ReadException throw exception is something is wrong
     */
    public List<Product> findAllProductsByTxoko(Integer idTxoko) throws ReadException;
    
    /**
     * This method retur all txokos
     * 
     * @return Lis of productBean with all products
     * @throws ReadException throw exception is something is wrong
     */
    public List<Product> findAllProducts() throws ReadException;
    
    /**
     * This method return product by id
     * 
     * @param idProduct the id of the product
     * @return One ProductBean
     * @throws ReadException throw exception is something is wrong
     */
    public Product findProductsById(Integer idProduct) throws ReadException;
    
    
    /**
     * This method create a product
     * 
     * @param product the product to create
     * @throws CreateException throw exception is create is wrong
     */
    public void createProduct(Product product) throws CreateException;
    
    /**
     * This method update a product
     * 
     * @param product the product we want to update
     * @throws UpdateException throw exception is update is wrong
     */
    public void updateProduct(Product product) throws UpdateException;
    
    /**
     * this method delete a product
     * 
     * @param product the product we want to delete
     * @throws DeleteException throw exception is delete is wrong
     */
    public void deleteProduct(Product product) throws DeleteException;

    /**
     * this method is for find by id by txoko
     * 
     * @param idProduct the id of the product we want to find
     * @param idTxoko theid of the txoko
     * @return the product we want to find
     * @throws ReadException throw exception is something is wrong
     */
    public Product findProductByIdByTxoko(Integer idProduct, Integer idTxoko) throws ReadException;

    
    
    
}