FROM openjdk:14
ADD build/libs/medi-patient.jar medi-patient.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "medi-patient.jar"]
