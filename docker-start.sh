#!/bin/bash

# Quick Docker Setup Script

echo "🐳 Docker Quick Start"
echo "===================="

# Step 1: Create .env.docker if it doesn't exist
if [ ! -f ".env.docker" ]; then
    echo "📝 Creating .env.docker from template..."
    cp .env.docker.example .env.docker
    echo "⚠️  Edit .env.docker with your actual values before running containers"
fi

# Step 2: Build JAR
echo ""
echo "🔨 Building JAR file..."
mvn clean package -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ JAR built successfully"
else
    echo "❌ Maven build failed"
    exit 1
fi

# Step 3: Build and run Docker
echo ""
echo "🚀 Starting Docker containers..."
docker-compose --env-file .env.docker up --build

echo ""
echo "✅ Done! App running at http://localhost:8080"
