FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
COPY target/cc_question-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java","-jar","/app/app.jar"]