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
     * @return 
     * @throws ReadException 
     */
    public Product find(String name, Integer idTxoko) throws ReadException;
    
    /**
     * This method is to find by name
     * @param name the name of the product
     * @param idTxoko th id of the txoko
     * @return List of product by name
     * @throws ReadException 
     */
    public List<Product> findProductByName(String name, Integer idTxoko) throws ReadException;
    
    /**
     * This method is for find all products by txoko
     * @param idTxoko the id of the txoko
     * @return List of Product bean with all product of the one txoko
     * @throws ReadException 
     */
    public List<Product> findAllProductsByTxoko(Integer idTxoko) throws ReadException;
    
    /**
     * This method retur all txokos
     * 
     * @return Lis of productBean with all products
     * @throws ReadException 
     */
    public List<Product> findAllProducts() throws ReadException;
    
    /**
     * This method return product by id
     * 
     * @param idProduct the id of the product
     * @return One ProductBean
     * @throws ReadException 
     */
    public Product findProductsById(Integer idProduct) throws ReadException;
    
    
    /**
     * This method create a product
     * 
     * @param product the product to create
     * @throws CreateException 
     */
    public void createProduct(Product product) throws CreateException;
    
    /**
     * This method update a product
     * 
     * @param product the product we want to update
     * @throws UpdateException 
     */
    public void updateProduct(Product product) throws UpdateException;
    
    /**
     * this method delete a product
     * 
     * @param product the product we want to delete
     * @throws DeleteException 
     */
    public void deleteProduct(Product product) throws DeleteException;

    /**
     * this method is for find by id by txoko
     * 
     * @param idProduct
     * @param idTxoko
     * @return
     * @throws ReadException 
     */
    public Product findProductByIdByTxoko(Integer idProduct, Integer idTxoko) throws ReadException;

    
    
    
}