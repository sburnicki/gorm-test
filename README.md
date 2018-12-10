
This project is a minimal example showing the different behavior of GORM 6.1.6 and GORM >= 6.1.7 with new transactions.

See src/integration-test/groovy/grailsapp/IntegrationTestSpec.groovy

Simply run
```
./gradlew integrationTest
```
to see the failing test where service code is executed in a new transaction and doesn't find data if the session is not flushed explicitly before.

With GORM 6.1.6 all tests work are green:
```
git checkout 6_1_6
./gradlew integrationTest
```

