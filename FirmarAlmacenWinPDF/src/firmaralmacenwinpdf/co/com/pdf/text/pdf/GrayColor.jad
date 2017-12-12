// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GrayColor.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            ExtendedColor

public class GrayColor extends ExtendedColor
{

    public GrayColor(int intGray)
    {
        this((float)intGray / 255F);
    }

    public GrayColor(float floatGray)
    {
        super(1, floatGray, floatGray, floatGray);
        gray = normalize(floatGray);
    }

    public float getGray()
    {
        return gray;
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof GrayColor) && ((GrayColor)obj).gray == gray;
    }

    public int hashCode()
    {
        return Float.floatToIntBits(gray);
    }

    private static final long serialVersionUID = 0xa4cc264472877cc6L;
    private float gray;
    public static final GrayColor GRAYBLACK = new GrayColor(0.0F);
    public static final GrayColor GRAYWHITE = new GrayColor(1.0F);

}
