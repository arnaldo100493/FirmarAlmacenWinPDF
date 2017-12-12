// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMEGenerator.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.cms.CMSEnvelopedGenerator;
import co.org.bouncy.util.Strings;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.*;
import javax.crypto.KeyGenerator;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

// Referenced classes of package co.org.bouncy.mail.smime:
//            SMIMEException

public class SMIMEGenerator
{

    protected SMIMEGenerator()
    {
        useBase64 = true;
        encoding = "base64";
    }

    public void setContentTransferEncoding(String encoding)
    {
        this.encoding = encoding;
        useBase64 = Strings.toLowerCase(encoding).equals("base64");
    }

    protected MimeBodyPart makeContentBodyPart(MimeBodyPart content)
        throws SMIMEException
    {
        try
        {
            MimeMessage msg = new MimeMessage((Session)null);
            Enumeration e = content.getAllHeaders();
            msg.setDataHandler(content.getDataHandler());
            Header hdr;
            for(; e.hasMoreElements(); msg.setHeader(hdr.getName(), hdr.getValue()))
                hdr = (Header)e.nextElement();

            msg.saveChanges();
            e = msg.getAllHeaders();
            do
            {
                if(!e.hasMoreElements())
                    break;
                Header hdr = (Header)e.nextElement();
                if(Strings.toLowerCase(hdr.getName()).startsWith("content-"))
                    content.setHeader(hdr.getName(), hdr.getValue());
            } while(true);
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("exception saving message state.", e);
        }
        return content;
    }

    protected MimeBodyPart makeContentBodyPart(MimeMessage message)
        throws SMIMEException
    {
        MimeBodyPart content = new MimeBodyPart();
        message.removeHeader("Message-Id");
        message.removeHeader("Mime-Version");
        MessagingException e;
        try
        {
            if(message.getContent() instanceof Multipart)
            {
                content.setContent(message.getRawInputStream(), message.getContentType());
                extractHeaders(content, message);
                return content;
            }
        }
        // Misplaced declaration of an exception variable
        catch(MessagingException e) { }
        content.setContent(message.getContent(), message.getContentType());
        content.setDataHandler(message.getDataHandler());
        extractHeaders(content, message);
          goto _L1
        e;
        throw new SMIMEException("exception saving message state.", e);
        e;
        throw new SMIMEException("exception getting message content.", e);
_L1:
        return content;
    }

    private void extractHeaders(MimeBodyPart content, MimeMessage message)
        throws MessagingException
    {
        Header hdr;
        for(Enumeration e = message.getAllHeaders(); e.hasMoreElements(); content.addHeader(hdr.getName(), hdr.getValue()))
            hdr = (Header)e.nextElement();

    }

    protected KeyGenerator createSymmetricKeyGenerator(String encryptionOID, Provider provider)
        throws NoSuchAlgorithmException
    {
        try
        {
            return createKeyGenerator(encryptionOID, provider);
        }
        catch(NoSuchAlgorithmException e)
        {
            try
            {
                String algName = (String)BASE_CIPHER_NAMES.get(encryptionOID);
                if(algName != null)
                    return createKeyGenerator(algName, provider);
            }
            catch(NoSuchAlgorithmException ex) { }
            if(provider != null)
                return createSymmetricKeyGenerator(encryptionOID, null);
            else
                throw e;
        }
    }

    private KeyGenerator createKeyGenerator(String algName, Provider provider)
        throws NoSuchAlgorithmException
    {
        if(provider != null)
            return KeyGenerator.getInstance(algName, provider);
        else
            return KeyGenerator.getInstance(algName);
    }

    private static Map BASE_CIPHER_NAMES;
    protected boolean useBase64;
    protected String encoding;

    static 
    {
        BASE_CIPHER_NAMES = new HashMap();
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.DES_EDE3_CBC, "DESEDE");
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.AES128_CBC, "AES");
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.AES192_CBC, "AES");
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.AES256_CBC, "AES");
    }
}
