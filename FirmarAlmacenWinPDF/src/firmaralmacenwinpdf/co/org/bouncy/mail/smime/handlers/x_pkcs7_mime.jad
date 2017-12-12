// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   x_pkcs7_mime.java

package co.org.bouncy.mail.smime.handlers;

import java.awt.datatransfer.DataFlavor;
import javax.activation.ActivationDataFlavor;
import javax.mail.internet.MimeBodyPart;

// Referenced classes of package co.org.bouncy.mail.smime.handlers:
//            PKCS7ContentHandler

public class x_pkcs7_mime extends PKCS7ContentHandler
{

    public x_pkcs7_mime()
    {
        super(ADF, DFS);
    }

    private static final ActivationDataFlavor ADF;
    private static final DataFlavor DFS[];

    static 
    {
        ADF = new ActivationDataFlavor(javax/mail/internet/MimeBodyPart, "application/x-pkcs7-mime", "Encrypted Data");
        DFS = (new DataFlavor[] {
            ADF
        });
    }
}
