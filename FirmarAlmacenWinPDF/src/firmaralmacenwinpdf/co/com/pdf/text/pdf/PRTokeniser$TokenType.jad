// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PRTokeniser.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PRTokeniser

public static final class PRTokeniser$TokenType extends Enum
{

    public static PRTokeniser$TokenType[] values()
    {
        return (PRTokeniser$TokenType[])$VALUES.clone();
    }

    public static PRTokeniser$TokenType valueOf(String name)
    {
        return (PRTokeniser$TokenType)Enum.valueOf(co/com/pdf/text/pdf/PRTokeniser$TokenType, name);
    }

    public static final PRTokeniser$TokenType NUMBER;
    public static final PRTokeniser$TokenType STRING;
    public static final PRTokeniser$TokenType NAME;
    public static final PRTokeniser$TokenType COMMENT;
    public static final PRTokeniser$TokenType START_ARRAY;
    public static final PRTokeniser$TokenType END_ARRAY;
    public static final PRTokeniser$TokenType START_DIC;
    public static final PRTokeniser$TokenType END_DIC;
    public static final PRTokeniser$TokenType REF;
    public static final PRTokeniser$TokenType OTHER;
    public static final PRTokeniser$TokenType ENDOFFILE;
    private static final PRTokeniser$TokenType $VALUES[];

    static 
    {
        NUMBER = new PRTokeniser$TokenType("NUMBER", 0);
        STRING = new PRTokeniser$TokenType("STRING", 1);
        NAME = new PRTokeniser$TokenType("NAME", 2);
        COMMENT = new PRTokeniser$TokenType("COMMENT", 3);
        START_ARRAY = new PRTokeniser$TokenType("START_ARRAY", 4);
        END_ARRAY = new PRTokeniser$TokenType("END_ARRAY", 5);
        START_DIC = new PRTokeniser$TokenType("START_DIC", 6);
        END_DIC = new PRTokeniser$TokenType("END_DIC", 7);
        REF = new PRTokeniser$TokenType("REF", 8);
        OTHER = new PRTokeniser$TokenType("OTHER", 9);
        ENDOFFILE = new PRTokeniser$TokenType("ENDOFFILE", 10);
        $VALUES = (new PRTokeniser$TokenType[] {
            NUMBER, STRING, NAME, COMMENT, START_ARRAY, END_ARRAY, START_DIC, END_DIC, REF, OTHER, 
            ENDOFFILE
        });
    }

    private PRTokeniser$TokenType(String s, int i)
    {
        super(s, i);
    }
}
