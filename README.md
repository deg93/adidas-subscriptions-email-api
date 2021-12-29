# Adidas Tech Challenge / Email API

This repository contains the microservice whose objective is to send the emails from the "subscriptions system". This microservice uses SendGrid service in order to deliver emails to subscribed users.

## API services

This microservice only exposes one endpoint:

- `POST /api/emails`: this endpoint receives a JSON describing the email to send.

This endpoint is secured with basic authentication.

API documentation (Swagger UI) can be found [here](https://email-api.adidas-tech-challenge.davidenjuan.es/swagger-ui/#/email-resource) for AWS environment (in a real environment, this API should not be exposed to Internet). In local environment, API docs are served in [http://localhost:8082/swagger-ui/](http://localhost:8082/swagger-ui/).

## Deployment

For deploying this microservice to AWS environment, is enough with pushing changes to main branch. This is done by [deploy-to-aws.yml](.github/workflows/deploy-to-aws.yml) GitHub workflow (pipeline).

For deploying locally, run the following command in the project root directory:
```
mvn spring-boot:run
```
Please, note that in development environment (`dev` Spring Boot profile), any email will be sent.