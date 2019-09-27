gradle clean build &&
docker build . -t service:v1 &&
docker-compose up 