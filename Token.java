/* 
    * Class: CS4308 Section 2
    * Term: Fall 2020
    * Julian Robinson, Josh Yang, Jordan Serban
    * Instructor: Deepa Muralidhar
    * Project: Deliverable 1 Scanner - Java
    * 10/7/2020
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/*
    Plan is to have a enum in the Token class for all 5 categories
    Match
    Use these enums with a switch/case statement to add the scanned token into a token array.
    Print this array with a special format to match project expectations
    DONE

*/



public class Token 
{
    /*token type could be incorporated with an array
        like int types[5]
        where each index in array is associated with a token type.
        Upon instantiation, check for which spot in array has a 1 value (others 0)
        use the spot to decide what the token type is. If/else statements should work. 
        This allows for easier printing of the token type.
    */
    Enum tokenType;
    String value;
    static int numOfTokens = 0;
    int tokenNum;
    int tokenCode;

    enum Ident      //enum (mini class) for identifiers
    {
        INT (001), FLOAT (002), STRING (003), BOOLEAN (004);
        private final int code;
        Ident(int tkCode)
        {
        code = tkCode;
        }
    }

    enum Keyword      //enum (mini class) for keywords
    {   
        IF (101), ELSE (102), THEN (103), END (104), CONSTANT (105),
        EXIT (106), WHILE (107), LOOP (108), FOR (109), NOT_REC(110), 
        EOF(111);
        private final int code;
        Keyword(int tkCode)
        {
        code = tkCode;
        }
    }

    enum Separator      //enum (mini class) for separators/delimiters
    {   
        OP_PAREN (201), CL_PAREN (202), SEMICOLON (203), ASSIGN (204), PARAM (205),
        ASSOC (206), DECIMAL (207), RANGE (208), STRING_SEP (209), CHAR_SEP (210);
        private final int code;
        Separator(int sepCode)
        {
        code = sepCode;
        }
    }

    enum Operator   //enum for operators
    {
        ADD (301), SUBTR (302), MULT (303), DIVIS (304), CONCAT (305),
        MOD (306), REMAIN (307), EQUAL_TO (308), NOT_EQUAL_TO (309), GREATER (310),
        LESS (311), G_EQUAL_TO (312), L_EQUAL_TO (313), AND (314), OR (315),
        XOR (316), POW (317), NOT (318), ABS_VAL (319), IN (320),
        NOT_IN (321);
        private final int code;
        Operator(int opCode)
        {
        code = opCode;
        }
    }

    enum Literal    //enum for literals
    {
        LIT_NUM (401), LIT_DEC (402), LIT_CHAR (403), LIT_STRING (404);
        private final int code;
        Literal(int litCode)
        {
        code = litCode;
        }

    }

    //Token Constructor
    Token(Enum type, int code, String scanned)
    {
        tokenType = type;
        value = scanned;
        tokenCode = code;
        numOfTokens++; 
        tokenNum = numOfTokens;       
    }

   

    //https://stackoverflow.com/questions/7732666/printing-out-characters-in-ada
    //May help with keywords ^^^

    //returns list of all tokens
    public static List<Token> getTokens(Scanner scan)
    {
        List<Token> tokens = new ArrayList<Token>();
        String next = "";

        while(scan.hasNext())
        {   
            /*if(scan.hasNextInt())
            {
                next = scan.next();
                System.out.println("Token: " + next);
                tokens.add(new Token(Literal.LIT_NUM, 401, next));
                continue;
            }*/
            next = scan.next();      //each token, starting to think I should scan one character at a time
            
            if(scan.hasNext() == false)     //special cases like literals
            {
                System.out.println("End of File");
                tokens.add(new Token(Keyword.EOF, 111, "End of File"));
                break;
            }
            else 
            {
                
                // switch for everything except literals
                switch (next) {
                    // Identifiers
                    case "int":
                        System.out.println("Token: " + next);
                        tokens.add(new Token(Ident.INT, 001, next));

                        // Keywords
                   /* case "if":
                        System.out.println("Token: " + next);
                        tokens.add(new Token(Keyword.IF, 101, next));
                        */

                        // Separators
                    case ":=":
                        System.out.println("Token: " + next);
                        tokens.add(new Token(Separator.ASSIGN, 308, next));
                    case ";":
                        System.out.println("Token: " + next);
                        tokens.add(new Token(Separator.SEMICOLON, 203, next));
                    case ":":
                        System.out.println("Token: " + next);
                        tokens.add(new Token(Separator.ASSOC, 206, next));
                        // Operators
                    case "+":
                        System.out.println("Token: " + next);
                        tokens.add(new Token(Operator.ADD, 301, next));
                    case "=":
                        System.out.println("Token: " + next);
                        tokens.add(new Token(Operator.EQUAL_TO, 204, next));
                
                        
                    default:
                        System.out.println("Token not recognized: " + next);
                        tokens.add(new Token(Keyword.NOT_REC, 110, next));

                }
                
            }
        }
        return tokens;
    }

    public static void printTokens(Scanner scan)
    {
        List<Token> tokens = getTokens(scan);

        System.out.println("TOKENS SCANNED: ");
        System.out.printf("%-15s%-15s%-15s%s\n", "Token Number:", "Token Type:", "Token Code:", "Token Value:");  //%-15 pads the string to the right

        for(Token x: tokens)
        {
            System.out.printf("%-15s%-15s%03d%15s%n", x.tokenNum, x.tokenType, x.tokenCode, x.value);
        }
    }
    

    public static void main(String[] args) throws FileNotFoundException
    {
        //Create File and Scanner
        File input = new File("AdaInput.txt");
        Scanner scan = new Scanner(input);

        //testing how to print values
        /*
        System.out.println("First Identifier (Ident.values()[0]): " + Ident.values()[0]);
        System.out.println(String.format("%s%03d", "Using string.format to ensure 3 digits for Ident code: ", Ident.values()[0].code));
        System.out.println("First Keyword: " + Keyword.values()[0].code);
        */
        
        //getTokens(scan);
        printTokens(scan);
        
        /*File testInput = new File("testInput.txt");
        Scanner test = new Scanner(testInput);

        String next = "";
        while(test.hasNext());
        {
            //next = test.next();
           
            System.out.println(test.next());
        }
        test.close();
    
*/
/*while(scan.hasNext());
{
    //next = test.next();
   
    System.out.println(scan.next());
}
*/
        scan.close();
        
        
    }

}

