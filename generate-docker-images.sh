#!/bin/bash
docker-compose down

docker image rm api-gateway-skma
docker build --tag=api-gateway-skma:latest ./api-gateway

docker image rm configuration-service-skma
docker build --tag=configuration-service-skma:latest ./configuration-service

docker image rm postgres-skma
docker build --tag=postgres-skma:latest ./postgres

docker image rm service-discovery-skma
docker build --tag=service-discovery-skma:latest ./service-discovery