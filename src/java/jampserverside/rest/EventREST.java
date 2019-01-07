/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package jampserverside.rest;


import jampserverside.ejb.EventEJBLocal;
import jampserverside.entity.Event;
import jampserverside.exception.CreateException;
import jampserverside.exception.DeleteException;
import jampserverside.exception.IdNotOkException;
import jampserverside.exception.NameNotOkException;
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
 * @author Usuario
 */

@Path("event")
public class EventREST {

    /**
     * Logger for class methods..
     */

    private static final Logger LOGGER
            = Logger.getLogger("javafxserverside----EVENT REST");
    /**
     * EJB reference for business logic object.
     */

    @EJB
    private EventEJBLocal ejb;

    @DELETE
    @Consumes({MediaType.APPLICATION_XML})
    public void deleteEvent(Event event){
        try {
            LOGGER.log(Level.INFO, "EventRESTful service: delete event");
            ejb.deleteEvent(event);
        } catch (DeleteException e) {
            LOGGER.log(Level.SEVERE,
                    "EventRESTful service: Exception deleting user by id, {0}",
                    e.getMessage());
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void createEvent(Event event) {
        try {
            LOGGER.log(Level.INFO, "EventRESTful service: create event {0}.", event);
            ejb.createEvent(event);
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE,
                    "EventRESTful service: Exception creating event, {0}",
                    ex.getMessage());
        }
    }
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Event> findAll() {
        List<Event> events = null;
        try {
            events = ejb.findAll();
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE,
                    "EventRESTful service: Exception reading event, {0}",
                    e.getMessage());
        }
        return events;
    }

    
    @GET
    @Path("{idTxoko}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Event> findAllEvents(@PathParam("idTxoko") Integer idTxoko) {
        List<Event> events = null;
        try {
            events = ejb.findAllEvents(idTxoko);
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE,
                    "EventRESTful service: Exception reading event, {0}",
                    e.getMessage());
        }
        return events;
    }

    @GET
    @Path("idEvent/{idEvent}/idTxoko/{idTxoko}")
    @Produces({MediaType.APPLICATION_XML})
    public Event findEventById(@PathParam("idEvent") Integer idEvent, @PathParam("idTxoko") Integer idTxoko) {
        Event event = null;
        try {
            event = ejb.findEventById(idEvent, idTxoko);
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE,
                    "EventRESTful service: Exception reading event, {0}",
                    e.getMessage());
        } catch (IdNotOkException e) {
            LOGGER.log(Level.SEVERE,
                    "ID DEL EVENTO ERRONEO",
                    e.getMessage());
        }
        return event;
    }

    
    @GET
    @Path("name/{name}/txoko/{idTxoko}")
    @Produces({MediaType.APPLICATION_XML})
    public Event findEventByName(@PathParam("name") String name, @PathParam("idTxoko") Integer idTxoko) {
        Event event = null;
        try {
            event = ejb.findEventByName(name, idTxoko);
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE,
                    "EventRESTful service: Exception reading event, {0}",
                    e.getMessage());
        } catch (NameNotOkException e) {
            LOGGER.log(Level.SEVERE,
                    "NOMBRE DEL EVENTO ERRONEO",
                    e.getMessage());
        }
        return event;
    }
    
    @PUT
    @Path("idEvent/{idEvent}/idUser/{idUser}")
    @Consumes({MediaType.APPLICATION_XML})
    public void attendEvent(@PathParam("idEvent") Integer idEvent, @PathParam("idUser") Integer idUser) {
        try {
            LOGGER.log(Level.INFO, "EventRESTful service: update event {0}.");
            ejb.attendEvent(idEvent, idUser);
        } catch (UpdateException e) {
            LOGGER.log(Level.SEVERE,
                    "EventRESTful service: Exception updating event, {0}",
                    e.getMessage());
        }
    }

}