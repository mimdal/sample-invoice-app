# Sample-Invoice-Application

[![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/apache/maven.svg?label=License)][license]

## About
This Project is about an api to calculate invoices. we have two rules in app., user and customer.
Currently, business and two Unit test classes (for main business) implemented. 

## Building
You need Java JDK 8 to build Plugin. Make sure Maven is using Java JDK 8 by setting JAVA_HOME before running Maven:

- export JAVA_HOME=/PATH/TO/JDK/8
- mvn clean package

## How Run

### by Maven
Open terminal and run following command: `mvn spring-boot:run`

### direct
After successful build open Terminal, change working directory to target folder and run following command:
```bash
java -jar URL-shortener-${version}.jar
``` 

## Technologies/Tools used
- spring boot
- maven: for automate build
- swagger: for API document
- lombok: to reduce boilerplate code. (eg. getter-setter-builder-toString)
- mapstruct: is a code generator that greatly simplifies the implementation of mappings between Java bean types
- h2: in memory database

## swagger API
see resource classes. by swagger API document is exposed to
[this](http://localhost:8080/swagger-ui.html) address.

[license]: https://www.apache.org/licenses/LICENSE-2.0
