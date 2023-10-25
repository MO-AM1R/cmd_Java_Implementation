# Command Line Interface (CLI) Application

This repository contains a Java application implementing a Command Line Interface (CLI) with various commands for file and directory manipulation. Below are the available commands and their functionalities:

## Commands:

### 1. `echo`
Takes 1 argument and prints it.
Example: `echo Hello`

### 2. `pwd`
Takes no arguments and prints the current path.
Example: `pwd`

### 3. `cd`
- `cd`: Takes no arguments and changes the current path to the home directory.
- `cd ..`: Changes the current directory to the previous directory.
- `cd <path>`: Changes the current path to the specified path.
Examples: 
  - `cd`
  - `cd ..`
  - `cd /path/to/directory`

### 4. `ls`
- `ls`: Takes no arguments and lists the contents of the current directory sorted alphabetically.
- `ls -r`: Takes no arguments and lists the contents of the current directory in reverse order.
Examples:
  - `ls`
  - `ls -r`

### 5. `mkdir`
Takes 1 or more arguments and creates a directory for each argument.
Examples:
  - `mkdir directory_name`
  - `mkdir /path/to/new_directory`

### 6. `rmdir`
- `rmdir *`: Removes all empty directories in the current directory.
- `rmdir <path>`: Removes the given directory only if it is empty.
Examples:
  - `rmdir *`
  - `rmdir /path/to/directory`

### 7. `touch`
Takes 1 argument which is a file name and creates this file.
Example: `touch file.txt`

### 8. `cp`
- `cp <source_file> <destination_file>`: Copies the source file onto the destination file.
- `cp -r <source_directory> <destination_directory>`: Copies the source directory (with all its content) into the destination directory.
Examples:
  - `cp file1.txt file2.txt`
  - `cp -r directory1 directory2`

### 9. `rm`
Takes 1 argument which is a file name that exists in the current directory and removes this file.
Example: `rm file.txt`

### 10. `cat`
- `cat <file>`: Prints the file’s content.
- `cat <file1> <file2>`: Concatenates the content of the two files and prints it.
Examples:
  - `cat file.txt`
  - `cat file1.txt file2.txt`

### 11. `wc`
Takes 1 argument and displays the number of lines, words, and characters in the file.
Example: `wc file.txt`
Output: `9 79 483 file.txt`

### 12. Redirection
- `command > FileName`: Redirects the output of the command to be written to a file. If the file doesn’t exist, it will be created. If the file exists, its original content will be replaced.
Example: `echo Hello World > myfile.txt`

- `command >> FileName`: Appends the output of the command to the file if it exists.

### 13. `history`
Takes no parameters and displays an enumerated list with the commands used in the past.
Example: `history`
Output:
```
1 ls
2 mkdir tutorial
3 history
```

## Notes:
- Implement the `exit` command to allow the CLI to terminate.
- Handle incorrect commands or bad parameters by printing error messages without terminating.
- Refer to the lab document for further information on the commands.

## How to Run:
To run the application, navigate to the `src/main` directory and execute the Java application.

Example:
```
cd src/main
javac Main.java
java Main
```

Feel free to reach out if you have any questions or need further assistance!
