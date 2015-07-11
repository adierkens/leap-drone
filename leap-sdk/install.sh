#!/bin/sh
OS=mac
mvn org.apache.maven.plugins:maven-install-plugin:2.3.1:install-file -Dfile=$OS/LeapJava.jar -DgroupId=com.leapmotion.leap -DartifactId=leapMotion -Dversion=1.0.0 -Dpackaging=jar
