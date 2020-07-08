# hello-world-challenge-tesing

This project contains API autotests for hello-world-challenge application.

### Prerequisites

For running on the tests from this project on your local machine you should have the following software installed:
1. [Java SE Runtime Environment](http://www.oracle.com/technetwork/java/javase/overview/index.html)
2. [IntelliJ IDEA](https://www.jetbrains.com/idea/)
3. [Maven] (https://maven.apache.org/)
4. [Git] (https://git-scm.com/)

## Running the tests

Follow these steps to run the tests:
1. Open the cloned project
2. Open Run/Debug Configurations window
3. Add maven configuration
4. Set the following text into Command line field: 
clean test -Dsuite=${suite}
5. Click Run

### Tests

You can find all the tests in the src/test/java/helloworldchallenge/tests folder.
For now we have tests for the following functionality:
* API. Path to the tests - src/test/java/helloworldchallenge/tests. Folder includes tests that used for API testing

### Supported OS

Tests from the project can be run on the following OS:
1. Microsoft Windows
2. Linux

## Built With

[Maven] - Dependency Management