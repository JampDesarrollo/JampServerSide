/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package jampserverside.rest;


import jampserverside.ejb.EventEJBLocal;
import jampserverside.entity.Event;
import jampserverside.entity.Expense;
import jampserverside.entity.User;
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
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Restful server class. The class that comunicates with the client part.
 * @author Paula
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
    /**
     * Method to delete an event
     * @param idEvent the event we want to delete 
     * @throws ReadException it throws if something is wrong
     * @throws IdNotOkException it throws if the id is not ok
     */
    @DELETE
    @Path("idEvent/{idEvent}")
  //  @Consumes({MediaType.APPLICATION_XML})

    public void deleteEvent(@PathParam("idEvent") Integer idEvent)throws IdNotOkException{
         LOGGER.log(Level.INFO, "EventRESTful service: delete event");
        try {
            LOGGER.log(Level.INFO, "EventRESTful service: delete event");
            ejb.deleteEvent(ejb.findEventById(idEvent));
        } catch (DeleteException | ReadException e) {
            LOGGER.log(Level.SEVERE,
                    "EventRESTful service: Exception deleting user by id, {0}",
                    e.getMessage());
        }
    }

    /**
     * Method to create an event
     * @param event the event we want to create
     */
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
    /**
     * Method to find ALL the events
     * @return it returns a list of events
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Event> findAll() {
        List<Event> events = null;
        try {
               LOGGER.log(Level.INFO,
                    "EventRESTful service: finding ALL EVENTS");
            events = ejb.findAll();           
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE,
                    "EventRESTful service: Exception reading all event, {0}",
                    e.getMessage());
        }
         LOGGER.log(Level.INFO, "Aqui los eventos los devuelve al restful client.", events);
        return events;
        
    }
    /**
     * Method to find all events of my txoko
     * @param idTxoko or txoko
     * @return it returns a list of events
     */   
    @GET
    @Path("{idTxoko}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Event> findAllEvents(@PathParam("idTxoko") Integer idTxoko) {
        List<Event> events = null;
        try {
            LOGGER.log(Level.INFO,
                    "EventRESTful service: finding all elements of my txoko");
            events = ejb.findAllEvents(idTxoko);
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE,
                    "EventRESTful service: Exception reading all event of my txoko, {0}",
                    e.getMessage());
        }
        return events;
    }
    /**
     * Method to find an event of our txoko by id 
     * @param idEvent the id of the event
     * @param idTxoko the id of our txoko
     * @return it return an event
     */
    @GET
    @Path("idEvent/{idEvent}/idTxoko/{idTxoko}")
    @Produces({MediaType.APPLICATION_XML})
    public Event findEventByIdByTxoko(@PathParam("idEvent") Integer idEvent, @PathParam("idTxoko") Integer idTxoko) {
        Event event = null;
        try {
             LOGGER.log(Level.INFO,
                    "EventRESTful service: finding element by id");
            event = ejb.findEventByIdByTxoko(idEvent, idTxoko);
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE,
                    "EventRESTful service: Exception reading event with the id, {0}",
                    e.getMessage());
        } catch (IdNotOkException e) {
            LOGGER.log(Level.SEVERE,
                    "ID DEL EVENTO ERRONEO",
                    e.getMessage());
        }
        return event;
    }

    /**
     * Method to find an event 
     * @param idEvent the id of the event
     * @return it returns an event
     */
    @GET
    @Path("idEvent/{idEvent}")
    @Produces({MediaType.APPLICATION_XML})
    public Event findEventById(@PathParam("idEvent") Integer idEvent) {
        Event event = null;
        try {
             LOGGER.log(Level.INFO,
                    "EventRESTful service: finding element by id");
            event = ejb.findEventById(idEvent);
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE,
                    "EventRESTful service: Exception reading event with the id, {0}",
                    e.getMessage());
        } catch (IdNotOkException e) {
            LOGGER.log(Level.SEVERE,
                    "ID DEL EVENTO ERRONEO",
                    e.getMessage());
        }
        return event;
    }

    /**
     * Method to find an event of our txoko by the name
     * @param name the name of the event
     * @param idTxoko the id of our txoko
     * @return it returns an event
     */
    @GET
    @Path("name/{name}/txoko/{idTxoko}")
    @Produces({MediaType.APPLICATION_XML})
    public Event findEventByName(@PathParam("name") String name, @PathParam("idTxoko") Integer idTxoko) {
        Event event = null;
        try {
            LOGGER.log(Level.INFO,
                    "EventRESTful service: finding element by name");
            event = ejb.findEventByName(name, idTxoko);
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE,
                    "EventRESTful service: Exception reading event with the name, {0}",
                    e.getMessage());
        } catch (NameNotOkException e) {
            LOGGER.log(Level.SEVERE,
                    "NOMBRE DEL EVENTO ERRONEO",
                    e.getMessage());
        }
        return event;
    }   
    //MODIFICAR UN EVENTO

    /**
     * Method to update an event
     * @param event the event we want to update
     */
    @PUT
    @Consumes({"application/xml"})
    public void update(Event event) {
        try {
            LOGGER.log(Level.INFO,"EventRESTful service: update {0}.",event);
            ejb.updateEvent(event);
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE,
                    "EventRESTful service: Exception updating event, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    //MOVILES 
    //crear una entrada en la tabla event - user 
    /*
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void createEventUser(Event event, User user) {
        try {
            LOGGER.log(Level.INFO, "EventRESTful service: creating an event for a user {0}.", event);
            ejb.createEventUser(event, user);
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE,
                    "EventRESTful service: Exception creating event, {0}",
                    ex.getMessage());
        }
    }
 */
}