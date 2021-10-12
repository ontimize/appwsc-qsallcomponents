FROM openjdk:11.0.12-oracle

EXPOSE 8080

COPY target/appwsc-qsallcomponents-jee-boot.jar appwsc-qsallcomponents-jee-boot.jar

ENTRYPOINT ["java","-Dspring.profiles.active=pro","-jar","appwsc-qsallcomponents-jee-boot.jar"]