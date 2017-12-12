// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSEnvelopedHelper.java

package co.org.bouncy.cms;

import co.org.bouncy.operator.DigestCalculator;
import java.io.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSSecureReadable, CMSException, CMSReadable, CMSEnvelopedHelper

static class CMSEnvelopedHelper$CMSDigestAuthenticatedSecureReadable
    implements CMSSecureReadable
{

    public InputStream getInputStream()
        throws IOException, CMSException
    {
        return new FilterInputStream(readable.getInputStream()) {

            public int read()
                throws IOException
            {
                int b = in.read();
                if(b >= 0)
                    digestCalculator.getOutputStream().write(b);
                return b;
            }

            public int read(byte inBuf[], int inOff, int inLen)
                throws IOException
            {
                int n = in.read(inBuf, inOff, inLen);
                if(n >= 0)
                    digestCalculator.getOutputStream().write(inBuf, inOff, n);
                return n;
            }

            final CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable this$0;

            
            {
                this$0 = CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable.this;
                super(x0);
            }
        }
;
    }

    public byte[] getDigest()
    {
        return digestCalculator.getDigest();
    }

    private DigestCalculator digestCalculator;
    private CMSReadable readable;


    public CMSEnvelopedHelper$CMSDigestAuthenticatedSecureReadable(DigestCalculator digestCalculator, CMSReadable readable)
    {
        this.digestCalculator = digestCalculator;
        this.readable = readable;
    }
}
