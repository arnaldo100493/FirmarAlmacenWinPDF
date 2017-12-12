// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsServer.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsPeer, TlsServerContext, ProtocolVersion, TlsCredentials, 
//            TlsKeyExchange, CertificateRequest, Certificate, TlsCompression, 
//            TlsCipher, NewSessionTicket

public interface TlsServer
    extends TlsPeer
{

    public abstract void init(TlsServerContext tlsservercontext);

    public abstract void notifyClientVersion(ProtocolVersion protocolversion)
        throws IOException;

    public abstract void notifyOfferedCipherSuites(int ai[])
        throws IOException;

    public abstract void notifyOfferedCompressionMethods(short aword0[])
        throws IOException;

    public abstract void notifySecureRenegotiation(boolean flag)
        throws IOException;

    public abstract void processClientExtensions(Hashtable hashtable)
        throws IOException;

    public abstract ProtocolVersion getServerVersion()
        throws IOException;

    public abstract int getSelectedCipherSuite()
        throws IOException;

    public abstract short getSelectedCompressionMethod()
        throws IOException;

    public abstract Hashtable getServerExtensions()
        throws IOException;

    public abstract Vector getServerSupplementalData()
        throws IOException;

    public abstract TlsCredentials getCredentials()
        throws IOException;

    public abstract TlsKeyExchange getKeyExchange()
        throws IOException;

    public abstract CertificateRequest getCertificateRequest();

    public abstract void processClientSupplementalData(Vector vector)
        throws IOException;

    public abstract void notifyClientCertificate(Certificate certificate)
        throws IOException;

    public abstract TlsCompression getCompression()
        throws IOException;

    public abstract TlsCipher getCipher()
        throws IOException;

    public abstract NewSessionTicket getNewSessionTicket()
        throws IOException;

    public abstract void notifyHandshakeComplete()
        throws IOException;
}
