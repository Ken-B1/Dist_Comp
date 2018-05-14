/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Account;
import Entities.Messages;
import java.util.Date;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author ken
 */
@Stateless
@LocalBean
public class messageCrud{

    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;
    
    public Messages getFullMessage(int messageId){
        Messages toFind = em.find(Messages.class, messageId);
        return toFind;
    }
    
    public void createMessage(int senderId, int receiverId, String subject, String content){
        Account sender = em.find(Account.class, senderId);
        Account receiver = em.find(Account.class, receiverId);
        if(sender == null || receiver == null){
            // Cannot create message, because sender or receiver is null
            return;
        }
        
        
        Messages newMessage = new Messages();
        newMessage.setSender(sender);
        newMessage.setReceiver(receiver);
        newMessage.setSubject(subject);
        newMessage.setContent(content);
        newMessage.setTimestamp(new Date(System.currentTimeMillis()));
        newMessage.setIsRead((short)0);
        try{
            em.persist(newMessage);
        } catch(ConstraintViolationException e){
            for(ConstraintViolation u: e.getConstraintViolations()){
                System.out.println("Message:");
                System.out.println(u.getMessage());
                System.out.println(u.toString());
            }
        }
    }
    
    public void removeMessage(int messageId){
        Messages findRemoved = em.find(Messages.class, messageId);
        if(findRemoved != null){
            em.remove(findRemoved);
        }
    }
    
    public void markAsRead(int messageId){
        Messages mark = em.find(Messages.class, messageId);
        
        if(mark != null){
            mark.setIsRead((short)1);
            em.flush();
        }
    }
}
