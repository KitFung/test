#!/bin/bash

allparams="$1"
chrome_driver_loc="$2"
chrome_loc="$3"

# run
cp config.properties.etc config.properties
mvn -Dmaven.test.skip=true clean install
mvn -Dwebdriver.chrome.bin=$chrome_loc -Dwebdriver.chrome.driver=$chrome_driver_loc -Dparams=$allparams -Dtest=ProductionSmokeTest test

