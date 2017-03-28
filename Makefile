dev-api: build
	docker-compose stop -t 0 api
	docker-compose build api
	docker-compose up -d api
	
dev-api-i: build
	docker-compose stop -t 0 api
	docker-compose build api
	docker-compose up api

up: build
	docker-compose up -d
	
up-i: build
	docker-compose up

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

