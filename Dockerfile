FROM eclipse-temurin:17

ARG APP_VERSION
COPY ./target/linksaver-$APP_VERSION.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]