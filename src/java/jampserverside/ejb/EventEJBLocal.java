/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.ejb;

import java.util.List;
import jampserverside.entity.Event;
import jampserverside.entity.User;
import jampserverside.exception.CreateException;
import jampserverside.exception.DeleteException;
import jampserverside.exception.IdNotOkException;
import jampserverside.exception.NameNotOkException;
import jampserverside.exception.ReadException;
import jampserverside.exception.UpdateException;
import javax.ejb.Local;

/**
 * The interface with all the empty methods.
 *
 * @author paula
 */
@Local
public interface EventEJBLocal {

    /**
     * Method to delete an event
     *
     * @param event we send an event
     * @throws DeleteException if something is wrong, throws this exception
     */
    public void deleteEvent(Event event) throws DeleteException;

    /**
     * Methos to create an event
     *
     * @param event we send the data that we put in the client side
     * @throws CreateException if something is wrong it throws this exception
     */
    public void createEvent(Event event) throws CreateException;

    /**
     * Method to select all the events of our txoko
     *
     * @param idTxoko the id of the txoko
     * @return a list of the events of the txoko
     * @throws ReadException if something is wrong it throws this exception
     */
    public List<Event> findAll() throws ReadException;

    public List<Event> findAllEvents(Integer idTxoko) throws ReadException;

    /**
     * Method to select a specific event
     *
     * @param idEvent we send the id of the event that we want to see
     * @param idTxoko we send the id of the txoko
     * @return it return the data of that specific event
     * @throws ReadException if something is wrong it throws this exception
     * @throws IdNotOkException if the id doesn't exist it throws this exception
     */
    public Event findEventByIdByTxoko(Integer idEvent, Integer idTxoko) throws ReadException, IdNotOkException;

    /**
     * Method to select a specific event
     *
     * @param name we send the name of the event
     * @param idTxoko we send the id of the txoko
     * @return it return the data of that specific event
     * @throws ReadException if something is wrong it throws this exception
     * @throws NameNotOkException if the name doesn't exist it throws this
     * exception
     */
    public Event findEventByName(String name, Integer idTxoko) throws ReadException, NameNotOkException;
    /**
     * Methot to find an event by id
     * @param idEvent the id of the event
     * @return ir returns an event
     * @throws ReadException if something is wrong it throws this exception
     * @throws IdNotOkException if the id doesn't exist it throws this exception
     */
    public Event findEventById(Integer idEvent) throws ReadException, IdNotOkException;
    /**
     * Method to update an event
     * @param event the event we want to update
     * @throws UpdateException if something is wrong it throws this exception
     */
    public void updateEvent(Event event) throws UpdateException;
    
    //esto es para moviles
   // public void createEventUser(Event event, User user)throws CreateException;

}
