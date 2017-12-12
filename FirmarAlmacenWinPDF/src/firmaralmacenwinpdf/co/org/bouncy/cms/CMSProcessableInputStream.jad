// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSProcessableInputStream.java

package co.org.bouncy.cms;

import co.org.bouncy.util.io.Streams;
import java.io.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSProcessable, CMSReadable, CMSException

class CMSProcessableInputStream
    implements CMSProcessable, CMSReadable
{

    public CMSProcessableInputStream(InputStream input)
    {
        used = false;
        this.input = input;
    }

    public InputStream getInputStream()
    {
        checkSingleUsage();
        return input;
    }

    public void write(OutputStream zOut)
        throws IOException, CMSException
    {
        checkSingleUsage();
        Streams.pipeAll(input, zOut);
        input.close();
    }

    public Object getContent()
    {
        return getInputStream();
    }

    private synchronized void checkSingleUsage()
    {
        if(used)
        {
            throw new IllegalStateException("CMSProcessableInputStream can only be used once");
        } else
        {
            used = true;
            return;
        }
    }

    private InputStream input;
    private boolean used;
}
