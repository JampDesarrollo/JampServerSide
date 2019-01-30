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
import jampserverside.exception.UserPrivilegeException;
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
 * RESTful web service class exposing CRUD operations for User entities.
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
     * RESTful GET method for reading an User object through an XML
     * representation in Mobile app.
     *
     * @param login Login value of the object.
     * @param password Password value of the object.
     * @return A User object containing data.
     */
    @GET
    @Path("Mov/{login}/{password}")
    @Produces({MediaType.APPLICATION_XML})
    public User findUserByLoginPasswMov(@PathParam("login") String login,
            @PathParam("password") String password) {
        User user = null;
        try {
            LOGGER.log(Level.INFO, "UserRESTful: find User by login={0}.", login);
            user = ejb.findUserByLoginPasswMov(login, password);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful : ReadException finding user by login, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        } catch (PasswordNotOkException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful : PasswordNotOkException finding user by login, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        } catch (UserNotExistException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful : UserNotExistException finding user by login, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return user;
    }

    /**
     * RESTful GET method for changing an Users password. It returns a boolean
     * of confirmation.
     *
     * @param idUser IdUser of the object.
     * @param oldPassw Old password of the object.
     * @param newPassw New password for the object.
     * @return Boolean confirmation.
     */
    @GET
    @Path("MovChangePassw/{idUser}/{oldPassw}/{newPassw}")
    @Produces({MediaType.TEXT_PLAIN})
    public Boolean findUserChangePasswMov(@PathParam("idUser") Integer idUser,
            @PathParam("oldPassw") String oldPassw, @PathParam("newPassw") String newPassw) {
        Boolean allOk = false;
        try {
            LOGGER.log(Level.INFO, "UserRESTful:changing passw for idUser={0}.", idUser);
            allOk = ejb.findUserChangePasswMov(idUser, oldPassw, newPassw);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful : ReadException finding user by login, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        } catch (PasswordNotOkException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful : PasswordNotOkException finding user by login, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        } catch (UserNotExistException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful : UserNotExistException finding user by login, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return allOk;
    }

    /**
     * RESTful GET method for reading an User object through an XML
     * representation in PC app.
     *
     * @param login Login of the object.
     * @param password Password of the object.
     * @return A User object containing data.
     */
    @GET
    @Path("PC/{login}/{password}")
    @Produces({MediaType.APPLICATION_XML})
    public User findUserByLoginPasswPC(@PathParam("login") String login,
            @PathParam("password") String password) {
        User user = null;
        try {
            LOGGER.log(Level.INFO, "UserRESTful: find User by login={0}.", login);
            user = ejb.findUserByLoginPasswPC(login, password);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful : ReadException finding user by login, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        } catch (PasswordNotOkException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful : PasswordNotOkException finding user, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        } catch (UserNotExistException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful : UserNotExistException finding user {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        } catch (UserPrivilegeException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful : UserPrivilegeException finding user , {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return user;
    }

    /**
     * RESTful GET method for getting a new password for the user. It returns a
     * boolean of confirmation.
     *
     * @param login Login of the object.
     * @return Boolean confirmation.
     */
    @GET
    @Path("/{login}")
    @Produces({MediaType.TEXT_PLAIN})
    public Boolean findUserForgotPassword(@PathParam("login") String login) {
        Boolean found = false;
        try {
            LOGGER.info("UserRESTful: User forgot password.");
            found = ejb.findUserForgotPassword(login);
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE, "UserRESTful: ReadException finding"
                    + "user forgot passw, {0}.", e.getMessage());
            throw new InternalServerErrorException(e);
        } catch (UserNotExistException ex) {
            LOGGER.log(Level.SEVERE, "UserRESTful : User no existe "
                    + " passw forgot, {0}.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return found;
    }

    /**
     * RESTful GET method for reading all User objects through an XML
     * representation.
     *
     * @param idTxoko IdTxoko of te Users object.
     * @return A List of User objects containing data.
     */
    @GET
    @Path("txoko/{idTxoko}")
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
     * RESTful GET method for reading all User objects through an XML
     * representation.
     *
     * @return A List of User objects containing data.
     */
    @GET
    @Path("users")
    @Produces({MediaType.APPLICATION_XML})
    public List<User> findAllUsers() {
        List<User> users = null;
        try {
            LOGGER.info("UserRESTful: Find all users in this txoko.");
            users = ejb.findAllTxokoUsers();
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE, "UserRESTful : Exception reading all "
                    + "users of txoko, {0}.", e.getMessage());
            throw new InternalServerErrorException(e);
        }
        return users;
    }

    /**
     * RESTful GET method for reading an User object through an XML
     * representation.
     *
     * @param idUser IdUser of the object.
     * @return A User object containing data.
     */
    @GET
    @Path("user/{idUser}")
    @Produces({MediaType.APPLICATION_XML})
    public User findUserById(@PathParam("idUser") Integer idUser) {
        User user = null;
        try {
            LOGGER.info("UserRESTful: Find user by id.");
            user = ejb.findUserById(idUser);
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE, "UserRESTful : Exception reading user"
                    + ", {0}.", e.getMessage());
            throw new InternalServerErrorException(e);
        }
        return user;
    }

    /**
     * RESTful POST method for creating User objects from XML representation.
     *
     * @param user The object containing user data.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
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

    /**
     * RESTful PUT method for updating user objects from XML representation.
     *
     * @param user The object containing user data.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
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

    /**
     * RESTful DELETE method for deleting User objects from id.
     *
     * @param idUser The id for the object to be deleted.
     */
    @DELETE
    @Path("user/{idUser}")
    public void deleteUser(@PathParam("idUser") Integer idUser) {
        try {
            LOGGER.log(Level.INFO, "UserRESTful service: delete User by idUser={0}.", idUser);
            ejb.deleteUser(ejb.findUserById(idUser));
        } catch (ReadException | DeleteException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception deleting user by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
}
