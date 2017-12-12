// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMESigned.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.cms.*;
import java.io.*;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.*;

// Referenced classes of package co.org.bouncy.mail.smime:
//            CMSProcessableBodyPartInbound, SMIMEException, SMIMEUtil

public class SMIMESigned extends CMSSignedData
{

    private static InputStream getInputStream(Part bodyPart)
        throws MessagingException
    {
        try
        {
            if(bodyPart.isMimeType("multipart/signed"))
                throw new MessagingException("attempt to create signed data object from multipart content - use MimeMultipart constructor.");
            else
                return bodyPart.getInputStream();
        }
        catch(IOException e)
        {
            throw new MessagingException((new StringBuilder()).append("can't extract input stream: ").append(e).toString());
        }
    }

    public SMIMESigned(MimeMultipart message)
        throws MessagingException, CMSException
    {
        super(new CMSProcessableBodyPartInbound(message.getBodyPart(0)), getInputStream(message.getBodyPart(1)));
        this.message = message;
        content = (MimeBodyPart)message.getBodyPart(0);
    }

    public SMIMESigned(MimeMultipart message, String defaultContentTransferEncoding)
        throws MessagingException, CMSException
    {
        super(new CMSProcessableBodyPartInbound(message.getBodyPart(0), defaultContentTransferEncoding), getInputStream(message.getBodyPart(1)));
        this.message = message;
        content = (MimeBodyPart)message.getBodyPart(0);
    }

    public SMIMESigned(Part message)
        throws MessagingException, CMSException, SMIMEException
    {
        super(getInputStream(message));
        this.message = message;
        CMSProcessable cont = getSignedContent();
        if(cont != null)
        {
            byte contBytes[] = (byte[])(byte[])cont.getContent();
            content = SMIMEUtil.toMimeBodyPart(contBytes);
        }
    }

    public MimeBodyPart getContent()
    {
        return content;
    }

    public MimeMessage getContentAsMimeMessage(Session session)
        throws MessagingException, IOException
    {
        Object content = getSignedContent().getContent();
        byte contentBytes[] = null;
        if(content instanceof byte[])
            contentBytes = (byte[])(byte[])content;
        else
        if(content instanceof MimePart)
        {
            MimePart part = (MimePart)content;
            ByteArrayOutputStream out;
            if(part.getSize() > 0)
                out = new ByteArrayOutputStream(part.getSize());
            else
                out = new ByteArrayOutputStream();
            part.writeTo(out);
            contentBytes = out.toByteArray();
        } else
        {
            String type = "<null>";
            if(content != null)
                type = content.getClass().getName();
            throw new MessagingException((new StringBuilder()).append("Could not transfrom content of type ").append(type).append(" into MimeMessage.").toString());
        }
        if(contentBytes != null)
        {
            ByteArrayInputStream in = new ByteArrayInputStream(contentBytes);
            return new MimeMessage(session, in);
        } else
        {
            return null;
        }
    }

    public Object getContentWithSignature()
    {
        return message;
    }

    Object message;
    MimeBodyPart content;

    static 
    {
        MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
        mc.addMailcap("application/pkcs7-signature;; x-java-content-handler=org.bouncy.mail.smime.handlers.pkcs7_signature");
        mc.addMailcap("application/pkcs7-mime;; x-java-content-handler=org.bouncy.mail.smime.handlers.pkcs7_mime");
        mc.addMailcap("application/x-pkcs7-signature;; x-java-content-handler=org.bouncy.mail.smime.handlers.x_pkcs7_signature");
        mc.addMailcap("application/x-pkcs7-mime;; x-java-content-handler=org.bouncy.mail.smime.handlers.x_pkcs7_mime");
        mc.addMailcap("multipart/signed;; x-java-content-handler=org.bouncy.mail.smime.handlers.multipart_signed");
        CommandMap.setDefaultCommandMap(mc);
    }
}
