// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfVisibilityExpression.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfArray, PdfLayer, PdfName, PdfObject

public class PdfVisibilityExpression extends PdfArray
{

    public PdfVisibilityExpression(int type)
    {
        switch(type)
        {
        case 0: // '\0'
            super.add(PdfName.OR);
            break;

        case 1: // '\001'
            super.add(PdfName.AND);
            break;

        case -1: 
            super.add(PdfName.NOT);
            break;

        default:
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("illegal.ve.value", new Object[0]));
        }
    }

    public void add(int index, PdfObject element)
    {
        throw new IllegalArgumentException(MessageLocalization.getComposedMessage("illegal.ve.value", new Object[0]));
    }

    public boolean add(PdfObject object)
    {
        if(object instanceof PdfLayer)
            return super.add(((PdfLayer)object).getRef());
        if(object instanceof PdfVisibilityExpression)
            return super.add(object);
        else
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("illegal.ve.value", new Object[0]));
    }

    public void addFirst(PdfObject object)
    {
        throw new IllegalArgumentException(MessageLocalization.getComposedMessage("illegal.ve.value", new Object[0]));
    }

    public boolean add(float values[])
    {
        throw new IllegalArgumentException(MessageLocalization.getComposedMessage("illegal.ve.value", new Object[0]));
    }

    public boolean add(int values[])
    {
        throw new IllegalArgumentException(MessageLocalization.getComposedMessage("illegal.ve.value", new Object[0]));
    }

    public static final int OR = 0;
    public static final int AND = 1;
    public static final int NOT = -1;
}
