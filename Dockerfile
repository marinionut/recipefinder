FROM maven:3.5-jdk-8-alpine AS build

WORKDIR /code

# Adding source, compile and package into a fat jar
COPY . /code
RUN ["mvn", "package"]

FROM openjdk:8-jre-alpine

COPY --from=build /code/target/recipefinder-0.0.1-SNAPSHOT.jar /

CMD ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "/recipefinder-0.0.1-SNAPSHOT.jar"]