#!/bin/bash

# define the source and destination paths
src="/path/to/source/directory"
dest="/path/to/destination/directory"

# define the filename pattern
filename_pattern="input_????????_??????.txt"

# move the file that matches the filename pattern from source to destination
mv "$src/$filename_pattern" "$dest"
