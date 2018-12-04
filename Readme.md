# Overview
"phone-app" is developed as two independent microservices built on Spring Boot. First service is called Catalog Service, second - Order Service. Both of them can be deployed via Docker.

# Starting services and API contract
Before starting Catalog Service we need to create database and corresponding database table. To do that you can use SQL script located in "phone-app/catalog/src/main/resources/sql/prepare_database.sql" file, scripts are written for MySQL database. As soon as all the necessary data are present in the database we can start Catalog Service. To do that we first need to build Docker image and then create a container based on it.

Docker command to build image for Catalog Service:
`docker build -t catalog-service_v1 .`

Docker command to run container with Catalog Service based on Docker image:
`docker run -it -p 8081:8080 catalog-service_v1`

After this Catalog Service API will be available on port 8081 so it can be called using following endpoint:
`GET http://localhost:8081/phones`

It returns all of the phones configured in the database. Example of the response:
```json
[
    {
        "name": "Apple iPhone",
        "description": "iPhone's description",
        "imageUrl": "http://www.apple.com",
        "price": 999.99
    },
    {
        "name": "Samsung Galaxy S9",
        "description": "Galaxy's description",
        "imageUrl": "https://www.samsung.com",
        "price": 989.99
    },
    {
        "name": "Google Pixel 2",
        "description": "Pixel's description",
        "imageUrl": "https://www.google.com",
        "price": 979.99
    }
]
```

To start Order Service we need to perform the same actions as for Catalog Service except of creating the database. Docker command to build image for Order Service:
`docker build -t order-service_v1 .`

Docker command to run container with Order Service based on Docker image:
`docker run -it -p 8082:8080 order-service_v1`

After this Order Service API will be available on port 8082 so it can be called using following endpoint:
`POST http://localhost:8082/placeOrder`
Request body:
```json
{
    "customer": {
        "name": "David",
        "surname": "Blaine",
        "email": "booking@davidblaine.com"
    },
    "items": [
        {
            "name": "Apple iPhone",
            "description": "iPhone's description",
            "imageUrl": "http://www.apple.com",
            "price": 999.99
        },
        {
            "name": "Google Pixel 2",
            "description": "Pixel's description",
            "imageUrl": "https://www.google.com",
            "price": 979.99
        }
    ]
}
```

It returns order with all of the phones that were bought and with total price. Example of the response:
```json
{
    "customer": {
        "name": "David",
        "surname": "Blaine",
        "email": "booking@davidblaine.com"
    },
    "items": [
        {
            "name": "Apple iPhone",
            "description": "iPhone's description",
            "imageUrl": "http://www.apple.com",
            "price": 999.99
        },
        {
            "name": "Google Pixel 2",
            "description": "Pixel's description",
            "imageUrl": "https://www.google.com",
            "price": 979.99
        }
    ],
    "totalPrice": 1979.98
}
```

# Improvements
Future improvements:
- add validation for all requests and request parameters for both Catalog Service and Order Service;
- configure both projects together via Docker Compose for example so they can be started together much easily;
- extract models that are common for both projects (for example "Phone" class) into a single library and use it instead of current approach;
- add more tests and improve code coverage for both services;
- add javadocs;
- improve documentation and readme file.