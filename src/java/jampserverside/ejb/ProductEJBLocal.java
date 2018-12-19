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
     * 
     * @param name
     * @param idTxoko
     * @return
     * @throws ReadException 
     */
    public Product find(String name, int idTxoko) throws ReadException;
    
    /**
     * 
     * @param name
     * @param idTxoko
     * @return
     * @throws ReadException 
     */
    public List<Product> findProductByName(String name, int idTxoko) throws ReadException;
    
    /**
     * 
     * @param idTxoko
     * @return
     * @throws ReadException 
     */
    public List<Product> findAllProducts(int idTxoko) throws ReadException;
    
    /**
     * 
     * @param product
     * @throws CreateException 
     */
    public void createProduct(Product product) throws CreateException;
    
    /**
     * 
     * @param product
     * @throws UpdateException 
     */
    public void updateProduct(Product product) throws UpdateException;
    
    /**
     * 
     * @param product
     * @throws DeleteException 
     */
    public void deleteProduct(Product product) throws DeleteException;
    
    
    
}
