package Terminal;
import Parser.Parser ;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 *<pre>
 *This class {@code Terminal} will handle the commands
 *and what each command will do
 *</pre>
 * <blockquote>
 * @version <strong style="color:'white'">1.1</strong>
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
        this.parser = parser ;
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
        StringBuilder ans = new StringBuilder();
        for (String argument :
                args) {
            ans.append(argument).append(' ');
        }

        return ans.toString();
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
        return currentDirectory;
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
     *This method {@code ls} will print the directories of mt directory
     *it return it sorted alphabetically
     *</pre>
     * <blockquote>
     * @return List of String <strong style="color: 'white'"> represent the directories</strong>
     * </blockquote>
     */
    public List<String> ls(){
        try {
            List<String> directories = new Vector<>();
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(currentDirectory));

            for (Path child :
                    directoryStream) {
                directories.add(child.getFileName().toString() + '\n');
            }

            return directories;
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**<pre>
     *This method {@code lsReversed} will print the directories of mt directory
     *it return it reversed
     *</pre>
     * <blockquote>
     * @return List of String <strong style="color: 'white'"> represent the directories</strong>
     * </blockquote>
     */
    public List<String> lsReversed(){
        // reverse the list not the text
        return ls().reversed();
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
        StringBuilder errors = new StringBuilder() ;

        for (String argument :
                args) {
            Path path = Paths.get(argument) ;

            if (path.isAbsolute()){
                File directory = new File(path.toString()) ;
                if (!directory.mkdir()){
                    errors.append("the directory ").append(directory).append(" is not created\n") ;
                }
            }
            else{
                path = Paths.get(currentDirectory, argument).normalize() ;

                File directory = new File(path.toString()) ;
                if (!directory.mkdir()){
                    errors.append("the directory ").append(directory).append(" is not created\n") ;
                }
            }
        }
        if (!errors.toString().isEmpty()){
            System.out.print(errors);
        }
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
     *</blockquote>
     */
    public void rmdir(String[] args){
        if (args.length == 1){
            String argument = args[0] ;

            try{
                if (argument.equals("*")) {
                    DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(currentDirectory));
                    for (Path child : directoryStream) {
                        if (!Files.newDirectoryStream(child).iterator().hasNext()) {
                            Files.delete(child);
                        }
                    }

                    directoryStream.close();
                } else {
                    Path path = Paths.get(argument);
                    if (!path.isAbsolute()) {
                        path = Paths.get(currentDirectory, argument).normalize();
                    }

                    DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
                    if (!directoryStream.iterator().hasNext()) {
                        Files.delete(path);
                    }

                    directoryStream.close();
                }
            }
            catch (Exception e){
                System.out.println("cannot find the directory");
            }
        }
        else{
            System.out.println("incorrect command line");
        }
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
     *</blockquote>
     */
    public void touch(String[] args){
        if (args.length == 1){
            try{
                String argument = args[0];
                Path path = Paths.get(argument);

                if (!path.isAbsolute()) {
                    path = Paths.get(currentDirectory, argument);
                }
                final File file = new File(path.toString());

                if (!file.createNewFile()) {
                    System.out.println("file not created");
                }
            }
            catch (IOException ioException){
                System.out.println("file not created");
            }
        }
        else{
            System.out.println("incorrect command line");
        }
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
    public void wc(String[] args){

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
        if(!parser.parse(parser.getInput())){
            System.out.println("Command not found.");
            return;
        }
        String commandName = parser.getCommandName() ;

        switch (commandName) {
            case "echo" -> System.out.println(echo(parser.getArgs()));
            case "pwd" -> System.out.println(pwd());
            case "cd" -> cd(parser.getArgs());
            case "ls" -> System.out.println(ls().toString().replaceAll(", ", "").
                    replaceAll("]", "").
                    replaceAll("\\[", ""));

            case "ls -r" -> System.out.println(lsReversed().toString().replaceAll(", ", "").
                    replaceAll("]", "").
                    replaceAll("\\[", ""));

            case "mkdir" -> mkdir(parser.getArgs());
            case "rmdir" -> rmdir(parser.getArgs());
            case "touch" -> touch(parser.getArgs());
            case "cp" -> cp();
            case "cp-r" -> cpR();
            case "rm" -> rm();
            case "cat" -> cat();
            case "wc" -> wc(parser.getArgs());
            case ">" -> redirect();
            case ">>" -> redirectIfExist();
            case "history" -> history();
            case "exit" -> {
                return;
            }
            default -> System.out.println("Command not found.");
        }

        commandsHistory.add(parser.getInput() + '\n') ;
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }
}
