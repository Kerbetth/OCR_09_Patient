FROM openjdk:14
ADD target/mediscreen-patient-1.jar mediscreen-patient-1.jar
EXPOSE 8081
ENTRYPOINT  ["java", "-jar", "mediscreen-patient-1.jar"]