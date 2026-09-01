# Toucan Transaction Processing Service

## Overview

This project is a Java Spring Boot REST API developed as part of the Toucan Payments 2026 Fresher Engineering Challenge.

The application provides a simple transaction-processing service for managing customer transactions. It supports creating transactions, retrieving individual transactions, updating transaction status, and retrieving all transactions belonging to a customer.

The application uses Spring Boot, Spring Data JPA, H2 Database, Maven, and JUnit.

---

## Problem Understanding

The application manages customer transactions.

Each transaction contains the following information:

- Transaction ID
- Customer ID
- Amount
- Currency
- Transaction Type
- Transaction Status

The service provides four main operations:

1. Create a transaction
2. Get a transaction by Transaction ID
3. Update the status of an existing transaction
4. Get all transactions for a Customer ID

The implementation focuses on clean Java code, input validation, business logic, error handling, persistence, and automated testing.

---

## Technology Stack

- Java 17
- Spring Boot 3.5.5
- Spring Web
- Spring Data JPA
- Spring Boot Validation
- H2 Database
- Maven
- JUnit 5
- Git and GitHub
- Postman for API testing

---

## Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.example.transactionstarter
│   │       ├── controller
│   │       │   └── TransactionController.java
│   │       │
│   │       ├── dto
│   │       │   ├── CreateTransactionRequest.java
│   │       │   └── UpdateStatusRequest.java
│   │       │
│   │       ├── exception
│   │       │   ├── DuplicateTransactionException.java
│   │       │   ├── GlobalExceptionHandler.java
│   │       │   └── TransactionNotFoundException.java
│   │       │
│   │       ├── model
│   │       │   └── Transaction.java
│   │       │
│   │       ├── repository
│   │       │   └── TransactionRepository.java
│   │       │
│   │       └── service
│   │           └── TransactionService.java
│   │
│   └── resources
│       └── application.yml
│
└── test
    └── java
        └── com.example.transactionstarter
            └── TransactionStarterApplicationTests.java
