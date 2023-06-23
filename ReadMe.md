# User guide
* This Project uses Spring Boot v3.1.2, Java 17, Spring MVC to create a Rest API Endpoint to calculate the reward points earned for each customer per month and total.  
* Note: 
  Because we do not access data from any Database, so I skip the @Repository. The business logic is presented in Service class.
* Clone the project from: https://github.com/TriTran0824/WebAPI.git
* Run the main class: WebAPIApplication
* Test REST API Endpoint using Postman
* Send POST Method for the endpoint "localhost:8080/web-api/v1/reward/rewardPoints" using below make up data set in Body -> raw -> JSON:

[
{"customerId": 1, "purchaseAmount": 120, "transactionDate": "2023-05-01"},
{"customerId": 1, "purchaseAmount": 80, "transactionDate": "2023-06-15"},
{"customerId": 1, "purchaseAmount": 150, "transactionDate": "2023-07-05"},
{"customerId": 2, "purchaseAmount": 60, "transactionDate": "2023-05-20"},
{"customerId": 2, "purchaseAmount": 75, "transactionDate": "2023-06-10"},
{"customerId": 2, "purchaseAmount": 75, "transactionDate": "2023-07-10"},
{"customerId": 3, "purchaseAmount": 20, "transactionDate": "2023-05-05"},
{"customerId": 3, "purchaseAmount": 30, "transactionDate": "2023-06-10"},
{"customerId": 3, "purchaseAmount": 60, "transactionDate": "2023-07-10"}
]

* Result:
  {
  "1": {
  "MAY-2023": 90,
  "JUNE-2023": 30,
  "JULY-2023": 150,
  "Total Reward Points": 270
  },
  "2": {
  "MAY-2023": 10,
  "JUNE-2023": 25,
  "JULY-2023": 25,
  "Total Reward Points": 60
  },
  "3": {
  "MAY-2023": 0,
  "JUNE-2023": 0,
  "JULY-2023": 10,
  "Total Reward Points": 10
  }
  }
