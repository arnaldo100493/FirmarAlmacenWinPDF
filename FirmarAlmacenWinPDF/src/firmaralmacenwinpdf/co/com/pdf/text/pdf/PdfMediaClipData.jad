// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfMediaClipData.java

package co.com.pdf.text.pdf;

import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfName, PdfString, PdfFileSpecification

public class PdfMediaClipData extends PdfDictionary
{

    PdfMediaClipData(String file, PdfFileSpecification fs, String mimeType)
        throws IOException
    {
        put(PdfName.TYPE, new PdfName("MediaClip"));
        put(PdfName.S, new PdfName("MCD"));
        put(PdfName.N, new PdfString((new StringBuilder()).append("Media clip for ").append(file).toString()));
        put(new PdfName("CT"), new PdfString(mimeType));
        PdfDictionary dic = new PdfDictionary();
        dic.put(new PdfName("TF"), new PdfString("TEMPACCESS"));
        put(new PdfName("P"), dic);
        put(PdfName.D, fs.getReference());
    }
}
