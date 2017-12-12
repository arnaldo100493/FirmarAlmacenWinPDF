// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPSXObject.java

package co.com.pdf.text.pdf;

import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfTemplate, PdfStream, ByteBuffer, PdfName, 
//            PdfWriter, PdfContentByte

public class PdfPSXObject extends PdfTemplate
{

    protected PdfPSXObject()
    {
    }

    public PdfPSXObject(PdfWriter wr)
    {
        super(wr);
    }

    public PdfStream getFormXObject(int compressionLevel)
        throws IOException
    {
        PdfStream s = new PdfStream(content.toByteArray());
        s.put(PdfName.TYPE, PdfName.XOBJECT);
        s.put(PdfName.SUBTYPE, PdfName.PS);
        s.flateCompress(compressionLevel);
        return s;
    }

    public PdfContentByte getDuplicate()
    {
        PdfPSXObject tpl = new PdfPSXObject();
        tpl.writer = writer;
        tpl.pdf = pdf;
        tpl.thisReference = thisReference;
        tpl.pageResources = pageResources;
        tpl.separator = separator;
        return tpl;
    }
}
