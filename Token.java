/* 
    * Class: CS4308 Section 2
    * Term: Fall 2020
    * Julian Robinson, Josh Yang, Jordan Serban
    * Instructor: Deepa Muralidhar
    * Project: Deliverable 1 Scanner - Java
    * 10/7/2020
*/


import java.util.Scanner;

/*
    Plan is to have a enum in the Token class for all 5 categories
    Match
    Use these enums with a switch/case statement to add the scanned token into a token array.
    Print this array with a special format to match project expectations
    DONE

*/


public class Token
{
    Ident idType;       //enum 
    Keyword keyType;
    Separator sepType;
    Operator opType;
    Literal litType;
    String value;

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
        EXIT (106), WHILE (107), LOOP (108), FOR (109);
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

    Token(Ident type, String scanned)
    {
        idType = type;
        value = scanned;        
    }

    Token(Keyword type, String scanned)
    {
        keyType = type;
        value = scanned;
    }

    Token(Separator type, String scanned)
    {
        sepType = type;
        value = scanned;
    }

    Token(Operator type, String scanned)
    {
        opType = type;
        value = scanned;
    }

    Token(Literal type, String scanned)
    {
        litType = type;
        value = scanned;
    }

    public static void main(String[] args)
    {
        //testing how to print values
        System.out.println("First Identifier (Ident.values()[0]): " + Ident.values()[0]);
        System.out.println(String.format("%s%03d", "Using string.format to ensure 3 digits for Ident code: ", Ident.values()[0].code));
        System.out.println("First Keyword: " + Keyword.values()[0].code);

    }

}

/*

*/

