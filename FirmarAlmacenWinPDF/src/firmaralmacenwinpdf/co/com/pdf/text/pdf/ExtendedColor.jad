// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExtendedColor.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.BaseColor;

public abstract class ExtendedColor extends BaseColor
{

    public ExtendedColor(int type)
    {
        super(0, 0, 0);
        this.type = type;
    }

    public ExtendedColor(int type, float red, float green, float blue)
    {
        super(normalize(red), normalize(green), normalize(blue));
        this.type = type;
    }

    public ExtendedColor(int type, int red, int green, int blue, int alpha)
    {
        super(normalize((float)red / 255F), normalize((float)green / 255F), normalize((float)blue / 255F), normalize((float)alpha / 255F));
        this.type = type;
    }

    public int getType()
    {
        return type;
    }

    public static int getType(BaseColor color)
    {
        if(color instanceof ExtendedColor)
            return ((ExtendedColor)color).getType();
        else
            return 0;
    }

    static final float normalize(float value)
    {
        if(value < 0.0F)
            return 0.0F;
        if(value > 1.0F)
            return 1.0F;
        else
            return value;
    }

    private static final long serialVersionUID = 0x25c8d4ffff6a72b0L;
    public static final int TYPE_RGB = 0;
    public static final int TYPE_GRAY = 1;
    public static final int TYPE_CMYK = 2;
    public static final int TYPE_SEPARATION = 3;
    public static final int TYPE_PATTERN = 4;
    public static final int TYPE_SHADING = 5;
    protected int type;
}
