#!/bin/bash

chrome_driver_loc="$1"
chrome_loc="$2"
allparams="$3"

paramsstr=""
for param in $allparams
do
    KEY="${param%%:*}"
    VALUE="${param##*:}"
    paramsstr+="-D${KEY}=${VALUE} "
done

# run
cp config.properties.etc config.properties
mvn -Dmaven.test.skip=true clean install
mvn -Dwebdriver.chrome.bin=$chrome_loc -Dwebdriver.chrome.driver=$chrome_driver_loc -Dtest=ProductionSmokeTest $paramsstr test

