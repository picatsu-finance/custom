#! /bin/bash

./gradlew clean build -x test

JAR_FILE=$(ls build/libs/ | grep "^finance-custom")

docker build . --build-arg jar=build/libs/$JAR_FILE -t ezzefiohez/finance-custom
docker push ezzefiohez/finance-custom

echo " ######## BUILD CUSTOM DONE ######## "

curl  -X POST http://94.239.109.172:9000/api/webhooks/cc022332-53f4-4d66-be95-df17ae514da0
