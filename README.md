``` Estructura del Proyecto ```
### my_project_docker ###
my_project_docker/
├── README.md
├── app-mi-agenda
│   ├── Dockerfile
│   ├── README.md
│   ├── dist
│   ├── index.html
│   ├── node_modules
│   ├── package-lock.json
│   ├── package.json
│   ├── public
│   ├── src
│   └── vite.config.js
├── db
│   ├── backup.sql
│   └── init.sql
├── demoApiAgenda
│   ├── Dockerfile
│   ├── HELP.md
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   ├── src
│   └── target
├── docker-compose.yml
└── docs
    ├── Instalacion-Docker-&-Implementacion-ServiceFullStack-Agenda-With-Docker.docx
    └── snap_code


### Descripción del Proyecto ###
Este proyecto está diseñado para implementar una aplicación completa de agenda utilizando Docker. La aplicación se compone de tres servicios principales: una base de datos PostgreSQL, una API backend construida con Spring Boot, y una aplicación frontend desarrollada con Vite.

 """ Contenido del archivo docker-compose.yml """
yaml
Copiar código
version: '3.9'
services:
  postgres_db:
    image: postgres:14
    container_name: postgres_db
    environment:
      POSTGRES_DB: db_agenda
      POSTGRES_USER: ${SPRING_DATASOURCE_USERNAME}
      POSTGRES_PASSWORD: ${SPRING_DATASOURCE_PASSWORD}
    volumes:
      - ./db/backup.sql:/docker-entrypoint-initdb.d/backup.sql
    ports:
      - "5432:5432"
    networks:
      - my_network

  api_service:
    build:
      context: ./demoApiAgenda
      dockerfile: Dockerfile
    container_name: api_service
    environment:
      SPRING_DATASOURCE_URL: ${SPRING_DATASOURCE_URL}
      SPRING_DATASOURCE_USERNAME: ${SPRING_DATASOURCE_USERNAME}
      SPRING_DATASOURCE_PASSWORD: ${SPRING_DATASOURCE_PASSWORD}
    depends_on:
      - postgres_db
    ports:
      - "9090:9090"
    networks:
      - my_network

  frontend_service:
    build:
      context: ./app-mi-agenda
      dockerfile: Dockerfile
    ports:
      - "5175:5175"
    depends_on:
      - api_service
    networks:
      - my_network

networks:
  my_network:
    driver: bridge

### Estructura de los Directorios ###

``` app-mi-agenda: Contiene la aplicación frontend desarrollada con Vite. ````

-> Dockerfile: Archivo Docker para construir la imagen de la aplicación frontend.
-> README.md: Documentación específica de la aplicación frontend.
-> dist: Archivos compilados de la aplicación.
-> index.html: Archivo HTML principal.
-> node_modules: Dependencias de Node.js.
-> package-lock.json: Archivo de bloqueo de dependencias de Node.js.
-> package.json: Archivo de configuración de dependencias de Node.js.
-> public: Archivos públicos de la aplicación.
-> src: Código fuente de la aplicación.
-> vite.config.js: Archivo de configuración de Vite.


```` db: Contiene archivos SQL para la base de datos. ````

-> backup.sql: Archivo SQL para inicializar la base de datos.
-> init.sql: Archivo SQL de respaldo.

```` demoApiAgenda: Contiene la aplicación backend desarrollada con Spring Boot. ````

-> Dockerfile: Archivo Docker para construir la imagen de la API backend.
-> HELP.md: Archivo de ayuda.(no creado)
-> mvnw, mvnw.cmd: Scripts para ejecutar Maven.
-> pom.xml: Archivo de configuración de Maven.
-> src: Código fuente de la aplicación backend.
-> target: Archivos compilados de la aplicación.

```` Archivo docker-compose ````

init.sql: Archivo SQL de respaldo.
-> docker-compose.yml: Archivo de configuración para Docker Compose.

```` docs: Contiene documentación del proyecto. (puedes ignorarlo) ````

-> Instalacion-Docker-&-Implementacion-ServiceFullStack-Agenda-With-Docker.docx: Documento de instalación y guía de implementación.
-> snap_code: Ejemplos de código y capturas de pantalla.

### Cómo Ejecutar el Proyecto ###

1.- Clona el repositorio.
2.- Crea un archivo .env en el directorio raíz con las siguientes variables de entorno:
 
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres_db:5432/db_agenda
SPRING_DATASOURCE_USERNAME=tu_usuario => postgres
SPRING_DATASOURCE_PASSWORD=tu_contraseña

3.- Ejecuta el comando docker-compose up en la terminal.
4.- Accede a la aplicación frontend en http://localhost:5175.
5.- La API backend estará disponible en http://localhost:9090.


### Contribuciones ###
Si deseas contribuir a este proyecto puedes mejorarlo a tu gusto , esto fue realizado con fines educativos


### Licencia ###
Este proyecto está bajo la Licencia MIT. Para más detalles, revisa el archivo LICENSE.


