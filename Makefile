dev-api: build
	docker-compose stop -t 0 api
	docker-compose build api
	docker-compose up -d api
	
run-i: build
	docker-compose stop -t 0 api
	docker-compose build api
	docker-compose up api

up: build
	docker-compose up -d db
	sleep 10
	docker-compose up -d api
	
up-i: build
	docker-compose up db &
	sleep 10
	docker-compose up api

build:
	mvn clean package -DskipTests=true

test:
	mvn test

logs:
	docker-compose logs -f api

down:
	docker-compose down

clean: down
	mvn clean
	docker volume rm interviewevaluations_pgdata

