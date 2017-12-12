// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Level.java

package co.com.pdf.text.log;


public final class Level extends Enum
{

    public static Level[] values()
    {
        return (Level[])$VALUES.clone();
    }

    public static Level valueOf(String name)
    {
        return (Level)Enum.valueOf(co/com/pdf/text/log/Level, name);
    }

    private Level(String s, int i)
    {
        super(s, i);
    }

    public static final Level ERROR;
    public static final Level WARN;
    public static final Level INFO;
    public static final Level DEBUG;
    public static final Level TRACE;
    private static final Level $VALUES[];

    static 
    {
        ERROR = new Level("ERROR", 0);
        WARN = new Level("WARN", 1);
        INFO = new Level("INFO", 2);
        DEBUG = new Level("DEBUG", 3);
        TRACE = new Level("TRACE", 4);
        $VALUES = (new Level[] {
            ERROR, WARN, INFO, DEBUG, TRACE
        });
    }
}
