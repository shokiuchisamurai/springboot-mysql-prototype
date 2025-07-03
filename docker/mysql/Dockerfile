FROM mysql:8.0.37-oraclelinux8

EXPOSE 3306

COPY ./my.cnf /etc/mysql/my.cnf

RUN chmod 644 /etc/mysql/my.cnf

COPY ./sql_init /docker-entrypoint-initdb.d
