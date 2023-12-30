FROM openjdk:17-slim
WORKDIR /app
COPY build/libs/app.jar /app/app.jar
COPY src/main/resources/application.yml /app/application.yml
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

# FROM ubuntu:22.04

# RUN mkdir -p /home/iamipro/java/pharmacy
# WORKDIR /home/iamipro/java/pharmacy

# RUN sudo apt-get-update
# RUN sudo apt-get install -y openjdk-17-jdk
# RUN sudo apt-get install -y gradle
# RUN git clone https://github.com/MyoungSoo7/cicd.git
# RUN sudo gradlew clean build
# RUN cd /home/iamipro/java/pharmacy/cicd/build/libs
# RUN sudo java -jar cicd-0.0.1-SNAPSHOT.jar



