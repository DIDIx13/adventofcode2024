#!/bin/bash

# Path to the README file
README_FILE="README.md"

# Header for the README file
echo "# Advent of Code 2024" > $README_FILE
echo "" >> $README_FILE
echo "This repository contains my solutions and inputs for [Advent of Code 2024](https://adventofcode.com/2024)." >> $README_FILE
echo "" >> $README_FILE
echo "## Days" >> $README_FILE
echo "" >> $README_FILE

# Generate links for all days
for i in $(seq 1 25); do
  DAY=$(printf "day%02d" "$i")
  echo "- [Day $i](./$DAY)" >> $README_FILE
done

echo "" >> $README_FILE

echo "README.md updated with links to all days!"

