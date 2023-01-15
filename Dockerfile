FROM gradle:latest
COPY . /src
WORKDIR /src
RUN chmod +x gradlew
RUN ./gradlew bootRun
