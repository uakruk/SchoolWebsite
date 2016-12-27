FROM maven:3.3.9-jdk-8-onbuild-alpine

EXPOSE 5000

CMD ["maven", "exec:java"]
