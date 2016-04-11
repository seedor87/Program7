import program4.LexicalAnalyzer;
import program4.Token;
import program4.Type;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Created by Bill Clark and Bob Seedorf on 4/4/2016.
 * This is a parser using the visitor pattern for expressions
 * that parses the output created by the program4 package. That
 * output is a list of tokens created from a minijava program.
 * A symbol table is also generated, which contains the evaluation
 * tree of any identifier the parser runs across.
 */
public class Parser
{

    private static ArrayList<Token> tokens;

    public static void main(String[] args) throws IOException
    {

        LexicalAnalyzer lex = new LexicalAnalyzer();
        // Read in from file put in collection tokens
        File f = new File("/Users/robertseedorf/IdeaProjects/program7/src/program4/sample");
        File output = new File("/Users/robertseedorf/IdeaProjects/program7/src/program4/output.txt");
        FileInputStream fis = new FileInputStream(f);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        tokens = new ArrayList<Token>();
        String line = "";
        while ((line = br.readLine()) != null)
        {
            System.out.println(line);
            tokens.addAll(lex.analyze(line));
        }
        br.close();

        System.out.println(generateSymbolTable());
    }

    public static ArrayList<Assign> generateSymbolTable()
    {
        ArrayList<String> keywords = new ArrayList<String>();
        keywords.addAll(Arrays.asList("public", "class", "static", "void", "String",
                "args", "for", "System", "out", "println", "Example", "main"));
        ArrayList<Assign> symbols = new ArrayList<>();
        while (tokens.size() > 0)
        {
            Token t = tokens.get(0);
            System.out.println(t);
            //a symbol begins with an atom.
            if (t.type == Type.ATOM)
            {
                if (keywords.contains(t.c))
                {
                    tokens.remove(0);
                    continue;
                }
                //Atom must be an identifier.
                Identifier id = new Identifier(t.c);
                //what is preformed on it?
                Token use = tokens.get(1);

                Assign a = null;
                //just assignment, or an expression?
                if (tokens.get(2).type == Type.DQUOTE) //String Assignment
                {
                    Token value1 = tokens.get(3);
                    if (value1.type == Type.DQUOTE)
                    {
                        tokens.remove(0);
                        tokens.remove(0);
                        tokens.remove(0);
                        tokens.remove(0);
                    }
                    else
                    {
                        Exp lvalue = tokenValue(value1);
                        tokens.remove(0);
                        tokens.remove(0);
                        tokens.remove(0);
                        tokens.remove(0);
                        tokens.remove(0);
                        a = new Assign(id, lvalue);
                    }
                }

                else if (tokens.get(3).type == Type.PERIOD)
                {
                    int i = 1;
                    String value = "";
                    while (tokens.get(1 + i).type != Type.SEMICOLON)
                    {
                        value += tokens.get(1 + i).c;
                        i++;
                    }
                    a = new Assign(id, new StringConst(value));

                    for (int ii = 0; ii <= i + 1; ii++)
                    {
                        tokens.remove(0);
                    }
                }

                else if (tokens.get(3).type == Type.OPERATOR) //expression
                {
                    Token value1 = tokens.get(2);
                    Exp lvalue = tokenValue(value1);
                    Token value2 = tokens.get(4);
                    Exp rvalue = tokenValue(value2);
                    //math or logical?
                    switch (tokens.get(3).c)
                    {
                        case "+":
                            a = new Assign(id, new Sum(lvalue, rvalue));
                            break;
                        case "-":
                            a = new Assign(id, new Difference(lvalue, rvalue));
                            break;
                        case "*":
                            a = new Assign(id, new Product(lvalue, rvalue));
                            break;
                        case "/":
                            a = new Assign(id, new Quotient(lvalue, rvalue));
                            break;
                        case "<":
                            a = new Assign(id, new LessThan(lvalue, rvalue));
                            break;
                        case ">":
                            a = new Assign(id, new GreaterThan(lvalue, rvalue));
                            break;
                    }

                    tokens.remove(0);
                    tokens.remove(0);
                    tokens.remove(0);
                    tokens.remove(0);
                    tokens.remove(0);

                }

                else if (tokens.get(2).type == Type.ATOM)
                {
                    Exp value = tokenValue(tokens.get(2));
                    a = new Assign(id, value);
                    tokens.remove(0);
                    tokens.remove(0);
                    tokens.remove(0);
                }

                symbols.add(a);
            }
            else
            {
                tokens.remove(0);
            }

            if (tokens.size() <= 0) break;
        }

        return symbols;
    }

    public static Exp tokenValue(Token t)
    {
        try
        {
            int value = Integer.parseInt(t.c);
            return new Constant(value);
        } catch (Exception e)
        {
            String value = t.c;
            return new StringConst(value);
        }
    }
}
