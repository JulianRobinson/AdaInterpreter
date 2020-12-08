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
   

    static int numOfTokens = 0;
    int tokenNum;
    String value;
    int tokenCode;
    String tokenType = null;

    Token()
    {
        numOfTokens++;
        tokenNum = numOfTokens;
    }
    //Token Constructor
    Token(int code, String scanned)
    {

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
            // Literals (special cases)


            if(scan.hasNextInt())
            {
                next = scan.next();
                System.out.println("Token: " + next);
                tokens.add(new Literal(401, next));
                continue;
            } // float in else if because ints in java can also be scanned as floats, so handle ints first
            else if(scan.hasNextFloat())
            {
                next = scan.next();
                System.out.println("Token: " + next);
                tokens.add(new Literal(402, next));
                continue;
            }




            next = scan.next();      //each token, starting to think I should scan one character at a time

            if(scan.hasNext() == false)     //End of file
            {
                System.out.println("End of File");
                tokens.add(new Keyword(111, "End of File"));
                break;
            }
            else
            {

                // switch for everything except literals
                switch (next) {
                    // Types
                    case "int":
                        tokens.add(new Type(001, next));
                        break;

                    case "float":
                        tokens.add(new Type(002, next));
                        break;

                    case "string":
                        tokens.add(new Type(003, next));
                        break;

                    case "boolean":
                        tokens.add(new Type(004, next));
                        break;

                    // Keywords
                    case "if":
                        tokens.add(new Keyword(101, next));
                        break;

                    case "else":
                        tokens.add(new Keyword(102, next));
                        break;

                    case "then":
                        tokens.add(new Keyword(103, next));
                        break;

                    case "end":
                        tokens.add(new Keyword(104, next));
                        break;

                    case "exit":
                        tokens.add(new Keyword(106, next));
                        break;

                    case "end_if":
                        tokens.add(new Keyword(112, next));
                        break;

                    case "elsif":
                        tokens.add(new Keyword(113, next));
                        break;

                    case "Get":
                        tokens.add(new Keyword(114, next));
                        break;

                    case "Put":
                        tokens.add(new Keyword(115, next));
                        break;

                    // Separators
                    case ":=":
                        tokens.add(new Separator(204, next));
                        break;

                    case ";":
                        tokens.add(new Separator(203, next));
                        break;

                    case ":":
                        tokens.add(new Separator(206, next));
                        break;

                    // Operators
                    case "+":
                        tokens.add(new Operator(301, next));
                        break;
                    case "-":
                        tokens.add(new Operator(302, next));
                        break;
                    case "*":
                        tokens.add(new Operator(303, next));
                        break;
                    case "/":
                        tokens.add(new Operator(304, next));
                        break;
                    case "&":
                        tokens.add(new Operator(305, next));
                        break;
                    case "=":
                        tokens.add(new Operator(308, next));
                        break;
                    case "/=":
                        tokens.add(new Operator(309, next));
                        break;
                    case ">":
                        tokens.add(new Operator(310, next));
                        break;
                    case "<":
                        tokens.add(new Operator(311, next));
                        break;
                    case ">=":
                        tokens.add(new Operator(312, next));
                        break;
                    case "<=":
                        tokens.add(new Operator(313, next));
                        break;


                    // Literals

                    case "Integer":
                        System.out.println("Token: " + next);
                        tokens.add(new Operator(401, next));
                        break;

                    case "Float":
                        System.out.println("Token: " + next);
                        tokens.add(new Operator(402, next));
                        break;

                    case "Character":
                        System.out.println("Token: " + next);
                        tokens.add(new Operator(403, next));
                        break;

                    case "String":
                        System.out.println("Token: " + next);
                        tokens.add(new Operator(404, next));
                        break;

                    case "true":
                        System.out.println("Token: " + next);
                        tokens.add(new Literal(405, next));
                        break;

                    case "false":
                        System.out.println("Token: " + next);
                        tokens.add(new Literal(405, next));
                        break;

                    // Parser may have to handle determining which strings are identifiers
                    // Also which strings are characters
                    default:
                        System.out.println("Token not recognized (string): " + next);
                        //possibly put string type in default, and have not recognized in special case before/after switch or vice versa
                        tokens.add(new Literal(404, next));
                }

            }
        }

        return tokens;
    }

    @Override
    public String toString()
    {
        return "Token Value: " + value;
    }

    public static void printTokens(Scanner scan)
    {
        List<Token> tokens = getTokens(scan);

        System.out.println("TOKENS SCANNED: ");
        /*
            %-15 pads the string to the right with 15 spaces
            %15 pads the string to the left
            %03d pads integer with 0s
            %n is newline
        */
        System.out.printf("%-15s %-15s %-15s %-15s %n", "Token Number:", "Token Type:", "Token Code:", "Token Value:");

        for(Token x: tokens)
        {
            System.out.printf("%-15s %-15s %03d %11s %-15s %n", x.tokenNum, x.tokenType, x.tokenCode, "", x.value);
        }
    }

}






class Type extends Token
{

    Type(int code, String scanned)
    {
        tokenType = "Type";
        tokenCode = code;
        value = scanned;
    }
}

class Keyword extends Token
{
    Keyword(int code, String scanned)
    {
        tokenType = "Keyword";
        tokenCode = code;
        value = scanned;
    }
}

class Operator extends Token
{
    Operator(int code, String scanned)
    {
        tokenType = "Operator";
        tokenCode = code;
        value = scanned;
    }
}

class Separator extends Token
{
    Separator(int code, String scanned)
    {
        tokenType = "Separator";
        tokenCode = code;
        value = scanned;
    }
}

class Literal extends Token
{
    Literal(int code, String scanned)
    {
        tokenType = "Literal";
        tokenCode = code;
        value = scanned;
    }
}

class Ident extends Token
{
    Ident(int code, String scanned)
    {
        tokenType = "Identifier";
        tokenCode = code;
        value = scanned;
    }
}

//Parser

