FROM openjdk:17
ADD target/book-management-system.jar book-management-system.jar
ENTRYPOINT ["java","-jar","/book-management-system.jar"]