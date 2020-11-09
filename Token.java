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

    //These are just here for token number references. Will need to update table
    enum TypeEnum      //enum (mini class) for identifiers *** Actually called types ***
    {
        INT (001), FLOAT (002), STRING (003), BOOLEAN (004);
        private final int code;
        TypeEnum(int tkCode)
        {
        code = tkCode;
        }
    }

    enum KeywordEnum      //enum (mini class) for keywords
    {   
        IF (101), ELSE (102), THEN (103), END (104), CONSTANT (105),
        EXIT (106), WHILE (107), LOOP (108), FOR (109), NOT_REC(110), 
        EOF(111);
        private final int code;
        KeywordEnum(int tkCode)
        {
        code = tkCode;
        }
    }

    enum SeparatorEnum      //enum (mini class) for separators/delimiters
    {   
        OP_PAREN (201), CL_PAREN (202), SEMICOLON (203), ASSIGN (204), PARAM (205),
        ASSOC (206), DECIMAL (207), RANGE (208), STRING_SEP (209), CHAR_SEP (210);
        private final int code;
        SeparatorEnum(int sepCode)
        {
        code = sepCode;
        }
    }

    enum OperatorEnum   //enum for operators
    {
        ADD (301), SUBTR (302), MULT (303), DIVIS (304), CONCAT (305),
        MOD (306), REMAIN (307), EQUAL_TO (308), NOT_EQUAL_TO (309), GREATER (310),
        LESS (311), G_EQUAL_TO (312), L_EQUAL_TO (313), AND (314), OR (315),
        XOR (316), POW (317), NOT (318), ABS_VAL (319), IN (320),
        NOT_IN (321);
        private final int code;
        OperatorEnum(int opCode)
        {
        code = opCode;
        }
    }

    enum LiteralEnum    //enum for literals
    {
        LIT_NUM (401), LIT_DEC (402), LIT_CHAR (403), LIT_STRING (404);
        private final int code;
        LiteralEnum(int litCode)
        {
        code = litCode;
        }

    }

    

    /*
    Maybe this will work?
    public String getType(){return null;};
    */
   

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
                        System.out.println("Token: " + next);
                        tokens.add(new Type(001, next));
                        break;

                    case "float":
                        System.out.println("Token: " + next);
                        tokens.add(new Type(002, next));
                        break;

                    case "string":
                        System.out.println("Token: " + next);
                        tokens.add(new Type(003, next));
                        break;

                    case "boolean":
                        System.out.println("Token: " + next);
                        tokens.add(new Type(004, next));
                        break;

                    // Keywords
                    case "if":
                        System.out.println("Token: " + next);
                        tokens.add(new Keyword(101, next));
                        break;

                    case "else":
                        System.out.println("Token: " + next);
                        tokens.add(new Keyword(102, next));
                        break;

                    case "then":
                        System.out.println("Token: " + next);
                        tokens.add(new Keyword(103, next));
                        break;

                    case "end":
                        System.out.println("Token: " + next);
                        tokens.add(new Keyword(104, next));
                        break;

                    case "exit":
                        System.out.println("Token: " + next);
                        tokens.add(new Keyword(106, next));
                        break;

                    case "end_if":
                        System.out.println("Token: " + next);
                        tokens.add(new Keyword(112, next));
                        break;

                    case "elsif":
                        System.out.println("Token: " + next);
                        tokens.add(new Keyword(113, next));
                        break;
                    
                    case "Get":
                        System.out.println("Token: " + next);
                        tokens.add(new Keyword(114, next));
                        break;
                    
                  	case "Put":
                        System.out.println("Token: " + next);
                        tokens.add(new Keyword(115, next));
                        break;

                    // Separators
                    case ":=":
                        System.out.println("Token: " + next);
                        tokens.add(new Separator(204, next));
                        break;

                    case ";":
                        System.out.println("Token: " + next);
                        tokens.add(new Separator(203, next));
                        break;

                    case ":":
                        System.out.println("Token: " + next);
                        tokens.add(new Separator(206, next));
                        break;

                    // Operators
                    case "+":
                        System.out.println("Token: " + next);
                        tokens.add(new Operator(301, next));
                        break;
                    case "-":
                        System.out.println("Token: " + next);
                        tokens.add(new Operator(302, next));
                        break;
                    case "*":
                        System.out.println("Token: " + next);
                        tokens.add(new Operator(303, next));
                        break;
                    case "/":
                        System.out.println("Token: " + next);
                        tokens.add(new Operator(304, next));
                        break;
                    case "&":
                        System.out.println("Token: " + next);
                        tokens.add(new Operator(305, next));
                        break;
                    case "=":
                        System.out.println("Token: " + next);
                        tokens.add(new Operator(308, next));
                        break;
                    case "/=":
                        System.out.println("Token: " + next);
                        tokens.add(new Operator(309, next));
                        break;
                    case ">":
                        System.out.println("Token: " + next);
                        tokens.add(new Operator(310, next));
                        break;
                    case "<":
                        System.out.println("Token: " + next);
                        tokens.add(new Operator(311, next));
                        break;
                    case ">=":
                        System.out.println("Token: " + next);
                        tokens.add(new Operator(312, next));
                        break;
                    case "<=":
                        System.out.println("Token: " + next);
                        tokens.add(new Operator(313, next));
                        break;


                    // Literals
                /*
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
                */

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
                    /*try{
                    		if(Integer.parseInt(next) > Integer.MIN_VALUE && Integer.parseInt(next) < Integer.MAX_VALUE)
                    		{
                                 tokens.add(new Literal(401, next));
                                 break;
                            }
                        }
                        catch(Exception e){System.out.print(e + "\n");}
                            */
                        System.out.println("Token not recognized (string): " + next);
                        //possibly put string type in default, and have not recognized in special case before/after switch or vice versa
                        tokens.add(new Literal(404, next));
                    
                   	

                }
                
            }
        }
        
        return tokens;
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

    

    public static void main(String[] args) throws FileNotFoundException
    {
        //Create File and Scanner
        File input = new File("AdaInput.txt");
        Scanner scan = new Scanner(input);

        //printTokens(scan);

        List<Token >tokens = getTokens(scan);

        Parser p = new Parser(tokens);

        System.out.print(p.parse());

        scan.close();

        
        
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

class Parser 
{

    List<Token> tokens;
    Parser(List<Token> tokenList)
    {
        tokens = tokenList;
    }

  //Types
    boolean isType(Token tok)
    {
        if(tok.tokenCode > 000 && tok.tokenCode < 005)
            return true;
       
        return false;
    }
  
  	boolean isTypeInt(Token tok)
    {
     		if(tok.tokenCode == 001)
          return true;
      return false;
    }
  	
  	boolean isTypeFloat(Token tok)
    {
     		if(tok.tokenCode == 002)
          return true;
      return false;
    }
  	
  	boolean isTypeString(Token tok)
    {
     		if(tok.tokenCode == 003)
          return true;
      return false;
    }
  
  	boolean isTypeBool(Token tok)
    {
     		if(tok.tokenCode == 004)
          return true;
      return false;
    }
    
  //Keywords
  	boolean isKeyword(Token tok)
    {
     		 if(tok.tokenCode > 100 && tok.tokenCode < 117)
            return true;
       
        return false;
    }
  
  	boolean isIf(Token tok)
    {
     			if(tok.tokenCode == 101)
            return true;
      
      	return false;
    }
  
  	boolean isElse(Token tok)
    {
     			if(tok.tokenCode == 102)
            return true;
      	return false;
    }
  
  	boolean isThen(Token tok)
    {
     			if(tok.tokenCode == 103)
            return true;
      	return false;
    }
  
  	boolean isEnd(Token tok)
    {
     			if(tok.tokenCode == 104)
            return true;
      	return false;
    }
  
  	boolean isExit(Token tok)
    {
     			if(tok.tokenCode == 106)
            return true;
      	return false;
    }
  
  	boolean isEndIf(Token tok)
    {
     			if(tok.tokenCode == 112)
            return true;
      	return false;
    }
  
  	boolean isElsIf(Token tok)
    {
     			if(tok.tokenCode == 113)
            return true;
      	return false;
    }
  
  	boolean isGet(Token tok)
    {
     			if(tok.tokenCode == 114)
            return true;
      	return false;
    }
  
  	boolean isPut(Token tok)
    {
     			if(tok.tokenCode == 115)
            return true;
      	return false;
    }
  
  // Separators
  	boolean isSeparator(Token tok) {
					if (tok.tokenCode == 203 || tok.tokenCode == 204 || tok.tokenCode == 206)
            return true;
      return false;
    }
  
  	boolean isEndLine(Token tok) {
					if (tok.tokenCode == 203)
            return true;
      return false;
    }
  
  	boolean isAssignment(Token tok) {
					if (tok.tokenCode == 204)
            return true;
      return false;
    }
  
   	boolean isAssociation(Token tok) {
					if (tok.tokenCode == 206)
            return true;
      return false;
    }
  
  // Operators
    boolean isOperator(Token tok) {
					if ((tok.tokenCode >= 301 && tok.tokenCode <= 305) || (tok.tokenCode >= 308 && tok.tokenCode <= 313))
            return true;
      return false;
    }
  
    boolean isAddition(Token tok) {
					if (tok.tokenCode == 301)
            return true;
      return false;
    }
  
    boolean isSubtraction(Token tok) {
					if (tok.tokenCode == 302)
            return true;
      return false;
    }
  
    boolean isMultiplication(Token tok) {
					if (tok.tokenCode == 303)
            return true;
      return false;
    }
  
    boolean isDivision(Token tok) {
					if (tok.tokenCode == 304)
            return true;
      return false;
    }
  
    boolean isConcatenate(Token tok) {
					if (tok.tokenCode == 305)
            return true;
      return false;
    }
  
    boolean isEqualTo(Token tok) {
					if (tok.tokenCode == 308)
            return true;
      return false;
    }
  
    boolean isNotEqualTo(Token tok) {
					if (tok.tokenCode == 309)
            return true;
      return false;
    }
  
    boolean isGreaterThan(Token tok) {
					if (tok.tokenCode == 310)
            return true;
      return false;
    }
  
    boolean isLessThan(Token tok) {
					if (tok.tokenCode == 311)
            return true;
      return false;
    }
  
    boolean isGreaterThanOrEqualTo(Token tok) {
					if (tok.tokenCode == 312)
            return true;
      return false;
    }
  
    boolean isLessThanOrEqualTo(Token tok) {
					if (tok.tokenCode == 313)
            return true;
      return false;
    }
		
  //Literals
  	boolean isLiteral(Token tok)
    {
     		if(tok.tokenCode >= 401 && tok.tokenCode <= 405)
            return true;
        
        return false;  
    }
  
    boolean isInteger(Token tok)
    {
        if(tok.tokenCode == 401)
            return true;
        
        return false;   
    }
  
  	boolean isFloat(Token tok)
    {
        if(tok.tokenCode == 402)
            return true;
        
        return false;   
    }
  
  	boolean isCharacter(Token tok)
    {
        if(tok.tokenCode == 403)
            return true;
        
        return false;   
    }
  
  	boolean isString(Token tok)
    {
        if(tok.tokenCode == 404)
            return true;
        
        return false;   
    }
  
  	boolean isBoolean(Token tok)
    {
        if(tok.tokenCode == 405)
            return true;
        
        return false;   
    }
	
  	//IDs
  	//if token is one character long, try checking if it is an ID
  	boolean isID(Token tok)
    {
        if(tok.value.length() == 1)
        {
            if(Character.isLetter(tok.value.charAt(0)))
            {
                return true;
            }
        }
        
        return false;
    }
    
    String parse()
    {
        System.out.println("PARSING... ");
        int counter = 0;
        String output = "";
        
        while(counter < tokens.size())
        {
            Token current = tokens.get(counter);
          //Types
            if(isTypeInt(current))
            {
                output = output + "<"+ current.tokenType + "> ";
                counter++;
                continue;
            }
          
          	if(isTypeString(current))
            {
                output = output + "<"+ current.tokenType + "> ";
                counter++;
                continue;
            }

          //Keywords
          if(isKeyword(current))
            {
                output = output + "<"+ current.tokenType + "> ";
                counter++;
                continue;
            }
          
          
          //Separators
          	if(isEndLine(current))
            {
                output = output + "<"+ current.tokenType + "> ";
                counter++;
                continue;
            }
          
          	if(isAssociation(current))
            {
                output = output + "<"+ current.tokenType + "> ";
                counter++;
                continue;
            }
          
          	if(isAssignment(current))
            {
                output = output + "<"+ current.tokenType + "> ";
                counter++;
                continue;
            }
          
          //Operators
          	if(isAddition(current))
            {
                output = output + "<"+ current.tokenType + "> ";
                counter++;
                continue;
            }
          
          //Literals
          	if(isLiteral(current))
            {
                output = output + "<"+ current.tokenType + "> ";
                counter++;
                continue;
            }
          
            if(isID(current))
            {
                output = output + "<" + current.tokenType + "> ";
                counter++;
                continue;
            }
            
            
            counter++;
            

        }

        return output;
    }
}
