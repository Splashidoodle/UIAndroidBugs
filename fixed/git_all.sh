#!/bin/bash
folders=(
  "bug-71"
  "bug-271"
  "bug-275"
  "bug-1151"
  "bug-1205"
)

for folder in "${folders[@]}"; do
  if [ -d "$folder" ]; then
    cd "$folder" && git init
    echo "✓ Initialized git repo in $folder"
    cd ..
  else
    echo "✗ Directory not found: $folder"
  fi
done