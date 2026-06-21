@echo off
REM Quick Docker Setup Script for Windows

echo 🐳 Docker Quick Start
echo ====================

REM Step 1: Create .env.docker if it doesn't exist
if not exist ".env.docker" (
    echo 📝 Creating .env.docker from template...
    copy .env.docker.example .env.docker
    echo ⚠️  Edit .env.docker with your actual values before running containers
)

REM Step 2: Build JAR
echo.
echo 🔨 Building JAR file...
call mvn clean package -DskipTests

if errorlevel 1 (
    echo ❌ Maven build failed
    pause
    exit /b 1
)

echo ✅ JAR built successfully

REM Step 3: Build and run Docker
echo.
echo 🚀 Starting Docker containers...
docker-compose --env-file .env.docker up --build

echo.
echo ✅ Done! App running at http://localhost:8080
pause
