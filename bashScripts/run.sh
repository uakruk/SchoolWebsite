#!/bin/bash

if [ "$ACTION" = "build" ];
then
  mvn clean install;
elif [ "$ACTION" = "run" ];
then
  mvn spring-boot:run;
fi
