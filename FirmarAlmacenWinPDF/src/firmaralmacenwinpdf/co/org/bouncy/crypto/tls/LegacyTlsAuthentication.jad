// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LegacyTlsAuthentication.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            ServerOnlyTlsAuthentication, TlsFatalAlert, Certificate, CertificateVerifyer

/**
 * @deprecated Class LegacyTlsAuthentication is deprecated
 */

public class LegacyTlsAuthentication extends ServerOnlyTlsAuthentication
{

    public LegacyTlsAuthentication(CertificateVerifyer verifyer)
    {
        this.verifyer = verifyer;
    }

    public void notifyServerCertificate(Certificate serverCertificate)
        throws IOException
    {
        if(!verifyer.isValid(serverCertificate.getCertificateList()))
            throw new TlsFatalAlert((short)90);
        else
            return;
    }

    protected CertificateVerifyer verifyer;
}
