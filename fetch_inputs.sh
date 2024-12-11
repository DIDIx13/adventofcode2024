#!/bin/bash

BASE_URL="https://adventofcode.com/2024/day"

SESSION_COOKIE="53616c7465645f5fba6739063776e5244c7efdde2b303d5484963281dedd571915a8d2724b6862d87b2e8246d115ebfc5078a398b273c61dfc2f31b18417d29c"

# Loop through days 1 to 25
for i in $(seq 1 25); do
  # Format day number
  DAY=$(printf "%d" "$i")
  DIR_NAME=$(printf "day%02d" "$i")

  # URL for the day's input
  URL="$BASE_URL/$DAY/input"

  # Create the directory if it doesn't exist
  mkdir -p "$DIR_NAME"

  # Path for the input file
  OUTPUT_FILE="$DIR_NAME/input"

  # Use curl to fetch the input and capture the HTTP status
  HTTP_STATUS=$(curl -s -w "%{http_code}" -b "session=$SESSION_COOKIE" -o "$OUTPUT_FILE" "$URL")

  # Check if the HTTP status indicates success
  if [[ "$HTTP_STATUS" -eq 200 ]]; then
    echo "Input downloaded for $DIR_NAME from $URL"
  elif [[ "$HTTP_STATUS" -eq 404 ]]; then
    echo "Input not yet available for $DIR_NAME"
  else
    echo "Failed to fetch input for $DIR_NAME (HTTP status: $HTTP_STATUS)"
  fi
done

