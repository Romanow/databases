# Elastic

GUI: [Kibana]()

|   Role   |   Address   |
|----------|-------------|
| Master   | 27017       |
| Slave    | 27117, 2721 |

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

## Ссылки

#### Образы Docker