// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExternalBlankSignatureContainer.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.pdf.PdfDictionary;
import co.com.pdf.text.pdf.PdfName;
import java.io.InputStream;
import java.security.GeneralSecurityException;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            ExternalSignatureContainer

public class ExternalBlankSignatureContainer
    implements ExternalSignatureContainer
{

    public ExternalBlankSignatureContainer(PdfDictionary sigDic)
    {
        this.sigDic = sigDic;
    }

    public ExternalBlankSignatureContainer(PdfName filter, PdfName subFilter)
    {
        sigDic = new PdfDictionary();
        sigDic.put(PdfName.FILTER, filter);
        sigDic.put(PdfName.SUBFILTER, subFilter);
    }

    public byte[] sign(InputStream data)
        throws GeneralSecurityException
    {
        return new byte[0];
    }

    public void modifySigningDictionary(PdfDictionary signDic)
    {
        signDic.putAll(sigDic);
    }

    private PdfDictionary sigDic;
}
