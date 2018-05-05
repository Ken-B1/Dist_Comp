/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business_Utility.Status;
import Entities.Account;
import Entities.Categories;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ken
 */
@Stateful
@LocalBean
public class AccountBean {
    @PersistenceContext(unitName = "statistics_EJBPU")
    private EntityManager em;
    
    // Stateless bean used for registration and validation
    @EJB 
    private Registration reg;
    
    // Entity object representing current user in database
    private int currentUser;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public AccountBean(){}
    
    public AccountBean(Account acc){
        currentUser = acc.getId();
        em.persist(currentUser);
    }
    
    // Put all crud functionalities here?
    
    public Status updateAccount(Account account) {
        Status result = reg.validate(account.getEmail(), account.getUsername());
        if(result.getStatusCode() == 0){
            Account user = em.find(Account.class, currentUser);
            user.setEmail(account.getEmail());
            user.setFname(account.getFname());
            user.setLname(account.getLname());
            user.setCountry(account.getCountry());
            user.setGender(account.getGender());
            user.setUsername(account.getUsername());
            em.flush();
        }
        
        return result;
    }
    
       public boolean hasCategories(){
        return em.find(Account.class, currentUser).getCategoriesCollection().isEmpty();
    }
    
    public ArrayList<Categories> getUserCategories(int accountId){
        return new ArrayList<Categories>();
    }
    
    public void addUserCategory(int accountId, Categories newCategory){
        Account currentUser = em.find(Account.class, accountId);
        Collection<Categories> categories = currentUser.getCategoriesCollection();
        categories.add(newCategory);
        currentUser.setCategoriesCollection(categories);
    }
    
    // Add an account and make it managed by entity manager
    public void setAccount(Account acc){
        currentUser = em.merge(acc).getId();
        
    }
    
    public Account getAccount(){
        return em.find(Account.class, currentUser);
    }
    
}
