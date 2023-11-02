# Command Line Interface (CLI) Application Read Me

## Table of Contents
1. Introduction
2. Supported Commands
   - `echo`
   - `pwd`
   - `cd`
   - `ls`
   - `ls -r`
   - `mkdir`
   - `rmdir`
   - `touch`
   - `cp`
   - `cp -r`
   - `exit`
   - `rm`
   - `cat`
   - `wc`
   - `>`
   - `>>`
   - `history`

## 1. Introduction
This Command Line Interface (CLI) application allows you to interact with your file system using a set of command line commands. It provides a simple and efficient way to perform various file and directory operations. Here, you will find information about the supported commands, their usage, and examples.

## 2. Supported Commands

### `echo`
- Usage: `echo <text>`
- Description: Takes 1 argument and prints it.

### `pwd`
- Usage: `pwd`
- Description: Takes no arguments and prints the current path.

### `cd`
- Usage:
   1. `cd`
   2. `cd ..`
   3. `cd <path>`
- Description:
   - `cd` takes no arguments and changes the current path to the path of your home directory.
   - `cd ..` changes the current directory to the previous directory.
   - `cd <path>` changes the current path to the provided path.

### `ls`
- Usage: `ls`
- Description: Takes no arguments and lists the contents of the current directory sorted alphabetically.

### `ls -r`
- Usage: `ls -r`
- Description: Takes no arguments and lists the contents of the current directory in reverse order.

### `mkdir`
- Usage: `mkdir <directory1> <directory2> ...`
- Description: Takes 1 or more arguments and creates a directory for each argument. Each argument can be a directory name or a path that ends with a directory name.

### `rmdir`
- Usage:
   1. `rmdir *`
   2. `rmdir <path>`
- Description:
   - `rmdir *` removes all empty directories in the current directory.
   - `rmdir <path>` removes the given directory if it is empty.

### `touch`
- Usage: `touch <file>`
- Description: Takes 1 argument, either a full path or a relative path that ends with a file name, and creates this file.

### `cp`
- Usage: `cp <sourceFile> <destinationFile>`
- Description: Takes 2 arguments, both are files, and copies the content of the first file onto the second.

### `cp -r`
- Usage: `cp -r <sourceDir> <destinationDir>`
- Description: Takes 2 arguments, both directories (empty or not), and copies the first directory with all its content into the second one.

### `exit`
- Usage: `exit`
- Description: Allows the CLI to terminate.

### `rm`
- Usage: `rm <file>`
- Description: Takes 1 argument, a file name that exists in the current directory, and removes this file.

### `cat`
- Usage:
   1. `cat <file>`
   2. `cat <file1> <file2>`
- Description:
   - `cat <file>` prints the content of the file.
   - `cat <file1> <file2>` concatenates the content of the two files and prints it.

### `wc`
- Usage: `wc <file>`
- Description: Counts the number of lines, words, and characters in the specified file.

### `>`
- Usage: `<command> > <FileName>`
- Description: Redirects the output of the first command to be written to a file. If the file doesn't exist, it will be created. If the file exists, its original content will be replaced.

### `>>`
- Usage: `<command> >> <FileName>`
- Description: Similar to `>`, but appends to the file if it exists.

### `history`
- Usage: `history`
- Description: Takes no parameters and displays an enumerated list with the commands you've used in the past.

Please note that this CLI application handles various error cases and provides feedback when a command or parameter is invalid.
