#!/bin/bash

echo "======== Installing pre-commit hook... ======="

cp scripts/pre-commit .git/hooks/pre-commit
chmod +x .git/hooks/pre-commit

echo "======== pre-commit hook installed successfully! ======="
