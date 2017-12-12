// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfCollectionSort.java

package co.com.pdf.text.pdf.collection;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.*;

public class PdfCollectionSort extends PdfDictionary
{

    public PdfCollectionSort(String key)
    {
        super(PdfName.COLLECTIONSORT);
        put(PdfName.S, new PdfName(key));
    }

    public PdfCollectionSort(String keys[])
    {
        super(PdfName.COLLECTIONSORT);
        PdfArray array = new PdfArray();
        for(int i = 0; i < keys.length; i++)
            array.add(new PdfName(keys[i]));

        put(PdfName.S, array);
    }

    public void setSortOrder(boolean ascending)
    {
        PdfObject o = get(PdfName.S);
        if(o instanceof PdfName)
            put(PdfName.A, new PdfBoolean(ascending));
        else
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("you.have.to.define.a.boolean.array.for.this.collection.sort.dictionary", new Object[0]));
    }

    public void setSortOrder(boolean ascending[])
    {
        PdfObject o = get(PdfName.S);
        if(o instanceof PdfArray)
        {
            if(((PdfArray)o).size() != ascending.length)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.number.of.booleans.in.this.array.doesn.t.correspond.with.the.number.of.fields", new Object[0]));
            PdfArray array = new PdfArray();
            for(int i = 0; i < ascending.length; i++)
                array.add(new PdfBoolean(ascending[i]));

            put(PdfName.A, array);
        } else
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("you.need.a.single.boolean.for.this.collection.sort.dictionary", new Object[0]));
        }
    }
}
