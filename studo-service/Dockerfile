FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS=""\
    SPRING_PROFILES_ACTIVE=prod

CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar app.jar

EXPOSE 8443

VOLUME /studo

COPY target/app.jar /app.jar
