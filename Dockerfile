FROM eclipse-temurin:17
COPY build/libs/*.jar RentACarApplication.jar
ENTRYPOINT ["java", "-jar", "/RentACarApplication.jar"]