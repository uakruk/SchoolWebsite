FROM maven:3-jdk-8

RUN mkdir -p /usr/src/app
RUN mkdir -p /usr/src/app/target

WORKDIR /usr/src/app
ADD . /usr/src/app

EXPOSE 8080

CMD bashScripts/run.sh