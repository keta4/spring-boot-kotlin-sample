FROM gradle:latest
COPY . /src
WORKDIR /src
EXPOSE 8080
RUN chmod +x gradlew
RUN ./gradlew bootRun
