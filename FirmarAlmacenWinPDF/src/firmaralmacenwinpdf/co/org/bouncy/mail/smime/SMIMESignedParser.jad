// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMESignedParser.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.cms.*;
import co.org.bouncy.operator.DigestCalculatorProvider;
import java.io.*;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.*;

// Referenced classes of package co.org.bouncy.mail.smime:
//            SMIMEException, SMIMEUtil

public class SMIMESignedParser extends CMSSignedDataParser
{
    private static class TemporaryFileInputStream extends BufferedInputStream
    {

        public void close()
            throws IOException
        {
            super.close();
            _file.delete();
        }

        private final File _file;

        TemporaryFileInputStream(File file)
            throws FileNotFoundException
        {
            super(new FileInputStream(file));
            _file = file;
        }
    }


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

    private static File getTmpFile()
        throws MessagingException
    {
        try
        {
            return File.createTempFile("bcMail", ".mime");
        }
        catch(IOException e)
        {
            throw new MessagingException((new StringBuilder()).append("can't extract input stream: ").append(e).toString());
        }
    }

    private static CMSTypedStream getSignedInputStream(BodyPart bodyPart, String defaultContentTransferEncoding, File backingFile)
        throws MessagingException
    {
        try
        {
            OutputStream out = new BufferedOutputStream(new FileOutputStream(backingFile));
            SMIMEUtil.outputBodyPart(out, bodyPart, defaultContentTransferEncoding);
            out.close();
            InputStream in = new TemporaryFileInputStream(backingFile);
            return new CMSTypedStream(in);
        }
        catch(IOException e)
        {
            throw new MessagingException((new StringBuilder()).append("can't extract input stream: ").append(e).toString());
        }
    }

    /**
     * @deprecated Method SMIMESignedParser is deprecated
     */

    public SMIMESignedParser(MimeMultipart message)
        throws MessagingException, CMSException
    {
        this(message, getTmpFile());
    }

    /**
     * @deprecated Method SMIMESignedParser is deprecated
     */

    public SMIMESignedParser(MimeMultipart message, File backingFile)
        throws MessagingException, CMSException
    {
        this(message, "7bit", backingFile);
    }

    /**
     * @deprecated Method SMIMESignedParser is deprecated
     */

    public SMIMESignedParser(MimeMultipart message, String defaultContentTransferEncoding)
        throws MessagingException, CMSException
    {
        this(message, defaultContentTransferEncoding, getTmpFile());
    }

    public SMIMESignedParser(DigestCalculatorProvider digCalcProvider, MimeMultipart message)
        throws MessagingException, CMSException
    {
        this(message, getTmpFile());
    }

    public SMIMESignedParser(DigestCalculatorProvider digCalcProvider, MimeMultipart message, File backingFile)
        throws MessagingException, CMSException
    {
        this(message, "7bit", backingFile);
    }

    public SMIMESignedParser(DigestCalculatorProvider digCalcProvider, MimeMultipart message, String defaultContentTransferEncoding)
        throws MessagingException, CMSException
    {
        this(digCalcProvider, message, defaultContentTransferEncoding, getTmpFile());
    }

    /**
     * @deprecated Method SMIMESignedParser is deprecated
     */

    public SMIMESignedParser(MimeMultipart message, String defaultContentTransferEncoding, File backingFile)
        throws MessagingException, CMSException
    {
        super(getSignedInputStream(message.getBodyPart(0), defaultContentTransferEncoding, backingFile), getInputStream(message.getBodyPart(1)));
        this.message = message;
        content = (MimeBodyPart)message.getBodyPart(0);
        drainContent();
    }

    public SMIMESignedParser(DigestCalculatorProvider digCalcProvider, MimeMultipart message, String defaultContentTransferEncoding, File backingFile)
        throws MessagingException, CMSException
    {
        super(digCalcProvider, getSignedInputStream(message.getBodyPart(0), defaultContentTransferEncoding, backingFile), getInputStream(message.getBodyPart(1)));
        this.message = message;
        content = (MimeBodyPart)message.getBodyPart(0);
        drainContent();
    }

    /**
     * @deprecated Method SMIMESignedParser is deprecated
     */

    public SMIMESignedParser(Part message)
        throws MessagingException, CMSException, SMIMEException
    {
        super(getInputStream(message));
        this.message = message;
        CMSTypedStream cont = getSignedContent();
        if(cont != null)
            content = SMIMEUtil.toWriteOnceBodyPart(cont);
    }

    public SMIMESignedParser(DigestCalculatorProvider digCalcProvider, Part message)
        throws MessagingException, CMSException, SMIMEException
    {
        super(digCalcProvider, getInputStream(message));
        this.message = message;
        CMSTypedStream cont = getSignedContent();
        if(cont != null)
            content = SMIMEUtil.toWriteOnceBodyPart(cont);
    }

    /**
     * @deprecated Method SMIMESignedParser is deprecated
     */

    public SMIMESignedParser(Part message, File file)
        throws MessagingException, CMSException, SMIMEException
    {
        super(getInputStream(message));
        this.message = message;
        CMSTypedStream cont = getSignedContent();
        if(cont != null)
            content = SMIMEUtil.toMimeBodyPart(cont, file);
    }

    public SMIMESignedParser(DigestCalculatorProvider digCalcProvider, Part message, File file)
        throws MessagingException, CMSException, SMIMEException
    {
        super(digCalcProvider, getInputStream(message));
        this.message = message;
        CMSTypedStream cont = getSignedContent();
        if(cont != null)
            content = SMIMEUtil.toMimeBodyPart(cont, file);
    }

    public MimeBodyPart getContent()
    {
        return content;
    }

    public MimeMessage getContentAsMimeMessage(Session session)
        throws MessagingException, IOException
    {
        if(message instanceof MimeMultipart)
        {
            BodyPart bp = ((MimeMultipart)message).getBodyPart(0);
            return new MimeMessage(session, bp.getInputStream());
        } else
        {
            return new MimeMessage(session, getSignedContent().getContentStream());
        }
    }

    public Object getContentWithSignature()
    {
        return message;
    }

    private void drainContent()
        throws CMSException
    {
        try
        {
            getSignedContent().drain();
        }
        catch(IOException e)
        {
            throw new CMSException((new StringBuilder()).append("unable to read content for verification: ").append(e).toString(), e);
        }
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
