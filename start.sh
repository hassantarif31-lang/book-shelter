#!/bin/bash
set -e  # Stop on error

echo "=== Starting application ==="
echo "Current directory: $(pwd)"

# Show what's in build/libs
echo "Contents of build/libs:"
ls -la build/libs/ || echo "build/libs/ not found!"

# Find the JAR file
JAR_FILE=$(find build/libs -name "*.jar" | head -1)
echo "JAR file found: $JAR_FILE"

if [ -z "$JAR_FILE" ]; then
    echo "ERROR: No JAR file found!"
    exit 1
fi

# Run the JAR
echo "Running: java -jar $JAR_FILE"
exec java -jar "$JAR_FILE"