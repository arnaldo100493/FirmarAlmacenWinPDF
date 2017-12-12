// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfCollectionField.java

package co.com.pdf.text.pdf.collection;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.*;

public class PdfCollectionField extends PdfDictionary
{

    public PdfCollectionField(String name, int type)
    {
        super(PdfName.COLLECTIONFIELD);
        put(PdfName.N, new PdfString(name, "UnicodeBig"));
        fieldType = type;
        switch(type)
        {
        default:
            put(PdfName.SUBTYPE, PdfName.S);
            break;

        case 1: // '\001'
            put(PdfName.SUBTYPE, PdfName.D);
            break;

        case 2: // '\002'
            put(PdfName.SUBTYPE, PdfName.N);
            break;

        case 3: // '\003'
            put(PdfName.SUBTYPE, PdfName.F);
            break;

        case 4: // '\004'
            put(PdfName.SUBTYPE, PdfName.DESC);
            break;

        case 5: // '\005'
            put(PdfName.SUBTYPE, PdfName.MODDATE);
            break;

        case 6: // '\006'
            put(PdfName.SUBTYPE, PdfName.CREATIONDATE);
            break;

        case 7: // '\007'
            put(PdfName.SUBTYPE, PdfName.SIZE);
            break;
        }
    }

    public void setOrder(int i)
    {
        put(PdfName.O, new PdfNumber(i));
    }

    public void setVisible(boolean visible)
    {
        put(PdfName.V, new PdfBoolean(visible));
    }

    public void setEditable(boolean editable)
    {
        put(PdfName.E, new PdfBoolean(editable));
    }

    public boolean isCollectionItem()
    {
        switch(fieldType)
        {
        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
            return true;
        }
        return false;
    }

    public PdfObject getValue(String v)
    {
        switch(fieldType)
        {
        case 0: // '\0'
            return new PdfString(v, "UnicodeBig");

        case 1: // '\001'
            return new PdfDate(PdfDate.decode(v));

        case 2: // '\002'
            return new PdfNumber(v);
        }
        throw new IllegalArgumentException(MessageLocalization.getComposedMessage("1.is.not.an.acceptable.value.for.the.field.2", new Object[] {
            v, get(PdfName.N).toString()
        }));
    }

    public static final int TEXT = 0;
    public static final int DATE = 1;
    public static final int NUMBER = 2;
    public static final int FILENAME = 3;
    public static final int DESC = 4;
    public static final int MODDATE = 5;
    public static final int CREATIONDATE = 6;
    public static final int SIZE = 7;
    protected int fieldType;
}
