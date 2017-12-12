// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MetaBrush.java

package co.com.pdf.text.pdf.codec.wmf;

import co.com.pdf.text.BaseColor;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf.codec.wmf:
//            MetaObject, InputMeta

public class MetaBrush extends MetaObject
{

    public MetaBrush()
    {
        style = 0;
        color = BaseColor.WHITE;
        type = 2;
    }

    public void init(InputMeta in)
        throws IOException
    {
        style = in.readWord();
        color = in.readColor();
        hatch = in.readWord();
    }

    public int getStyle()
    {
        return style;
    }

    public int getHatch()
    {
        return hatch;
    }

    public BaseColor getColor()
    {
        return color;
    }

    public static final int BS_SOLID = 0;
    public static final int BS_NULL = 1;
    public static final int BS_HATCHED = 2;
    public static final int BS_PATTERN = 3;
    public static final int BS_DIBPATTERN = 5;
    public static final int HS_HORIZONTAL = 0;
    public static final int HS_VERTICAL = 1;
    public static final int HS_FDIAGONAL = 2;
    public static final int HS_BDIAGONAL = 3;
    public static final int HS_CROSS = 4;
    public static final int HS_DIAGCROSS = 5;
    int style;
    int hatch;
    BaseColor color;
}
