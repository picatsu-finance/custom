#! /bin/bash

./gradlew clean build -x test

JAR_FILE=$(ls build/libs/ | grep "^finance-custom")

docker build . --build-arg jar=build/libs/$JAR_FILE -t ezzefiohez/finance-custom
docker push ezzefiohez/finance-custom
