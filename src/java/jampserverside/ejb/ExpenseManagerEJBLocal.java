/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.ejb;

import jampserverside.entity.Expense;
import java.util.List;
import jampserverside.exception.CreateException;
import jampserverside.exception.ReadException;
import javax.ejb.Local;

/**
 * EJB Local Interface for managing Expense entity CRUD operations.
 *
 * @author ander
 */
@Local
public interface ExpenseManagerEJBLocal {

    /**
     * Finds all expenses for the users of a txoko.
     *
     * @param idTxoko The id of the txoko used to find the users.
     * @return A List of Expense objects.
     * @throws ReadException If there is any Exception during processing.
     */
    public List<Expense> findAllExpensesUsers(Integer idTxoko) throws ReadException;

    /**
     * Finds expenses of the current month for the users of a txoko.
     *
     * @param idTxoko The id of the txoko used to find the users.
     * @return A List of Expense objects.
     * @throws ReadException If there is any Exception during processing.
     */
    public List<Expense> findMonthExpensesUsers(Integer idTxoko) throws ReadException;

    /**
     * Finds expenses of a user and returns the sum of expense's price.
     *
     * @param idUser IdUser of the user.
     * @return Sum of expense's price.
     * @throws ReadException If there is any Exception during processing.
     */
    public Float findMonthExpensesSingleUser(Integer idUser) throws ReadException;

    /**
     * Creates an Expense and stores it in the underlying application storage.
     *
     * @param expense The Expense object containing expense data.
     * @throws CreateException If there is any Exception during processing.
     */
    public void createExpense(Expense expense) throws CreateException;
}