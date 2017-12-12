// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfSignature.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfArray, PdfNumber, PdfString, 
//            PdfName, PdfDate

public class PdfSignature extends PdfDictionary
{

    public PdfSignature(PdfName filter, PdfName subFilter)
    {
        super(PdfName.SIG);
        put(PdfName.FILTER, filter);
        put(PdfName.SUBFILTER, subFilter);
    }

    public void setByteRange(int range[])
    {
        PdfArray array = new PdfArray();
        for(int k = 0; k < range.length; k++)
            array.add(new PdfNumber(range[k]));

        put(PdfName.BYTERANGE, array);
    }

    public void setContents(byte contents[])
    {
        put(PdfName.CONTENTS, (new PdfString(contents)).setHexWriting(true));
    }

    public void setCert(byte cert[])
    {
        put(PdfName.CERT, new PdfString(cert));
    }

    public void setName(String name)
    {
        put(PdfName.NAME, new PdfString(name, "UnicodeBig"));
    }

    public void setDate(PdfDate date)
    {
        put(PdfName.M, date);
    }

    public void setLocation(String name)
    {
        put(PdfName.LOCATION, new PdfString(name, "UnicodeBig"));
    }

    public void setReason(String name)
    {
        put(PdfName.REASON, new PdfString(name, "UnicodeBig"));
    }

    public void setContact(String name)
    {
        put(PdfName.CONTACTINFO, new PdfString(name, "UnicodeBig"));
    }
}
