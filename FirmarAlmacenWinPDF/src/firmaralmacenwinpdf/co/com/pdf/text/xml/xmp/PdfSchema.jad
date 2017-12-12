// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfSchema.java

package co.com.pdf.text.xml.xmp;

import co.com.pdf.text.Version;

// Referenced classes of package co.com.pdf.text.xml.xmp:
//            XmpSchema

/**
 * @deprecated Class PdfSchema is deprecated
 */

public class PdfSchema extends XmpSchema
{

    public PdfSchema()
    {
        super("xmlns:pdf=\"http://ns.adobe.com/pdf/1.3/\"");
        addProducer(Version.getInstance().getVersion());
    }

    public void addKeywords(String keywords)
    {
        setProperty("pdf:Keywords", keywords);
    }

    public void addProducer(String producer)
    {
        setProperty("pdf:Producer", producer);
    }

    public void addVersion(String version)
    {
        setProperty("pdf:PDFVersion", version);
    }

    private static final long serialVersionUID = 0xea9cbd7366cc6587L;
    public static final String DEFAULT_XPATH_ID = "pdf";
    public static final String DEFAULT_XPATH_URI = "http://ns.adobe.com/pdf/1.3/";
    public static final String KEYWORDS = "pdf:Keywords";
    public static final String VERSION = "pdf:PDFVersion";
    public static final String PRODUCER = "pdf:Producer";
}
