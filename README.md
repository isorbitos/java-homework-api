
# Ignitis api

Ignitis homework REST API


## Swagger

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

TODO:
- need to add swagger configuration for authorization header


## H2

[http://localhost:8080/h2](http://localhost:8080/h2)
## Admin function

Get admin token:
- Postman
- http://localhost:8080/login
- POST
- body->raw-json:

```bash
  {
    "email":"admin@test.lt",
    "password":"123456"
}
```
Delete user:
- Headers -> Authorization : "Bearer "+"admin.token"
- GET
- http://localhost:8080/statistics/2
- DELETE
- http://localhost:8080/deleteuser/2


## Author

- [@Marius Pašiulevičius](https://www.linkedin.com/in/marius-pasiulevicius/)

