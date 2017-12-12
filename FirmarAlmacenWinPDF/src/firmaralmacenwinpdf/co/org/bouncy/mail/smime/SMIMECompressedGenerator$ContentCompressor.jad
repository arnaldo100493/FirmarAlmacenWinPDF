// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMECompressedGenerator.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.cms.CMSCompressedDataStreamGenerator;
import java.io.IOException;
import java.io.OutputStream;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

// Referenced classes of package co.org.bouncy.mail.smime:
//            SMIMEStreamingProcessor, SMIMECompressedGenerator

private class SMIMECompressedGenerator$ContentCompressor
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

    SMIMECompressedGenerator$ContentCompressor(MimeBodyPart content, String compressionOid)
    {
        this$0 = SMIMECompressedGenerator.this;
        super();
        _content = content;
        _compressionOid = compressionOid;
    }
}
