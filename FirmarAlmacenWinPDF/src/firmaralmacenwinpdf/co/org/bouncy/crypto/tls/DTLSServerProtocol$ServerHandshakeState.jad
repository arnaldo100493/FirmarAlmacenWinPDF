// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTLSServerProtocol.java

package co.org.bouncy.crypto.tls;

import java.util.Hashtable;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            DTLSServerProtocol, TlsServer, TlsServerContextImpl, TlsKeyExchange, 
//            TlsCredentials, CertificateRequest, Certificate

protected static class DTLSServerProtocol$ServerHandshakeState
{

    TlsServer server;
    TlsServerContextImpl serverContext;
    int offeredCipherSuites[];
    short offeredCompressionMethods[];
    Hashtable clientExtensions;
    int selectedCipherSuite;
    short selectedCompressionMethod;
    boolean secure_renegotiation;
    boolean expectSessionTicket;
    Hashtable serverExtensions;
    TlsKeyExchange keyExchange;
    TlsCredentials serverCredentials;
    CertificateRequest certificateRequest;
    short clientCertificateType;
    Certificate clientCertificate;

    protected DTLSServerProtocol$ServerHandshakeState()
    {
        server = null;
        serverContext = null;
        selectedCipherSuite = -1;
        selectedCompressionMethod = -1;
        secure_renegotiation = false;
        expectSessionTicket = false;
        serverExtensions = null;
        keyExchange = null;
        serverCredentials = null;
        certificateRequest = null;
        clientCertificateType = -1;
        clientCertificate = null;
    }
}
