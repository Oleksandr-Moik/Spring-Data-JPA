FROM openjdk:12-alpine
VOLUME /tmp
COPY build/libs/*.jar app.jar 
ENTRYPOINT ["java","-jar","/app.jar"]