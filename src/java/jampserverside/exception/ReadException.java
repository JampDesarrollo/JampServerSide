/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.exception;

/**
 *Class for the exception for all selects
 * @author paula
 */
public class ReadException extends Exception {

    public ReadException() {
    }

    /**
     *Exception with a message
     * @param msg the detail message.
     */
    public ReadException(String msg) {
        super(msg);
    }
    
}
