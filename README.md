# rateCalculation
Standalone java app to rate calculation system.

## Frameworks implemented on this solution

1. JUnit (testing)
2. Log4j2 (Logs)

## Assumptions

- Only one calculation for execution
- Not allow concurrent execution
- I can get a part of available amount of loan to complete requested amount.

## Compile and run

when we execute the installation command the tests are run automatically

```
mvn clean install
java -jar target\rateCalculation-1.0-SNAPSHOT.jar MarketDataforExercise.csv 1000
```