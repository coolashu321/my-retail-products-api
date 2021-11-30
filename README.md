# my-retail-products-api
Assessment Application for Target My Retail

# Problem Statement:
`myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps.
The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller.
Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.

Build an application that performs the following actions:
•	Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number.
Example product IDs: 13860428, 54456119, 13264003, 12954218)
•	Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
•	Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail)  
•	Example:
https://redsky-uat.perf.target.com/redsky_aggregations/v1/redsky/case_study_v1?key=3yUxt7WltYG7MFKPp7uyELi1K40ad2ys&tcin=13860428
•	Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response.  
•	BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store.
`

# System Requirements:
System is build on following tech stack:


```
- Java 17
- Gradle 7.3
- Spring Boot 2.6.0
- MongoDB
- TestContainers
- Docker
- Jib
```

# How to Run the application.

Make sure you have Java 17 and Gradle 7.3 installed. 
There are multiple ways to run this application locally

## 1) Running as Docker containers (No Need to run MongoDB seperately.)


- Build the project using gradle jib task using following command:

`./gradlew bootJar jibDockerBuild`

- Project already contains various docker-compose yml file to start various containers. Use following command to start all the container:

`docker-compose -f src/main/docker/app.yml up -d`

***Please note By default mongoDB Container does not persist data on hard disk. So once container is destroyed, data will be lost. In case, if we want to persist data on harddisk please uncomment the following line in `src/main/docker/mongodb.yml` file which will mount the volume in container.*

```aidl
# volumes:
    #     - ~/volumes/jhipster/mongoDemo/mongodb/:/data/db/
```

## 2) Running Spring boot application directly.

In this case, mongoDB server need to be running separately. We can still run mongoDB in docker container or can install mongoDB server directly onto system.

In case we want to run mongoDB in docker container, please use following command:

`docker-compose -f src/main/docker/mongodb.yml up -d`

***Again, Please note By default mongoDB Container does not persist data on hard disk. So once container is destroyed, data will be lost. In case, if we want to persist data on harddisk please uncomment the following line in `src/main/docker/mongodb.yml` file which will mount the volume in container.*

```aidl
# volumes:
    #     - ~/volumes/jhipster/mongoDemo/mongodb/:/data/db/
```

Once MongoDB server is up and running, run the following command:

`gradle :bootRun`

## Server information
Server: Embedded Tomcat
port: 8080 can be changed in application.yaml file

## Other configurations:
`src/main/resource/application.yaml` files contains configuration like url and apiKey for Redsky API. HOST and PORT MongoDB.
