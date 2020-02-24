FROM openjdk:8
ADD target/zoomanagerapp.jar zoomanagerapp.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "zoomanagerapp.jar"]