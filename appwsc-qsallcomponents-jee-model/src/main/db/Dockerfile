FROM openjdk:8-jdk-alpine

WORKDIR /opt/hsqldb

COPY templateDB.txt templateDB.script
COPY templateDB.properties templateDB.properties

EXPOSE 9001

ENV HSQLDB_VERSION 2.3.4

# Install HSQLDB
RUN mkdir -p /opt/database && \
    mkdir -p /opt/hsqldb && \
    chmod -R 777 /opt/database && \
    mkdir -p /scripts && \
    wget -O /opt/hsqldb/hsqldb.jar https://repo1.maven.org/maven2/org/hsqldb/hsqldb/${HSQLDB_VERSION}/hsqldb-${HSQLDB_VERSION}.jar && \
    wget -O /opt/hsqldb/sqltool.jar https://repo1.maven.org/maven2/org/hsqldb/sqltool/${HSQLDB_VERSION}/sqltool-${HSQLDB_VERSION}.jar

ENTRYPOINT java -cp /opt/hsqldb/sqltool.jar org.hsqldb.Server -database.0 templateDB -dbname.0 templateDB