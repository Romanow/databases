# MongoDB

## Внутреннее устройство

### GridFS

Instead of storing a file in a single document, GridFS divides the file into parts, or chunks [1], and stores each chunk
as a separate document. By default, GridFS uses a default chunk size of 255 kB; that is, GridFS divides a file into
chunks of 255 kB with the exception of the last chunk. GridFS uses two collections to store files. One collection stores
the file chunks, and the other stores file metadata.

## Пример

```shell
$ mongosh mongodb://localhost:27017,localhost:27018,localhost:27019/ -u program -p test

> rs.status()
> db.words.find().sort({counter:-1}).limit(10)
```

## Ссылки

1. [Deploying a MongoDB Cluster with Docker](https://www.mongodb.com/compatibility/deploying-a-mongodb-cluster-with-docker)
