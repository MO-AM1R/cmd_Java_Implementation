package Parser;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 *<pre>
 *This class {@code Parser} will handle parsing of command
 *extract the command and it's arguments
 *</pre>
 * <blockquote>
 * @version <strong style="color:'white'">1.3</strong>
 * @author <pre style="color:'white'">Malik Khaled
 *     Mohamed Amir
 *     </pre>
 * </blockquote>
 */
public class Parser {
    String commandName;
    String[] args;
    String input ;
    String redirectCommandName ;

    /**
     *<pre>
     *This method will divide the {@code input} into commandName and args
     *</pre>
     * <blockquote>
     * @param input <strong style="color:'white'">is the string command entered by the user</strong>
     * @return <strong style="color:'white'">true if the input correct otherwise false</strong>
     * </blockquote>
     */
    public boolean parse(String input) {
        try {
            this.input = input;
            if (input.isEmpty()) {
                return false;
            }

            String[] parts = input.split(" ", 2);
            commandName = parts[0];

            if (parts.length > 1) {
                // Take first argument which command name and remove all spacing with trim func.
                String argString = parts[1].trim();

                // For case cp -r, ls -r so i will add -r to the command Name
                if (argString.charAt(0) == '-') {
                    commandName += " -" + argString.charAt(1);
                    argString = argString.substring(2);

                    if (argString.length() > 1 && argString.charAt(0) == ' '){
                        argString = argString.replaceFirst(" ", "") ;
                    }
                }
                List<String> arguments = new Vector<>() ;

                while (!argString.isEmpty()){
                    // if the first char is ", so I will take the content
                    // to another " and make argString to the remaining content
                    if (argString.charAt(0) == '\"'){
                        arguments.add(argString.substring(1, argString.substring(1).indexOf("\"") + 1)) ;
                        if (argString.substring(1).indexOf("\"") + 3 >= argString.length()){
                            break ;
                        }
                        argString = argString.substring(argString.substring(1).indexOf("\"") + 3) ;
                    }
                    // if the first char is space, so I will take the content
                    // to another space and make argString to the remaining content
                    else{
                        if (argString.contains(" ")){
                            arguments.add(argString.substring(0, argString.indexOf(' '))) ;
                            argString = argString.substring(argString.indexOf(" ") + 1) ;
                        }
                        else{
                            arguments.add(argString) ;
                            break;
                        }
                    }
                }

                args = new String[arguments.size()] ;
                args = arguments.toArray(new String[0]);

                if (Arrays.stream(args).toList().contains(">") ||
                        Arrays.stream(args).toList().contains(">>")){
                    redirectCommandName = commandName ;
                    commandName = ">" ;
                    // here remove the '>' from args
                    if (Arrays.stream(args).toList().contains(">>")){
                        // here remove the second '>' from args
                        commandName = ">>" ;
                    }
                    List<String> argsList = new Vector<>(List.of(args));
                    argsList.remove(commandName);
                    args = argsList.toArray(new String[0]);
                }
            } else {
                args = new String[0];
            }

            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * This method to return command name which parsed
     *
     * @return String <strong style="color:'white'">{@code commandName}</strong>
     */
    public String getCommandName(){
        return commandName;
    }

    /**
     * This method to return arguments which parsed
     *
     * @return Array of strings <strong style="color:'white'">{@code args}</strong>
     */
    public String[] getArgs(){
        return args;
    }

    /**
     * This method to return the input which parsed
     *
     * @return String <strong style="color:'white'"> {@code input}</strong>
     */
    public String getInput(){
        return input;
    }

    /**
     *<pre>
     *This method {@code setInput} it setter for input property
     *</pre>
     * @param input <strong style="color:'white'"> {@code the new input which will be set to input}</strong>
     */
    public void setInput(String input) {
        this.input = input ;
    }

    /**
     *<pre>
     *This method {@code getRedirectCommandName} it getter for getRedirectCommandName property
     *</pre>
     * @return redirectCommandName <strong style="color:'white'"> {@code the new redirect command
     * which be called to redirect the output}</strong>
     */
    public String getRedirectCommandName() {
        return redirectCommandName ;
    }
}