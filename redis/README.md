# Redis

GUI: [Medis](https://github.com/luin/medis)

|   Role   |        Address      |
|----------|---------------------|
| Master   | 6379                |
| Slave    | 6380                |
| Sentinel | 26379, 26380, 26381 |

### Типы данных

* [Strings](https://redis.io/docs/data-types/strings/)
* [Lists](https://redis.io/docs/data-types/lists/)
* [Sets](https://redis.io/docs/data-types/sets/)
* [Streams](https://redis.io/docs/data-types/streams/)

### Запуск кластера

Docker: [redis.yml](docker/docker-compose.yml)

```shell
$ docker-compose up -d
```

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

```
$ echo "127.0.0.1    redis-master" | sudo tee -a /etc/hosts
127.0.0.1    redis-master
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
