# Overview
The Smart Campus IoT Sensor Management API is a robust RESTfil web service designed to manage campus infrastructure including rooms, sensors, and environmental data. Built using Java, JAX-RS(Jersey), and the Grizzly HTTP Server, the system provides a centralized platform for real-time monitoring and administrative control of IoT devices.

### API Design Features
- **Resource-Oriented Architecture:** Clear separation between Rooms, Sensors, and Readings.
- **Advanced Exception Handling:** Custom ExceptionMappers translate Java exceptions into meaningful HTTP status codes (e.g., 403 Forbidden for maintenance mode, 409 Conflict for deleting non-empty rooms, and 422 for unprocessable entities).
- **Request/Response Logging:** A LoggerFilter intercepts all traffic to provide real-time console logs of HTTP methods, URIs, and status codes.
- **Service Discovery:** A root endpoint provides HATEOAS-style navigation links to explore the API.
- **Thread-Safe Storage:** An in-memory DataStore using ConcurrentHashMap ensures high performance and data integrity without the overhead of an external database for this version.

---

# Build and Launch Instructions
### Prerequistes
- JDK 11 or higher
- Apache Maven

### Step-by-Step Instructions
**1. Clone the Repository:**
   Ensure all source files are placed in their respective package directories under src/main/java/com/smartcampus/.
   
**2. Build the Project:**
    Navigate to the project root directory and run the following command to compile and package the application:

  ```mvn clean install```

**3. Launch the Server:**
Start the Grizzly server by executing the Main class. The server will bind to http://localhost:8080/api/v1/.

  ```mvn exec:java -Dexec.mainClass="com.smartcampus.application.Main"```

**Verify Status:**
Look for the console log: ```[INFO] Smart Campus API started.```

---

# Sample cURL Commands
**1. Discover API Endpoints**
Get a high-level overview of available resources and service metadata.

```curl -X GET http://localhost:8080/api/v1/```

**2. Create a Room**
Add a new physical location to the campus database.

```
curl -X POST http://localhost:8080/api/v1/rooms \
-H "Content-Type: application/json" \
-d '{"id": "LAB-101", "name": "Advanced Robotics Lab", "capacity": 25}'
```

**3. Register a Sensor to a Room**
Link a new sensor (e.g., Temperature) to the newly created room.

```
curl -X POST http://localhost:8080/api/v1/sensors \
-H "Content-Type: application/json" \
-d '{"id": "TEMP-LAB101-01", "type": "Temperature", "roomId": "LAB-101", "status": "ACTIVE"}'
```

**4. Post a Sensor Reading**
Submit a data point for a specific sensor. This automatically updates the sensor's currentValue.

```
curl -X POST http://localhost:8080/api/v1/sensors/TEMP-LAB101-01/readings \
-H "Content-Type: application/json" \
-d '{"value": 22.5}'
```

**5. Filter Sensors by Type**
Retrieve all sensors of a specific type (e.g., CO2) across the entire campus.

```curl -X GET "http://localhost:8080/api/v1/sensors?type=CO2"```

**6. Test Error Handling (Constraint Violation)**
Attempting to delete a room that still contains sensors will trigger a 409 Conflict error managed by the RoomNotEmptyExceptionMapper.

```curl -X DELETE http://localhost:8080/api/v1/rooms/LAB-101```
