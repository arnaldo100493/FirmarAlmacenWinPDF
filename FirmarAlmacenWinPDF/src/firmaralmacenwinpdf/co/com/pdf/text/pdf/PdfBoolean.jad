// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfBoolean.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfObject, BadPdfFormatException

public class PdfBoolean extends PdfObject
{

    public PdfBoolean(boolean value)
    {
        super(1);
        if(value)
            setContent("true");
        else
            setContent("false");
        this.value = value;
    }

    public PdfBoolean(String value)
        throws BadPdfFormatException
    {
        super(1, value);
        if(value.equals("true"))
            this.value = true;
        else
        if(value.equals("false"))
            this.value = false;
        else
            throw new BadPdfFormatException(MessageLocalization.getComposedMessage("the.value.has.to.be.true.of.false.instead.of.1", new Object[] {
                value
            }));
    }

    public boolean booleanValue()
    {
        return value;
    }

    public String toString()
    {
        return value ? "true" : "false";
    }

    public static final PdfBoolean PDFTRUE = new PdfBoolean(true);
    public static final PdfBoolean PDFFALSE = new PdfBoolean(false);
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    private boolean value;

}
