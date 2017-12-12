// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   pkcs7_signature.java

package co.org.bouncy.mail.smime.handlers;

import java.awt.datatransfer.DataFlavor;
import javax.activation.ActivationDataFlavor;
import javax.mail.internet.MimeBodyPart;

// Referenced classes of package co.org.bouncy.mail.smime.handlers:
//            PKCS7ContentHandler

public class pkcs7_signature extends PKCS7ContentHandler
{

    public pkcs7_signature()
    {
        super(ADF, DFS);
    }

    private static final ActivationDataFlavor ADF;
    private static final DataFlavor DFS[];

    static 
    {
        ADF = new ActivationDataFlavor(javax/mail/internet/MimeBodyPart, "application/pkcs7-signature", "Signature");
        DFS = (new DataFlavor[] {
            ADF
        });
    }
}
