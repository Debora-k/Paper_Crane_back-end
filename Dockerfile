FROM ubuntu:latest
USER root
WORKDIR /app
RUN apt-get update && apt-get -y upgrade
RUN apt-get -y install curl
RUN curl -sL https://deb.nodesource.com/setup_18.x | bash
RUN apt-get -y install nodejs
RUN apt-get install -y openjdk-18-jdk
COPY . .
RUN ./mvnw clean install

CMD ./mvnw spring-boot:run