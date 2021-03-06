# New JPetStore

This project is refactoring of [MyPetStore](https://github.com/junyussh/spring_project), a multi-seller pet store system composed by Java+Spring Boot.

Improving the project scaffold and using annotation instead XML configuration in MyBatis.

## Requirement

- Runtime environment: JRE 8, MySQL/MariaDB
- Building tool: Maven

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

## Build & Run

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

## Development

If you want to develop the project, you have to install JDK 8.

The project is developed with JetBrains IntelliJ IDEA. For best developing experience, I recommend you open the project with IDEA. IDEA will import all dependencies automatically then you can try to run.

## API Reference

The design of API interface follows RESTful style principle strictly. Our API design prototype is on [HackMD](https://hackmd.io/@cheer/ByzEIZG6I).

You can view the final API document generated by [redoc-cli](https://www.npmjs.com/package/redoc-cli).

https://junyussh.github.io/New-JPetstore/api.html

## Front-end

The frontend UI is built with [Nuxt.js](). The source code is in another [repository](https://github.com/junyussh/newpetstore_frontend).

## Built with

- [Maven](https://maven.apache.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [MyBatis 3](https://mybatis.org/)
- [Spring Security](https://spring.io/projects/spring-security)
- [Java JWT](https://github.com/jwtk/jjwt)
- [springfox-swagger2](https://springfox.github.io/springfox/docs/current/)
- [MariaDB](https://mariadb.org/)

## License

The project is under [Apache License 2.0](./LICENSE).
