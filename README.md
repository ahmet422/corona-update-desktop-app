# corona-update-desktop-app
A desktop app to visualize corona affected countries and their statistics 

JAVA Desktop APP – Corona Update

The application is divided in four main logical components Model, View, Services and Controller. The code was divided into 5 packages namely: 
1. Controller: This package has three classes:
  a. ControlTableViewController : class for displaying the countries, maps, deaths, confirmed cases and recovered in tabular form when the button “show all” is clicked on.
  b. Main: class is used to execute the program and calls all methods required to run the the application. 
  c. MainController: class for displaying the MainGUI that displays the total numbers of confirmed, recovered, deaths, comboBox (dropdown of country names) and labels.

2. Model: This package has one interface and 6 classes : 
  a. CoronaDataProvider: implements the interface CoronaData Supplier and gets data from the API. 
  b. CoronaDAtaSupplier: is an interface that specifies what classes that implement it must do. 
  c. Countries: class that puts the countries in an Arraylist
  d. CountriesFactory: uses the factory pattern to give back instances of Countries object 
  e. Country: class that holds information for each country 
  f. Day: class that holds information for each day 
  g. Utils: is used to provide functionalities/methods for different scenarios in the model package. e.g. for calculating the total number of confirmed, recovered and deaths;      getting the country with the most cases and most deaths. 

3. Services: This package has 1 Interface and 2 classes:
  a. CountryCodes: class that uses the CountrycodeList.txt in order to get the flags using the country codes 
  b. IFetcher: is an interface with one class and any class which implement it must provide that functionality. 
  c. RestAPI: implements IFetcher to access the data and can also be used even if the source of data is changed.
  
4. View: This package has two fxml files:
 a. MainGUI: is the XML-based user interface markup language used in creating the main GIU of the project.
 b. TableView: is the XML-based user interface markup language used in creating the table-like interface of the project 

5. Resources: Three resources were used: 
  a. CountrycodeList.txt: is a list of all the countries and their codes separated by “|”. 
  b. DefaultIcon.png : Its an icon used for countries that didn’t have their flags available in the Flags API used. 
  c. Stylesheet.css: used to control the layout of the GUI
