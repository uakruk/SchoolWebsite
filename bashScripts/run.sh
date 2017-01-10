#!/bin/bash

if [ "$ACTION" = "build" ];
then
  mvn install;
elif [ "$ACTION" = "run" ];
then
  mvn spring-boot:run;
fi