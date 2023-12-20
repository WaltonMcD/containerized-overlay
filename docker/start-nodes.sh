#!/bin/bash

NUM_NODES = $1

for i in $NUM_NODES do
    java -jar /app/containerized-overlay.jar node 6200 localhost
done