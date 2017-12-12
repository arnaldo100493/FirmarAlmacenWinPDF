// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSProcessableBodyPartInbound.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.CMSProcessable;
import java.io.IOException;
import java.io.OutputStream;
import javax.mail.BodyPart;
import javax.mail.MessagingException;

// Referenced classes of package co.org.bouncy.mail.smime:
//            SMIMEUtil

public class CMSProcessableBodyPartInbound
    implements CMSProcessable
{

    public CMSProcessableBodyPartInbound(BodyPart bodyPart)
    {
        this(bodyPart, "7bit");
    }

    public CMSProcessableBodyPartInbound(BodyPart bodyPart, String defaultContentTransferEncoding)
    {
        this.bodyPart = bodyPart;
        this.defaultContentTransferEncoding = defaultContentTransferEncoding;
    }

    public void write(OutputStream out)
        throws IOException, CMSException
    {
        try
        {
            SMIMEUtil.outputBodyPart(out, bodyPart, defaultContentTransferEncoding);
        }
        catch(MessagingException e)
        {
            throw new CMSException((new StringBuilder()).append("can't write BodyPart to stream: ").append(e).toString(), e);
        }
    }

    public Object getContent()
    {
        return bodyPart;
    }

    private final BodyPart bodyPart;
    private final String defaultContentTransferEncoding;
}
