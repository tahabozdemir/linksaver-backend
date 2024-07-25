FROM eclipse-temurin:17

ARG APP_VERSION
COPY linksaver-$APP_VERSION.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod" ,"app.jar"]
