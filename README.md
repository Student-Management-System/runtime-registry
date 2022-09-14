# runtime registry

## Build

First run:
```
export GITHUB_USER=<user>
export GITHUB_PASSWORD=<password>
mvn -s mvn-settings.xml install
```

Later it is enough to run:
```
mvn package
```

To run the application you can download a jar from our [jenkins](https://jenkins-2.sse.uni-hildesheim.de/view/Teaching/job/Teaching_infrastructure-registry-service/) or run the jar inside the `target` directory. 
The default port is 8761.


Since this a spring-boot application, you can provide own properties. 