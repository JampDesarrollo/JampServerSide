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
public class UserPrivilegeException extends Exception {
    public UserPrivilegeException(){
        
    }
    
    public UserPrivilegeException(String msg){
        super(msg);
    }
}
