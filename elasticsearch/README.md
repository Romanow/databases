# Elastic

GUI: Kibana (запускается в docker)

|     Role      |     Address      |
|---------------|------------------|
| ElasticSearch | 9200, 9201, 9202 |
| Kibana        | 5601             |

## Внутреннее устройство

* `index` – (аналог базы данных в реляционной БД) каждый `index` имеет схему (`mapping`), которая определяет
  каждый `type` в индексе, плюс широкий ряд настроек индекса. Схема может быть задана явно или будет создана
  автоматически при индексации.
* `document` – (аналог строки в реляционной БД) это объект json, сохраненный в `index`, имеет `type` и
  идентификатор (`id`);
* `shards` – shard will contain a subset of an index data and is in itself fully functional and independent, and you can
  kind of think of a shard as an `independent index`. There are two main reasons why sharding is important, with the
  first one being that it allows you to split and thereby scale volumes of data. So if you have growing amounts of data,
  you will not face a bottleneck because you can always tweak the number of shards for a particular index. I will get
  back to how to specify the number of shards in just a moment. The other reason why sharding is important, is that
  operations can be distributed across multiple nodes and thereby parallelized.
* Put simply, shards are a single Lucene index. They are the building blocks of Elasticsearch and what facilitate its
  scalability. Index size is a common cause of Elasticsearch crashes. Since there is no limit to how many documents you
  can store on each index, an index may take up an amount of disk space that exceeds the limits of the hosting server.
  As soon as an index approaches this limit, indexing will begin to fail. One way to counter this problem is to split up
  indices horizontally into pieces called shards. This allows you to distribute operations across shards and nodes to
  improve performance. When you create an index, you can define how many shards you want. Each shard is an independent
  Lucene index that can be hosted anywhere in your cluster:
  ```http request
  PUT localhost:9200/example
  Content-Type: application/json
  
  {
    "settings" : {
      "index" : {
        "number_of_shards" : 2, 
        "number_of_replicas" : 1 
      }
    }
  }
  ```
  ![Shard](images/shard.png)
* `replicas` – количество реплик индекса, Elastic позволяет выполнять операции чтения с реплик.
* `routing` – определяет в каком шарде хранится документ.
* `analizers` – Analyzers are used during indexing to break down – or parse – phrases and expressions into their
  constituent terms. Defined within an index, an analyzer consists of a single tokenizer and any number of token
  filters. For example, a tokenizer could split a string into specifically defined terms when encountering a specific
  expression. By default, Elasticsearch will apply the Standard Analyzer, which contains a grammar-based tokenizer that
  removes common English words and applies additional filters. Elasticsearch comes bundled with a series of built-in
  tokenizers as well, and you can also use a custom tokenizer.

### Запуск кластера

Docker: [docker-compose.yml](docker/docker-compose.yml)

```shell
$ docker-compose up -d
```

### Примеры

```http request
POST /words/_search

{
  "query": {
    "match_all": {}
  },
  "sort": [
    {
      "counter": {
        "order": "desc"
      }
    }
  ],
  "size": 10
}
```

Информация об индексе

```http request
GET /_cat/words
```

## Ссылки

1. [Understanding Sharding in Elasticsearch](https://codingexplained.com/coding/elasticsearch/understanding-sharding-in-elasticsearch)
2. [10 Elasticsearch Concepts You Need to Learn](https://logz.io/blog/10-elasticsearch-concepts/)

#### Образы Docker

1. [Bitnami ElasticSearch](https://hub.docker.com/r/bitnami/elasticsearch)
1. [Bitnami Kibana](https://hub.docker.com/r/bitnami/kibana)