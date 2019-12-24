#!/bin/sh
echo " *** Building the base api *** "
cd ../seor-base-api
mvn clean install -DskipTests

echo " *** Building the SDK *** "
cd ../seor-sdk
mvn clean install -DskipTests

echo " *** Building the product api *** "
cd ../seor-product-api
mvn clean install -DskipTests

echo " *** updating aws lambda *** "

cd ./target

# copy jar to zip
cp seor-product-api-1.0.0.jar seor-product-api-1.0.0.zip

aws lambda update-function-code \
    --function-name "seor-product" \
    --zip-file fileb://seor-product-api-1.0.0.jar

echo ""
echo "----------------------------------------------------------------"
echo " PRODUCT LAMBDA DEPLOYMENT IS COMPLETED SUCCESSFULLY"
echo "----------------------------------------------------------------"
echo ""
