// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfDocument.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.Version;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfString, PdfDate, PdfName, 
//            PdfDocument

public static class PdfDocument$PdfInfo extends PdfDictionary
{

    void addTitle(String title)
    {
        put(PdfName.TITLE, new PdfString(title, "UnicodeBig"));
    }

    void addSubject(String subject)
    {
        put(PdfName.SUBJECT, new PdfString(subject, "UnicodeBig"));
    }

    void addKeywords(String keywords)
    {
        put(PdfName.KEYWORDS, new PdfString(keywords, "UnicodeBig"));
    }

    void addAuthor(String author)
    {
        put(PdfName.AUTHOR, new PdfString(author, "UnicodeBig"));
    }

    void addCreator(String creator)
    {
        put(PdfName.CREATOR, new PdfString(creator, "UnicodeBig"));
    }

    void addProducer()
    {
        put(PdfName.PRODUCER, new PdfString(Version.getInstance().getVersion()));
    }

    void addCreationDate()
    {
        PdfString date = new PdfDate();
        put(PdfName.CREATIONDATE, date);
        put(PdfName.MODDATE, date);
    }

    void addkey(String key, String value)
    {
        if(key.equals("Producer") || key.equals("CreationDate"))
        {
            return;
        } else
        {
            put(new PdfName(key), new PdfString(value, "UnicodeBig"));
            return;
        }
    }

    PdfDocument$PdfInfo()
    {
        addProducer();
        addCreationDate();
    }

    PdfDocument$PdfInfo(String author, String title, String subject)
    {
        this();
        addTitle(title);
        addSubject(subject);
        addAuthor(author);
    }
}
