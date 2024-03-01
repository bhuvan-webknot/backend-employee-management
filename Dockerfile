FROM openjdk:11-jdk

#COPY JAR FILE
COPY target/Employee-Management-0.0.1-SNAPSHOT.jar bhuvan-backend-server-1.0.0.jar

ENTRYPOINT ["java","-jar","/bhuvan-backend-server-1.0.0.jar"]