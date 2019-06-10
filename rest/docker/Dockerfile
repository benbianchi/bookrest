FROM maven:3.6.0-jdk-8 as build
# copy application WAR (with libraries inside)
WORKDIR /usr/src/app

COPY ./ ./

# specify default command
RUN mvn clean package -am -pl rest

ENTRYPOINT ["java", "-jar", "rest/target/rest-1.0-SNAPSHOT.jar", "server", "config/local/config.yaml"]
