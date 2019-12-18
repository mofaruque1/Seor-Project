#!/bin/sh
echo " *** updating aws lambda *** "

mvn clean install -DskipTests

cd ./target

# copy jar to zip
cp seor-base-api-1.0.jar seor-base-api-1.0.zip

aws lambda update-function-code \
    --function-name "test" \
    --zip-file fileb://seor-base-api-1.0.jar

echo ""
echo "----------------------------------------------------------------"
echo " LAMBDA DEPLOYMENT IS COMPLETED SUCCESSFULLY"
echo "----------------------------------------------------------------"
echo ""
