/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Messages;

/**
 *
 * @author ken
 */
public interface messageCrudInterface {
    public Messages getFullMessage(int messageId);
    
    public void createMessage(int senderId, int receiverId, String subject, String content);
    
    public void removeMessage(int messageId);
    
    public void markAsRead(int messageId);
}
