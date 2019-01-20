/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.rest;

import jampserverside.ejb.TelephoneEJBLocal;
import jampserverside.entity.Telephone;
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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Julen
 */

@Path("telephone")
public class TelephoneREST{
    
    /**
    * Logger for class methods.
    */
    private static final Logger LOGGER =
            Logger.getLogger("javafxserverside");
            
            
    /**
     * EJB reference for business logic object.
     */
    @EJB
    private TelephoneEJBLocal ejb;

    /**
     *
     * @param telephone
     * @throws CreateException
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void createTelephone(Telephone telephone) throws CreateException {
        LOGGER.info("TelephoneREST: Creating telephone.");
        try{
            ejb.createTelephone(telephone);
            LOGGER.info("TelephoneREST: Telephone created.");
        }catch(CreateException e){
            LOGGER.log(Level.SEVERE, "TelephoneREST: Exception creating telephone.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }    
    }

    /**
     *
     * @param telephone
     * @throws UpdateException
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
     public void updateTelephone(Telephone telephone) throws UpdateException {
        LOGGER.info("TelephoneREST: Updating telephone.");
        try{
            ejb.updateTelephone(telephone);
            LOGGER.info("TelephoneREST: telephone updated.");
        }catch(UpdateException e){
            LOGGER.log(Level.SEVERE, "TelephoneREST: Exception updating telephone.{0}",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }  
    }

    /**
     *
     * @param telephone
     * @throws DeleteException
     */
    @DELETE
    @Consumes({MediaType.APPLICATION_XML})
    public void deleteTelephone(Telephone telephone) throws DeleteException {
        LOGGER.info("TelephoneREST: Deleting telephone.");
        try{
            ejb.deleteTelephone(telephone);
            LOGGER.info("TelephoneREST: telephone deleted.");
        }catch(DeleteException e){
            LOGGER.log(Level.SEVERE, "TelephoneREST: Exception deleting telephone.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }  
    }

    /**
     *
     * @param idTxoko
     * @return
     * @throws ReadException
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Telephone> findAllProducts(int idTxoko) throws ReadException {
        List<Telephone> telephone=null;
        try{
            LOGGER.info("TelephoneREST: Finding all telephone.");
            telephone=ejb.findAllTelephones();
            LOGGER.log(Level.INFO,"TelephoneREST: User found {0}",telephone.get(idTxoko));
        }catch(ReadException e){
            LOGGER.log(Level.SEVERE, "TelephoneREST: Exception Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return telephone;    
    }
    


}
