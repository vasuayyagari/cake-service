## Overview

This is cake service to create cakes and retrieve cakes

## Pre-requisites

* JDK 8+
* Docker

## Building

### Testing

`./gradlew test`

### Building (no tests)

`./gradlew assemble`

### Building (with tests)

`./gradlew build`

### Running in Docker

`./gradlew assemble docker dockerRun`

### Stopping Docker container

`./gradlew dockerStop`

### Deploying to AWS

`./gradlew awsCfnMigrateStack awsCfnWaitStackComplete -PsubnetId=<your-subnet-id> -Pregion=<your-region>`

### Deleting AWS deployment

`./gradlew awsCfnDeleteStack awsCfnWaitStackComplete`

## Using API

* get all cakes - GET [/cake](http://localhost:8080/cake) to get a list of all the cakes
* create cake - POST JSON to [/cake](http://localhost:8080/cake) to create a new cake 
