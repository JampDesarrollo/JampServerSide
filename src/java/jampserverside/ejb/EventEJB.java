/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.ejb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jampserverside.entity.Event;
import jampserverside.entity.Txoko;
import jampserverside.entity.User;
import javax.persistence.PersistenceContext;
import jampserverside.exception.CreateException;
import jampserverside.exception.DeleteException;
import jampserverside.exception.IdNotOkException;
import jampserverside.exception.NameNotOkException;
import jampserverside.exception.ReadException;
import jampserverside.exception.UpdateException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

//en el lado servidor no hay que almacenar el estado del cliente
/**
 * The class that implements the interface
 *
 * @author paula
 */
@Stateless
public class EventEJB implements EventEJBLocal {

    /**
     * Logger for the class
     */
    private static final Logger LOGGER
            = Logger.getLogger("Lado servidor -- Event EJB");

    /**
     * Entity Manager object.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Method to delete an event
     *
     * @param event we send an event
     * @throws DeleteException if something is wrong, throws this exception
     */
    @Override
    public void deleteEvent(Event event) throws DeleteException {
        LOGGER.info("Deleting an event.");
        try {
            //para asegurarnos que el objeto existe antes de borrarlo
            if(!em.contains(event))
                event=em.merge(event);
            //borra el evento
            em.remove(event);  
 //           em.flush();
            LOGGER.log(Level.INFO, "Event deleted {0}.",event.getIdEvent());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception deleting event: {0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * Methos to create an event
     *
     * @param event we send the data that we put in the client side
     * @throws CreateException if something is wrong it throws this exception
     */
    @Override
    public void createEvent(Event event) throws CreateException {

        LOGGER.info(" Creating an event.");
        try {
              em.persist(event); //creacion del evento
//            em.flush();
            LOGGER.info("Event created.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception creating the event.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }

    }

    @Override
    public List<Event> findAll() throws ReadException {
        List<Event> events = null;
        try {
            LOGGER.info("Reading all events of my txoko.");
            //llamamos al metodo de crear del entity manager
            events = em.createNamedQuery("findAll")
                    .getResultList();
             LOGGER.info("Hasta aqui llego.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    "UserManager: Exception reading events, {0}",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
            LOGGER.info("Le devuelve todos los eventos al restful server.");
        return events;
    }

    /**
     * Method to select all the events of our txoko
     *
     * @param idTxoko the id of the txoko
     * @return a list of the events of the txoko
     * @throws ReadException if something is wrong it throws this exception
     */
    @Override
    public List<Event> findAllEvents(Integer idTxoko) throws ReadException {
        List<Event> events = null;
        try {
            LOGGER.info("Reading all events of my txoko.");
            //llamamos al metodo de crear del entity manager
            events = em.createNamedQuery("findAllEvents")
                    .setParameter("idTxoko", idTxoko)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    "UserManager: Exception reading events, {0}",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return events;
    }

    /**
     * Method to select a specific event
     * @param idEvent we send the id of the event that we want to see
     * @param idTxoko we send the id of the txoko
     * @return it return the data of that specific event
     * @throws ReadException if something is wrong it throws this exception
     * @throws IdNotOkException if the id doesn't exist it throws this exception
     */
    @Override
    public Event findEventByIdByTxoko(Integer idEvent, Integer idTxoko) throws ReadException, IdNotOkException {
        Event event = null;
        try {
            LOGGER.info("Finding event by id and by txoko.");
            //buscame el evento por el id que ponga el usuario y el id del txoko
            event = (Event) em.createNamedQuery("findEventByIdByTxoko")
                    .setParameter("idEvent", idEvent)
                    .setParameter("idTxoko", idTxoko)
                    .getSingleResult();
            LOGGER.log(Level.INFO, "Event found");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception Finding event:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return event;
    }
       /**
     * Methot to find an event by id
     * @param idEvent the id of the event
     * @return ir returns an event
     * @throws ReadException if something is wrong it throws this exception
     * @throws IdNotOkException if the id doesn't exist it throws this exception
     */
    @Override
    public Event findEventById(Integer idEvent) throws ReadException, IdNotOkException {
       Event event=null;
        try{
            LOGGER.info("EventManager: Finding event by id.");
            event=em.find(Event.class, idEvent);
            if(event!=null)
                LOGGER.log(Level.INFO,"EventManager: Event found {0}",event.getIdEvent());
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "EventManager: Exception Finding event by ID:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return event;
    }

    @Override
    public Event findEventById(Integer idEvent) throws ReadException, IdNotOkException {
       Event event=null;
        try{
            LOGGER.info("EventManager: Finding event by id.");
            event=em.find(Event.class, idEvent);
            if(event!=null)
                LOGGER.log(Level.INFO,"EventManager: Event found {0}",event.getIdEvent());
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "UserManager: Exception Finding event by ID:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return event;
    }

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
    @Override
    public Event findEventByName(String name, Integer idTxoko) throws ReadException, NameNotOkException {
        Event event = null;
        try {
            LOGGER.info("EventManager: Finding event by name.");
            //buscame el evento por nombre y por id del txoko
            event = (Event) em.createNamedQuery("findEventByName")
                    .setParameter("name", name)
                    .setParameter("idTxoko", idTxoko)
                    .getSingleResult();
            LOGGER.log(Level.INFO, "Event found");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception Finding event:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return event;
    }

     /**
     * Method to update an event
     * @param event the event we want to update
     * @throws UpdateException if something is wrong it throws this exception
     */   
    //MODIFICAR UN EVENTO
    @Override
    public void updateEvent(Event event)throws UpdateException{
        
        LOGGER.info("EventManager: Updating event.");
        try{
            em.merge(event);
//            em.flush();
            LOGGER.info("EventManager: event updated.");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "EventManager: Exception updating event.{0}",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    
    }
    
    //MOVILES   
    //esto es para moviles
    /*
    @Override
    public void createEventUser(Event event, User user)throws CreateException{
         
        LOGGER.info(" Creating an event for a user.");
        try {
            em.persist(event);
            em.persist(user);//creacion del evento
            LOGGER.info("Event created.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception creating the event and user.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }

    
    }
    
*/
}
