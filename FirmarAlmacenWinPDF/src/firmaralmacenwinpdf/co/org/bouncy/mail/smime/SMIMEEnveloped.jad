// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMEEnveloped.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.cms.CMSEnvelopedData;
import co.org.bouncy.cms.CMSException;
import java.io.IOException;
import java.io.InputStream;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.*;

public class SMIMEEnveloped extends CMSEnvelopedData
{

    private static InputStream getInputStream(Part bodyPart)
        throws MessagingException
    {
        try
        {
            return bodyPart.getInputStream();
        }
        catch(IOException e)
        {
            throw new MessagingException((new StringBuilder()).append("can't extract input stream: ").append(e).toString());
        }
    }

    public SMIMEEnveloped(MimeBodyPart message)
        throws MessagingException, CMSException
    {
        super(getInputStream(message));
        this.message = message;
    }

    public SMIMEEnveloped(MimeMessage message)
        throws MessagingException, CMSException
    {
        super(getInputStream(message));
        this.message = message;
    }

    public MimePart getEncryptedContent()
    {
        return message;
    }

    MimePart message;
}
