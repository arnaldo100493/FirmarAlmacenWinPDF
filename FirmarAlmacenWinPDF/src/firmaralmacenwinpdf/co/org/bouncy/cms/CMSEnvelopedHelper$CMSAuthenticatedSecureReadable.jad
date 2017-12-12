// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSEnvelopedHelper.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package co.org.bouncy.cms:
//            CMSSecureReadable, CMSException, CMSReadable, CMSEnvelopedHelper

static class CMSEnvelopedHelper$CMSAuthenticatedSecureReadable
    implements CMSSecureReadable
{

    public InputStream getInputStream()
        throws IOException, CMSException
    {
        return readable.getInputStream();
    }

    private AlgorithmIdentifier algorithm;
    private CMSReadable readable;

    CMSEnvelopedHelper$CMSAuthenticatedSecureReadable(AlgorithmIdentifier algorithm, CMSReadable readable)
    {
        this.algorithm = algorithm;
        this.readable = readable;
    }
}
