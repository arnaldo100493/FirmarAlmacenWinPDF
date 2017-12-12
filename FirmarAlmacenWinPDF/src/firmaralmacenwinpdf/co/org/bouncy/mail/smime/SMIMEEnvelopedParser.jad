// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMEEnvelopedParser.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.cms.CMSEnvelopedDataParser;
import co.org.bouncy.cms.CMSException;
import java.io.*;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.*;

public class SMIMEEnvelopedParser extends CMSEnvelopedDataParser
{

    private static InputStream getInputStream(Part bodyPart, int bufferSize)
        throws MessagingException
    {
        InputStream in;
        try
        {
            in = bodyPart.getInputStream();
            if(bufferSize == 0)
                return new BufferedInputStream(in);
        }
        catch(IOException e)
        {
            throw new MessagingException((new StringBuilder()).append("can't extract input stream: ").append(e).toString());
        }
        return new BufferedInputStream(in, bufferSize);
    }

    public SMIMEEnvelopedParser(MimeBodyPart message)
        throws IOException, MessagingException, CMSException
    {
        this(message, 0);
    }

    public SMIMEEnvelopedParser(MimeMessage message)
        throws IOException, MessagingException, CMSException
    {
        this(message, 0);
    }

    public SMIMEEnvelopedParser(MimeBodyPart message, int bufferSize)
        throws IOException, MessagingException, CMSException
    {
        super(getInputStream(message, bufferSize));
        this.message = message;
    }

    public SMIMEEnvelopedParser(MimeMessage message, int bufferSize)
        throws IOException, MessagingException, CMSException
    {
        super(getInputStream(message, bufferSize));
        this.message = message;
    }

    public MimePart getEncryptedContent()
    {
        return message;
    }

    private final MimePart message;
}
