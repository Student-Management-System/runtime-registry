FROM openjdk:17-jdk-bullseye
RUN mkdir -p /opt/registry
WORKDIR /opt/registry

COPY target/*.jar registry.jar
EXPOSE 8671
ENTRYPOINT ["java", "-jar", "registry.jar"] 
