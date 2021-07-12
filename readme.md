# Web Front-End Automation Exercise

## Installation

This projects needs the Java JDK 11, Gradle and Chrome to execute the test scenarios.

### AdoptOpenJDK11

You can download the AdoptOpenJDK11 (with HotSpot JVM) for your system [here](https://adoptopenjdk.net/). Also you can
find the installation steps for your operative system [here](https://adoptopenjdk.net/installation.html).

### Gradle

There is no need to install Gradle, this projects comes with a gradle wrapper in the root folder.

### Google Chrome

Make sure to have installed Google Chrome in your system, there is no need for a particular version
since [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) downloads the ChromeDriver version needed for
the Google Chrome version installed on your system.

You can download the lastest Google Chrome from [here](https://www.google.com/intl/es/chrome/)

## Getting Started

Once you have clone the repository, open a terminal and run the following commands in the root project folder:

```bash
  ./gradlew clean build
```

```bash
  ./gradlew downloadAllure
```

## Running Tests

To run tests, run the following command

```bash
  ./gradlew clean executeScenarios
```

### Logs

Each test scenarios execution will generate a log file inside the folder `logs`

## Reporting

To generate the execution report execute, after running the test, the following command:

```bash
  ./gradlew allureServe
```

This command will open a report in your system default browser containing a detailed reportfor the execution, all faling
test will have attached a screenshot under the After step.

In order to end the command simply input ``TRL+C` in your terminal.