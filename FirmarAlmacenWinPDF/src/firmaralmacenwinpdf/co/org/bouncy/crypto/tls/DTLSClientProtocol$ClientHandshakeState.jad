// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTLSClientProtocol.java

package co.org.bouncy.crypto.tls;

import java.util.Hashtable;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            DTLSClientProtocol, TlsClient, TlsClientContextImpl, TlsKeyExchange, 
//            TlsAuthentication, CertificateRequest, TlsCredentials

protected static class DTLSClientProtocol$ClientHandshakeState
{

    TlsClient client;
    TlsClientContextImpl clientContext;
    int offeredCipherSuites[];
    short offeredCompressionMethods[];
    Hashtable clientExtensions;
    int selectedCipherSuite;
    short selectedCompressionMethod;
    boolean secure_renegotiation;
    boolean expectSessionTicket;
    TlsKeyExchange keyExchange;
    TlsAuthentication authentication;
    CertificateRequest certificateRequest;
    TlsCredentials clientCredentials;

    protected DTLSClientProtocol$ClientHandshakeState()
    {
        client = null;
        clientContext = null;
        offeredCipherSuites = null;
        offeredCompressionMethods = null;
        clientExtensions = null;
        selectedCipherSuite = -1;
        selectedCompressionMethod = -1;
        secure_renegotiation = false;
        expectSessionTicket = false;
        keyExchange = null;
        authentication = null;
        certificateRequest = null;
        clientCredentials = null;
    }
}
