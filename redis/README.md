# Redis

GUI: [Medis](https://github.com/luin/medis)

|   Role   |        Address      |
|----------|---------------------|
| Master   | 6379                |
| Slave    | 6380, 6381          |
| Sentinel | 26379, 26380, 26381 |

### Типы данных

* [Strings](https://redis.io/docs/data-types/strings/)
* [Lists](https://redis.io/docs/data-types/lists/)
* [Sets](https://redis.io/docs/data-types/sets/)
* [Streams](https://redis.io/docs/data-types/streams/)

### Запуск кластера

Docker: [docker-compose.yml](docker/docker-compose.yml)

```shell
$ docker-compose up -d
```

### Особенности запуска

Для того чтобы Redis поднять в Sentinel внутри Docker нужно чтобы в `sentinel.conf` было прописано:

```
# Normally Sentinel uses only IP addresses and requires SENTINEL MONITOR
# to specify an IP address. Also, it requires the Redis replica-announce-ip
# keyword to specify only IP addresses.
#
# You may enable hostnames support by enabling resolve-hostnames. Note
# that you must make sure your DNS is configured properly and that DNS
# resolution does not introduce very long delays.
#
sentinel resolve-hostnames yes

# When resolve-hostnames is enabled, Sentinel still uses IP addresses
# when exposing instances to users, configuration files, etc. If you want
# to retain the hostnames when announced, enable announce-hostnames below.
#
sentinel announce-hostnames yes
```

При запуске будет строка:

```log
slave slave 192.168.224.3:6379 192.168.224.3 6379 @ mymaster redis-master 6379
```

И на host машине прописать в `/etc/hosts`

```shell
$ sudo tee -a /etc/hosts > /dev/null <<EOT
127.0.0.1    redis-master
127.0.0.1    redis-slave-1
127.0.0.1    redis-slave-2
EOT
```

Без этого Sentinel отдает внутренний ip адрес master ноды и при старте приложения возникает ошибка:

```log
org.springframework.data.redis.RedisConnectionFailureException: Unable to connect to Redis; nested exception is io.lettuce.core.RedisConnectionException: Unable to connect to 192.168.80.2:6379
	at org.springframework.data.redis.connection.lettuce
```

Все redis-sentinel описываются руками, потому что `docker compose up -d --scale redis-sentinel=3` приводит к ошибке:

```
Error response from daemon: Ports are not available: exposing port TCP 0.0.0.0:26379 -> 0.0.0.0:0: listen tcp 0.0.0.0:26379: bind: address already in use
```

### Пример

```
eval "
  local order = redis.call('hkeys', KEYS[1]);
  return redis.call('sadd', KEYS[2], unpack(order));
" 2 hset:books list:words

sort list:words limit 0 50 alpha desc

sort list:words by hset:books->* limit 0 50

hget hset:books address
```

## Ссылки

1. [High availability with Redis Sentinel](https://redis.io/docs/manual/sentinel/)
2. [Sentinel client spec](https://redis.io/docs/reference/sentinel-clients/)
3. [Разбираемся с Redis](https://habr.com/ru/company/wunderfund/blog/685894/)

#### Образы Docker

1. [Bitnami Redis](https://hub.docker.com/r/bitnami/redis)
1. [Bitnami Redis Sentinel](https://hub.docker.com/r/bitnami/redis-sentinel)