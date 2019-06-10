mvn clean package;

docker-compose up;


java -jar rest/target/rest-1.0-SNAPSHOT.jar server config/local/config;