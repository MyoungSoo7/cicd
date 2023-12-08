FROM openjdk:17
ARG JAR_FILE=build/libs/app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-jar", "/app.jar"}