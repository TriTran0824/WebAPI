# Getting Started
This Project Using Spring Boot, Java 17, Spring MVC. 
Note: 
Because we do not access dada from Any Database, so I skip the @Repository. The business logic is stored in Service class. 

* Clone the project from: https://github.com/TriTran0824/WebAPI.git
* Run the main class: WebAPIApplication
# Test REST API Endpoint using Postman
* Send POST Method for the endpoint "localhost:8080/webapi/v1/reward/points" using example data below in Body -> raw -> JSON:

[
{"customerId": 1, "purchaseAmount": 120, "transactionDate": "2023-05-01"},
{"customerId": 1, "purchaseAmount": 80, "transactionDate": "2023-06-15"},
{"customerId": 2, "purchaseAmount": 60, "transactionDate": "2023-05-20"},
{"customerId": 1, "purchaseAmount": 150, "transactionDate": "2023-07-05"},
{"customerId": 2, "purchaseAmount": 75, "transactionDate": "2023-06-10"},
{"customerId": 2, "purchaseAmount": 75, "transactionDate": "2023-07-10"},
{"customerId": 3, "purchaseAmount": 20, "transactionDate": "2023-05-05"},
{"customerId": 3, "purchaseAmount": 30, "transactionDate": "2023-06-10"},
{"customerId": 3, "purchaseAmount": 60, "transactionDate": "2023-07-10"}
]

* Expect result:
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
