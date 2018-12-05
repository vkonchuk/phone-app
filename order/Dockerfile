# base Docker image with Java 10
FROM openjdk:10.0.2-jdk-slim

# added a VOLUME pointing to "/tmp" because that is where a Spring Boot application creates
# working directories for Tomcat by default
VOLUME /tmp

# copy sources to the container
COPY . $HOME/order-service/tmp

# switch to project sources directory
WORKDIR $HOME/order-service/tmp

# build project using Gradle
RUN ./gradlew clean build && \
    # copy and rename jar file to have a common name so we do not depend on project version
    cp build/libs/order-*.jar ../order-service.jar

# switch to project directory
WORKDIR $HOME/order-service

# remove all temporary files and folders 
RUN rm -rf tmp && \
    # remove Gradle cache and other related data as it is not longer needed
    rm -rf /root/.gradle

# informs Docker that the container listens on the specified network ports at runtime
EXPOSE 8080

# start container by executing "java -jar" command
ENTRYPOINT ["java", "-jar", "order-service.jar"]