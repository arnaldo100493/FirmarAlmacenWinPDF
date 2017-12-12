// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsAuthentication.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            Certificate, CertificateRequest, TlsCredentials

public interface TlsAuthentication
{

    public abstract void notifyServerCertificate(Certificate certificate)
        throws IOException;

    public abstract TlsCredentials getClientCredentials(CertificateRequest certificaterequest)
        throws IOException;
}
