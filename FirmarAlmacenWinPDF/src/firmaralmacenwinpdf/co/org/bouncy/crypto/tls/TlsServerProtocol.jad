// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsServerProtocol.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.crypto.util.PublicKeyFactory;
import co.org.bouncy.util.Arrays;
import java.io.*;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsProtocol, SecurityParameters, TlsServerContextImpl, TlsFatalAlert, 
//            ProtocolVersion, TlsServer, RecordStream, TlsKeyExchange, 
//            TlsCredentials, AbstractTlsContext, Certificate, TlsUtils, 
//            TlsSigner, CertificateRequest, NewSessionTicket, TlsPeer

public class TlsServerProtocol extends TlsProtocol
{

    public TlsServerProtocol(InputStream input, OutputStream output, SecureRandom secureRandom)
    {
        super(input, output, secureRandom);
        tlsServer = null;
        tlsServerContext = null;
        keyExchange = null;
        serverCredentials = null;
        certificateRequest = null;
        clientCertificateType = -1;
        clientCertificate = null;
        certificateVerifyHash = null;
    }

    public void accept(TlsServer tlsServer)
        throws IOException
    {
        if(tlsServer == null)
            throw new IllegalArgumentException("'tlsServer' cannot be null");
        if(this.tlsServer != null)
        {
            throw new IllegalStateException("accept can only be called once");
        } else
        {
            this.tlsServer = tlsServer;
            securityParameters = new SecurityParameters();
            securityParameters.entity = 0;
            securityParameters.serverRandom = createRandomBlock(secureRandom);
            tlsServerContext = new TlsServerContextImpl(secureRandom, securityParameters);
            this.tlsServer.init(tlsServerContext);
            recordStream.init(tlsServerContext);
            recordStream.setRestrictReadVersion(false);
            completeHandshake();
            this.tlsServer.notifyHandshakeComplete();
            return;
        }
    }

    protected AbstractTlsContext getContext()
    {
        return tlsServerContext;
    }

    protected TlsPeer getPeer()
    {
        return tlsServer;
    }

    protected void handleChangeCipherSpecMessage()
        throws IOException
    {
        switch(connection_state)
        {
        case 10: // '\n'
            if(certificateVerifyHash != null)
                failWithError((short)2, (short)10);
            // fall through

        case 11: // '\013'
            connection_state = 12;
            break;

        default:
            failWithError((short)2, (short)40);
            break;
        }
    }

    protected void handleHandshakeMessage(short type, byte data[])
        throws IOException
    {
        ByteArrayInputStream buf = new ByteArrayInputStream(data);
        switch(type)
        {
        case 1: // '\001'
            switch(connection_state)
            {
            case 0: // '\0'
                receiveClientHelloMessage(buf);
                connection_state = 1;
                sendServerHelloMessage();
                connection_state = 2;
                securityParameters.prfAlgorithm = getPRFAlgorithm(selectedCipherSuite);
                securityParameters.compressionAlgorithm = selectedCompressionMethod;
                securityParameters.verifyDataLength = 12;
                recordStream.notifyHelloComplete();
                Vector serverSupplementalData = tlsServer.getServerSupplementalData();
                if(serverSupplementalData != null)
                    sendSupplementalDataMessage(serverSupplementalData);
                connection_state = 3;
                keyExchange = tlsServer.getKeyExchange();
                keyExchange.init(getContext());
                serverCredentials = tlsServer.getCredentials();
                if(serverCredentials == null)
                {
                    keyExchange.skipServerCredentials();
                } else
                {
                    keyExchange.processServerCredentials(serverCredentials);
                    sendCertificateMessage(serverCredentials.getCertificate());
                }
                connection_state = 4;
                byte serverKeyExchange[] = keyExchange.generateServerKeyExchange();
                if(serverKeyExchange != null)
                    sendServerKeyExchangeMessage(serverKeyExchange);
                connection_state = 5;
                if(serverCredentials != null)
                {
                    certificateRequest = tlsServer.getCertificateRequest();
                    if(certificateRequest != null)
                    {
                        keyExchange.validateCertificateRequest(certificateRequest);
                        sendCertificateRequestMessage(certificateRequest);
                    }
                }
                connection_state = 6;
                sendServerHelloDoneMessage();
                connection_state = 7;
                break;

            default:
                failWithError((short)2, (short)10);
                break;
            }
            break;

        case 23: // '\027'
            switch(connection_state)
            {
            case 7: // '\007'
                tlsServer.processClientSupplementalData(readSupplementalDataMessage(buf));
                connection_state = 8;
                break;

            default:
                failWithError((short)2, (short)10);
                break;
            }
            break;

        case 11: // '\013'
            switch(connection_state)
            {
            case 7: // '\007'
                tlsServer.processClientSupplementalData(null);
                // fall through

            case 8: // '\b'
                if(certificateRequest == null)
                    failWithError((short)2, (short)10);
                receiveCertificateMessage(buf);
                connection_state = 9;
                break;

            default:
                failWithError((short)2, (short)10);
                break;
            }
            break;

        case 16: // '\020'
            switch(connection_state)
            {
            case 7: // '\007'
                tlsServer.processClientSupplementalData(null);
                // fall through

            case 8: // '\b'
                if(certificateRequest == null)
                {
                    keyExchange.skipClientCredentials();
                } else
                {
                    ProtocolVersion equivalentTLSVersion = getContext().getServerVersion().getEquivalentTLSVersion();
                    if(ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(equivalentTLSVersion))
                        failWithError((short)2, (short)10);
                    else
                    if(equivalentTLSVersion.isSSL())
                    {
                        if(clientCertificate == null)
                            failWithError((short)2, (short)10);
                    } else
                    {
                        notifyClientCertificate(Certificate.EMPTY_CHAIN);
                    }
                }
                // fall through

            case 9: // '\t'
                receiveClientKeyExchangeMessage(buf);
                connection_state = 10;
                break;

            default:
                failWithError((short)2, (short)10);
                break;
            }
            break;

        case 15: // '\017'
            switch(connection_state)
            {
            case 10: // '\n'
                if(certificateVerifyHash == null)
                    failWithError((short)2, (short)10);
                receiveCertificateVerifyMessage(buf);
                connection_state = 11;
                break;

            default:
                failWithError((short)2, (short)10);
                break;
            }
            break;

        case 20: // '\024'
            switch(connection_state)
            {
            case 12: // '\f'
                processFinishedMessage(buf);
                connection_state = 13;
                if(expectSessionTicket)
                    sendNewSessionTicketMessage(tlsServer.getNewSessionTicket());
                connection_state = 14;
                sendChangeCipherSpecMessage();
                connection_state = 15;
                sendFinishedMessage();
                connection_state = 16;
                break;

            default:
                failWithError((short)2, (short)10);
                break;
            }
            break;

        case 0: // '\0'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 17: // '\021'
        case 18: // '\022'
        case 19: // '\023'
        case 21: // '\025'
        case 22: // '\026'
        default:
            failWithError((short)2, (short)10);
            break;
        }
    }

    protected void handleWarningMessage(short description)
        throws IOException
    {
        switch(description)
        {
        case 41: // ')'
            if(getContext().getServerVersion().isSSL() && certificateRequest != null)
                notifyClientCertificate(Certificate.EMPTY_CHAIN);
            break;

        default:
            super.handleWarningMessage(description);
            break;
        }
    }

    protected void notifyClientCertificate(co.org.bouncy.crypto.tls.Certificate clientCertificate)
        throws IOException
    {
        if(certificateRequest == null)
            throw new IllegalStateException();
        if(this.clientCertificate != null)
            throw new TlsFatalAlert((short)10);
        this.clientCertificate = clientCertificate;
        if(clientCertificate.isEmpty())
        {
            keyExchange.skipClientCredentials();
        } else
        {
            clientCertificateType = TlsUtils.getClientCertificateType(clientCertificate, serverCredentials.getCertificate());
            keyExchange.processClientCertificate(clientCertificate);
        }
        tlsServer.notifyClientCertificate(clientCertificate);
    }

    protected void receiveCertificateMessage(ByteArrayInputStream buf)
        throws IOException
    {
        co.org.bouncy.crypto.tls.Certificate clientCertificate = Certificate.parse(buf);
        assertEmpty(buf);
        notifyClientCertificate(clientCertificate);
    }

    protected void receiveCertificateVerifyMessage(ByteArrayInputStream buf)
        throws IOException
    {
        byte clientCertificateSignature[] = TlsUtils.readOpaque16(buf);
        assertEmpty(buf);
        try
        {
            TlsSigner tlsSigner = TlsUtils.createTlsSigner(clientCertificateType);
            tlsSigner.init(getContext());
            Certificate x509Cert = clientCertificate.getCertificateAt(0);
            co.org.bouncy.asn1.x509.SubjectPublicKeyInfo keyInfo = x509Cert.getSubjectPublicKeyInfo();
            co.org.bouncy.crypto.params.AsymmetricKeyParameter publicKey = PublicKeyFactory.createKey(keyInfo);
            tlsSigner.verifyRawSignature(clientCertificateSignature, publicKey, certificateVerifyHash);
        }
        catch(Exception e)
        {
            throw new TlsFatalAlert((short)51);
        }
    }

    protected void receiveClientHelloMessage(ByteArrayInputStream buf)
        throws IOException
    {
        ProtocolVersion client_version = TlsUtils.readVersion(buf);
        if(client_version.isDTLS())
            failWithError((short)2, (short)47);
        byte client_random[] = TlsUtils.readFully(32, buf);
        byte sessionID[] = TlsUtils.readOpaque8(buf);
        if(sessionID.length > 32)
            failWithError((short)2, (short)47);
        int cipher_suites_length = TlsUtils.readUint16(buf);
        if(cipher_suites_length < 2 || (cipher_suites_length & 1) != 0)
            failWithError((short)2, (short)50);
        offeredCipherSuites = TlsUtils.readUint16Array(cipher_suites_length / 2, buf);
        int compression_methods_length = TlsUtils.readUint8(buf);
        if(compression_methods_length < 1)
            failWithError((short)2, (short)47);
        offeredCompressionMethods = TlsUtils.readUint8Array(compression_methods_length, buf);
        clientExtensions = readExtensions(buf);
        getContext().setClientVersion(client_version);
        tlsServer.notifyClientVersion(client_version);
        securityParameters.clientRandom = client_random;
        tlsServer.notifyOfferedCipherSuites(offeredCipherSuites);
        tlsServer.notifyOfferedCompressionMethods(offeredCompressionMethods);
        if(arrayContains(offeredCipherSuites, 255))
            secure_renegotiation = true;
        if(clientExtensions != null)
        {
            byte renegExtValue[] = (byte[])(byte[])clientExtensions.get(EXT_RenegotiationInfo);
            if(renegExtValue != null)
            {
                secure_renegotiation = true;
                if(!Arrays.constantTimeAreEqual(renegExtValue, createRenegotiationInfo(TlsUtils.EMPTY_BYTES)))
                    failWithError((short)2, (short)40);
            }
        }
        tlsServer.notifySecureRenegotiation(secure_renegotiation);
        if(clientExtensions != null)
            tlsServer.processClientExtensions(clientExtensions);
    }

    protected void receiveClientKeyExchangeMessage(ByteArrayInputStream buf)
        throws IOException
    {
        keyExchange.processClientKeyExchange(buf);
        assertEmpty(buf);
        establishMasterSecret(getContext(), keyExchange);
        recordStream.setPendingConnectionState(tlsServer.getCompression(), tlsServer.getCipher());
        if(expectCertificateVerifyMessage())
            certificateVerifyHash = recordStream.getCurrentHash(null);
    }

    protected void sendCertificateRequestMessage(CertificateRequest certificateRequest)
        throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        TlsUtils.writeUint8((short)13, buf);
        TlsUtils.writeUint24(0, buf);
        certificateRequest.encode(buf);
        byte message[] = buf.toByteArray();
        TlsUtils.writeUint24(message.length - 4, message, 1);
        safeWriteRecord((short)22, message, 0, message.length);
    }

    protected void sendNewSessionTicketMessage(NewSessionTicket newSessionTicket)
        throws IOException
    {
        if(newSessionTicket == null)
        {
            throw new TlsFatalAlert((short)80);
        } else
        {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            TlsUtils.writeUint8((short)4, buf);
            TlsUtils.writeUint24(0, buf);
            newSessionTicket.encode(buf);
            byte message[] = buf.toByteArray();
            TlsUtils.writeUint24(message.length - 4, message, 1);
            safeWriteRecord((short)22, message, 0, message.length);
            return;
        }
    }

    protected void sendServerHelloMessage()
        throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        TlsUtils.writeUint8((short)2, buf);
        TlsUtils.writeUint24(0, buf);
        ProtocolVersion server_version = tlsServer.getServerVersion();
        if(!server_version.isEqualOrEarlierVersionOf(getContext().getClientVersion()))
            failWithError((short)2, (short)80);
        recordStream.setReadVersion(server_version);
        recordStream.setWriteVersion(server_version);
        recordStream.setRestrictReadVersion(true);
        getContext().setServerVersion(server_version);
        TlsUtils.writeVersion(server_version, buf);
        buf.write(securityParameters.serverRandom);
        TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, buf);
        selectedCipherSuite = tlsServer.getSelectedCipherSuite();
        if(!arrayContains(offeredCipherSuites, selectedCipherSuite) || selectedCipherSuite == 0 || selectedCipherSuite == 255)
            failWithError((short)2, (short)80);
        selectedCompressionMethod = tlsServer.getSelectedCompressionMethod();
        if(!arrayContains(offeredCompressionMethods, selectedCompressionMethod))
            failWithError((short)2, (short)80);
        TlsUtils.writeUint16(selectedCipherSuite, buf);
        TlsUtils.writeUint8(selectedCompressionMethod, buf);
        serverExtensions = tlsServer.getServerExtensions();
        if(secure_renegotiation)
        {
            boolean noRenegExt = serverExtensions == null || !serverExtensions.containsKey(EXT_RenegotiationInfo);
            if(noRenegExt)
            {
                if(serverExtensions == null)
                    serverExtensions = new Hashtable();
                serverExtensions.put(EXT_RenegotiationInfo, createRenegotiationInfo(TlsUtils.EMPTY_BYTES));
            }
        }
        if(serverExtensions != null)
        {
            expectSessionTicket = serverExtensions.containsKey(EXT_SessionTicket);
            writeExtensions(buf, serverExtensions);
        }
        byte message[] = buf.toByteArray();
        TlsUtils.writeUint24(message.length - 4, message, 1);
        safeWriteRecord((short)22, message, 0, message.length);
    }

    protected void sendServerHelloDoneMessage()
        throws IOException
    {
        byte message[] = new byte[4];
        TlsUtils.writeUint8((short)14, message, 0);
        TlsUtils.writeUint24(0, message, 1);
        safeWriteRecord((short)22, message, 0, message.length);
    }

    protected void sendServerKeyExchangeMessage(byte serverKeyExchange[])
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        TlsUtils.writeUint8((short)12, bos);
        TlsUtils.writeUint24(serverKeyExchange.length, bos);
        bos.write(serverKeyExchange);
        byte message[] = bos.toByteArray();
        safeWriteRecord((short)22, message, 0, message.length);
    }

    protected boolean expectCertificateVerifyMessage()
    {
        return clientCertificateType >= 0 && TlsUtils.hasSigningCapability(clientCertificateType);
    }

    protected TlsServer tlsServer;
    protected TlsServerContextImpl tlsServerContext;
    protected int offeredCipherSuites[];
    protected short offeredCompressionMethods[];
    protected Hashtable clientExtensions;
    protected int selectedCipherSuite;
    protected short selectedCompressionMethod;
    protected Hashtable serverExtensions;
    protected TlsKeyExchange keyExchange;
    protected TlsCredentials serverCredentials;
    protected CertificateRequest certificateRequest;
    protected short clientCertificateType;
    protected co.org.bouncy.crypto.tls.Certificate clientCertificate;
    protected byte certificateVerifyHash[];
}
