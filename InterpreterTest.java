import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class InterpreterTest {

    
    
    public static void main(String[] args) throws FileNotFoundException
    {
        //Create File and Scanner
        File input = new File("AdaInput.txt");
        Scanner scan = new Scanner(input);

        //printTokens(scan);

        List<Token >tokens = Token.getTokens(scan);

        Parser p = new Parser(tokens);

        //System.out.print(p.parse());
        p.parseRewritten(tokens);


        scan.close();



    }
}
