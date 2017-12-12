// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsKeyExchange.java

package co.org.bouncy.crypto.tls;

import java.io.*;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsContext, TlsCredentials, Certificate, CertificateRequest

public interface TlsKeyExchange
{

    public abstract void init(TlsContext tlscontext);

    public abstract void skipServerCredentials()
        throws IOException;

    public abstract void processServerCredentials(TlsCredentials tlscredentials)
        throws IOException;

    public abstract void processServerCertificate(Certificate certificate)
        throws IOException;

    public abstract boolean requiresServerKeyExchange();

    public abstract byte[] generateServerKeyExchange()
        throws IOException;

    public abstract void skipServerKeyExchange()
        throws IOException;

    public abstract void processServerKeyExchange(InputStream inputstream)
        throws IOException;

    public abstract void validateCertificateRequest(CertificateRequest certificaterequest)
        throws IOException;

    public abstract void skipClientCredentials()
        throws IOException;

    public abstract void processClientCredentials(TlsCredentials tlscredentials)
        throws IOException;

    public abstract void processClientCertificate(Certificate certificate)
        throws IOException;

    public abstract void generateClientKeyExchange(OutputStream outputstream)
        throws IOException;

    public abstract void processClientKeyExchange(InputStream inputstream)
        throws IOException;

    public abstract byte[] generatePremasterSecret()
        throws IOException;
}
