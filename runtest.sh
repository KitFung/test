#!/bin/bash

export JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8
export LIBGL_DEBUG=verbose

allparams=$ARGU
chrome_loc=$(which google-chrome)
chrome_driver_loc=$(which chromedriver)

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

echo "mvn -fn -Dwebdriver.chrome.bin=$chrome_loc -Dwebdriver.chrome.driver=$chrome_driver_loc -Dtest=ProductionSmokeTest $paramsstr test"

mvn -fn -Dwebdriver.chrome.bin=$chrome_loc -Dwebdriver.chrome.driver=$chrome_driver_loc -Dtest=ProductionSmokeTest $paramsstr test


