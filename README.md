# skt exercise

## Prerequisites

Before you begin, ensure you have installed following:
* Java 8
* Maven 3
* Docker

## Installing <project_name>

To install *skt example*:

```
git clone https://github.com/isiasns/skt.git
docker-compose -f ./skt/docker-compose.yml up -d
mvn -f ./skt/common-library/pom.xml install
```

## Contact

If you want to contact me you can reach me at isias@nearsoft.com.