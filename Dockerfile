FROM maven:3.6.3-jdk-11-slim AS build

COPY . /app

WORKDIR /app

RUN mvn clean package -DskipTests


FROM openjdk:11-jre-slim

RUN mkdir /app

COPY --from=build /app/target/*.jar /app/cc-question-back.jar

WORKDIR /app

CMD "java" "-jar" "cc-question-back.jar"
