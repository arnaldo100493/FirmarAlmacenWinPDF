// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMYKColor.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            ExtendedColor

public class CMYKColor extends ExtendedColor
{

    public CMYKColor(int intCyan, int intMagenta, int intYellow, int intBlack)
    {
        this((float)intCyan / 255F, (float)intMagenta / 255F, (float)intYellow / 255F, (float)intBlack / 255F);
    }

    public CMYKColor(float floatCyan, float floatMagenta, float floatYellow, float floatBlack)
    {
        super(2, 1.0F - floatCyan - floatBlack, 1.0F - floatMagenta - floatBlack, 1.0F - floatYellow - floatBlack);
        cyan = normalize(floatCyan);
        magenta = normalize(floatMagenta);
        yellow = normalize(floatYellow);
        black = normalize(floatBlack);
    }

    public float getCyan()
    {
        return cyan;
    }

    public float getMagenta()
    {
        return magenta;
    }

    public float getYellow()
    {
        return yellow;
    }

    public float getBlack()
    {
        return black;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof CMYKColor))
        {
            return false;
        } else
        {
            CMYKColor c2 = (CMYKColor)obj;
            return cyan == c2.cyan && magenta == c2.magenta && yellow == c2.yellow && black == c2.black;
        }
    }

    public int hashCode()
    {
        return Float.floatToIntBits(cyan) ^ Float.floatToIntBits(magenta) ^ Float.floatToIntBits(yellow) ^ Float.floatToIntBits(black);
    }

    private static final long serialVersionUID = 0x527077067e7016e4L;
    float cyan;
    float magenta;
    float yellow;
    float black;
}
