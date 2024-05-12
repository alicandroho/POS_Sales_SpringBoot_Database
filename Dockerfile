FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/pos_sales-0.0.1-SNAPSHOT.jar pos_sales.jar
ENTRYPOINT ["java","-jar","pos_sales.jar"]
