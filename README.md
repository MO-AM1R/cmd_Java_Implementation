## Table of Contents
1. [Introduction](#1-introduction)
2. [Supported Commands](#2-supported-commands)
   - [echo](#echo)
   - [pwd](#pwd)
   - [cd](#cd)
   - [ls](#ls)
   - [ls -r](#ls--r)
   - [mkdir](#mkdir)
   - [rmdir](#rmdir)
   - [touch](#touch)
   - [cp](#cp)
   - [cp -r](#cp--r)
   - [exit](#exit)
   - [rm](#rm)
   - [cat](#cat)
   - [wc](#wc)
   - [>](#)
   - [>>](#)
   - [history](#history)

## 1. Introduction
This Command Line Interface (CLI) application allows you to interact with your file system using a set of command line commands. It provides a simple and efficient way to perform various file and directory operations. Here, you will find information about the supported commands, their usage, and examples.

## 2. Supported Commands

### echo
- Usage: `echo <text>`
- Description: Takes 1 argument and prints it.

### pwd
- Usage: `pwd`
- Description: Takes no arguments and prints the current path.

### cd
- Usage:
   1. `cd`
   2. `cd ..`
   3. `cd <path>`
- Description:
   - `cd` takes no arguments and changes the current path to the path of your home directory.
   - `cd ..` changes the current directory to the previous directory.
   - `cd <path>` changes the current path to the provided path.

### ls
- Usage: `ls`
- Description: Takes no arguments and lists the contents of the current directory sorted alphabetically.

### ls -r
- Usage: `ls -r`
- Description: Takes no arguments and lists the contents of the current directory in reverse order.

### mkdir
- Usage: `mkdir <directory1> <directory2> ...`
- Description: Takes 1 or more arguments and creates a directory for each argument. Each argument can be a directory name or a path that ends with a directory name.

### rmdir
- Usage:
   1. `rmdir *`
   2. `rmdir <path>`
- Description:
   - `rmdir *` removes all empty directories in the current directory.
   - `rmdir <path>` removes the given directory if it is empty.

### touch
- Usage: `touch <file>`
- Description: Takes 1 argument, either a full path or a relative path that ends with a file name, and creates this file.

### cp
- Usage: `cp <sourceFile> <destinationFile>`
- Description: Takes 2 arguments, both are files, and copies the content of the first file onto the second.

### cp -r
- Usage: `cp -r <sourceDir> <destinationDir>`
- Description: Takes 2 arguments, both directories (empty or not), and copies the first directory with all its content into the second one.

### exit
- Usage: `exit`
- Description: Allows the CLI to terminate.

### rm
- Usage: `rm <file>`
- Description: Takes 1 argument, a file name that exists in the current directory, and removes this file.

### cat
- Usage:
   1. `cat <file>`
   2. `cat <file1> <file2>`
- Description:
   - `cat <file>` prints the content of the file.
   - `cat <file1> <file2>` concatenates the content of the two files and prints it.

### wc
- Usage: `wc <file>`
- Description: Counts the number of lines, words, and characters in the specified file.

### >
- Usage: `<command> > <FileName>`
- Description: Redirects the output of the first command to be written to a file. If the file doesn't exist, it will be created. If the file exists, its original content will be replaced.

### >>
- Usage: `<command> >> <FileName>`
- Description: Similar to `>`, but appends to the file if it exists.

### history
- Usage: `history`
- Description: Takes no parameters and displays an enumerated list with the commands you've used in the past.


### Prerequisites
1. Ensure you have Java Development Kit (JDK) installed on your system. You can download the latest version from the official Oracle website or choose an open-source alternative like OpenJDK.

2. Make sure you have Git installed on your system if you want to clone the application's repository.

### Installation Steps
1. **Clone the Repository** (Optional):
   - You can download the CLI application's source code from the repository's URL or clone it using Git. Open your terminal and use the following command:

   ```shell
   git clone <repository_url>
   ```

2. **Navigate to the Application Directory**:
   - Change your current directory to the folder where you cloned or downloaded the application (if you did this in the previous step).

   ```shell
   cd <application_folder>
   ```

3. **Compile the Java Code**:
   - Use the Java compiler (`javac`) to compile the Java source code files. This will create the executable Java classes.

   ```shell
   javac Main.java
   ```

4. **Run the CLI Application**:
   - Execute the compiled Java application using the `java` command. Replace `Main` with the name of the main class in your application.

   ```shell
   java Main
   ```

   Make sure that the class containing the `main` method is correctly specified.

5. **Start Using the CLI**:
   - Once the CLI application is running, you can start using the supported commands as described in the table of contents. Enter the command and its arguments in the terminal.

6. **Enjoy the CLI Application**:
   - You're now ready to use the CLI application to perform various file and directory operations in Java.
