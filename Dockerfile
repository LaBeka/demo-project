FROM openjdk:21-oracle
COPY target/*.jar demo-project.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "demo-project.jar"]
