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
 * Class that contains a statuscode and potential message when someone logs in
 * Essentially just a dataclass for now
 */
public class RegistrationStatus {
    private int statusCode;
    private String returnmessage;
    
    public RegistrationStatus(){}
    
    public RegistrationStatus(int statusCode, String message){
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
