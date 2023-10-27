package Terminal;
import Parser.Parser ;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
            currentDirectory = "C:\\Users\\Malik\\Desktop";
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
                    currentDirectory = newPath.toString();
                } else {
                    Path path = Paths.get(currentDirectory, target);
                    currentDirectory = path.toString();
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
     *This method {@code rmdir} will print the history of commands reversed
     *</pre>
     */
    public void history(){

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

        commandsHistory.add(parser.getInput()) ;
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }
}
