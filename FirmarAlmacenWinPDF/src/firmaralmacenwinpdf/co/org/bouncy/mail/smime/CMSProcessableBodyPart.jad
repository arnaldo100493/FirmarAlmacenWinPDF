// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSProcessableBodyPart.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.CMSProcessable;
import java.io.IOException;
import java.io.OutputStream;
import javax.mail.BodyPart;
import javax.mail.MessagingException;

public class CMSProcessableBodyPart
    implements CMSProcessable
{

    public CMSProcessableBodyPart(BodyPart bodyPart)
    {
        this.bodyPart = bodyPart;
    }

    public void write(OutputStream out)
        throws IOException, CMSException
    {
        try
        {
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
}
