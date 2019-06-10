pwd;
ls;

docker-compose up -d;

java -jar uploader/target/uploader-1.0-SNAPSHOT.jar -c config/local/config.yaml -f uploader/src/main/resources/letters-from-a-stoic.txt

java -jar rest/target/rest-1.0-SNAPSHOT.jar server config/local/config.yaml;