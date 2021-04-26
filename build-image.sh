#! /bin/bash

./gradlew clean build -x test

JAR_FILE=$(ls build/libs/ | grep "^finance-custom")

docker build . --build-arg jar=build/libs/$JAR_FILE -t ezzefiohez/finance-custom
docker push ezzefiohez/finance-custom

echo " ######## BUILD CUSTOM DONE ######## "

curl  -X POST http://146.59.195.214:9000/api/webhooks/13cd54cf-cf29-4fe0-b84d-9141ec2643db
