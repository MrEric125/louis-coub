echo off
setlocal enabledelayedexpansion

@REM #!/bin/bash
@REM # deploy-cluster.sh

echo "Building Java application with Maven..."
call mvn clean package -DskipTests
if !errorlevel! neq 0 (
    echo Maven package error！
    exit /b 1
)

echo "Building Docker images..."
docker-compose build
if !errorlevel! neq 0 (
    echo Docker Compose build error！
    exit /b 1
)

echo "Starting cluster..."
docker-compose up -d
if !errorlevel! neq 0 (
    echo "cluster start failed!"
    exit /b 1
)

echo "Waiting for services to start..."
timeout /t 10 /nobreak >nul

echo "Cluster status:"
docker-compose ps

echo "Testing nodes:"
for /f "delims=" %%i in ('curl -s http://localhost:8081/health') do (
    set "response=%%i"
    echo "response:" !response!
)

for /f "delims=" %%i in ('curl -s http://localhost:8082/health') do (
    set "response=%%i"
    echo "response" !response!
)
for /f "delims=" %%i in ('curl -s http://localhost:8083/health') do (
    set "response=%%i"
    echo "response" !response!
)
if !errorlevel! neq 0 (
    echo "interface check error"
    echo "show container logs"
    docker-compose logs
    exit /b 1
)
echo "interface check success"
pause