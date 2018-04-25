Pinterest 4 Food#
#################

To run the code:
 - Load the p4food, p4food_war and p4food_ejb projects into netbeans
     -> A reference error will likely be shown. This can easily be solved using the resolve button
	
- Our project uses mysql, so a mysql setup with working connection pools and data source (in glassfish) are nessecary)
     -> Our project is also tested using the sample derby database, but this database won't have prepopulated data
	 -> A .sql file for creating and populating mysql can be found in the Database folder. Use the "referenceDatabase.sql" file
	 
- Make sure that the persistence file references to the correct datasource.

The project should now be runnable