// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MetaPen.java

package co.com.pdf.text.pdf.codec.wmf;

import co.com.pdf.text.BaseColor;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf.codec.wmf:
//            MetaObject, InputMeta

public class MetaPen extends MetaObject
{

    public MetaPen()
    {
        style = 0;
        penWidth = 1;
        color = BaseColor.BLACK;
        type = 1;
    }

    public void init(InputMeta in)
        throws IOException
    {
        style = in.readWord();
        penWidth = in.readShort();
        in.readWord();
        color = in.readColor();
    }

    public int getStyle()
    {
        return style;
    }

    public int getPenWidth()
    {
        return penWidth;
    }

    public BaseColor getColor()
    {
        return color;
    }

    public static final int PS_SOLID = 0;
    public static final int PS_DASH = 1;
    public static final int PS_DOT = 2;
    public static final int PS_DASHDOT = 3;
    public static final int PS_DASHDOTDOT = 4;
    public static final int PS_NULL = 5;
    public static final int PS_INSIDEFRAME = 6;
    int style;
    int penWidth;
    BaseColor color;
}
