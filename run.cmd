call ./gradlew clean build

docker-compose up -d db adminer
docker-compose stop app
docker-compose rm -f app
docker-compose build
docker-compose up app