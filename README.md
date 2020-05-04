# New JPetStore

This project is refactoring of [MyPetStore](https://github.com/junyussh/spring_project).

Improving the project scaffold and using annotation instead XML configuration in Mybatis.

## Requirement

- Runtime environment: JDK 8
- Compiling tool: Maven

## Build & Run

Sever listen `8081` port by default.
 
### Linux

```bash
$ ./mvnw clean install
$ ./mvnw spring-boot:run
```

### Windows

```bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

## Configuration

Spring's configuration file path is `/src/main/resources/application.yml`.

Here you can override the those values like the MySQL/MariaDB connection URL.

## Acknowledgement

- Maven
- Spring Boot
- MyBatis
- Spring Security
- Swagger

## License

The project is under [Apache License 2.0](./LICENSE).