FROM postgres:9.5.4
ENV POSTGRES_PASSWORD="123456"
ADD studoinit.sh /
RUN cat /studoinit.sh >> /docker-entrypoint-initdb.d/postgis.sh

