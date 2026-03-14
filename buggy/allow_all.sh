ROOT_DIR="${1:-.}" # can run if you just provide the directory as well
for dir in "$ROOT_DIR"/*/; do
    if [ -f "$dir/gradlew" ]; then
        echo ">>> Setting permissions: $dir"
        cd "$dir"
        chmod +x ./gradlew
        cd -
        echo ""
    else
        echo ">>> Skipping $dir (no gradlew found)"
    fi
done
