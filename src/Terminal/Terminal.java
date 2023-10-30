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
        catch (IOException ioException){
            System.out.println("Something wrong happened");
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
            System.out.println(argument);
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
                        if (Files.isDirectory(child) && !Files.newDirectoryStream(child).iterator().hasNext()) {
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
     *This method {@code cp} will copy the content of the file
     *to another file in the same directory
     *</pre>
     *<blockquote>
     * @param args <strong style="color:'white'"> represent the file names or the directories of them</strong>
     *</blockquote>
     */
    public void cp(String[] args){
        try {
            if (args.length == 2){
                Path firstPath, secondPath ;

                if (Paths.get(args[0]).isAbsolute()){
                    firstPath = Paths.get(args[0]) ;
                }else{
                    firstPath = Paths.get(currentDirectory, args[0]) ;
                }
                if (Paths.get(args[1]).isAbsolute()){
                    secondPath = Paths.get(args[1]) ;
                } else{
                    secondPath = Paths.get(currentDirectory, args[1]) ;
                }

                if (Files.exists(firstPath) && Files.exists(secondPath)){
                    Files.copy(firstPath, secondPath, REPLACE_EXISTING) ;
                    return;
                }
            }
            System.out.println("file names incorrect");
        }
        catch (Exception exception){
            System.out.println("file names incorrect");
        }
    }

    /**<pre>
     *This method {@code copyDirectories} will copy the first file with all it's content
     *to the second file recursively
     *</pre>
     *<blockquote>
     * @param src <strong style="color:'white'"> represent the source file</strong>
     * @param dest <strong style="color:'white'"> represent the destination file</strong>
     *</blockquote>
     */
    private void copyDirectories(File src, File dest) {
        if (dest.isDirectory()){
            if (!dest.exists()) {
                if (!dest.mkdir()){
                    System.out.println("cannot copy the directories");
                    return;
                }
            }

            String[] list = src.list();
            assert list != null;

            for (String child :
                    list) {
                File srcChild = new File(src, child);
                File destChild = new File(dest, child);

                copyDirectories(srcChild, destChild);
            }
        }
        else{
            cp(new String[]{src.toPath().toString(), dest.toPath().toString()});
        }
    }

    /**<pre>
     *This method {@code cpR} will copy the first file with all it's content
     *to the second file
     *</pre>
     *<blockquote>
     * @param args <strong style="color:'white'"> represent the paths of the files</strong>
     *</blockquote>
     */
    public void cpR(String[] args){
        try {
            if (args.length == 2){
                Path firstPath, secondPath ;

                if (Paths.get(args[0]).isAbsolute()){
                    firstPath = Paths.get(args[0]) ;
                }else{
                    firstPath = Paths.get(currentDirectory, args[0]) ;
                }
                if (Paths.get(args[1]).isAbsolute()){
                    secondPath = Paths.get(args[1]) ;
                } else{
                    secondPath = Paths.get(currentDirectory, args[1]) ;
                }
                if (Files.isDirectory(firstPath) && Files.isDirectory(secondPath)) {
                    File destination = new File(Paths.get(secondPath.toString(), firstPath.getFileName().toString()).toString()) ;
                    if (!destination.exists()){
                        if (destination.mkdir()){
                            copyDirectories(new File(firstPath.toString()), destination) ;
                            return;
                        }
                    }
                }
            }
            System.out.println("directory names incorrect");
        }
        catch (Exception exception){
            System.out.println("directory names incorrect");
        }
    }

    /**<pre>
     *This method {@code rm} will remove file in the directory
     *</pre>
     *<blockquote>
     * @param args
     *          <strong style="color:'white'">it represent the file name</strong>
     *</blockquote>
     */
    public void rm(String[] args){
        if (args.length == 1){
            try{
                String argument = args[0];
                Path path = Paths.get(currentDirectory, argument);

                if (Files.exists(path)){
                    Files.delete(path);
                }
            }
            catch (Exception exception){
                System.out.println("file doesn't exist");
            }
        }
        else{
            System.out.println("incorrect command line");
        }
    }

    /**
     *<pre>
     *This method {@code cat} will take 1 argument and return the file’s content
     *</pre>
     * @param file <strong style="color:'white'"> represent the file which return content</strong>
     * @return String <strong style="color:'white'"> represent the file content</strong>
     * @throws FileNotFoundException <strong style="color:'white'"> if the file not exit</strong>
     */
    private String printFileContent(File file) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()){
            content.append(scanner.nextLine()).append('\n') ;
        }
        return content.toString();
    }

    /**
     *<pre>
     *This method {@code cat} will take 1 argument and prints the file’s content or takes 2
     *arguments and concatenates the content of the 2 files and prints it.
     *</pre>
     *<blockquote>
     * @param args <strong style="color:'white'"> represent the file name\s</strong>
     *</blockquote>
     */
    public void cat(String[] args){
        try {
            if (args.length == 1) {
                Path firstPath;
                if (Paths.get(args[0]).isAbsolute()) {
                    firstPath = Paths.get(args[0]);
                } else {
                    firstPath = Paths.get(currentDirectory, args[0]);
                }

                System.out.println(printFileContent(new File(firstPath.toString())));
            } else if (args.length == 2) {
                Path firstPath, secondPath;
                if (Paths.get(args[0]).isAbsolute()) {
                    firstPath = Paths.get(args[0]);
                } else {
                    firstPath = Paths.get(currentDirectory, args[0]);
                }
                if (Paths.get(args[1]).isAbsolute()) {
                    secondPath = Paths.get(args[1]);
                } else {
                    secondPath = Paths.get(currentDirectory, args[1]);
                }

                System.out.println(printFileContent(new File(firstPath.toString())) +
                        printFileContent(new File(secondPath.toString())));
            } else {
                System.out.println("incorrect command line");
            }
        }
        catch (Exception exception){
            System.out.println("files doesn't exist");
        }
    }

    /**<pre>
     *This method {@code wc} will print the number of lines, words, characters
     *and name in the file
     *</pre>
     *<blockquote>
     * @param args
     *          <strong style="color:'white'">the name of the file</strong>
     *</blockquote>
     */
    public void wc(String[] args){
        Path path = Paths.get(currentDirectory, args[0]) ;

        try {
            if (Files.exists(path)) {
                FileReader fileReader = new FileReader(path.toString());
                Scanner scanner = new Scanner(fileReader) ;
                int lines = 0, words = 0, character = 0;

                while (scanner.hasNextLine()){
                    String line = scanner.nextLine() ;
                    character += line.length() ;
                    words += line.split("\\s+").length ;
                    ++lines ;
                }
                System.out.println(lines + " " + words + " " + character + " " + path.getFileName().toString());
                fileReader.close();
            }
            else{
                System.out.println("the file doesn't exit");
            }
        }
        catch (Exception exception){
            System.out.println("the file doesn't exit");
        }
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
            case "cp" -> cp(parser.getArgs());
            case "cp -r" -> cpR(parser.getArgs());
            case "rm" -> rm(parser.getArgs());
            case "cat" -> cat(parser.getArgs());
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
