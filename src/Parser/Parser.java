package Parser;


/**
 *<pre>
 *This class {@code Parser} will handle parsing of command
 *extract the command and it's arguments
 *</pre>
 * <blockquote>
 * @version <strong style="color:'white'">1.0</strong>
 * @author <pre style="color:'white'">Malik Khaled
 *     Mohamed Amir
 *     </pre>
 * </blockquote>
 */
public class Parser {
    String commandName;
    String[] args;

    /**
     *<pre>
     *This method will divide the {@code input} into commandName and args
     *</pre>
     * <blockquote>
     * @param input <strong style="color:'white'">is the string command entered by the user</strong>
     * @return <strong style="color:'white'">true if the input correct otherwise false</strong>
     * </blockquote>
     */
    public boolean parse(String input){
        return false;
    }

    /**
     * This method to return command name which parsed
     *
     * @return String <strong style="color:'white'">{@code commandName}</strong>
     */
    public String getCommandName(){
        return null;
    }

    /**
     * This method to return arguments which parsed
     *
     * @return Array of strings <strong style="color:'white'">{@code args}</strong>
     */
    public String[] getArgs(){
        return new String[0];
    }
}