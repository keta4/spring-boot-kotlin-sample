FROM gradle:latest
COPY . /src
WORKDIR /src

# ENTRYPOINT [ "sh", "gradlew", "bootRun" ]
