// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfRendition.java

package co.com.pdf.text.pdf;

import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfName, PdfString, PdfMediaClipData, 
//            PdfFileSpecification

public class PdfRendition extends PdfDictionary
{

    PdfRendition(String file, PdfFileSpecification fs, String mimeType)
        throws IOException
    {
        put(PdfName.S, new PdfName("MR"));
        put(PdfName.N, new PdfString((new StringBuilder()).append("Rendition for ").append(file).toString()));
        put(PdfName.C, new PdfMediaClipData(file, fs, mimeType));
    }
}
