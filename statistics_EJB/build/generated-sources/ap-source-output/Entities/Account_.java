package Entities;

import Entities.Statistics;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-15T09:40:12")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, String> fname;
    public static volatile SingularAttribute<Account, String> country;
    public static volatile SingularAttribute<Account, String> password;
    public static volatile SingularAttribute<Account, String> lname;
    public static volatile SingularAttribute<Account, String> gender;
    public static volatile SingularAttribute<Account, Boolean> admin;
    public static volatile SingularAttribute<Account, Integer> id;
    public static volatile SingularAttribute<Account, String> email;
    public static volatile CollectionAttribute<Account, Statistics> statisticsCollection;
    public static volatile SingularAttribute<Account, String> username;

}