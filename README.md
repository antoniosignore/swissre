# Swissre Inteview code tests

## Installation

This project requires JDK 8 and maven for building and testing it.

Install Java on linux using this command:

    sudo apt-get install openjdk-8-jdk

## Install maven

    sudo apt-get install maven

## Build

    mvn clean package

## Test

    mvn clean test

## Design

The application is composed of 3 components :

    1. CLI Client command line interface
       Interact with the user by command line

    2. Portfolio service
       Build the portfolio which is an ArrayList of PortfolioEntry

    3. Quotes Service:
       Retrieve live quotes from the open Internet service

### Running the Command Line Client

The CLI can be started by typing at terminal executing a self contained jar executable (built by maven):

    java -jar target/portfolio-1.0-SNAPSHOT.jar

At the prompt digit:

    src/test/resources/bob_crypto.txt

If successfull you should read at terminal

    Enter your portfolio filename  :src/test/resources/bob_crypto.txt
    Total portfolio value:73856.4500
    ticker='BTC', amount=10, value=72833.10
    ticker='ETH', amount=5, value=643.15
    ticker='XRP', amount=2000, value=380.2000

### unit test code coverage

Coverage is execute by Jacoco. The code coverage is not too bad.

    target/jacoco-report/index.html

# Limitation

The portfolio is an ArratList and we are assuming that the portfolio has distinct tickers.

