FROM openjdk:21-jdk-slim

WORKDIR /api

RUN apt update && apt-get install -y curl

COPY target/vitor-0.0.1-SNAPSHOT.jar vitor-0.0.1-SNAPSHOT.jar

HEALTHCHECK --interval=15s --timeout=5s --start-period=15s --retries=3 \
    CMD ["curl","-f", "localhost:8080/health"]

ENTRYPOINT ["java", "-jar", "vitor-0.0.1-SNAPSHOT.jar"]
