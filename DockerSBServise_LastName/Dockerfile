FROM openjdk:12
VOLUME /tmp
COPY build/libs/*.jar app.jar 
ENTRYPOINT ["java","-jar","/app.jar"]