FROM openjdk:17
ADD /target/parsing-kolesa.jar  backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]