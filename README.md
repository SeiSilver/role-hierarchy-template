# Project Name
[Insert project description here.]

## Getting Started
To run the code in local with Docker-Compose, please follow these steps:

### Prerequisites

Java 17
Maven
Docker
Docker-Compose

### Installation

1. Clone the repository to your local machine. 
2. Open a terminal window and navigate to the root directory of the project. 
3. Run the following command to build the application and generate the executable JAR file:
    ```shell
    mvn clean install
    ```
4. Navigate to the infras/ directory using the command cd infras/.
5. Run the following command to build the Docker images required for the application:
    ```shell
    docker-compose build
    ```

### Usage

1. Run the following command to start the containers in detached mode. This will start the application and its dependencies, such as databases or message queues:
    ```shell
    docker-compose up -d
    ```
2. You can verify that the application is running by navigating to http://localhost:8080 in your web browser.

### Stopping

* If you want to stop the running containers, run the following command:
    ```shell
    docker-compose down
    ```
   
### Removing Existing Docker-Compose

* If you want to remove the existing Docker-Compose and all its associated resources, run the following command:
    ```shell
    docker-compose down -v
    ```
This will stop and remove all the containers, networks, and volumes created by Docker-Compose.

### Contributing
[Insert contribution guidelines here.]

### License
[Insert license information here.]

### Acknowledgments
[Insert acknowledgments here.]