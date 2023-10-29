package Terminal;
import Parser.Parser ;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 *<pre>
 *This class {@code Terminal} will handle the commands
 *and what each command will do
 *</pre>
 * <blockquote>
 * @version <strong style="color:'white'">1.0</strong>
 * @author <pre style="color:'white'">Malik Khaled
 *     Mohamed Amir
 *     </pre>
 * </blockquote>
 */
public class Terminal {
    /*Parser obj to parse the inputs*/
    Parser parser;
    String currentDirectory = System.getProperty("user.dir");
    List<String> commandsHistory = new LinkedList<>();

    public void setParser(Parser parser){
        this.parser = parser ;
    }




    /**
     *<pre>
     *This method {@code echo} will print the arguments
     *</pre>
     * @return string <strong style="color:'white'">represent the current path</strong>
     */

    public String echo(String[] args){
        StringBuilder output = new StringBuilder();
        for (String arg : args) {
            output.append(arg).append(" ");
        }
        return output.toString().trim();
    }

    /**
     *<pre>
     *This method {@code pwd} will get the current path
     *</pre>
     * @return string <strong style="color:'white'">represent the current path</strong>
     */
    public String pwd(){
        return currentDirectory;
    }

    /**
     *<pre>
     *This method {@code cd} will change the path to new one
     *  -case 1: <strong style="color:'white'">no arguments and changes the current path
     *  to the path of your home directory.</strong>
     *
     *  -case 2: <strong style="color:'white'">1 argument which is “..” (e.g. cd ..)
     *  and changes the current directory to the previous directory.</strong>
     *
     *  -case 3: <strong style="color:'white'">1 argument which is either the full path or
     *  the relative (short) path and changes the current path to that path.</strong>
     *</pre>
     */

    public void cd(String[] args){
        if (args.length == 0) {
            currentDirectory = System.getProperty("user.home");
        } else if (args.length == 1) {
            String target = args[0];
            target = target.replaceAll("\"", "") ;

            if (target.equals("..")) {
                Path path = Paths.get(currentDirectory);
                Path parent = path.getParent();
                if (parent != null) {
                    currentDirectory = parent.toString();
                } else {
                    System.out.println("Already at the root directory.");
                }
            } else {
                Path newPath = Paths.get(target);
                    if (newPath.isAbsolute()) {
                        if(Files.exists(newPath) && Files.isDirectory(newPath)) {
                            currentDirectory = newPath.toString();
                        }else{
                            System.out.println("Directory not found.");
                        }
                    } else{
                        if(Files.isDirectory(Paths.get(currentDirectory, target)) && Files.exists(Paths.get(currentDirectory, target))){
                        Path path = Paths.get(currentDirectory, target);
                        currentDirectory = path.toString();
                        }
                       else{
                            System.out.println("Directory not found.");
                        }
                    }
                }
        } else {
            System.out.println("Usage: cd directory path or ..");
        }
    }

    /**<pre>
     *This method {@code ls} will print the history of commands
     *</pre>
     */
    public List<String> ls(){
        List<String> pathList = new Vector<>();
        try {
            DirectoryStream<Path> file = Files.newDirectoryStream(Paths.get(currentDirectory));
            for (Path path : file) {
                pathList.add(path.getFileName().toString());
            }
        } catch (IOException e) {
            System.err.println("Error listing directory: " + currentDirectory);
        }
        return pathList;
    }

    /**<pre>
     *This method {@code lsReversed} will print the history of commands reversed
     *</pre>
     */
    public List<String> lsReversed(){
       return ls().reversed() ;
    }

    /**<pre>
     *This method {@code mkdir} will print the history of commands reversed
     *</pre>
     */
    public void mkdir(String... directories) {
        for (String dir : directories) {
            Path dirPath;
            if (dir.contains("/") || dir.contains("\\")) {
                dirPath = Paths.get(dir);
            } else {
                dirPath = Paths.get(currentDirectory, dir);
            }
            try {
                Files.createDirectories(dirPath);
                System.out.println("Created directory: " + dirPath);
            } catch (FileAlreadyExistsException e) {
                System.err.println("Directory already exists: " + dirPath);
            } catch (IOException e) {
                System.err.println("Error creating directory: " + dirPath);
            }
        }

    }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void rmdir(String... args) throws IOException {

        }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void touch(String... args){
        Path dirPath;
        if (args[0].contains("/") || args[0].contains("\\")) {
            dirPath = Paths.get(args[0]);
        } else {
            dirPath = Paths.get(currentDirectory, args);
        }
        try {
            Files.createFile(dirPath);
            System.out.println("Created file: " + dirPath);
        } catch (FileAlreadyExistsException e) {
            System.err.println("File already exists: " + dirPath);
        } catch (IOException e) {
            System.err.println("Error creating file: " + dirPath);
        }

    }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void cp(String... args){
        Path dirPath1, dirPath2;
        if(args[0].contains("/") || args[0].contains("\\")){
            dirPath1 = Paths.get(args[0]);
            dirPath2 = Paths.get(args[1]);
        } else {
            dirPath1 = Paths.get(currentDirectory, args[0]);
            dirPath2 = Paths.get(currentDirectory, args[1]);
        }
        try {

            Files.write(dirPath2,Files.readAllLines(dirPath1), StandardOpenOption.APPEND);
            System.out.println("Copied file: " + dirPath1 + " to " + dirPath2);
        } catch (FileAlreadyExistsException e) {
            System.err.println("File already exists: " + dirPath2);
        } catch (IOException e) {
            System.err.println("Error copying file: " + dirPath1);
        }
    }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void cpR(String... args){

    }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void rm(String... args){
        Path dirPath;
        dirPath = Paths.get(currentDirectory, args[0]);

        try {
            Files.delete(dirPath);
            System.out.println("Deleted file: " + dirPath);
        } catch (NoSuchFileException e){
            System.err.println("File doesn't exist: ");
        } catch (IOException e) {
            System.err.println("Error deleting file: ");
        }
    }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void cat(String... args){
        Path dirPath1;
        dirPath1 = Paths.get(currentDirectory, args[0]);
        if(args.length == 2){
            Path dirPath2;
            dirPath2 = Paths.get(currentDirectory, args[1]);
            try {
                List<String> lines = Files.readAllLines(dirPath1);
                for (String line : lines) {
                    System.out.println(line);
                }
                lines = Files.readAllLines(dirPath2);
                for (String line : lines) {
                    System.out.println(line);
                }
            } catch (NoSuchFileException e){
                System.err.println("File doesn't exist: ");
            } catch (IOException e) {
                System.err.println("Error reading file: ");
            }
        }
        else {
            try {
                List<String> lines = Files.readAllLines(dirPath1);
                for (String line : lines) {
                    System.out.println(line);
                }
            } catch (NoSuchFileException e) {
                System.err.println("File doesn't exist: ");
            } catch (IOException e) {
                System.err.println("Error reading file: ");
            }
        }

    }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void wc(){

    }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void redirect(){

    }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void redirectIfExist(){

    }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void history(){
        for (int i = 0; i < commandsHistory.size(); i++) {
            System.out.println(i + 1 + " " + commandsHistory.get(i));
        }
    }

    /**
     *<pre>
     *This method {@code chooseCommandAction} will choose the suitable command method to be called
     *</pre>
     */
    public void chooseCommandAction(){
        String commandName = parser.getCommandName() ;

        if (commandName.equals("echo")) {
            System.out.println(echo(parser.getArgs()));
        } else if (commandName.equals("pwd")) {
            System.out.println(pwd());
        } else if (commandName.equals("cd")) {
            cd(parser.getArgs());
        }
        else if (commandName.equals("ls")){
            for (String s : ls()) {
                System.out.println(s);
            }
        }
        else if (commandName.equals("ls -r")){
            for (String s : lsReversed()) {
                System.out.println(s);
            }
        }
        else if (commandName.equals("mkdir")){
            mkdir(parser.getArgs());
        }
        else if (commandName.equals("rmdir")){

        }
        else if (commandName.equals("touch")){
            touch(parser.getArgs());
        }
        else if (commandName.equals("cp")){
            cp(parser.getArgs());
        }
        else if (commandName.equals("cp -r")){
            cpR(parser.getArgs());
        }
        else if (commandName.equals("rm")){
            rm(parser.getArgs());
        }
        else if (commandName.equals("cat")){
            cat(parser.getArgs());
        }
        else if (commandName.equals("wc")){
            wc();
        }
        else if(commandName.equals(">")){
            redirect();
        }
        else if(commandName.equals(">>")){
            redirectIfExist();
        }
        else if(commandName.equals("history")){
            history();
        }
        else {
            System.out.println("Command not found.");
        }

        commandsHistory.add(parser.getInput()) ;
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }
}
