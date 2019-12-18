#!/bin/sh
echo " *** Building the base api *** "
cd ../seor-base-api
mvn clean install -DskipTests

echo " *** Building the auth api *** "
cd ../seor-authentication-api
mvn clean install -DskipTests

echo " *** updating aws lambda *** "

cd ./target

# copy jar to zip
cp seor-authentication-api-1.0.jar seor-authentication-api-1.0.zip

aws lambda update-function-code \
    --function-name "test" \
    --zip-file fileb://seor-authentication-api-1.0.jar

echo ""
echo "----------------------------------------------------------------"
echo " LAMBDA DEPLOYMENT IS COMPLETED SUCCESSFULLY"
echo "----------------------------------------------------------------"
echo ""