# User guide

## Backend
* Spring Boot v3.1.2, Java 17, Spring MVC to create a Rest API Endpoint to calculate the total reward points earned by each customer.  
* Note: 
  Because we do not access data from any Database, so skip the @Repository. The business logic is presented in Service class.
  The business logic for the requirement: A customer receives 2 points for every dollar spent over $100 in each transaction, 
  plus 1 point for every dollar spent between $50 and $100 in each transaction. (e.g., a $120 purchase = 2x$20 + 1x$50 = 90 points).
  Assume the customer transactions are collected and saved into transaction.txt by the system.
* Clone the project from: https://github.com/TriTran0824/WebAPI.git.
* Run the main class: WebAPIApplication.java.

## Frontend
* Angular/TypeScript Framework.
* Note:
  When clone the project from: https://github.com/TriTran0824/WebAPI.git, the Angular/TypeScript code included.
  After running backend server, run frontend server by "ng serve --open"

## The logic:
  When Spring boot application starts, it accesses the file content where transactions are stored, processes to
   calculate reward points for each Customer, and finally shows the result to UI.

## Testing
* Backend: Mockito Framework.
* Frontend: Do testcases by getting result from UI and compare to the output from backend. 
  


