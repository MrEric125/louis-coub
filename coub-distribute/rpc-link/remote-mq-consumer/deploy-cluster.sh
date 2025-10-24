#!/bin/bash
# deploy-cluster.sh

echo "Building Java application with Maven..."
mvn clean package -DskipTests

echo "Building Docker images..."
docker-compose build

echo "Starting cluster..."
docker-compose up -d

echo "Waiting for services to start..."
sleep 30

echo "Cluster status:"
docker-compose ps

echo "Testing nodes:"
echo "Node 1: $(curl -s http://localhost:8081/health)"
echo "Node 2: $(curl -s http://localhost:8082/health)"
echo "Node 3: $(curl -s http://localhost:8083/health)"