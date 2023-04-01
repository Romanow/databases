FROM postgres:15

RUN apt-get update -y \
    && apt-get install python3 python3-pip -y \
    && pip3 install --upgrade setuptools \
    && pip3 install psycopg2-binary \
    && pip3 install patroni[etcd] \
    && mkdir -p /data/patroni \
    && chown -R postgres:postgres /data/patroni \
    && chmod 700 /data/patroni \
    && mkdir -p /usr/local/bin/scripts \
    && chown -R postgres:postgres /usr/local/bin/scripts \
    && chmod 755 /usr/local/bin/scripts

COPY patroni.yml /etc/patroni.yml
COPY docker-entrypoint.sh ./docker-entrypoint.sh

USER postgres

ENTRYPOINT ["bin/sh", "/docker-entrypoint.sh"]