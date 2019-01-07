/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.rest;

import jampserverside.ejb.UserManagerEJBLocal;
import jampserverside.entity.User;
import jampserverside.exception.CreateException;
import jampserverside.exception.DeleteException;
import jampserverside.exception.PasswordNotOkException;
import jampserverside.exception.ReadException;
import jampserverside.exception.TxokoNotExistException;
import jampserverside.exception.UpdateException;
import jampserverside.exception.UserLoginExistException;
import jampserverside.exception.UserNotExistException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
 *
 * @author ander
 */
@Path("user")
public class UserREST {

    /**
     * Logger for the class.
     */
    private static final Logger LOGGER
            = Logger.getLogger("jampserverside");
    /**
     * EJB reference for business logic object.
     */
    @EJB
    private UserManagerEJBLocal ejb;

    /**
     *
     * @param login
     * @return
     */
    @GET
    @Path("{login}")
    @Produces({MediaType.APPLICATION_XML})
    public User findUserByLogin(@PathParam("login") String login) {
        User user = null;
        try {
            LOGGER.log(Level.INFO, "UserRESTful: find User by login={0}.", login);
            user = ejb.findUserByLogin(login);
        } catch (ReadException | PasswordNotOkException | UserNotExistException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful : Exception finding user by login, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return user;
    }

    /**
     *
     * @param idTxoko
     * @return
     */
    @GET
    @Path("{users}")
    @Produces({MediaType.APPLICATION_XML})
    public List<User> findAllTxokoUsers(@PathParam("idTxoko") Integer idTxoko) {
        List<User> users = null;
        try {
            LOGGER.info("UserRESTful: Find all users in this txoko.");
            users = ejb.findAllTxokoUsers(idTxoko);
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE, "UserRESTful : Exception reading all "
                    + "users of txoko, {0}.", e.getMessage());
            throw new InternalServerErrorException(e);
        }
        return users;
    }

    /**
     *
     * @param user
     */
    @POST
    @Produces({MediaType.APPLICATION_XML})
    public void createUser(User user) {
        try {
            LOGGER.log(Level.INFO, "UserRESTful: create {0}.", user);
            ejb.createUser(user);
        } catch (CreateException | UserLoginExistException | TxokoNotExistException e) {
            LOGGER.log(Level.SEVERE, "UserRESTful: Exception creating expense.{0}",
                    e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    @PUT
    @Produces({MediaType.APPLICATION_XML})
    public void updateUser(User user) {
        try {
            LOGGER.log(Level.INFO, "UserRESTful: update {0}.", user);
            ejb.updateUser(user);
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful : Exception updating user, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    //*** CAMBIOS EN DIAGRAMA UML**********************************
    @DELETE
    @Path("{login}")
    @Produces({MediaType.APPLICATION_XML})
    public void deleteUser(@PathParam("login") String login) {
        try {
            LOGGER.log(Level.INFO, "UserRESTful service: delete User by login={0}.", login);
            ejb.deleteUser(ejb.findUserByLogin(login));
        } catch (ReadException | DeleteException | PasswordNotOkException | UserNotExistException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception deleting user by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
}
