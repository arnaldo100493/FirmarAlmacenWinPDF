// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMECompressedGenerator.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.cms.CMSCompressedDataStreamGenerator;
import java.io.IOException;
import java.io.OutputStream;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

// Referenced classes of package co.org.bouncy.mail.smime:
//            SMIMEGenerator, SMIMEException, SMIMEStreamingProcessor

public class SMIMECompressedGenerator extends SMIMEGenerator
{
    private class ContentCompressor
        implements SMIMEStreamingProcessor
    {

        public void write(OutputStream out)
            throws IOException
        {
            CMSCompressedDataStreamGenerator cGen = new CMSCompressedDataStreamGenerator();
            OutputStream compressed = cGen.open(out, _compressionOid);
            try
            {
                _content.writeTo(compressed);
                compressed.close();
            }
            catch(MessagingException e)
            {
                throw new IOException(e.toString());
            }
        }

        private final MimeBodyPart _content;
        private final String _compressionOid;
        final SMIMECompressedGenerator this$0;

        ContentCompressor(MimeBodyPart content, String compressionOid)
        {
            this$0 = SMIMECompressedGenerator.this;
            super();
            _content = content;
            _compressionOid = compressionOid;
        }
    }


    public SMIMECompressedGenerator()
    {
    }

    private MimeBodyPart make(MimeBodyPart content, String compressionOID)
        throws SMIMEException
    {
        try
        {
            MimeBodyPart data = new MimeBodyPart();
            data.setContent(new ContentCompressor(content, compressionOID), "application/pkcs7-mime; name=\"smime.p7z\"; smime-type=compressed-data");
            data.addHeader("Content-Type", "application/pkcs7-mime; name=\"smime.p7z\"; smime-type=compressed-data");
            data.addHeader("Content-Disposition", "attachment; filename=\"smime.p7z\"");
            data.addHeader("Content-Description", "S/MIME Compressed Message");
            data.addHeader("Content-Transfer-Encoding", encoding);
            return data;
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("exception putting multi-part together.", e);
        }
    }

    public MimeBodyPart generate(MimeBodyPart content, String compressionOID)
        throws SMIMEException
    {
        return make(makeContentBodyPart(content), compressionOID);
    }

    public MimeBodyPart generate(MimeMessage message, String compressionOID)
        throws SMIMEException
    {
        try
        {
            message.saveChanges();
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("unable to save message", e);
        }
        return make(makeContentBodyPart(message), compressionOID);
    }

    public static final String ZLIB = "1.2.840.113549.1.9.16.3.8";
    private static final String COMPRESSED_CONTENT_TYPE = "application/pkcs7-mime; name=\"smime.p7z\"; smime-type=compressed-data";

    static 
    {
        MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
        mc.addMailcap("application/pkcs7-mime;; x-java-content-handler=org.bouncy.mail.smime.handlers.pkcs7_mime");
        mc.addMailcap("application/x-pkcs7-mime;; x-java-content-handler=org.bouncy.mail.smime.handlers.x_pkcs7_mime");
        CommandMap.setDefaultCommandMap(mc);
    }
}
