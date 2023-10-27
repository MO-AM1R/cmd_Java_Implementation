import Parser.Parser;
import Terminal.Terminal;
import java.util.Scanner;

/**
 *<pre>
 *This class {@code Main} will handle the run
 *of the application
 *</pre>
 * <blockquote>
 * @version <strong style="color:'white'">1.1</strong>
 * @author <pre style="color:'white'">Malik Khaled
 *     Mohamed Amir
 *     </pre>
 * </blockquote>
 */

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser() ;
        Terminal terminal = new Terminal() ;
        String input = "";

        while (!input.equals("exit")){
            Scanner scanner = new Scanner(System.in) ;
            System.out.print(terminal.getCurrentDirectory() + '>');
            input = scanner.nextLine() ;
            parser.setInput(input) ;
            terminal.setParser(parser);
            terminal.chooseCommandAction();
        }
    }
}