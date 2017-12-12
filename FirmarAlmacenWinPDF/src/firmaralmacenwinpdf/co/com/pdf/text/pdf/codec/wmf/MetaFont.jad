// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MetaFont.java

package co.com.pdf.text.pdf.codec.wmf;

import co.com.pdf.text.*;
import co.com.pdf.text.pdf.BaseFont;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

// Referenced classes of package co.com.pdf.text.pdf.codec.wmf:
//            MetaObject, InputMeta, MetaState

public class MetaFont extends MetaObject
{

    public MetaFont()
    {
        faceName = "arial";
        font = null;
        type = 3;
    }

    public void init(InputMeta in)
        throws IOException
    {
        height = Math.abs(in.readShort());
        in.skip(2);
        angle = (float)(((double)in.readShort() / 1800D) * 3.1415926535897931D);
        in.skip(2);
        bold = in.readShort() < 600 ? 0 : 1;
        italic = in.readByte() == 0 ? 0 : 2;
        underline = in.readByte() != 0;
        strikeout = in.readByte() != 0;
        charset = in.readByte();
        in.skip(3);
        pitchAndFamily = in.readByte();
        byte name[] = new byte[32];
        int k = 0;
        do
        {
            if(k >= 32)
                break;
            int c = in.readByte();
            if(c == 0)
                break;
            name[k] = (byte)c;
            k++;
        } while(true);
        try
        {
            faceName = new String(name, 0, k, "Cp1252");
        }
        catch(UnsupportedEncodingException e)
        {
            faceName = new String(name, 0, k);
        }
        faceName = faceName.toLowerCase();
    }

    public BaseFont getFont()
    {
        if(font != null)
            return font;
        Font ff2 = FontFactory.getFont(faceName, "Cp1252", true, 10F, (italic == 0 ? 0 : 2) | (bold == 0 ? 0 : 1));
        font = ff2.getBaseFont();
        if(font != null)
            return font;
        String fontName;
        if(faceName.indexOf("courier") != -1 || faceName.indexOf("terminal") != -1 || faceName.indexOf("fixedsys") != -1)
            fontName = fontNames[0 + italic + bold];
        else
        if(faceName.indexOf("ms sans serif") != -1 || faceName.indexOf("arial") != -1 || faceName.indexOf("system") != -1)
            fontName = fontNames[4 + italic + bold];
        else
        if(faceName.indexOf("arial black") != -1)
            fontName = fontNames[4 + italic + 1];
        else
        if(faceName.indexOf("times") != -1 || faceName.indexOf("ms serif") != -1 || faceName.indexOf("roman") != -1)
            fontName = fontNames[8 + italic + bold];
        else
        if(faceName.indexOf("symbol") != -1)
        {
            fontName = fontNames[12];
        } else
        {
            int pitch = pitchAndFamily & 3;
            int family = pitchAndFamily >> 4 & 7;
            switch(family)
            {
            case 3: // '\003'
                fontName = fontNames[0 + italic + bold];
                break;

            case 1: // '\001'
                fontName = fontNames[8 + italic + bold];
                break;

            case 2: // '\002'
            case 4: // '\004'
            case 5: // '\005'
                fontName = fontNames[4 + italic + bold];
                break;

            default:
                switch(pitch)
                {
                case 1: // '\001'
                    fontName = fontNames[0 + italic + bold];
                    break;

                default:
                    fontName = fontNames[4 + italic + bold];
                    break;
                }
                break;
            }
        }
        try
        {
            font = BaseFont.createFont(fontName, "Cp1252", false);
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        return font;
    }

    public float getAngle()
    {
        return angle;
    }

    public boolean isUnderline()
    {
        return underline;
    }

    public boolean isStrikeout()
    {
        return strikeout;
    }

    public float getFontSize(MetaState state)
    {
        return Math.abs(state.transformY(height) - state.transformY(0)) * Document.wmfFontCorrection;
    }

    static final String fontNames[] = {
        "Courier", "Courier-Bold", "Courier-Oblique", "Courier-BoldOblique", "Helvetica", "Helvetica-Bold", "Helvetica-Oblique", "Helvetica-BoldOblique", "Times-Roman", "Times-Bold", 
        "Times-Italic", "Times-BoldItalic", "Symbol", "ZapfDingbats"
    };
    static final int MARKER_BOLD = 1;
    static final int MARKER_ITALIC = 2;
    static final int MARKER_COURIER = 0;
    static final int MARKER_HELVETICA = 4;
    static final int MARKER_TIMES = 8;
    static final int MARKER_SYMBOL = 12;
    static final int DEFAULT_PITCH = 0;
    static final int FIXED_PITCH = 1;
    static final int VARIABLE_PITCH = 2;
    static final int FF_DONTCARE = 0;
    static final int FF_ROMAN = 1;
    static final int FF_SWISS = 2;
    static final int FF_MODERN = 3;
    static final int FF_SCRIPT = 4;
    static final int FF_DECORATIVE = 5;
    static final int BOLDTHRESHOLD = 600;
    static final int nameSize = 32;
    static final int ETO_OPAQUE = 2;
    static final int ETO_CLIPPED = 4;
    int height;
    float angle;
    int bold;
    int italic;
    boolean underline;
    boolean strikeout;
    int charset;
    int pitchAndFamily;
    String faceName;
    BaseFont font;

}
