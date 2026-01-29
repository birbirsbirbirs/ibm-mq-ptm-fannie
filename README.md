```shell
export DOCKER_DEFAULT_PLATFORM=linux/amd64
```

```shell
docker compose up -d
```

```shell
docker compose down
```

* https://localhost:9443
* un: admin
* pwd: passw0rd

* publish Hero
```shell
postman request POST 'http://localhost:8080/hero' \
  --header 'Content-Type: application/json' \
  --body '{
    "name":"Jeanie",
    "power":"Investor Implementation Executive"
}'
```

* receive Hero
```shell
postman request 'http://localhost:8080/hero/hero-receive'
```


---
### work in progress
* this commit have following error