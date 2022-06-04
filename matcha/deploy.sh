mvn clean install -DskipTests
docker build . --tag matcha:latest
docker-compose -f docker-compose.yml up -d