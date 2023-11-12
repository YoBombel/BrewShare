#### Stage 1: Build the application
FROM azul/zulu-openjdk:17-latest

# Set the current working directory inside the image
WORKDIR /app

# Copy maven executable and pom.xml to the image
COPY .mvn .mvn
COPY mvnw pom.xml ./

# Build all the dependencies in preparation to go offline.
# This is a separate step so the dependencies will be cached unless
# the pom.xml file has changed.
RUN ./mvnw dependency:go-offline -B

# Copy the project source
COPY src ./src

# Package the application
RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

#### Stage 2: A minimal docker image with command to run the app
FROM azul/zulu-openjdk:17-jre-latest

ARG DEPENDENCY=/app/target/dependency

# Copy project dependencies from the build stage
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com/yobombel/brewshare/BrewShareApplication.java"]