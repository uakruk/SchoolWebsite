FROM maven:3-jdk-8

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

ADD . /usr/src/app
#############################
RUN service start postgres
#############################
RUN mvn install

EXPOSE 5000

#CMD ["mvn", "exec:java"]
