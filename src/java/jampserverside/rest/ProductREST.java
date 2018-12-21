/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.rest;

import jampserverside.ejb.ProductEJBLocal;
import jampserverside.entity.Product;
import jampserverside.exception.CreateException;
import jampserverside.exception.DeleteException;
import jampserverside.exception.ReadException;
import jampserverside.exception.UpdateException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Julen
 */

@Path("product")
public class ProductREST{
    
    /**
    * Logger for class methods.
    */
    private static final Logger LOGGER =
            Logger.getLogger("javafxserverside");
            
            
    /**
     * EJB reference for business logic object.
     */
    @EJB
    private ProductEJBLocal ejb;


    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void createProduct(Product product) throws CreateException {
        LOGGER.info("ProductManager: Creating product.");
        try{
            ejb.createProduct(product);
            LOGGER.info("ProductManager: Product created.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductManager: Exception creating product.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }    
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void updateProduct(Product product) throws UpdateException {
        LOGGER.info("ProductManager: Updating product.");
        try{
            ejb.updateProduct(product);
            LOGGER.info("ProductManager: Product updated.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductManager: Exception updating product.{0}",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }  
    }


    @DELETE
    @Consumes({MediaType.APPLICATION_XML})
    public void deleteProduct(Product product) throws DeleteException {
        LOGGER.info("ProductManager: Deleting product.");
        try{
            ejb.deleteProduct(product);
            LOGGER.info("ProductManager: Product deleted.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductManager: Exception deleting product.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }  
    }

    @GET
    @Path("id/{id}/txoko/{idTxoko}")
    @Produces({MediaType.APPLICATION_XML})
    public Product find(@PathParam("id") String id, @PathParam("idTxoko") int idTxoko) throws ReadException {
        Product product=null;
        try{
            LOGGER.info("ProductManager: Finding product by id.");
            product=ejb.find(id, idTxoko);
            LOGGER.log(Level.INFO,"ProductManager: User found {0}",product.getId());
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductManager: Exception Finding product by idProduct and idTxoko:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return product;
    }

    @GET
    @Path("name/{name}/txoko/{idTxoko}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Product> findProductByName(@PathParam("name") String name,@PathParam("idTxoko") int idTxoko) throws ReadException {
        List<Product> product=null;
        try{
            LOGGER.info("Product: Finding product by name.");
            product=ejb.findProductByName(name, idTxoko);
           // LOGGER.log(Level.INFO,"ProductManager: User found {0}",product.get(idTxoko);
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "ProductManager: Exception Finding product by idToko and name:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return product;
    }

 /*   @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Product> findAllProducts(int idTxoko) throws ReadException {
        List<Product> product=null;
        try{
            LOGGER.info("Product: Finding all products.");
            product=ejb.findAllProducts(idTxoko);
            LOGGER.log(Level.INFO,"ProductManager: User found {0}",product.get(idTxoko));
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "UserManager: Exception Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return product;    
    }
    */
       @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Product> findAllProducts() throws ReadException {
        List<Product> product=null;
        try{
            LOGGER.info("Product: Finding all products.");
            product=ejb.findAllProducts();
            LOGGER.log(Level.INFO,"ProductManager: {0} products found.",product.size());
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "UserManager: Exception Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return product;    
    }

}
