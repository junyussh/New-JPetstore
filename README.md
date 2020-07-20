# New JPetStore

This project is refactoring of [MyPetStore](https://github.com/junyussh/spring_project), a multi-seller pet store system composed by Java+Spring Boot.

Improving the project scaffold and using annotation instead XML configuration in MyBatis.

## Requirement

- Runtime environment: JDK 8
- Compiling tool: Maven

## Setup

### Prepare

Create a database named `jpetstore` and import `schema.sql`.

```bash
mysql -u root -p jpetstore < schema.sql
```

### Configuration

Spring's configuration file path is `/src/main/resources/application.yml`.

Here you can override the those values like the MySQL/MariaDB connection URL.

```yaml
server:
  port: 8081
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/jpetstore?useUnicode=true&characterEncoding=utf8&useSSL=false
logging:
  level:
    org:
      csu:
        jpetstore:
          dao: debug
```

### Build & Run

Sever listen `8081` port by default.

### **Linux**

```bash
$ ./mvnw clean install
$ ./mvnw spring-boot:run
```

**Windows**

```bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

Open http://127.0.0.1:8081/swagger-ui.html, you can see API documentation .

## Front-end

The frontend UI is in another [repository](https://github.com/junyussh/newpetstore_frontend).

## Acknowledgement

- Maven
- Spring Boot
- MyBatis
- Spring Security
- [Java JWT](https://github.com/jwtk/jjwt)
- Swagger

## License

The project is under [Apache License 2.0](./LICENSE).