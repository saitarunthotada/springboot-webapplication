FROM openjdk:17
ADD ./target/webapplication.jar webapplication.jar
ENTRYPOINT ["java", "-jar", "webapplication.jar"]