This project contains basic setup for Automation test cases with Java, TestNG, POM.

Command for running the tests:
-    If you want to run with specific env and browser then user below command.
    mvn clean verify -DargLine="-DtestEnv=ppe -Dbrowser=firefox"
-    else
    mvn clean verify
    - Browser name can be chrome, firefox, edge.

For the Graph QL request We need to pass all the filter parameters, otherwise it will give error.
However, if we pass null value in filter it should not give error. But current used api is free one and we need to pass all the filters.

- Fill free to add comment or suggestion.
