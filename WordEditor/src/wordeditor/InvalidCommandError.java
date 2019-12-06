/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordeditor;

/**
 *
 * @author Kunal sharma
 */
public class InvalidCommandError  extends Exception {
    public InvalidCommandError(String s) 
    { 
        // Call constructor of parent Exception 
        super(s); 
    } 
    
}
