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
     *This method {@code getCurrentDirectory} return {@code currentDirectory}
     *</pre>
     *<blockquote>
     * @return String <strong style="color:'white'"> represent the current directory</strong>
     *</blockquote>
     */
    public String getCurrentDirectory() {
        return currentDirectory;
    }

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
     * @param args <strong style="color:'white'"> the arguments which will print</strong>
     * @return string <strong style="color:'white'"> the text which print</strong>
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
     *     @return string <strong style="color:'white'"> represent the current path</strong>
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
        List<String> directories = new Vector<>();
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(currentDirectory));

        for (Path child :
                directoryStream) {
            directories.add(child.getFileName().toString() + '\n');
        }

        directoryStream.close() ;
        return directories;
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
     *@throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     *</blockquote>
     */
    public void rmdir(String[] args) throws IOException {
        if (args.length == 1){
            String argument = args[0] ;

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
        else{
            System.out.println("incorrect command line");
        }
    }

    /**<pre>
     *This method {@code touch} will create a new file
     *in the passed directory
     *user can write a specific extension
     *</pre>
     * @throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     */
    public void touch(String[] args) throws IOException {
        if (args.length == 1){
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
     * @throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     *</blockquote>
     */
    public void cp(String[] args) throws IOException {
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

    /**<pre>
     *This method {@code copyDirectories} will copy the first file with all it's content
     *to the second file recursively
     *</pre>
     *<blockquote>
     * @param src <strong style="color:'white'"> represent the source file</strong>
     * @param dest <strong style="color:'white'"> represent the destination file</strong>
     * @throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     *</blockquote>
     */
    private void copyDirectories(File src, File dest) throws IOException {
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
     * @throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     *</blockquote>
     */
    public void cpR(String[] args) throws IOException {
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

    /**<pre>
     *This method {@code rm} will remove file in the directory
     *</pre>
     *<blockquote>
     * @param args <strong style="color:'white'">it represent the file name</strong>
     * @throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     *</blockquote>
     */
    public void rm(String[] args) throws IOException {
        if (args.length == 1){
            String argument = args[0];
            Path path = Paths.get(currentDirectory, argument);

            if (Files.exists(path)){
                Files.delete(path);
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
        scanner.close();
        return content.toString();
    }

    /**
     *<pre>
     *This method {@code cat} will take 1 argument and prints the file’s content or takes 2
     *arguments and concatenates the content of the 2 files and prints it.
     *</pre>
     *<blockquote>
     * @param args <strong style="color:'white'"> represent the file name\s</strong>
     * @throws FileNotFoundException <strong style="color:'white'"> if the file not exit</strong>
     * @return String <strong style="color:'white'"> represent the content of the process</strong>
     *</blockquote>
     */
    public String cat(String[] args) throws FileNotFoundException {
        if (args.length == 1) {
            Path firstPath;
            if (Paths.get(args[0]).isAbsolute()) {
                firstPath = Paths.get(args[0]);
            } else {
                firstPath = Paths.get(currentDirectory, args[0]);
            }

            return (printFileContent(new File(firstPath.toString())));
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

            return (printFileContent(new File(firstPath.toString())) +
                    printFileContent(new File(secondPath.toString())));
        } else {
            System.out.println("incorrect command line");
            return "" ;
        }
    }

    /**<pre>
     *This method {@code wc} will print the number of lines, words, characters
     *and name in the file
     *</pre>
     *<blockquote>
     * @param args <strong style="color:'white'"> the name of the file</strong>
     * @throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     * @return String <strong style="color:'white'"> represent the information of the file</strong>
     *</blockquote>
     */
    public String wc(String[] args) throws IOException {
        Path path ;
        if (Paths.get(args[0]).isAbsolute()){
            path = Paths.get(args[0]) ;
        }
        else{
            path = Paths.get(currentDirectory, args[0]) ;
        }

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
            fileReader.close();
            return (lines + " " + words + " " + character + " " + path.getFileName().toString());
        }
        else{
            System.out.println("the file doesn't exit");
            return "" ;
        }
    }

    /**<pre>
     *This method {@code handleRedirection} will call the command function
     *then call {@code redirect} function to fill the file
     *</pre>
     *<blockquote>
     * @param commandName <strong style="color:'white'"> represent the command which run</strong>
     * @param args <strong style="color:'white'"> represent the all arguments</strong>
     * @param ifShouldExist <strong style="color:'white'"> represent the case of file can create one or not</strong>
     *</blockquote>
     */
    private void handleRedirection(String commandName, String[] args, boolean ifShouldExist) {
        try {
            String file = args[args.length - 1];
            String[] arguments = Arrays.stream(args).toList().subList(0, args.length - 1).toArray(new String[0]);

            switch (commandName) {
                case "echo" -> redirect(echo(arguments), file, ifShouldExist);
                case "pwd" -> redirect(pwd(), file, ifShouldExist);
                case "ls" -> redirect(ls().toString().replaceAll(", ", "").
                        replaceAll("]", "").
                        replaceAll("\\[", ""), file, ifShouldExist);

                case "ls -r" -> redirect(lsReversed().toString().replaceAll(", ", "").
                        replaceAll("]", "").
                        replaceAll("\\[", ""), file, ifShouldExist);

                case "cat" -> redirect(cat(arguments), file, ifShouldExist);
                case "wc" -> redirect(wc(arguments), file, ifShouldExist);
                case "history" -> redirect(history(), file, ifShouldExist);

                default -> System.out.println("Command not found.");
            }
        }
        catch (Exception exception){
            System.out.println("Command not found.");
        }
    }

    /**<pre>
     *This method {@code redirect} it contains two cases
     *  1- if {@code ifShouldExist = true} so it must be the file created
     *      if not the method will show error to user
     *  2- if {@code ifShouldExist = false} so it must not be the file created
     *      if not the method will create one
     *</pre>
     *<blockquote>
     * @param content <strong style="color:'white'"> represent the content which put in the {@code file}</strong>
     * @param file <strong style="color:'white'"> represent the file which will put into the {@code content}</strong>
     * @param ifShouldExist <strong style="color:'white'"> represent the case of file</strong>
     * @throws IOException <strong style="color:'white'"> if failed or interrupted I/O operations.</strong>
     *</blockquote>
     */
    public void redirect(String content, String file, boolean ifShouldExist) throws IOException {
        File outputFile;
        if (Paths.get(file).isAbsolute()){
            outputFile = new File(file) ;
        }
        else{
            outputFile = new File(Paths.get(currentDirectory, file).toString()) ;
        }

        if (ifShouldExist){
            if (!Files.exists(outputFile.toPath())) {
                System.out.println("file doesn't exist");
                return;
            }
        }
        else{
            if (!Files.exists(outputFile.toPath())){
                if(!outputFile.createNewFile()){
                    System.out.println("file cannot create");
                    return;
                }
            }
        }

        PrintWriter printWriter = new PrintWriter(outputFile);
        printWriter.print("");
        printWriter.print(content);
        printWriter.close();
    }

    /**<pre>
     *This method {@code history} will print the history of commands reversed
     *</pre>
     *<blockquote>
     * @return String <strong style="color:'white'"> represent the history of the commands</strong>
     *</blockquote>
     */
    public String history(){
        StringBuilder stringBuilder = new StringBuilder() ;
        for (String command :
                commandsHistory) {
            stringBuilder.append(command) ;
        }
        return stringBuilder.toString();
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
                case "cat" -> System.out.println(cat(parser.getArgs()));
                case "wc" -> System.out.println(wc(parser.getArgs()));
                case ">" ->{
                    String firstCommand = parser.getRedirectCommandName() ;
                    String[] args = parser.getArgs() ;
                    handleRedirection(firstCommand, args, false);
                }
                case ">>" -> {
                    String firstCommand = parser.getRedirectCommandName() ;
                    String[] args = parser.getArgs() ;
                    handleRedirection(firstCommand, args, true);
                }
                case "history" -> System.out.println(history());
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Command not found.");
            }
        }
        catch (Exception exception){
            System.out.println("Incorrect using for the command.");
        }
        commandsHistory.add(parser.getInput() + '\n') ;
    }
}