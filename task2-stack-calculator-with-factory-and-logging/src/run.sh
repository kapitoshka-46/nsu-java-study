#!/bin/bash

CLASSPATH=commons-cli-1.11.0/commons-cli-1.11.0.jar
java --class-path=$CLASSPATH jcalc/Main.java "$@"
