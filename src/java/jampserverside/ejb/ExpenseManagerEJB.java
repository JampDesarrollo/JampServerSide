/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.ejb;

import jampserverside.entity.Expense;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jampserverside.exception.CreateException;
import jampserverside.exception.ReadException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB Local Interface for managing Expense entity CRUD operations.
 *
 * @author ander
 */
@Stateless
public class ExpenseManagerEJB implements ExpenseManagerEJBLocal {

    /**
     * Logger for the class.
     */
    private static final Logger LOGGER
            = Logger.getLogger("jampserverside");
    /**
     * Entity manager object.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Finds all expenses for the users of a txoko.
     *
     * @param idTxoko The id of the txoko used to find the users.
     * @return A List of Expense objects.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public List<Expense> findAllExpensesUsers(int idTxoko) throws ReadException {
        List<Expense> expenses = null;
        try {
            LOGGER.info("ExpenseManager: Reading all expenses in this txoko.");
            expenses = em.createNamedQuery("findAllExpensesUsers").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ExpenseManager: Exception reading all "
                    + "expenses users: ", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return expenses;
    }

    /**
     * Finds expenses of the current month for the users of a txoko.
     *
     * @param idTxoko The id of the txoko used to find the users.
     * @return A List of Expense objects.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public List<Expense> findMonthExpensesUsers(int idTxoko) throws ReadException {
        List<Expense> expenses = null;
        try {
            LOGGER.info("ExpenseManager: Reading all expenses "
                    + "this month in this txoko.");
            expenses = em.createNamedQuery("findMonthExpensesUsers").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ExpenseManager: Exception reading all "
                    + "expenses users month: ", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return expenses;
    }

    /**
     * Creates an Expense and stores it in the underlying application storage.
     *
     * @param expense The Expense object containing expense data.
     * @throws CreateException If there is any Exception during processing.
     */
    @Override
    public void createExpense(Expense expense) throws CreateException {
        LOGGER.info("ExpenseManager: Creating Expense.");
        try {
            em.persist(expense);
            LOGGER.info("ExpenseManager: Expense created.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ExpenseManager: Exception creating expense.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }
}
