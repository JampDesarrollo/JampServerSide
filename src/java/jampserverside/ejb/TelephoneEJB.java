/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.ejb;

import jampserverside.entity.Telephone;
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
public class TelephoneEJB implements TelephoneEJBLocal{
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
     * @return telephone
     * @throws ReadException 
     */
    
    @Override
    public List<Telephone> findAllTelephones() throws ReadException {
        List<Telephone> telephone=null;
        try{
            LOGGER.info("TelephoneEJB: Finding all telephones.");
            telephone=(List<Telephone>)em.createNamedQuery("findAllTelephones").getResultList();
           // LOGGER.log(Level.INFO,"ProductManager: User found {0}",product.get(idTxoko);
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "TelephoneEJB: Exception Finding telephone by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return telephone;    
        
    }

    /**
     * 
     * @param telephone
     * @throws CreateException 
     */
    @Override
    public void createTelephone(Telephone telephone) throws CreateException {
        LOGGER.info("TelephoneEJB: Creating telephone.");
        try{
            em.persist(telephone);
            LOGGER.info("TelephoneEJB: Telephone created.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "TelephoneEJB: Exception creating telephone.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }    
    }

    /**
     * 
     * @param telephone
     * @throws UpdateException 
     */
    @Override
    public void updateTelephone(Telephone telephone) throws UpdateException {
        LOGGER.info("TelephoneEJB: Updating telephone.");
        try{
            em.merge(telephone);
            LOGGER.info("TelephoneEJB: telephone updated.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "TelephoneEJB: Exception updating telephone.{0}",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }  
    }

    /**
     * 
     * @param telephone
     * @throws DeleteException 
     */
    @Override
    public void deleteTelephone(Telephone telephone) throws DeleteException {
        LOGGER.info("TelephoneEJB: Deleting telephone.");
        try{
            telephone = em.merge(telephone);
            em.remove(telephone);
            LOGGER.info("TelephoneEJB: telephone deleted.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "TelephoneEJB: Exception deleting telephone.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }  
    }
    
}
