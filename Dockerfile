FROM openjdk:9-jre
ARG JAR_FILE
COPY target/dublin_eventhub_demo.jar /app/demo/
EXPOSE 8080
WORKDIR /app/demo/
CMD java -jar /app/demo/dublin_eventhub_demo.jar
