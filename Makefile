CONTAINER_ID = $(shell docker ps -aq --filter "name=inteval-dev")

run: build stop
	docker run --rm -d \
	--name inteval-dev \
	-p 8080:8080 \
	-e INTEVAL_URL=$(INTEVAL_URL) \
	-e INTEVAL_USER=$(INTEVAL_USER) \
	-e INTEVAL_PASS=$(INTEVAL_PASS) \
	revature/interview-evaluations:0.1

stop:
	docker rm -vf $(CONTAINER_ID) || true

build:
	mvn clean package -DskipTests=true
	docker build -t revature/interview-evaluations:0.1 .
	
test:
	mvn test

init:
	. src/main/resources/SQL/development/bash/pg_evaluations_install.sh 
