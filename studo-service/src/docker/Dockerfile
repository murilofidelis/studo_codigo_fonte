FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS=" -Xms128m -Xmx512m"\
    SPRING_PROFILES_ACTIVE=prod

CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar app.jar

RUN apk --update add fontconfig ttf-dejavu

EXPOSE 8081

COPY app.jar /app.jar
