# Software architecture document

## 1. Introduction and Goals

### Purpose
This document outlines the architecture for a Secret Santa application. The solution is structured following layered architecture principles and tailored to handle the unique constraints of Secret Santa pairings. The goal is to provide a clear and comprehensive overview for technical and business teams while ensuring scalability, maintainability, and compliance with business rules.
### Architectural Goals
* Implement a fair and constraint-compliant pairing system for Secret Santa.
* Enable scalability to support growth in the number of users and requests.
* Leverage modular and maintainable layered architecture.
* Incorporate monitoring and security in future iterations.

### Architectural Goals

* Ensure proper pairing of participants under the specified constraints:
    * No self-assignments.
    * No repeated pairings within three years.
    * Immediate family members cannot select one another.
* Support high concurrency for simultaneous usage by multiple users.
* Enable test-driven development with sufficient unit tests to validate edge cases.
* Use modern technologies like Spring Boot and PostgreSQL while keeping room for future enhancements (e.g., monitoring and advanced security).
* Deliver a clean, maintainable codebase with comprehensive documentation for building and running the solution.

---
## 2. Business Context

### Overview

The application facilitates a Secret Santa gift exchange for extended families. It adheres to the following constraints:

* No self-pairing.
* Pairing recurrence is restricted to once every three years.
* Immediate family members cannot pair with each other.

### External actors

* Family Members: End users participating in the Secret Santa exchange.
* Administrators: Users managing family member data and resolving pairing conflicts.

---
## 3. Technical Context

### Integrations (Pending)

* Pending Components: Monitoring tools (e.g., Prometheus, Grafana) and security frameworks will be finalized in future iterations.

### Context Diagram

### Container Diagram

### Component Diagram

---
## 4. Architectural Structure

### Application Layers
1. Presentation Layer: Exposes RESTful APIs for creating, managing participants, and generating pairings.
2. Service Layer: Implements business logic, including constraints and validation for pairings.
3. Data Access Layer: Manages interaction with the database.
4. Database: Stores participant details, family relationships, and pairing history.

### Bounded Contexts
1. Participant Management: Handles CRUD operations for participants and family relationships.
2. Pairing Engine: Implements the pairing algorithm while enforcing constraints.

---
## 5. Architectural Concepts

### Key Patterns
* Layered Architecture: Separation of concerns to enhance modularity and maintainability.
* Constraint-Driven Algorithm: Ensures fair pairing based on defined rules.
* Stateless APIs: For scalable and reusable endpoints.

---
## 6. Key Scenarios

### Use Case: Generate Secret Santa Pairings
1. Organizers submit a pairing request with required data(family members data).
2. Service Layer retrieves participant data and pairing history.
3. Pairing Engine enforces constraints and generates pairings.
4. Results are saved to the database and optionally returned to the organizer.

### Use Case: Manage Participants
1. Organizer adds or updates participant details via API.
2. System validates input and updates the database.
3. Confirmation is returned to the organizer.

---
## 7. Implementation Strategy

### Technologies
* Backend: Java with Spring Boot.
* Database: PostgreSQL for persistence.
* Containerization: Docker for deployment.

### Deployment
Application and PostgreSQL database are deployed as Docker containers.
Future iterations may include orchestration with Kubernetes and monitoring using Prometheus and Grafana.

### Monitoring (Pending, for future iterations)

---
## 8. Architectural Decisions (ADRs)
1. Architectural Decisions (ADRs)
2. Layered Architecture as the foundational style
3. Spring Boot as the application framework
4. PostgreSQL for database management
5. Docker for containerization
6. Constraint-Driven Pairing Algorithm
7. Pending security and monitoring tool selection

---
## 9. Quality and Non-Functional Requirements
* Availability: 99.9% SLA.
* Scalability: Capable of handling 10,000 concurrent transactions.
* Security: Encryption of sensitive data and OAuth 2.0-based authentication.

---
## 10. Appendices

### Glossary
* Layered Architecture: A design pattern dividing an application into distinct layers for modularity and maintainability.
* Constraint-Driven Algorithm: Logic that enforces predefined rules while processing inputs.

### References
* Spring Boot technical documentation.
* PostgreSQL documentation.
* Arc42 principles.