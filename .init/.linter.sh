#!/bin/bash
cd /home/kavia/workspace/code-generation/product-management-api-34350-34369/products_api_backend
./gradlew checkstyleMain
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

