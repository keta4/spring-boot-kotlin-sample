FROM gradle:latest
COPY . /src
WORKDIR /src
RUN chmod +x gradlew
ENTRYPOINT [ "./gradlew", "bootRun" ]
