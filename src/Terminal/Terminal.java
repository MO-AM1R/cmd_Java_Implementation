package Terminal;
import Parser.Parser ;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 *<pre>
 *This class {@code Terminal} will handle the commands
 *and what each command will do
 *</pre>
 * <blockquote>
 * @version <strong style="color:'white'">1.3</strong>
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

    /**
     *<pre>
     *This setter {@code setParse} it set the parser to {@code parser}
     *</pre>
     * @param parser <strong style="color:'white'">the parser which will set</strong>
     */
    public void setParser(Parser parser){

    }

    /**
     *<pre>
     *This method {@code echo} will print the arguments {@code args}
     *</pre>
     * <blockquote>
     * @param args <strong style="color:'white'">the arguments which will print</strong>
     * @return string <strong style="color:'white'">the text which print</strong>
     * </blockquote>
     */
    public String echo(String[] args){
        return null;
    }

    /**
     *<pre>
     *This method {@code pwd} will get the current path
     *</pre>
     * <blockquote>
     *     @return string <strong style="color:'white'">represent the current path</strong>
     * </blockquote>
     */
    public String pwd(){
        return null;
    }

    /**
     *<pre>
     *This method {@code cd} will change the path to new one
     *</pre>
     * <blockquote>
     * @param args
     *      <p></p>
     *      -case 1: <strong style="color:'white'">no arguments and changes the current path
     *          to the path of your home directory.</strong>
     *        <p></p>
     *      -case 2: <strong style="color:'white'">1 argument which is “..” (e.g. cd ..)
     *        and changes the current directory to the previous directory.</strong>
     *        <p></p>
     *      -case 3: <strong style="color:'white'">1 argument which is either the full path or
     *      the relative (short) path and changes the current path to that path.</strong>
     *      *</blockquote>
     */

    public void cd(String[] args){

    }

    /**<pre>
     *This method {@code ls} will print the directories of mt directory
     *it return it sorted alphabetically
     *</pre>
     * <blockquote>
     * @return List of String <strong style="color: 'white'"> represent the directories</strong>
     * @throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     * </blockquote>
     */
    public List<String> ls() throws IOException {
        return null;
    }

    /**<pre>
     *This method {@code lsReversed} will print the directories of mt directory
     *it return it reversed
     *</pre>
     * <blockquote>
     * @return List of String <strong style="color: 'white'"> represent the directories</strong>
     * @throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     * </blockquote>
     */
    public List<String> lsReversed() throws IOException {
        return null;
    }

    /**<pre>
     *This method {@code mkdir} will creates a directory for each
     *argument
     *</pre>
     *<blockquote>
     *@param args
     *      <strong style="color:'white'">
     *          it can be relative or short path
     *          represent the directory which will create
     *      </strong>
     *</blockquote>
     */
    public void mkdir(String[] args){

    }

    /**<pre>
     *This method {@code rmdir} will remove the directory
     *if the directory is empty will remove it
     *</pre>
     *<blockquote>
     * @param args
     *          <strong style="color:'white'">
     *              it can be relative or short path
     *              represent the directory which will remove
     *              </strong>
     *@throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     *</blockquote>
     */
    public void rmdir(String[] args) throws IOException {

    }

    /**<pre>
     *This method {@code touch} will create a new file
     *in the passed directory
     *user can write a specific extension
     *</pre>
     *<blockquote>
     *@param args
     *      <strong style="color:'white'">
     *          it can be relative or short path
     *          represent the directory which will create into
     *          new file
     *      </strong>
     *@throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     *</blockquote>
     */
    public void touch(String[] args) throws IOException {

    }

    /**<pre>
     *This method {@code cp} will copy the content of the file
     *to another file in the same directory
     *</pre>
     *<blockquote>
     * @param args <strong style="color:'white'"> represent the file names or the directories of them</strong>
     * @throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     *</blockquote>
     */
    public void cp(String[] args) throws IOException {

    }

    /**<pre>
     *This method {@code cpR} will copy the first file with all it's content
     *to the second file
     *</pre>
     *<blockquote>
     * @param args <strong style="color:'white'"> represent the paths of the files</strong>
     * @throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     *</blockquote>
     */
    public void cpR(String[] args) throws IOException {

    }

    /**<pre>
     *This method {@code rm} will remove file in the directory
     *</pre>
     *<blockquote>
     * @param args <strong style="color:'white'">it represent the file name</strong>
     * @throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     *</blockquote>
     */
    public void rm(String[] args) throws IOException {

    }

    /**
     *<pre>
     *This method {@code cat} will take 1 argument and prints the file’s content or takes 2
     *arguments and concatenates the content of the 2 files and prints it.
     *</pre>
     *<blockquote>
     * @param args <strong style="color:'white'"> represent the file name\s</strong>
     * @throws FileNotFoundException <strong style="color:'white'"> if the file not exit</strong>
     *</blockquote>
     */
    public void cat(String[] args) throws FileNotFoundException {

    }

    /**<pre>
     *This method {@code wc} will print the number of lines, words, characters
     *and name in the file
     *</pre>
     *<blockquote>
     * @param args <strong style="color:'white'"> the name of the file</strong>
     * @throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     *</blockquote>
     */
    public void wc(String[] args) throws IOException {

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

    }

    /**
     *<pre>
     *This method {@code chooseCommandAction} will choose the suitable command method to be called
     *</pre>
     */
    public void chooseCommandAction(){
        try {
            if (!parser.parse(parser.getInput())) {
                System.out.println("Command not found.");
                return;
            }
            String commandName = parser.getCommandName();

            if (commandName.equals("echo")) {
                System.out.println(echo(parser.getArgs()));
            } else if (commandName.equals("pwd")) {
                System.out.println(pwd());
            } else if (commandName.equals("cd")) {
                cd(parser.getArgs());
            } else if (commandName.equals("ls")) {
                System.out.println(ls().toString().replaceAll(", ", "").
                        replaceAll("]", "").
                        replaceAll("\\[", ""));

            } else if (commandName.equals("ls -r")) {
                System.out.println(lsReversed().toString().replaceAll(", ", "").
                        replaceAll("]", "").
                        replaceAll("\\[", ""));

            } else if (commandName.equals("mkdir")) {
                mkdir(parser.getArgs());
            } else if (commandName.equals("rmdir")) {
                rmdir(parser.getArgs());
            } else if (commandName.equals("touch")) {
                touch(parser.getArgs());
            } else if (commandName.equals("cp")) {
                cp(parser.getArgs());
            } else if (commandName.equals("cp -r")) {
                cpR(parser.getArgs());
            } else if (commandName.equals("rm")) {
                rm(parser.getArgs());
            } else if (commandName.equals("cat")) {
                cat(parser.getArgs());
            } else if (commandName.equals("wc")) {
                wc(parser.getArgs());
            } else if (commandName.equals(">")) {
                redirect();
            } else if (commandName.equals(">>")) {
                redirectIfExist();
            } else if (commandName.equals("history")) {
                history();
            } else if (commandName.equals("exit")) {
                return;
            } else {
                System.out.println("Command not found.");
            }
        }
        catch (Exception exception){
            System.out.println("Incorrect using for teh command.");
        }
        commandsHistory.add(parser.getInput() + '\n') ;
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }
}
