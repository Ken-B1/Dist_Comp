package Entities;

import Entities.Account;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-20T10:34:11")
@StaticMetamodel(Statistics.class)
public class Statistics_ { 

    public static volatile SingularAttribute<Statistics, Integer> id;
    public static volatile SingularAttribute<Statistics, Account> userid;
    public static volatile SingularAttribute<Statistics, Date> timestamp;

}