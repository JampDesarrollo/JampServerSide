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
        LOGGER.info("TelephoneManager: Creating telephone.");
        try{
            ejb.createTelephone(telephone);
            LOGGER.info("TelephoneManager: Telephone created.");
        }catch(CreateException e){
            LOGGER.log(Level.SEVERE, "TeleponeManager: Exception creating telephone.{0}",
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
        LOGGER.info("TelephoneManager: Updating telephone.");
        try{
            ejb.updateTelephone(telephone);
            LOGGER.info("TelephoneManager: telephone updated.");
        }catch(UpdateException e){
            LOGGER.log(Level.SEVERE, "TelephoneManager: Exception updating telephone.{0}",
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
        LOGGER.info("TelephoneManager: Deleting telephone.");
        try{
            ejb.deleteTelephone(telephone);
            LOGGER.info("TelephoneManager: telephone deleted.");
        }catch(DeleteException e){
            LOGGER.log(Level.SEVERE, "TelephoneManager: Exception deleting telephone.{0}",
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
            LOGGER.info("TelephoneRest: Finding all telephone.");
            telephone=ejb.findAllTelephones();
            LOGGER.log(Level.INFO,"TelephoneRest: User found {0}",telephone.get(idTxoko));
        }catch(ReadException e){
            LOGGER.log(Level.SEVERE, "TelephoneRest: Exception Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return telephone;    
    }
    


}
