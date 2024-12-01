for i in $(seq -w 1 25); do
  mkdir "day$i"
  echo "day$i" > "day$i/day$i.txt"
  touch "day$i/input"
done
