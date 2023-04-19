FROM maven:3.6.3-openjdk-17 as MAVEN_BUILD

COPY ./ ./

RUN mvn clean package

FROM openjdk:17

COPY --from=MAVEN_BUILD /target/*.jar /app.jar

CMD ["java", "-jar", "app.jar"]