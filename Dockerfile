FROM openjdk:8
ADD target/subscription-service.jar subscription-service.jar
EXPOSE 8087
ENTRYPOINT ["java","-jar", "subscription-service.jar"]
