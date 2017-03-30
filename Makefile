dev: build
	docker-compose up -d api
	
dev-i: build
	docker-compose up api

up: build
	docker-compose up -d
	
up-i: build
	docker-compose up

build: build-maven
	docker-compose stop -t 0 api
	docker-compose build api

build-maven:
	mvn clean package -DskipTests=true

test:
	mvn test

logs:
	docker-compose logs -f api
	
logs-db:
	docker-compose logs -f db

logs-all:
	docker-compose logs -f

down:
	docker-compose down

clean: down
	mvn clean
	docker volume rm interviewevaluations_pgdata

