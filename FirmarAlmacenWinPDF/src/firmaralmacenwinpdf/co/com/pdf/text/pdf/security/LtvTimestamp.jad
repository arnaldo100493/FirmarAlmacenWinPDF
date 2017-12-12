// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LtvTimestamp.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.DocumentException;
import co.com.pdf.text.Rectangle;
import co.com.pdf.text.pdf.*;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            TSAClient

public class LtvTimestamp
{

    public LtvTimestamp()
    {
    }

    public static void timestamp(PdfSignatureAppearance sap, TSAClient tsa, String signatureName)
        throws IOException, DocumentException, GeneralSecurityException
    {
        int contentEstimated = tsa.getTokenSizeEstimate();
        sap.addDeveloperExtension(PdfDeveloperExtension.ESIC_1_7_EXTENSIONLEVEL5);
        sap.setVisibleSignature(new Rectangle(0.0F, 0.0F, 0.0F, 0.0F), 1, signatureName);
        PdfSignature dic = new PdfSignature(PdfName.ADOBE_PPKLITE, PdfName.ETSI_RFC3161);
        dic.put(PdfName.TYPE, PdfName.DOCTIMESTAMP);
        sap.setCryptoDictionary(dic);
        HashMap exc = new HashMap();
        exc.put(PdfName.CONTENTS, new Integer(contentEstimated * 2 + 2));
        sap.preClose(exc);
        InputStream data = sap.getRangeStream();
        MessageDigest messageDigest = tsa.getMessageDigest();
        byte buf[] = new byte[4096];
        int n;
        while((n = data.read(buf)) > 0) 
            messageDigest.update(buf, 0, n);
        byte tsImprint[] = messageDigest.digest();
        byte tsToken[];
        try
        {
            tsToken = tsa.getTimeStampToken(tsImprint);
        }
        catch(Exception e)
        {
            throw new GeneralSecurityException(e);
        }
        if(contentEstimated + 2 < tsToken.length)
        {
            throw new IOException("Not enough space");
        } else
        {
            byte paddedSig[] = new byte[contentEstimated];
            System.arraycopy(tsToken, 0, paddedSig, 0, tsToken.length);
            PdfDictionary dic2 = new PdfDictionary();
            dic2.put(PdfName.CONTENTS, (new PdfString(paddedSig)).setHexWriting(true));
            sap.close(dic2);
            return;
        }
    }
}
