FROM amazoncorretto:21
LABEL authors="rlaredo"
ARG JAR_FILE
COPY config config
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Duser.language=es","-Duser.region=BO","-Duser.country=BO","-Duser.timezone=America/La_Paz", "-jar","/app.jar"]
