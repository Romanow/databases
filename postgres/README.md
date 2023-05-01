# Postgres HA cluster with Patroni

GUI: [pgAdmin](https://www.pgadmin.org/)

| Role    | Address    |
|---------|------------|
| haproxy | 5000       |
| etcd    | 2379       |
| patroni | 5432, 8008 |

### Запуск кластера

Docker: [docker-compose.yml](docker/docker-compose.yml)

```shell
$ docker-compose up -d

$ docker exec -it patroni1 /bin/bash

$ patronictl --config-file /etc/patroni.yml topology cluster
+ Cluster: cluster -------+---------+---------+----+-----------+
| Member     | Host       | Role    | State   | TL | Lag in MB |
+------------+------------+---------+---------+----+-----------+
| patroni2   | 172.22.0.6 | Leader  | running |  1 |           |
| + patroni1 | 172.22.0.7 | Replica | running |  1 |         0 |
| + patroni3 | 172.22.0.5 | Replica | running |  1 |         0 |
+------------+------------+---------+---------+----+-----------+

$ psql -h localhost -p 5000 -U program -d holder
```

### Patroni

Patroni — это шаблон для построения Postgres HA cluster из коробки. Он устанавливается на каждом сервере с базой
данных и является своего рода init-системой для Postgres: запускает, останавливает, перезапускает, меняет конфигурацию и
топологию кластера.

Patroni хранит состояние кластера в DCS (Distributed Configuration Store), это может быть один из вариантов: Etcd,
Consul, ZooKeeper либо Etcd Kubernetes.

## Ссылки

1. [Управление высокодоступными PostgreSQL кластерами с помощью Patroni](https://habr.com/ru/post/504044/)
2. [Истории аварий с Patroni, или Как уронить PostgreSQL-кластер](https://habr.com/ru/company/oleg-bunin/blog/489206/)
3. Заряжай Patroni. Тестируем Patroni + Zookeeper
   кластер. [Часть 1](https://habr.com/ru/company/vsrobotics/blog/534828/), [Часть 2](https://habr.com/ru/company/vsrobotics/blog/534840/)

#### Образы Docker

1. [Bitnami Postgres](https://hub.docker.com/r/bitnami/postgres)
2. [Bitnami Haproxy](https://hub.docker.com/r/bitnami/haproxy)
3. [Bitnami ETCd](https://hub.docker.com/r/bitnami/etcd)