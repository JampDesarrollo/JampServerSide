/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.exception;
/**
 * The exception class
 * @author paula
 */
public class CreateException extends Exception {
    
    public CreateException() {
    }
    /**
     * Exception with a message
     * @param msg the detail message.
     */
    public CreateException(String msg) {
        super(msg);
    }
}
