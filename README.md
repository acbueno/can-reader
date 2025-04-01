# CAN Reader

**CAN Reader** is an example project demonstrating how to read data from a CAN (Controller Area Network) device using **Spring Boot**. The project includes a **Docker container** that simulates a CAN sensor sending data over a virtual CAN network. Additionally, it leverages a Linux library to facilitate the reading of CAN messages.

## Features

- **Spring Boot Integration:** A simple, scalable way to process CAN messages.
- **Virtual CAN Sensor:** A container that simulates a real CAN device, sending data over a virtual network.
- **Linux CAN Library Support:** Uses Linux utilities to read CAN messages efficiently.
- **REST API for Data Access:** Exposes an API to retrieve processed CAN messages.
- **Docker Support:** Easily run the simulation and data reader in a containerized environment.

## Prerequisites

- **Linux-based system** with CAN utilities installed (e.g., `can-utils`).
- **Docker** installed and running.
- **Java 17** (or higher) and **Maven** to build the Spring Boot application.

## Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/acbueno/can-reader.git
cd can-reader
```
### 2. Build the Application
```bash
./mvnw clean install
```
### 3. Start the Virtual CAN Interface (Linux)
If you are using a Linux system, you can create a virtual CAN network for testing:
```bash
sudo modprobe vcan
sudo ip link add dev vcan0 type vcan
sudo ip link set up vcan0
```
### 4. Run the Docker Container (Simulated CAN Sensor)
To start the container that simulates a CAN device sending messages:
```bash
docker-compose up -d
```
The container will start sending CAN messages over the virtual CAN network.

### 5. Start the Spring Boot Application
```bash
java -jar target/can-reader-1.0.0.jar
```
## Usage
### 1. Check Incoming CAN Messages
You can use candump to verify if CAN messages are being transmitted:
```bash
candump vcan0
```
### 2. Access CAN Data via REST API
The Spring Boot application exposes a REST API to retrieve CAN messages:
```bash
curl http://localhost:8080/api/can/messages
```
### 3. Stopping the Services
To stop the sensor simulation:
```bash
docker-compose down
```
