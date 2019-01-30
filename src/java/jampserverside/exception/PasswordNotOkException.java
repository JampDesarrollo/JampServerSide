/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.exception;

/**
 *
 * @author 2dam
 */
public class PasswordNotOkException extends Exception {
    public PasswordNotOkException(){
        
    }
    
    public PasswordNotOkException(String msg){
        super(msg);
    }
}
