# batch-credit-analysis
Project to analyze credit information from [generator-credit-info](https://github.com/isaquebrb/generator-credit-info), using batch process with Spring Batch.
The job consist in **reading** a csv file which contains a person's document; obtain their credit information from generator; **process** the information in the analysis processors from step processFileStep and **write** the result in the postgreSQL database.
The analysis processors can be disabled through database. Each processor uses one or more parameters to approve or reject the person, it can be found inside parameter table or parameters enums.
Changing their default value will affect the approval/rejection percentage.

## Solution
```batch-credit-analysis.herokuapp.com ```

This solution was built with Java 11 + Spring Boot 2.4 and others tools like OpenFeign, Caffeine Cache, JPA, PostgreSQL, Lombok, Spring Validation, Spring Batch, Flyway migrations, Swagger2, Docker Compose.

#### Requirements

- Install Java 11.
- Install Git.
- Install Gradle (or use gradle wrapper on project).
- Install Docker Compose (or PostgreSQL*).

Note: If you already have PostgreSQL running, you can use it. Just change de database configs in **application.properties**.

#### Executing the Project
- Clone the [Project](https://github.com/isaquebrb/batch-credit-analysis.git) and enter in its folder.
- Open terminal or cmd (windows) and execute: **docker-compose up***.
- Execute ./gradle clean build.
- Execute the BatchCreditAnalysisApplication.java in project or execute ./gradle bootRun.

Note: Docker-compose up will a create postgreSQL database and start a Adminer service, it can be access within the config in **docker-compose.yml**.

## Api Documentation

Heroku: https://batch-credit-analysis.herokuapp.com/swagger-ui/

Running Local: http://localhost:8081/swagger-ui/

## Postman Collection
[Download Collection](https://www.getpostman.com/collections/3b96a80b12848ab59104)

## Flow
- Start a job (processFileJob or processFileJobSample)
- The related csv file will be read (complete or sample) in chunks (500)
- The step will select which processors will be available* to work
- For each document found the application will:
    - Search the necessary information in search processors
    - Process this information in the analysis processors**, set an information on analysis object and approve or reject the person
    - Write the approved analysis objects on database
    - A listener will receive the rejected analysis and save it too
- The job is over. YAY

Notes:
- *You can disable or enable the processor in analysis_validation table.
- **You can change the default value of some parameters to increase or decrease the approval/rejection results.
  Ex: The parameter MIN_SCORE_SERASA by default has its value equals 300. If you update to 500, all persons that has score serasa above this value, will be rejected.