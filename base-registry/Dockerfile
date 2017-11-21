FROM java:8
VOLUME /tmp
ADD ./target/base-registry.jar /app.jar
CMD ["java", "-Xmx200m", "-jar", "/app.jar"]
EXPOSE 8761