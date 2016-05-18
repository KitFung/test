Setup
-----

1. Open the terminal and go to the folder
2. Ensure that you have installed maven
3. type `mvn install`(It will also run the test case after install the test case, run `mvn -Dmaven.test.skip=true install` if you want "install only")
4. copy `config.properties.etc` to `config.properties`. You can modify the config according to your need

Testing through Eclipse
----
1. open this project
2. Test all: right-click the root folder, select "run as" -> "JUnit test"

	Test one class: right-click the specific .java file, select "run as" -> "JUnit test"

	Test one function: double click the function name, select "run as" -> "JUnit test"

Testing through Command Line
-----
1. open cmd, go the the root level of this project, same as the location of this file.
2. Test all: type `mvn site` and press enter.

	Test one class: type `mvn surefire:test -Dtest={classname}` and press enter.

	Test one function: type `mvn -Dtest={classname}#{method name} test`	and press enter.
	
3.You can check the report by open the `index.html` in `target/site/` folder.
 
	
[see more](http://maven.apache.org/surefire/maven-surefire-plugin/examples/single-test.html) 


Testing through docker-compose
-----

Requirement:

- [docker](https://docs.docker.com/engine/installation/)
- [docker-compose](https://docs.docker.com/compose/install/)

```bash
export text="test:{TEST_CLASS}"
sudo -E docker-compose up artstack-test
sudo docker cp $(sudo docker-compose ps -q artstack-test):/usr/src/app/target/surefire-reports/ ./
sudo docker-compose down
```

if you want to run all the test class, change {TEST_CLASS} to .*
if you want to run test class "A", change {TEST_CLASS} to A
if you want to run test case "B" in test class "A", change {TEST_CLASS} to A#B

Optional
-----
1. You can change the testing browser by changing the initialise in the TestBase.java. But don't use the headless browser, it will cause unexpected behaviour which will loss the meaning of the test.

Remind
-----

1. It is assume that your computer have install the webdriver globally and setup the path (chromedriver, etc) correctly (except 'Testing through docker-compose').

2. Delete the account after you run the test that have created account.

3. There are config part for each test suite. Check it before run test.

4. It will take a screenshot to the folder 'screenshot/failure' using the "classname-testcasename.png" format when any error or assertion fail happen.
