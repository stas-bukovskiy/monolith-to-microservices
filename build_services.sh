#!/bin/bash

base_dir=$(pwd)
services="clerk-service product-service transaction-service warehouse-service"

for service in $services; do
    echo "Entering $service directory..."
    # shellcheck disable=SC2164
    cd "$service"
    echo "Cleaning and packaging $service..."
    mvn clean package spring-boot:repackage
    # shellcheck disable=SC2164
    cd "$base_dir"
    echo "Back to base directory."
    echo
done

echo "All services have been built and packaged."
