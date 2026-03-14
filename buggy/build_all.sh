# You can search >>> on terminal to see all instances of each repo being built.
# Haven't saved build outputs to a file yet, it's a TODO.

ROOT_DIR="${1:-.}" 
for dir in "$ROOT_DIR"/*/; do
    if [ -f "$dir/gradlew" ]; then
        echo ">>> Building: $dir"
        cd "$dir"
        ./gradlew clean assembleDebug --no-daemon
        cd -
        echo ""
    else
        echo ">>> Skipping $dir (no gradlew found)"
    fi
done
