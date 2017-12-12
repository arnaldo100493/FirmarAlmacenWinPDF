// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfEncryptor.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocumentException;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.RecipientInformation;
import co.org.bouncy.cms.jcajce.JceKeyTransEnvelopedRecipient;
import java.io.IOException;
import java.io.OutputStream;
import java.security.PrivateKey;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfStamper, PdfReader

public final class PdfEncryptor
{

    private PdfEncryptor()
    {
    }

    public static void encrypt(PdfReader reader, OutputStream os, byte userPassword[], byte ownerPassword[], int permissions, boolean strength128Bits)
        throws DocumentException, IOException
    {
        PdfStamper stamper = new PdfStamper(reader, os);
        stamper.setEncryption(userPassword, ownerPassword, permissions, strength128Bits);
        stamper.close();
    }

    public static void encrypt(PdfReader reader, OutputStream os, byte userPassword[], byte ownerPassword[], int permissions, boolean strength128Bits, HashMap newInfo)
        throws DocumentException, IOException
    {
        PdfStamper stamper = new PdfStamper(reader, os);
        stamper.setEncryption(userPassword, ownerPassword, permissions, strength128Bits);
        stamper.setMoreInfo(newInfo);
        stamper.close();
    }

    public static void encrypt(PdfReader reader, OutputStream os, boolean strength, String userPassword, String ownerPassword, int permissions)
        throws DocumentException, IOException
    {
        PdfStamper stamper = new PdfStamper(reader, os);
        stamper.setEncryption(strength, userPassword, ownerPassword, permissions);
        stamper.close();
    }

    public static void encrypt(PdfReader reader, OutputStream os, boolean strength, String userPassword, String ownerPassword, int permissions, HashMap newInfo)
        throws DocumentException, IOException
    {
        PdfStamper stamper = new PdfStamper(reader, os);
        stamper.setEncryption(strength, userPassword, ownerPassword, permissions);
        stamper.setMoreInfo(newInfo);
        stamper.close();
    }

    public static void encrypt(PdfReader reader, OutputStream os, int type, String userPassword, String ownerPassword, int permissions, HashMap newInfo)
        throws DocumentException, IOException
    {
        PdfStamper stamper = new PdfStamper(reader, os);
        stamper.setEncryption(type, userPassword, ownerPassword, permissions);
        stamper.setMoreInfo(newInfo);
        stamper.close();
    }

    public static void encrypt(PdfReader reader, OutputStream os, int type, String userPassword, String ownerPassword, int permissions)
        throws DocumentException, IOException
    {
        PdfStamper stamper = new PdfStamper(reader, os);
        stamper.setEncryption(type, userPassword, ownerPassword, permissions);
        stamper.close();
    }

    public static String getPermissionsVerbose(int permissions)
    {
        StringBuffer buf = new StringBuffer("Allowed:");
        if((0x804 & permissions) == 2052)
            buf.append(" Printing");
        if((8 & permissions) == 8)
            buf.append(" Modify contents");
        if((0x10 & permissions) == 16)
            buf.append(" Copy");
        if((0x20 & permissions) == 32)
            buf.append(" Modify annotations");
        if((0x100 & permissions) == 256)
            buf.append(" Fill in");
        if((0x200 & permissions) == 512)
            buf.append(" Screen readers");
        if((0x400 & permissions) == 1024)
            buf.append(" Assembly");
        if((4 & permissions) == 4)
            buf.append(" Degraded printing");
        return buf.toString();
    }

    public static boolean isPrintingAllowed(int permissions)
    {
        return (0x804 & permissions) == 2052;
    }

    public static boolean isModifyContentsAllowed(int permissions)
    {
        return (8 & permissions) == 8;
    }

    public static boolean isCopyAllowed(int permissions)
    {
        return (0x10 & permissions) == 16;
    }

    public static boolean isModifyAnnotationsAllowed(int permissions)
    {
        return (0x20 & permissions) == 32;
    }

    public static boolean isFillInAllowed(int permissions)
    {
        return (0x100 & permissions) == 256;
    }

    public static boolean isScreenReadersAllowed(int permissions)
    {
        return (0x200 & permissions) == 512;
    }

    public static boolean isAssemblyAllowed(int permissions)
    {
        return (0x400 & permissions) == 1024;
    }

    public static boolean isDegradedPrintingAllowed(int permissions)
    {
        return (4 & permissions) == 4;
    }

    public static byte[] getContent(RecipientInformation recipientInfo, PrivateKey certificateKey, String certificateKeyProvider)
        throws CMSException
    {
        co.org.bouncy.cms.Recipient jceKeyTransRecipient = (new JceKeyTransEnvelopedRecipient(certificateKey)).setProvider(certificateKeyProvider);
        return recipientInfo.getContent(jceKeyTransRecipient);
    }
}
