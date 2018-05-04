/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Utility;

/**
 *
 * @author Ken
 * 
 * Class that contains a statuscode and potential message 
 * This class is used often as a statusmessage for when someone for example creates an account
 * (Where alot of different constraints can be violated)
 */
public class Status {
    private int statusCode;
    private String returnmessage;
    
    public Status(){}
    
    public Status(int statusCode, String message){
        this.statusCode = statusCode;
        this.returnmessage = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReturnmessage() {
        return returnmessage;
    }

    public void setReturnmessage(String returnmessage) {
        this.returnmessage = returnmessage;
    }
    
    
}
