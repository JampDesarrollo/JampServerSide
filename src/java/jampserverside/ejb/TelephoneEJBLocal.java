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

/**
 *
 * @author Julen
 */

public interface TelephoneEJBLocal {
    /**
     * 
     * @return
     * @throws ReadException 
     */
    public List<Telephone> findAllTelephones() throws ReadException;
    
    /**
     * 
     * @param product
     * @throws CreateException 
     */
    public void createTelephone(Telephone telephone) throws CreateException;
    
    /**
     * 
     * @param product
     * @throws UpdateException 
     */
    public void updateTelephone(Telephone telephone) throws UpdateException;
    
    /**
     * 
     * @param product
     * @throws DeleteException 
     */
    public void deleteTelephone(Telephone telephone) throws DeleteException;
    
    
    
}
