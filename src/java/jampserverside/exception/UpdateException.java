/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.exception;

/**
 *Class for the exception Update
 * @author paula
 */
public class UpdateException extends Exception {

    public UpdateException() {
    }

    /**
     *Exception with a message
     * @param msg the detail message.
     */
    public UpdateException(String msg) {
        super(msg);
    }
    
}
