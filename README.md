# Secret Santa Project

This is a Spring Boot application designed to manage a Secret Santa gift exchange. It includes APIs for managing families, family members, 
and generating pairings while considering specific constraints. The application uses PostgreSQL as its database and runs inside Docker 
containers.

---

## **Prerequisites**

Before running the project, ensure the following are installed on your system:

- **Docker**: [Install Docker](https://docs.docker.com/get-docker/)
- **Docker Compose**: [Install Docker Compose](https://docs.docker.com/compose/install/)

---

## **Project Setup**

### **1. Clone the Repository**

Clone this repository to your local machine:
```bash
git clone https://github.com/pablo-epl/ss
cd ss/ss
```

### **2. Build the Docker Images**

Build the Docker images using Docker Compose:
```bash
docker-compose build
```

---

## **Running the Project**

### **1. Start the Services**

Run the application and its dependencies using:
```bash
docker-compose up
```

This will:
- Start the PostgreSQL database.
- Build and run the Spring Boot application.

### **2. Verify the Setup**

- **API Endpoint**: The Spring Boot application will be available at:
  ```
  http://localhost:8080
  ```

- **Database**: The PostgreSQL database is exposed on:
  ```
  Host: localhost
  Port: 5432
  Database Name: secret_santa
  Username: username
  Password: password
  ```

---

## **Testing the APIs**

## Using Postman

A Postman collection is available in the repository under the /docs/postman folder. You can import this collection into Postman to quickly test the API endpoints.

To use the Postman collection:

1. Open Postman.
2. Click on Import.
3. Select the collection file (Secret Santa.postman_collection.json) from the /docs/postman folder.
4. Start testing the API using the pre-configured requests.

### Example Endpoints:
- **Add a Main Family**:
  ```bash
  POST http://localhost:8080/mainfamilies
  Body: { "name": "La Colmena" }
  ```

- **Add a Family**:
  ```bash
  POST http://localhost:8080/families
  Body: { "name": "Kokos", "mainFamilyId": 1 }
  ```

- **Add a Family Member**:
  ```bash
  POST http://localhost:8080/familymembers
  Body: { "name": "Nube", "email": "nube@example.com", "familyId": 1 }
  ```

- **Generate Secret Santa Pairings**:
  ```bash
  POST http://localhost:8080/secretsanta/generate?mainFamilyId=1&year=2024
  ```

---

## **Stopping the Project**

To stop the running containers:
```bash
docker-compose down
```

### **Optional: Remove Volumes**
To clear all data stored in the PostgreSQL volume:
```bash
docker-compose down --volumes
```

---

## **Rebuilding the Project**

If you make changes to the code, rebuild the application and restart the services:
```bash
docker-compose down
docker-compose up --build
```

---

## **Project Structure**

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── mx.com.makeitworth.ss
│   │   ├── resources
│   │       └── application.properties
│   └── test
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

## **Troubleshooting**

- **Port Conflicts**:
  If port `8080` or `5432` is already in use, change the port mappings in the `docker-compose.yml` file.

- **Database Connection Issues**:
  Ensure the database credentials in `application.properties` match the environment variables in `docker-compose.yml`.

- **Build Errors**:
  Clean and rebuild the project:
  ```bash
  mvn clean package
  docker-compose up --build
  ```
