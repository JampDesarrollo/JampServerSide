/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.rest;

import jampserverside.exception.IdNotOkException;
import java.util.List;
import java.util.logging.Logger;
import jampserverside.ejb.EventEJBLocal;
import jampserverside.entity.Event;
import jampserverside.exception.CreateException;
import jampserverside.exception.DeleteException;
import jampserverside.exception.NameNotOkException;
import jampserverside.exception.ReadException;
import jampserverside.exception.UpdateException;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
/**
 *
 * @author paula
 */
public class EventREST {

    /**
     * Logger for class methods.
     */
    private static final Logger LOGGER
            = Logger.getLogger("Parte servidora --- EVENT REST");

    //GET(Select), POST(Create), PUT(Update), DELETE
    /**
     * EJB object
     */
    @EJB //asi le estamos diciendo que queremos usar un ejb
    private EventEJBLocal ejb;

    @DELETE
    public void deleteEvent(Event event) throws DeleteException {

    }

    @POST
    public void createEvent(Event event) throws CreateException {

    }

    @GET
    public List<Event> findAllEvents(int idTxoko) throws ReadException {
        return null;

    }

    @GET
    public Event findEventById(int idEvent, int idTxoko) throws ReadException, IdNotOkException {
        return null;
    }

    @GET
    public Event findEventByName(String name, int idTxoko) throws ReadException, NameNotOkException {
        return null;

    }

    @PUT
    public void attendEvent(int idEvent, int idUser) throws UpdateException {

    }

}
