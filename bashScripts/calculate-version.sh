#!/bin/bash

touch target/classes/my.properties
echo "projVersion=" $(git describe) > target/classes/my.properties

