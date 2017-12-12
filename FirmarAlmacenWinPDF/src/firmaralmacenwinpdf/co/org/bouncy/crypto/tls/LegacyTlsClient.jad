// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LegacyTlsClient.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            DefaultTlsClient, LegacyTlsAuthentication, CertificateVerifyer, TlsAuthentication

/**
 * @deprecated Class LegacyTlsClient is deprecated
 */

public class LegacyTlsClient extends DefaultTlsClient
{

    /**
     * @deprecated Method LegacyTlsClient is deprecated
     */

    public LegacyTlsClient(CertificateVerifyer verifyer)
    {
        this.verifyer = verifyer;
    }

    public TlsAuthentication getAuthentication()
        throws IOException
    {
        return new LegacyTlsAuthentication(verifyer);
    }

    /**
     * @deprecated Field verifyer is deprecated
     */
    protected CertificateVerifyer verifyer;
}
