// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfCollectionSchema.java

package co.com.pdf.text.pdf.collection;

import co.com.pdf.text.pdf.PdfDictionary;
import co.com.pdf.text.pdf.PdfName;

// Referenced classes of package co.com.pdf.text.pdf.collection:
//            PdfCollectionField

public class PdfCollectionSchema extends PdfDictionary
{

    public PdfCollectionSchema()
    {
        super(PdfName.COLLECTIONSCHEMA);
    }

    public void addField(String name, PdfCollectionField field)
    {
        put(new PdfName(name), field);
    }
}
