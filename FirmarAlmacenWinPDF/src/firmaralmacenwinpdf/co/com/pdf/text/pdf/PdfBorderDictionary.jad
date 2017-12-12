// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfBorderDictionary.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfNumber, PdfDashPattern, PdfName

public class PdfBorderDictionary extends PdfDictionary
{

    public PdfBorderDictionary(float borderWidth, int borderStyle, PdfDashPattern dashes)
    {
        put(PdfName.W, new PdfNumber(borderWidth));
        switch(borderStyle)
        {
        case 0: // '\0'
            put(PdfName.S, PdfName.S);
            break;

        case 1: // '\001'
            if(dashes != null)
                put(PdfName.D, dashes);
            put(PdfName.S, PdfName.D);
            break;

        case 2: // '\002'
            put(PdfName.S, PdfName.B);
            break;

        case 3: // '\003'
            put(PdfName.S, PdfName.I);
            break;

        case 4: // '\004'
            put(PdfName.S, PdfName.U);
            break;

        default:
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("invalid.border.style", new Object[0]));
        }
    }

    public PdfBorderDictionary(float borderWidth, int borderStyle)
    {
        this(borderWidth, borderStyle, null);
    }

    public static final int STYLE_SOLID = 0;
    public static final int STYLE_DASHED = 1;
    public static final int STYLE_BEVELED = 2;
    public static final int STYLE_INSET = 3;
    public static final int STYLE_UNDERLINE = 4;
}
