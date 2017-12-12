// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfCollection.java

package co.com.pdf.text.pdf.collection;

import co.com.pdf.text.pdf.*;

// Referenced classes of package co.com.pdf.text.pdf.collection:
//            PdfCollectionSchema, PdfCollectionSort

public class PdfCollection extends PdfDictionary
{

    public PdfCollection(int type)
    {
        super(PdfName.COLLECTION);
        switch(type)
        {
        case 1: // '\001'
            put(PdfName.VIEW, PdfName.T);
            break;

        case 2: // '\002'
            put(PdfName.VIEW, PdfName.H);
            break;

        case 3: // '\003'
            put(PdfName.VIEW, PdfName.C);
            break;

        default:
            put(PdfName.VIEW, PdfName.D);
            break;
        }
    }

    public void setInitialDocument(String description)
    {
        put(PdfName.D, new PdfString(description, null));
    }

    public void setSchema(PdfCollectionSchema schema)
    {
        put(PdfName.SCHEMA, schema);
    }

    public PdfCollectionSchema getSchema()
    {
        return (PdfCollectionSchema)get(PdfName.SCHEMA);
    }

    public void setSort(PdfCollectionSort sort)
    {
        put(PdfName.SORT, sort);
    }

    public static final int DETAILS = 0;
    public static final int TILE = 1;
    public static final int HIDDEN = 2;
    public static final int CUSTOM = 3;
}
