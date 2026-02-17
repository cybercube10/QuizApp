# Microservices Tutorial Project

This project demonstrates the implementation of a **microservices architecture** using Spring Boot. It focuses on breaking down a monolithic quiz application into independent services, enhancing **scalability**, **maintainability**, and **deployment flexibility**.

---

## Project Overview

The project illustrates core microservices concepts such as **service discovery**, **inter-service communication**, and **API Gateway**. It consists of the following key services:

- **Question Service**: Manages all operations related to quiz questions (adding, retrieving, generating questions for a quiz).  
- **Quiz Service**: Handles quiz creation, participant answers, and scoring. Interacts with the Question Service to fetch questions.  
- **Service Registry (Eureka Server)**: Allows microservices to register themselves and discover other services dynamically.  
- **API Gateway**: Acts as a single entry point for client requests, routing them to the appropriate microservice while handling cross-cutting concerns like security and rate limiting.

---

## Key Concepts Demonstrated


| Microservices Architecture | Decomposing a large application into small, independent services |
| Service Discovery (Eureka) | Services register with Eureka and discover each other dynamically | 
| Inter-service Communication (Feign) | Using Feign Client for HTTP calls between microservices | 
| Load Balancing | Multiple instances registered with Eureka, enabling client-side load balancing |
| API Gateway | Centralized entry point for routing requests to backend services  |

---

## Technologies Used

- **Spring Boot** – Framework for building production-grade Spring applications  
- **Maven** – Project build automation  
- **Java 17** – Programming language  
- **PostgreSQL** – Relational database 
- **Lombok** – Reduces boilerplate code 
- **Spring Data JPA** – Database interaction 
- **Spring Cloud Netflix Eureka** – Service discovery  
- **Spring Cloud OpenFeign** – Declarative REST client  
- **Spring Cloud Gateway** – API Gateway implementation  

---

## Services Breakdown

### Question Service

- **Dependencies**: Web, PostgreSQL, Lombok, JPA, OpenFeign, Eureka Client  
- **Functionality**:  
  - `getAllQuestions` – Retrieves all questions 
  - `getQuestionsForQuiz` – Generates a list of question IDs for a quiz based on category and number of questions   
  - `getQuestionsById` – Retrieves questions by a list of IDs  
  - `getScore` – Calculates the score for submitted answers   

---

### Quiz Service

- **Dependencies**: Web, Data JPA, PostgreSQL, Lombok, OpenFeign, Eureka Client
- **Functionality**:  
  - `createQuiz` – Creates a quiz by requesting questions from Question Service 
  - `getQuizQuestions` – Retrieves questions for a specific quiz ID  
  - `submitQuiz` – Submits answers and gets the score from Question Service  
- **Inter-service Communication**: Uses Feign Client to interact with Question Service  

---

### Service Registry (Eureka Server)

- **Dependencies**: Web, Eureka Server  
- **Functionality**: Enables service registration and discovery  
- **Setup**: Use `@EnableEurekaServer` in main application class; configure `application.properties` 

---

### API Gateway

- **Functionality**: Routes incoming client requests to the appropriate microservice   
- **Setup**: Discovery client enabled to locate services registered with Eureka 

---

## How to Run the Project

1. **Clone the Repository**  
 https://github.com/cybercube10/QuizApp/
2. **Database Setup**  
   Create PostgreSQL databases: `questionDB` and `quizDB` as per `application.properties`.  

3. **Build Each Service**  

```bash
cd <service-directory>
mvn clean install
