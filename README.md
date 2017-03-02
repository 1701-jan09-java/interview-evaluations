# interview evaluations microservice

TODO: description


# For the impatient

### From command line

```
mvn clean package -DskipTests=true && java -jar target/*.jar
```

### From IDE
* It's a Maven project, so it should easily import into any IDE
* We're using Spring Boot, so run the `main` method of `com.revature.InterviewEvaluationsApplication`

# For the curious - Build & Run Docker container

### Maven package

```
mvn clean package -DskipTests=true
```

### Build Docker image

```
docker build -t revature/interview-evaluations:0.1 .
```

### Run Docker container
* `--rm` removes container when it exits *(optional)*
* `-d` run container in background *(optional)*
* `-p 8080:8080` maps [**host port**]:[**container port**]
* `revature/interview-evaluations:0.1` is the [**image name**]:[**tag**]

```
docker run --rm -d -p 8080:8080 revature/interview-evaluations:0.1
```

If above failed, then run the following.  It passes in environment variables for the Postgres connection.
* Note you have to supply values for url, user, and pass.

```
docker run --rm -d -p 8080:8080 \
-e INTEVAL_URL=<someUrl> \
-e INTEVAL_USER=<someUser> \
-e INTEVAL_PASS=<somePass> \
revature/interview-evaluations:0.1
```

### List running containers

```
docker ps
```

### Make sure app is working

visit `http://localhost:8080/swagger-ui.html` to see swagger documentation
