FROM openjdk:17-jdk-slim
WORKDIR /app

COPY build/libs/app.jar /app/app.jar
COPY src/main/resources/application.yml /app/application.yml

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]

# RUN mkdir -p /app
# WORKDIR /app

# RUN apt-get update
# RUN git clone https://github.com/MyoungSoo7/cicd.git
# RUN ./gradlew clean build
# RUN cd /home/iamipro/java/cicd/build/libs
# RUN java -jar app.jar



