# Employee Management System

## Overview
This is a Java Spring Boot project for an Employee Management System. The system utilizes Spring Boot for the backend, Redis for caching, PostgreSQL for data storage, and Swagger-UI for API documentation.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Getting Started](#setup)

## Features
- CRUD operations for managing employee data.
- Caching employee data using Redis for improved performance.
- API documentation through Swagger-UI.
- PostgreSQL database for persistent data storage.

## Technologies Used
- Java Spring Boot
- Redis
- PostgreSQL
- Swagger-UI

## Prerequisites
Before you begin, ensure you have the following installed on your machine:
- Java 17
- Maven
- Redis
- PostgreSQL

## Setup

1. **Clone the Repository:**
   bash
   git clone https://github.com/bhuvan-webknot/backend-employee-management.git

2. **Configure PostgreSQL:**
    - Create a PostgreSQL database named employee_management.
    - Update the database configurations in application.properties file.

3. **Start Redis Server:**
   Start the Redis server on the default port.

4. **Build and Run:**
   bash
   cd your-project-directory
   mvn clean install
   java -jar target/your-application.jar

5. **Access Swagger UI:**
   Open a web browser and go to `http://localhost:8080/swagger-ui.html` to access the Swagger UI for API documentation.

## Usage

Use the Swagger UI to test and interact with the RESTful APIs.
Perform CRUD operations on the Employee, Timesheet, and User entities.
Use sorting and pagination parameters in API requests to retrieve data accordingly.

---

