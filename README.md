# Desciption
Java 21
Spring Boot 4.x.x

# API
port 8080

# How to run project
## Intellij
right click on the project and select run

## Command line
mvn clean install 
java -jar target/IceTrackCore-0.0.1-SNAPSHOT.jar

# How to run tests
## Intellij
right click on the project and select run all tests

## Command line
mvn clean install

# Technical Choices
ApplicationEventPublisher is used to publish events (Thread Safe)


# Depts
- ZoneRepository is not implemented 
- Add metrics about
    - volume in bus
    - Time in bus (accepted to handled)
- Use RabbitMQ or Kafka for the pub sub
- QualityService should not handle logic about incident Detection
- Missing Tests Unit & Intégration

- Ordering is not handled and will be complicated in the future. Even if we address it soon, there is still a risk. We need to make sure a sensor cannot make multiple calls at the same time. 
  - Add timestamp early and skip events if before the last one : could be done but see with PO.
  - Put the telemetry in a queue with a lock. and push to the bus later. (Slower but safer, maybe not compatible with the "Le endpoint doit répondre immédiatement (ACK).")


# if needed for performance
- Split as multiple modules (apps)
- Redis to store invalidConsecutiveCounter in order to scale consumers