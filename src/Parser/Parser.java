package Parser;
import java.util.List;
import java.util.Vector;

/**
 *<pre>
 *This class {@code Parser} will handle parsing of command
 *extract the command and it's arguments
 *</pre>
 * <blockquote>
 * @version <strong style="color:'white'">1.2</strong>
 * @author <pre style="color:'white'">Malik Khaled
 *     Mohamed Amir
 *     </pre>
 * </blockquote>
 */
public class Parser {
    String commandName;
    String[] args;
    String input ;

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
                String argString = parts[1].trim();
                if (argString.charAt(0) == '-') {
                    commandName += " -" + argString.charAt(1);
                    argString = argString.substring(2);
                }
                List<String> arguments = new Vector<>() ;

                while (!argString.isEmpty()){
                    if (argString.charAt(0) == '\"'){
                        arguments.add(argString.substring(1, argString.substring(1).indexOf("\"") + 1)) ;
                        if (argString.substring(1).indexOf("\"") + 3 >= argString.length()){
                            break ;
                        }
                        argString = argString.substring(argString.substring(1).indexOf("\"") + 3) ;
                    }
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
     * @return String <strong style="color:'white'">{@code input}</strong>
     */
    public String getInput(){
        return input;
    }

    public void setInput(String input) {
        this.input = input ;
    }
}