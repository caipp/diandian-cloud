FROM java:8
VOLUME /tmp
ADD ./target/service-auth.jar /app.jar
CMD ["java", "-Xmx200m", "-jar", "/app.jar"]
EXPOSE 5000