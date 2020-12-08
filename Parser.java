import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Parser
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

    void printList(List<Token> tokenList)
    {
        System.out.print("[");
        for (Token a: tokenList){
            System.out.print(a + " | ");
        }
        System.out.println("]");
    }

    List<Token> editable_list = tokens;
    public void parseRewritten(List<Token> input_token_list) {
        // Create new lists for when the input_token_list is split.
        List<Token> first_half = new ArrayList<Token>(100);
        List<Token> second_half = new ArrayList<Token>(100);

        // Go through the input_token_list and look for semicolons (tokenCode 203)
        // When one is found, stop adding to the first_half list and start adding to the second_half list.
        

         
        boolean switch_boolean = true;
        for (Token x: input_token_list) {
            
            if (switch_boolean) {
                first_half.add(x);
            } else {
                second_half.add(x);
            }

            if (x.tokenCode == 203) {
                switch_boolean = false;
            }
        }
        
        //EOF
        if(!(first_half.size() == 0))
        {
        
        
        /*
        System.out.println("First Half: ");
        this.printList(first_half);
        System.out.println("Second Half: ");
        this.printList(second_half);
        */
        

        // Check if first_half is an <assoc>
        //printList(first_half);
        checkAssoc(first_half);
        

        // Recursive call for second_half if it's not null
        if (second_half != null) {
            parseRewritten(second_half);
        }
    }
    }

    public boolean checkAssoc(List<Token> input_token_list) {
        // <assoc> -> <type> : <id> | <type> : <assign>

        // Makes sure the first token is a <type>
        if(input_token_list.size() == 0)
        {
            return false;
        }
        System.out.println("Size of input: " + input_token_list.size());
        System.out.println("At index 0: " + input_token_list.get(0));
        if (!checkType(input_token_list.get(0))) {
            return false;
        }

        // Makes sure the second token is a colon (:)
        if (!(input_token_list.get(1).tokenCode == 206)) {
            return false;
        }

        // Makes sure the third token (and on) is an <id> or an <assign>
        List<Token> sublist = input_token_list.subList(2, input_token_list.size() - 1);
        if (!(checkId(input_token_list.get(2))) && !(checkAssign(sublist))) {
            return false;
        }

        return true;
    }

    // Returns true if the token is a <type>
    public boolean checkType(Token t) {
        if (t.tokenCode == 1 | t.tokenCode == 2 | t.tokenCode == 3 | t.tokenCode == 4) {
            return true;
        } else return false;
    }

    // Returns true if the token is an <id>
    public boolean checkId(Token t) {
        if (t.tokenCode == 404) {
            return true;
        } else return false;
    }

    // Returns true if the list of tokens is an <assign>
    public boolean checkAssign(List<Token> input_token_list) {
        // Makes sure the first token is an <id>
        if (!checkId(input_token_list.get(0))) {
            return false;
        }

        // Makes sure the second token is a assignment operator (:=)
        if (!(input_token_list.get(1).tokenCode == 204)) {
            return false;
        }

        // Makes sure the last section is an <expr>
        List<Token> sublist = input_token_list.subList(2, input_token_list.size() - 1);
        if (!(checkExpr(sublist))) {
            return false;
        }

        return true;
    }

    // TODO
    // Returns true if the list of tokens is an <expr>
    public boolean checkExpr(List<Token> input_token_list) {
        // Makes sure lists of only 1 token contain either an <id> or a <literal>
        if (input_token_list.size() == 1) {
            if (!(checkId(input_token_list.get(0)) || isLiteral(input_token_list.get(0)))) {
                return false;
            } else return true;
        }

        // Makes sure lists longer than 1 token are made up of two <expr> separated by a "+", "-", "*", or "/"
        List<Token> first_expr = new ArrayList<Token>();
        List<Token> second_expr = new ArrayList<Token>();

        boolean switch_boolean = true;
        boolean operator_found = false;
        for (Token x: input_token_list) {
            if (!operator_found && (x.tokenCode >= 301 && x.tokenCode <= 304)) {
                operator_found = true;
                switch_boolean = false;
                continue;
            }

            if (switch_boolean) {
                first_expr.add(x);
            } else {
                second_expr.add(x);
            }
        }

        if (switch_boolean) { // If switch_boolean is still true at this point, it means the input_token_list doesn't contain a "+", "-", "*", or "/"
            return false;
        }

        // Recursive call to check sublists
        if (!(checkExpr(first_expr) && checkExpr(second_expr))) {
            return false;
        }

        return true; // If all checks pass, return true
    }
}