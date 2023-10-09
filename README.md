# RentACarApplication
RentACarApplication is a web application designed for car rental companies. Through the application, rental customers can place car rental orders, 
rental employees can handle incoming orders and monitor technical and insurance issues of the owned fleet of vehicles. The owner of the rental company has the ability to create a new customer, a new employee, and a new car. 
In the current version of the application, the owner through the admin panel has a preview and the ability to react in a situation when the vehicle does not have a valid technical inspection, 
valid insurance policy or the vehicle has a technical defect. Thus, it enables or disables vehicles to the fleet. 
The application is still in the development phase.

The application consists of two parts, backend and frontend. 
Writing backend I used technologies such as: Java 17, Spring Boot, Gradle, Spring MVC, REST API, Hibernate, PostgreSQL, Flyway, MapStruct, Spring Security, unit and integration tests. 
To create the frontend I used: Thymeleaf, HTML, CSS.

Running the application: Due to technical problems on my side (my PC processor does not support Second Level Address Translation) I cannot make a Docker container and place the project in the cloud. 
Therefore, to run the application you need to open the project in IDE and run it from IDE level.

Target shape of the application: 
Ultimately, the application will handle the following business cases:

•	Four roles will be defined: admin, owner, employee, customer
•	The admin role will have the broadest permissions (it will combine the roles of owner, employee, customer), 
	but above all it will allow to determine the owner of the car rental company
•	The owner role will allow:
	o	adding/removing vehicles and their photos,
	o	adding/removing employees,
	o	adding/removing customers,
	o	temporary disabling of a vehicle (e.g. due to a breakdown, invalidity of a technical inspection, expiration of an insurance policy)
	o	preview/report on cars during rental
	o	preview/report on cars available for rent
	o	preview/report on cars whose insurance ends within 30 days
	o	preview/report on cars whose technical inspection ends within 30 days
	o	reports on employees - e.g. number of rentals completed in x days

•	The employee role will allow:
	o	adding customers,
	o	updating information about vehicles (mileage, damage, additional notes),
	o	updating dates of vehicle availability for rent,
	o	renting a vehicle for a specific customer for a specified time,
	o	cancelling a rental,
	o	updating data regarding the vehicle's OC/AC insurance
	o	updating data regarding the validity of the technical inspection
	o	temporary disabling of a vehicle (e.g. due to a breakdown, invalidity of a technical inspection, expiration of an insurance policy)
	o	preview/report on cars during rental
	o	preview/report on cars available for rent

•	The customer role will allow:
	o	registering as a rental customer,
	o	viewing vehicles that are available for rent,
	o	viewing dates of vehicle availability for rent,
	o	reserving a vehicle for a specified time,
	o	cancelling a reservation,
	o	viewing history of their rentals.

In addition, I would like to connect the application with Google Maps api and weather api, so that they are visible from the customer's view 
(to be used e.g. for planning trips, viewing road conditions etc.)
Ultimately, I would like to make the client part available in a mobile application for Android system.
