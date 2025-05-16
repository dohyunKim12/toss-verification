# Toss 본인인증 서비스

[//]: # ([![Coverage: Classes 90%]&#40;https://img.shields.io/badge/Classes-90%25-brightgreen&#41;]&#40;#&#41;)

[//]: # ([![Coverage: Methods 86%]&#40;https://img.shields.io/badge/Methods-86%25-green&#41;]&#40;#&#41;)

[//]: # ([![Coverage: Lines 86%]&#40;https://img.shields.io/badge/Lines-86%25-green&#41;]&#40;#&#41;)

[//]: # ([![Coverage: Branches 71%]&#40;https://img.shields.io/badge/Branches-71%25-yellowgreen&#41;]&#40;#&#41;)

Toss api를 활용하여 본인인증을 하는 서비스의 prototype입니다.

---
## 🚀 How to Run the Application

### ▶️ **Default (H2)**

- By default, the application runs using an in-memory H2 database.
- Execute the following command without additional options:

```shell
./gradlew bootRun
```

### ▶️ **Production Mode (PostgreSQL)**

To run the application with PostgreSQL:

1. **Launch PostgreSQL using Docker Compose:**

```shell
docker compose -f asset/manifest/postgres/docker-compose.yml up -d
```

2. **Run the Spring Boot application with the `prod` profile activated:**

- Using Gradle command:

```shell
./gradlew bootRun --args='--spring.profiles.active=prod'
```

- Or add VM option in your IDE:

```
-Dspring.profiles.active=prod
```

---

## 📜 API Documentation

To view the API service lists, please visit the following link:
- [Swagger UI](http://localhost:8080/swagger-ui/index.html)

---

## 📁 Project Structure

```
tossverification
├── 📂asset
│   └── 📂manifest
│       └── 📂postgres
│           ├── 🐳 docker-compose.yml
│           └── 📂ddl
│                 └── 🛢️ schema.sql
├── 📂src
│   ├── 📂main
│   │   ├── 📂java
│   │   │   └── 📂com
│   │   │       └── 📂netmarble
│   │   │           └── 📂tossverification
│   │   │               ├── 📂config
│   │   │               ├── 📂controller
│   │   │               ├── 📂dto
│   │   │               ├── 📂entity
│   │   │               ├── 📂repository
│   │   │               ├── 📂service
│   │   │               └── 📂exception
│   │   └── 📂resources
│   │       ├── ⚙️ application.yml
│   │       ├── ⚙️ application-prod.yml
│   │       └── ⚙️ log4j2-spring.xml
│   └── 📂test
│       └── 📂java
│           └── 📂com
│               └── 📂netmarble
│                   └── 📂tossverification
└── 📜 build.gradle
```
