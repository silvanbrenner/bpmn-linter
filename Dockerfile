FROM openjdk:14-alpine
COPY target/bpmn-linter-*.jar bpmn-linter.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "bpmn-linter.jar"]