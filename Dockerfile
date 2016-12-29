FROM maven:3-jdk-8
RUN mkdir -p ./target
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

ADD . /usr/src/app

EXPOSE 8080

CMD mvn install;
