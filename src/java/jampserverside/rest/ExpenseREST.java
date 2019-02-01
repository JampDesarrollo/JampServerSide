/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.rest;

import jampserverside.ejb.ExpenseManagerEJBLocal;
import jampserverside.entity.Expense;
import jampserverside.exception.CreateException;
import jampserverside.exception.ReadException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ander
 */
@Path("expense")
public class ExpenseREST {

    /**
     * Logger for the class.
     */
    private static final Logger LOGGER
            = Logger.getLogger("jampserverside");
    /**
     * EJB reference for business logic object.
     */
    @EJB
    private ExpenseManagerEJBLocal ejb;

    /**
     * Find all expenses of all users for a specific txoko.
     * @param idTxoko Id of the txoko.
     * @return List of users.
     */
    @GET
    @Path("{idTxoko}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Expense> findAllExpensesUsers(@PathParam("idTxoko") Integer idTxoko) {
        List<Expense> expenses = null;
        try {
            LOGGER.info("ExpenseRESTful: Find all expenses in this txoko.");
            expenses = ejb.findAllExpensesUsers(idTxoko);
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE, "ExpenseRESTful: Exception reading all "
                    + "expenses users, {0}.", e.getMessage());
            throw new InternalServerErrorException(e);
        }
        return expenses;
    }

    /**
     * Find all expenses of all users for a specific txoko of the current month.
     * @param idTxoko Id of the txoko.
     * @return List of users.
     */
    @GET
    @Path("month/{idTxoko}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Expense> findMonthExpensesUsers(@PathParam("idTxoko") Integer idTxoko) {
        List<Expense> expenses = null;
        try {
            LOGGER.info("ExpenseRESTful: Reading all expenses "
                    + "this month in this txoko.");
            expenses = ejb.findMonthExpensesUsers(idTxoko);
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE, "ExpenseRESTful: Exception reading all "
                    + "expenses users month, {0}.", e.getMessage());
            throw new InternalServerErrorException(e);
        }
        return expenses;
    }
    /**
     * Find all expenses of a user of the current month and sum it.
     * @param idUser Id of the user.
     * @return Amount to pay.
     */
    @GET
    @Path("monthUser/{idUser}")
    @Produces({MediaType.TEXT_PLAIN})
    public Float findMonthExpensesSingleUser(@PathParam("idUser") Integer idUser) {
        Float expenses = 0.0f;
        try {
            LOGGER.info("ExpenseRESTful: Reading all expenses "
                    + "this month for user.");
            expenses = ejb.findMonthExpensesSingleUser(idUser);
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE, "ExpenseRESTful: Exception reading all "
                    + "expenses of user in month, {0}.", e.getMessage());
            throw new InternalServerErrorException(e);
        }
        return expenses;
    }

    /**
     * Create an expense.
     * @param expense Expense to create.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void createExpense(Expense expense) {
        try {
            LOGGER.log(Level.INFO, "ExpenseRESTful: create {0}.", expense);
            ejb.createExpense(expense);
        } catch (CreateException e) {
            LOGGER.log(Level.SEVERE, "ExpenseRESTful: Exception creating expense.{0}",
                    e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

}
