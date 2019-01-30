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
import java.sql.Timestamp;
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
    public List<Expense> findAllExpensesUsers(Integer idTxoko) throws ReadException {
        List<Expense> expenses = null;
        try {
            LOGGER.info("ExpenseManager: Reading all expenses in this txoko.");
            expenses = em.createNamedQuery("findAllExpensesUsers").
                    setParameter("idTxoko", idTxoko).getResultList();
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
    public List<Expense> findMonthExpensesUsers(Integer idTxoko) throws ReadException {
        List<Expense> expenses = null;
        Timestamp current = new Timestamp(System.currentTimeMillis());
        try {
            LOGGER.info("ExpenseManager: Reading all expenses "
                    + "this month in this txoko.");
            expenses = em.createNamedQuery("findMonthExpensesUsers")
                    .setParameter("idTxoko", idTxoko)
                    .setParameter("current", current).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ExpenseManager: Exception reading all "
                    + "expenses users month: ", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return expenses;
    }

    /**
     * Finds expenses of a user and returns the sum of expense's price.
     *
     * @param idUser IdUser of the user.
     * @return Sum of expense's price.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public Float findMonthExpensesSingleUser(Integer idUser) throws ReadException {
        Double expenses = 0.0;
        Timestamp current = new Timestamp(System.currentTimeMillis());
        try {
            LOGGER.info("ExpenseManager: Counting all expenses "
                    + "this month for user.");
            expenses = (Double) em.createNamedQuery("findMonthExpensesSingleUser")
                    .setParameter("idUser", idUser)
                    .setParameter("current", current).getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ExpenseManager: Exception reading all "
                    + "expenses users month: ", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return expenses.floatValue();
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
