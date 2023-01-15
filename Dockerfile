FROM gradle:latest as build
COPY . /src
WORKDIR /src
RUN gradle build

FROM eclipse-temurin:17.0.5_8-jre
COPY --from=build /src/build/libs/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
