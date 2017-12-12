// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSProcessableBodyPartOutbound.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.CMSProcessable;
import co.org.bouncy.mail.smime.util.CRLFOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

// Referenced classes of package co.org.bouncy.mail.smime:
//            SMIMEUtil

public class CMSProcessableBodyPartOutbound
    implements CMSProcessable
{

    public CMSProcessableBodyPartOutbound(BodyPart bodyPart)
    {
        this.bodyPart = bodyPart;
    }

    public CMSProcessableBodyPartOutbound(BodyPart bodyPart, String defaultContentTransferEncoding)
    {
        this.bodyPart = bodyPart;
        this.defaultContentTransferEncoding = defaultContentTransferEncoding;
    }

    public void write(OutputStream out)
        throws IOException, CMSException
    {
        try
        {
            if(SMIMEUtil.isCanonicalisationRequired((MimeBodyPart)bodyPart, defaultContentTransferEncoding))
                out = new CRLFOutputStream(out);
            bodyPart.writeTo(out);
        }
        catch(MessagingException e)
        {
            throw new CMSException("can't write BodyPart to stream.", e);
        }
    }

    public Object getContent()
    {
        return bodyPart;
    }

    private BodyPart bodyPart;
    private String defaultContentTransferEncoding;
}
