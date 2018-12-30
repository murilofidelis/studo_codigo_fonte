FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS=" -Xms128m -Xmx512m"\
    SPRING_PROFILES_ACTIVE=prod

CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar app.jar

RUN apk update && \
    apk add --no-cache ttf-dejavu font-adobe-100dpi

EXPOSE 8443

COPY target/app.jar /app.jar
