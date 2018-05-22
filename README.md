Pinterest 4 Food#
#################

WebAPP:

To run the code:
 - Load the p4food, p4food_war and statistics_ejb projects into netbeans
 - Add the reference database to netbeans by copying it to the correct directory
 - Make sure that the persistence file references to the correct datasource.
 - Copy all the images in the /Images folder to "${home_dir}/p4foodPictures/". This is the directory used by the program to store and retrieve images. This is not mandatory, but to display the correct images it is.

 - Clean and build p4food_war and statistics_ejb. Deploy both and run the warfile
	
API:
 - To run the api, deploy both statistics_ejb and restAPI
 
 
MobileAPP:
 - Deploy restapi on any machine
 
Alternative One:
 - Extract the zip Eat What You Wish, change the ip address of all the pages associated on which machine restApi runs.
 - Change the ipaddress also in the pin.html for the image display(Not mandatory but for viewing the pin image).
 - Open the code in Visual Studio, build it and run with similation on browser.

Alternative Two:
 - If we dont have VisualStudio installed. Install http-server using this cmd -> npm install http-server -g and go to the project directory and enter cmd -> http-server and run that http on any browser for the mobile app view.

