// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsClient.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsPeer, TlsClientContext, ProtocolVersion, TlsKeyExchange, 
//            TlsAuthentication, TlsCompression, TlsCipher, NewSessionTicket

public interface TlsClient
    extends TlsPeer
{

    public abstract void init(TlsClientContext tlsclientcontext);

    public abstract ProtocolVersion getClientHelloRecordLayerVersion();

    public abstract ProtocolVersion getClientVersion();

    public abstract int[] getCipherSuites();

    public abstract short[] getCompressionMethods();

    public abstract Hashtable getClientExtensions()
        throws IOException;

    public abstract void notifyServerVersion(ProtocolVersion protocolversion)
        throws IOException;

    public abstract void notifySessionID(byte abyte0[]);

    public abstract void notifySelectedCipherSuite(int i);

    public abstract void notifySelectedCompressionMethod(short word0);

    public abstract void notifySecureRenegotiation(boolean flag)
        throws IOException;

    public abstract void processServerExtensions(Hashtable hashtable)
        throws IOException;

    public abstract void processServerSupplementalData(Vector vector)
        throws IOException;

    public abstract TlsKeyExchange getKeyExchange()
        throws IOException;

    public abstract TlsAuthentication getAuthentication()
        throws IOException;

    public abstract Vector getClientSupplementalData()
        throws IOException;

    public abstract TlsCompression getCompression()
        throws IOException;

    public abstract TlsCipher getCipher()
        throws IOException;

    public abstract void notifyNewSessionTicket(NewSessionTicket newsessionticket)
        throws IOException;

    public abstract void notifyHandshakeComplete()
        throws IOException;
}
