dev-api: build
	docker-compose stop -t 0 api
	docker-compose build api
	docker-compose up -d api

up: build
	docker-compose up -d

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
	rm -rf pgdata/

