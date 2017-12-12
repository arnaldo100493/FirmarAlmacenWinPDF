// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMECompressedParser.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.cms.CMSCompressedDataParser;
import co.org.bouncy.cms.CMSException;
import java.io.*;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.*;

public class SMIMECompressedParser extends CMSCompressedDataParser
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

    public SMIMECompressedParser(MimeBodyPart message)
        throws MessagingException, CMSException
    {
        this(message, 0);
    }

    public SMIMECompressedParser(MimeMessage message)
        throws MessagingException, CMSException
    {
        this(message, 0);
    }

    public SMIMECompressedParser(MimeBodyPart message, int bufferSize)
        throws MessagingException, CMSException
    {
        super(getInputStream(message, bufferSize));
        this.message = message;
    }

    public SMIMECompressedParser(MimeMessage message, int bufferSize)
        throws MessagingException, CMSException
    {
        super(getInputStream(message, bufferSize));
        this.message = message;
    }

    public MimePart getCompressedContent()
    {
        return message;
    }

    private final MimePart message;
}
