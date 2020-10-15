FROM openjdk:11
COPY ./target/com.silvanbrenner.bpmnlinter-*jar-with-dependencies.jar /app/application.jar
WORKDIR /app

CMD ["java", "-jar", "application.jar"]