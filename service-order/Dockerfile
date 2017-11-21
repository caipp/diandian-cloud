FROM java:8
VOLUME /tmp
ADD ./target/service-order.jar /app.jar
CMD ["java", "-Xmx200m", "-jar", "/app.jar"]
EXPOSE 8080