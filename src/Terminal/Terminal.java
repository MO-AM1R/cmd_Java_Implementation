package Terminal;
import Parser.Parser ;

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

    /**<pre>
     *This method {@code ls} will print the history of commands
     *</pre>
     */
    public void ls(){
        StringBuilder history = new StringBuilder();
        for (String command :
                commandsHistory) {
            history.append(command).append('\n');
        }

        System.out.println(history);
    }

    /**<pre>
     *This method {@code lsReversed} will print the history of commands reversed
     *</pre>
     */
    public void lsReversed(){
        StringBuilder history = new StringBuilder();

        for (int i = commandsHistory.size() - 1; i >= 0; --i) {
            history.append(commandsHistory.get(i)).append('\n');
        }

        System.out.println(history);
    }

    /**
     *<pre>
     *This method {@code chooseCommandAction} will choose the suitable command method to be called
     *</pre>
     */
    public void chooseCommandAction(){
        String commandName = parser.getCommandName();

        if (commandName.equals("ls")){
            ls() ;
        }
        else if (commandName.equals("ls-r")){
            lsReversed() ;
        }
        else{
            System.out.println("Command not found.");
        }

        String command = parser.getCommandName() + " ";
        command += Arrays.toString(parser.getArgs()).
                replace("[", "").
                replace("]", "").
                replace(",", "") ;

        commandsHistory.add(command) ;
    }
}
