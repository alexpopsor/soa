# Catalog Application

Catalog is an application that allows users to manage students and grades entities:

- See all students
- Add a new student
- Delete an existing student
- See all grades
- Add a new grade for a student

The application is secured using the Ouath2 service provided by Okta

# Architecture

## Context Diagram
![Context Diagram](./context-diagram.png)

## Container Diagram
![Container Diagram](./container-diagram.png)

## Component Diagram

- Focused on the Api Gateway

![Component Diagram](./component-diagram.png)

## Service-oriented Architecture Patterns

This application is composed of multiple Microservices patterns. 

Those are the following:
- API gateway - a service that provides each client with unified interface to services
- Discovery Server - a service that registers all services
- Access Token - a token that securely stores information about user that is exchanged between services
- Service instance per Container - deploy each service instance in its container
