# Postgres HA cluster with Patroni

## Пример

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

## Ссылки

1. [Управление высокодоступными PostgreSQL кластерами с помощью Patroni](https://habr.com/ru/post/504044/)
2. [Истории аварий с Patroni, или Как уронить PostgreSQL-кластер](https://habr.com/ru/company/oleg-bunin/blog/489206/)
