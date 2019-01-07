/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.exception;

/**
 * Class for the exception 
 * @author paula
 */
public class IdNotOkException extends Exception {
    public IdNotOkException() {
    }

    /**
     *Exception with a message
     * @param msg the detail message.
     */
    public IdNotOkException(String msg) {
        super(msg);
    }
    
}
