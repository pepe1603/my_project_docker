
# Dockerfile para demoApiAgenda (Build proyectjar)
FROM openjdk:17-jdk-alpine AS build

#crear espacio de trabajo /app
WORKDIR /app

#Copiar todoos los archivos de configuracion y otros a la rutas raiz de nuestro proyecto
COPY . .

#corremos contruimos el jar de la API saltando los test de ser necesario
RUN ./mvnw clean package -DskipTests

#etapa de produccion (Produccion Stage)
FROM openjdk:17-jdk-alpine
WORKDIR /app

#copiaremos elñ Jar generado anteriomente a la ruta rtaiz de /app
COPY --from=build /app/target/demoAgenda-0.0.1-SNAPSHOT.jar demoApiAgenda.jar
#exponemos a escucha el puerto 9090 de  nuestar API
EXPOSE 9090
#corremos el jar en produccion
ENTRYPOINT ["java", "-jar", "demoApiAgenda.jar"]

