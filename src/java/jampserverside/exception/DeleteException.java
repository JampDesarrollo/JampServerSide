/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.exception;


/**
 *The class for the exception DeleteException
 * @author paula
 */
public class DeleteException extends Exception {
    
    public DeleteException() {
    }

    /**
     *Exception with the message
     * @param msg the detail message.
     */
    public DeleteException(String msg) {
        super(msg);
    }
}
