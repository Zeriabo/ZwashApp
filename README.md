# Zwash Car Washing App
## Spring boot car washing App

Welcome to Zwash, a car washing application that allows users to book car washing services conveniently. This repository contains the code and documentation for the Zwash app.

Zwash is a car washing app designed to simplify the process of booking car washing services. It provides a set of RESTful APIs to manage car, booking, user, and car washing program-related operations.

Installation
To use the Zwash app and interact with its APIs, you need to follow these installation steps:

Clone the repository to your local machine using the following command:

bash
git clone https://github.com/zeriabo/zwash.git
Open the project in your preferred IDE (Integrated Development Environment).
Clone the repository to your local machine using the following command:

bash

git clone https://github.com/zeriabo/zwash.git
Navigate to the project directory:

bash
cd zwash
Build the Docker image by running the following command:

Copy code
docker build -t zwash .
Build the project to download the required dependencies.

Usage
Once you have completed the installation steps, you can start using the Zwash app. To access and interact with the APIs, we will be using Swagger UI.

Start the application by running the main method in the ZwashApplication class.

Open your web browser and navigate to http://localhost:8080/swagger-ui/index.html to access the Swagger UI.
Usage
Once you have completed the installation steps, you can start using the Zwash app. To run the app using Docker Compose:

Make sure you have Docker Compose installed on your machine.

In the project directory, create a file named docker-compose.yml 
Open a terminal and navigate to the project directory.

Run the following command to start the Zwash app using Docker Compose:

docker-compose up
Once the containers are up and running, you can access the Zwash app by opening your web browser and navigating to (http://localhost:8080/swagger-ui/index.html). This will launch the Swagger UI, where you can explore and interact with the available endpoints.


In the Swagger UI, you will see a list of available endpoints categorized by controllers. Explore the available endpoints to understand the functionality of the Zwash app.

To interact with the APIs, click on an endpoint and provide the required parameters. You can also try out the endpoints by clicking the "Try it out" button and observing the responses.

When making requests, make sure to include the necessary JSON payload in the request body. code is using the Jackson library, ensure that you include the @class attribute in the JSON payload for proper serialization and deserialization.

Endpoints
Here are the main controllers and their associated endpoints:

Car Controller: Manages car-related operations.
Booking Controller: Handles booking-related operations.
User Controller: Manages user-related operations.
Car Washing Program Controller: Deals with car washing program-related operations.
Please refer to the Swagger UI for detailed documentation on each endpoint.

License
The Zwash app is released under the MIT License. 

Program:
JavaSE-17,
Gradle,
Postgresql,
docker,

Database design:
car wash application spring boot backend
Database design:

## Database description:


### Program description:




please feel free to see the app on the master branch.

Updates:
1. payment gateway to allow users to pay for their bookings
2. Notifications: Implement a notification system to inform users about their booking status, such as confirmation of the booking, changes in the schedule, or cancellation. Notification system on the backend side is ready waiting the frontend after the frontend the backend need adjusting and to place more notifications.
Usage:
3. Mobile app: Consider developing a mobile app to provide a more convenient and user-friendly experience for your customers.
4. Graphql Implemented.
5. service provider User implementation and functions
6. execute a wash


TODO:

Adding a Diameter charging protocol

use gRPC for communication between the microservices.

send accouting transactions to the Zaccounting project
Implement Invoicing 

Implement accouting microservice for stations calculate the revenue
Implementing a Microservies architecture

push notifications

Rating system: Allow users to rate their experience with the car wash and the service they received.

Discounts and promotions: Implement discounts and promotional codes to attract more customers and retain existing ones.

Reporting and analytics: Develop reports and analytics to track the performance of your business, such as the number of bookings, revenue generated, and user behavior.

Customer support: Set up a customer support system to handle any issues that may arise, such as complaints or questions about the service.

Loyalty program: Offer a loyalty program to reward repeat customers and encourage them to continue using your car washing service.

