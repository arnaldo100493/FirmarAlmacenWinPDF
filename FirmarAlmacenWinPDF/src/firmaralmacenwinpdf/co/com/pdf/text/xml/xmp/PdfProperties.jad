// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfProperties.java

package co.com.pdf.text.xml.xmp;

import co.com.pdf.xmp.XMPException;
import co.com.pdf.xmp.XMPMeta;

public class PdfProperties
{

    public PdfProperties()
    {
    }

    public static void setKeywords(XMPMeta xmpMeta, String keywords)
        throws XMPException
    {
        xmpMeta.setProperty("http://ns.adobe.com/pdf/1.3/", "Keywords", keywords);
    }

    public static void setProducer(XMPMeta xmpMeta, String producer)
        throws XMPException
    {
        xmpMeta.setProperty("http://ns.adobe.com/pdf/1.3/", "Producer", producer);
    }

    public static void setVersion(XMPMeta xmpMeta, String version)
        throws XMPException
    {
        xmpMeta.setProperty("http://ns.adobe.com/pdf/1.3/", "PDFVersion", version);
    }

    public static final String KEYWORDS = "Keywords";
    public static final String VERSION = "PDFVersion";
    public static final String PRODUCER = "Producer";
}
