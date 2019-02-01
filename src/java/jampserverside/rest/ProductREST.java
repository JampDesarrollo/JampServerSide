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
 * The class that comunicates with the client RestFull
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

    /**
     * This method is for update product
     * 
     * @param product the product we want to update
     * @throws UpdateException 
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void updateProduct(Product product) throws UpdateException {
        LOGGER.info("ProductRest: Updating product.");
        try{
            ejb.updateProduct(product);
            LOGGER.info("ProductRest: Product updated.");
        }catch(UpdateException e){
            LOGGER.log(Level.SEVERE, "ProductRest: Exception updating product.{0}",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }  
    }
    /**
     * This method is for create product
     * 
     * @param product the product we wat to create
     * @throws CreateException 
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void createProduct(Product product) throws CreateException {
        LOGGER.info("ProductRest: Creating product.");
        try{
            ejb.createProduct(product);
            LOGGER.info("ProductRest: Product created.");
        }catch(CreateException e){
            LOGGER.log(Level.SEVERE, "ProductRest: Exception creating product.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }    
    }


    /**
     * The product we want to delete
     * 
     * @param idProduct the id of the product we want to delete
     * @throws DeleteException 
     */
    @DELETE
    @Path("idProducto/{idProduct}")
    public void deleteProduct(@PathParam("idProduct") Integer idProduct) throws DeleteException {
        LOGGER.info("ProductRest: Deleting product.");
        try{
            Product product = ejb.findProductsById(idProduct);
            ejb.deleteProduct(product);

            LOGGER.info("ProductRest: Product deleted.");
        }catch(DeleteException | ReadException e){
            LOGGER.log(Level.SEVERE, "ProductRest: Exception deleting product.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }  
    }

    /**
     * This method return products by id and by txoko
     * @param idProduct the id of the product
     * @param idTxoko the id of the txoko
     * @return list of products by id and by txoko
     * @throws ReadException 
     */
    @GET
    @Path("idProduct/{idProduct}/txoko/{idTxoko}")
    @Produces({MediaType.APPLICATION_XML})
    public Product findProductByIdByTxoko(@PathParam("idProduct") Integer idProduct, @PathParam("idTxoko") Integer idTxoko) throws ReadException {
        Product product=null;
        try{
            LOGGER.info("ProductManager: Finding product by id.");
            product=ejb.findProductByIdByTxoko(idProduct, idTxoko);
            LOGGER.log(Level.INFO,"ProductManager: User found {0}",product.getIdProduct());
        }catch(ReadException e){
            LOGGER.log(Level.SEVERE, "ProductManager: Exception Finding product by idProduct and idTxoko:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return product;
    }

    /**
     * This method find products by id
     * 
     * @param idProduct
     * @return 
     */
    @GET
    @Path("idProducto/{idProduct}")
    @Produces({MediaType.APPLICATION_XML})
    public Product findProductsById(@PathParam("idProduct") Integer idProduct){
        Product product=null;
        try{
            LOGGER.info("ProductRest: Finding product by id.");
            product = ejb.findProductsById(idProduct);
            LOGGER.log(Level.INFO,"ProductRest: Prodcut found {0}");
        }catch(ReadException e){
            LOGGER.log(Level.SEVERE, "ProductRest: Exception Finding product by idProduct:",
                    e.getMessage());
        }
        return product;
    }
    /**
     * This method finnd products by name
     * 
     * @param name the name of the product
     * @param idTxoko the id of the txoko
     * @return List  of products with the name
     * @throws ReadException 
     */
    @GET
    @Path("name/{name}/txoko/{idTxoko}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Product> findProductByName(@PathParam("name") String name,@PathParam("idTxoko") Integer idTxoko) throws ReadException {
        List<Product> product=null;
        try{
            LOGGER.info("ProductRest: Finding product by name.");
            product=ejb.findProductByName(name, idTxoko);
            LOGGER.log(Level.INFO,"ProductRest: Product found {0}");
        }catch(ReadException e){
            LOGGER.log(Level.SEVERE, "ProductRest: Exception Finding product by idToko and name:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return product;
    }
    
    /**
     * This method find all proucts
     * 
     * @return list of products with all products
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Product> findAllProducts(){
        List<Product> product=null;
        try{
            LOGGER.info("ProductRest: Finding all products.");
            product=ejb.findAllProducts();
            LOGGER.log(Level.INFO,"ProductRest: {0} products found.",product.size());
        }catch(ReadException e){
            LOGGER.log(Level.SEVERE, "ProductRest: Exception Finding all products:",
                    e.getMessage());
        }
        return product;    
    }
    
    /**
     * This method find all products by txoko
     * 
     * @param idTxoko
     * @return list of products with all products by txoko
     */
    @GET
    @Path("txoko/{idTxoko}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Product> findAllProductsByTxoko(@PathParam("idTxoko") Integer idTxoko) {
        List<Product> product=null;
        try{
            LOGGER.info("ProductRest: Finding all products.");
            product=ejb.findAllProductsByTxoko(idTxoko);
            LOGGER.log(Level.INFO,"ProductRest: {0} products found.",product.size());
        }catch(ReadException e){
            LOGGER.log(Level.SEVERE, "ProductRest: Exception Finding user by login:",
                    e.getMessage());
        }
        return product;    
    }

}