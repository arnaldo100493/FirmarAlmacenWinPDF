// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfCollectionItem.java

package co.com.pdf.text.pdf.collection;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.*;
import java.util.Calendar;

// Referenced classes of package co.com.pdf.text.pdf.collection:
//            PdfCollectionField, PdfCollectionSchema

public class PdfCollectionItem extends PdfDictionary
{

    public PdfCollectionItem(PdfCollectionSchema schema)
    {
        super(PdfName.COLLECTIONITEM);
        this.schema = schema;
    }

    public void addItem(String key, String value)
    {
        PdfName fieldname = new PdfName(key);
        PdfCollectionField field = (PdfCollectionField)schema.get(fieldname);
        put(fieldname, field.getValue(value));
    }

    public void addItem(String key, PdfString value)
    {
        PdfName fieldname = new PdfName(key);
        PdfCollectionField field = (PdfCollectionField)schema.get(fieldname);
        if(field.fieldType == 0)
            put(fieldname, value);
    }

    public void addItem(String key, PdfDate d)
    {
        PdfName fieldname = new PdfName(key);
        PdfCollectionField field = (PdfCollectionField)schema.get(fieldname);
        if(field.fieldType == 1)
            put(fieldname, d);
    }

    public void addItem(String key, PdfNumber n)
    {
        PdfName fieldname = new PdfName(key);
        PdfCollectionField field = (PdfCollectionField)schema.get(fieldname);
        if(field.fieldType == 2)
            put(fieldname, n);
    }

    public void addItem(String key, Calendar c)
    {
        addItem(key, new PdfDate(c));
    }

    public void addItem(String key, int i)
    {
        addItem(key, new PdfNumber(i));
    }

    public void addItem(String key, float f)
    {
        addItem(key, new PdfNumber(f));
    }

    public void addItem(String key, double d)
    {
        addItem(key, new PdfNumber(d));
    }

    public void setPrefix(String key, String prefix)
    {
        PdfName fieldname = new PdfName(key);
        PdfObject o = get(fieldname);
        if(o == null)
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("you.must.set.a.value.before.adding.a.prefix", new Object[0]));
        } else
        {
            PdfDictionary dict = new PdfDictionary(PdfName.COLLECTIONSUBITEM);
            dict.put(PdfName.D, o);
            dict.put(PdfName.P, new PdfString(prefix, "UnicodeBig"));
            put(fieldname, dict);
            return;
        }
    }

    PdfCollectionSchema schema;
}
