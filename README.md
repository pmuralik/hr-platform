# hr-platform
Steps to run the project in local 

1. run "MVN CLEAN INSTALL"
2. run " docker-compose up --build"
3. Invoke the APIs as per the swagger specification in src/main/resources/swagger.yaml
4. Invoke the APIs on localhost like http://127.0.0.1:8181/hr-platform/employee/1 , http://127.0.0.1:8181/hr-platform/employee , http://127.0.0.1:8181/hr-platform/employee/state-change
