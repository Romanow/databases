# MongoDB

GUI: [MongoDB Compass](https://www.mongodb.com/docs/compass/current/)

| Role   | Address      |
|--------|--------------|
| Master | 27017        |
| Slave  | 27018, 27019 |

### Запуск кластера

Docker: [docker-compose.yml](docker/docker-compose.yml)

```shell
$ docker-compose up -d
```

И на host машине прописать в `/etc/hosts`

```shell
$ sudo tee -a /etc/hosts > /dev/null <<EOT
127.0.0.1    mongo-primary
127.0.0.1    mongo-secondary-1
127.0.0.1    mongo-secondary-2
EOT
```

### Примеры

```
$ mongosh mongodb://localhost:27017,localhost:27018,localhost:27019/ -u program -p test

> rs.status()
> db.words.find().sort({counter:-1}).limit(10)
```

### Устройство

#### GridFS

Instead of storing a file in a single document, GridFS divides the file into parts, or chunks [1], and stores each chunk
as a separate document. By default, GridFS uses a default chunk size of 255 kB; that is, GridFS divides a file into
chunks of 255 kB with the exception of the last chunk. GridFS uses two collections to store files. One collection stores
the file chunks, and the other stores file metadata.

## Ссылки

1. [Deploying a MongoDB Cluster with Docker](https://www.mongodb.com/compatibility/deploying-a-mongodb-cluster-with-docker)

#### Образы Docker

1. [Bitnami MongoDB](https://hub.docker.com/r/bitnami/mongodb)
