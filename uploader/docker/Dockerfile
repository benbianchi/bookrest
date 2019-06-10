FROM maven:3.6.0-jdk-8 as build
# copy application WAR (with libraries inside)
WORKDIR /usr/src/app

COPY ./ ./

EXPOSE 3000

# specify default command
RUN mvn clean package -am -pl uploader

ENTRYPOINT ["java", "-jar", "uploader/target/uploader-1.0-SNAPSHOT.jar", "-c", "config/local/config.yaml", "-f", "uploader/src/main/resources/letters-from-a-stoic.txt"]
