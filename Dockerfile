FROM amazoncorretto:17.0.1
EXPOSE 8080
COPY build/libs/*.jar userauth.jar
CMD java -jar userauth.jar