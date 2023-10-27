package Terminal;
import Parser.Parser ;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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

    public String echo(String[] args){
        return null ;
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
        try{
            if (args.length == 0) {
                currentDirectory = System.getProperty("user.home");
            } else if (args.length == 1) {
                String argString = args[0];

                if (Objects.equals(argString, "..")) {
                    Path path = Paths.get(currentDirectory);
                    path = path.getParent();
                    if (path == null) {
                        System.out.println("you already at home");
                        return;
                    }
                    currentDirectory = path.toString();
                } else {
                    Path path = Paths.get(argString);

                    if (path.isAbsolute()) {
                        if (Files.exists(path) && Files.isDirectory(path)) {
                            currentDirectory = path.toString();
                        } else {
                            System.out.println("direction is invalid");
                        }
                    } else {
                        if (Files.exists(Paths.get(currentDirectory, argString)) &&
                                Files.isDirectory(Paths.get(currentDirectory, argString))) {
                            currentDirectory = Paths.get(currentDirectory, argString).
                                    normalize().toString();
                        } else {
                            System.out.println("direction is invalid");
                        }
                    }
                }
            } else {
                System.out.println("cd used incorrect");
            }
        }
        catch (InvalidPathException invalidPathException){
            System.out.println("cd used incorrect");
        }
    }

    /**<pre>
     *This method {@code ls} will print the history of commands
     *</pre>
     */
    public void ls(){
    }

    /**<pre>
     *This method {@code lsReversed} will print the history of commands reversed
     *</pre>
     */
    public void lsReversed(){
    }

    /**<pre>
     *This method {@code mkdir} will print the history of commands reversed
     *</pre>
     */
    public void mkdir(){

    }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void rmdir(){

    }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void touch(){

    }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void cp(){

    }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void cpR(){

    }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void rm(){

    }

    /**<pre>
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void cat(){

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
     *This method {@code history} will print the history of commands reversed
     *</pre>
     */
    public void history(){
        StringBuilder stringBuilder = new StringBuilder() ;
        for (String command :
                commandsHistory) {
            stringBuilder.append(command) ;
        }
        System.out.println(stringBuilder);
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
            ls();
        }
        else if (commandName.equals("ls-r")){
            lsReversed();
        }
        else if (commandName.equals("mkdir")){
            mkdir();
        }
        else if (commandName.equals("rmdir")){
            rmdir();
        }
        else if (commandName.equals("touch")){
            touch();
        }
        else if (commandName.equals("cp")){
            cp();
        }
        else if (commandName.equals("cp-r")){
            cpR();
        }
        else if (commandName.equals("rm")){
            rm();
        }
        else if (commandName.equals("cat")){
            cat();
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

        commandsHistory.add(parser.getInput() + '\n') ;
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }
}
