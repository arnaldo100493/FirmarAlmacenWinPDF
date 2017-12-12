// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTLSServerProtocol.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.crypto.util.PublicKeyFactory;
import co.org.bouncy.util.Arrays;
import java.io.*;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            DTLSProtocol, SecurityParameters, TlsServerContextImpl, DTLSRecordLayer, 
//            TlsFatalAlert, DTLSReliableHandshake, DTLSTransport, TlsServer, 
//            DatagramTransport, ProtocolVersion, TlsProtocol, TlsKeyExchange, 
//            TlsCredentials, TlsUtils, Certificate, CertificateRequest, 
//            NewSessionTicket, TlsSigner

public class DTLSServerProtocol extends DTLSProtocol
{
    protected static class ServerHandshakeState
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
        co.org.bouncy.crypto.tls.Certificate clientCertificate;

        protected ServerHandshakeState()
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


    public DTLSServerProtocol(SecureRandom secureRandom)
    {
        super(secureRandom);
        verifyRequests = true;
    }

    public boolean getVerifyRequests()
    {
        return verifyRequests;
    }

    public void setVerifyRequests(boolean verifyRequests)
    {
        this.verifyRequests = verifyRequests;
    }

    public DTLSTransport accept(TlsServer server, DatagramTransport transport)
        throws IOException
    {
        if(server == null)
            throw new IllegalArgumentException("'server' cannot be null");
        if(transport == null)
            throw new IllegalArgumentException("'transport' cannot be null");
        SecurityParameters securityParameters = new SecurityParameters();
        securityParameters.entity = 0;
        securityParameters.serverRandom = TlsProtocol.createRandomBlock(secureRandom);
        ServerHandshakeState state = new ServerHandshakeState();
        state.server = server;
        state.serverContext = new TlsServerContextImpl(secureRandom, securityParameters);
        server.init(state.serverContext);
        DTLSRecordLayer recordLayer = new DTLSRecordLayer(transport, state.serverContext, server, (short)22);
        try
        {
            return serverHandshake(state, recordLayer);
        }
        catch(TlsFatalAlert fatalAlert)
        {
            recordLayer.fail(fatalAlert.getAlertDescription());
            throw fatalAlert;
        }
        catch(IOException e)
        {
            recordLayer.fail((short)80);
            throw e;
        }
        catch(RuntimeException e)
        {
            recordLayer.fail((short)80);
        }
        throw new TlsFatalAlert((short)80);
    }

    public DTLSTransport serverHandshake(ServerHandshakeState state, DTLSRecordLayer recordLayer)
        throws IOException
    {
        SecurityParameters securityParameters = state.serverContext.getSecurityParameters();
        DTLSReliableHandshake handshake = new DTLSReliableHandshake(state.serverContext, recordLayer);
        DTLSReliableHandshake.Message clientMessage = handshake.receiveMessage();
        ProtocolVersion client_version = recordLayer.getDiscoveredPeerVersion();
        state.serverContext.setClientVersion(client_version);
        if(clientMessage.getType() == 1)
            processClientHello(state, clientMessage.getBody());
        else
            throw new TlsFatalAlert((short)10);
        byte serverHelloBody[] = generateServerHello(state);
        handshake.sendMessage((short)2, serverHelloBody);
        securityParameters.prfAlgorithm = TlsProtocol.getPRFAlgorithm(state.selectedCipherSuite);
        securityParameters.compressionAlgorithm = state.selectedCompressionMethod;
        securityParameters.verifyDataLength = 12;
        handshake.notifyHelloComplete();
        Vector serverSupplementalData = state.server.getServerSupplementalData();
        if(serverSupplementalData != null)
        {
            byte supplementalDataBody[] = generateSupplementalData(serverSupplementalData);
            handshake.sendMessage((short)23, supplementalDataBody);
        }
        state.keyExchange = state.server.getKeyExchange();
        state.keyExchange.init(state.serverContext);
        state.serverCredentials = state.server.getCredentials();
        if(state.serverCredentials == null)
        {
            state.keyExchange.skipServerCredentials();
        } else
        {
            state.keyExchange.processServerCredentials(state.serverCredentials);
            byte certificateBody[] = generateCertificate(state.serverCredentials.getCertificate());
            handshake.sendMessage((short)11, certificateBody);
        }
        byte serverKeyExchange[] = state.keyExchange.generateServerKeyExchange();
        if(serverKeyExchange != null)
            handshake.sendMessage((short)12, serverKeyExchange);
        if(state.serverCredentials != null)
        {
            state.certificateRequest = state.server.getCertificateRequest();
            if(state.certificateRequest != null)
            {
                state.keyExchange.validateCertificateRequest(state.certificateRequest);
                byte certificateRequestBody[] = generateCertificateRequest(state, state.certificateRequest);
                handshake.sendMessage((short)13, certificateRequestBody);
            }
        }
        handshake.sendMessage((short)14, TlsUtils.EMPTY_BYTES);
        clientMessage = handshake.receiveMessage();
        if(clientMessage.getType() == 23)
        {
            processClientSupplementalData(state, clientMessage.getBody());
            clientMessage = handshake.receiveMessage();
        } else
        {
            state.server.processClientSupplementalData(null);
        }
        if(state.certificateRequest == null)
            state.keyExchange.skipClientCredentials();
        else
        if(clientMessage.getType() == 11)
        {
            processClientCertificate(state, clientMessage.getBody());
            clientMessage = handshake.receiveMessage();
        } else
        {
            ProtocolVersion equivalentTLSVersion = state.serverContext.getServerVersion().getEquivalentTLSVersion();
            if(ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(equivalentTLSVersion))
                throw new TlsFatalAlert((short)10);
            notifyClientCertificate(state, Certificate.EMPTY_CHAIN);
        }
        if(clientMessage.getType() == 16)
            processClientKeyExchange(state, clientMessage.getBody());
        else
            throw new TlsFatalAlert((short)10);
        recordLayer.initPendingEpoch(state.server.getCipher());
        if(expectCertificateVerifyMessage(state))
        {
            byte certificateVerifyHash[] = handshake.getCurrentHash();
            clientMessage = handshake.receiveMessage();
            if(clientMessage.getType() == 15)
                processCertificateVerify(state, clientMessage.getBody(), certificateVerifyHash);
            else
                throw new TlsFatalAlert((short)10);
        }
        byte clientFinishedHash[] = handshake.getCurrentHash();
        clientMessage = handshake.receiveMessage();
        if(clientMessage.getType() == 20)
        {
            byte expectedClientVerifyData[] = TlsUtils.calculateVerifyData(state.serverContext, "client finished", clientFinishedHash);
            processFinished(clientMessage.getBody(), expectedClientVerifyData);
        } else
        {
            throw new TlsFatalAlert((short)10);
        }
        if(state.expectSessionTicket)
        {
            NewSessionTicket newSessionTicket = state.server.getNewSessionTicket();
            byte newSessionTicketBody[] = generateNewSessionTicket(state, newSessionTicket);
            handshake.sendMessage((short)4, newSessionTicketBody);
        }
        byte serverVerifyData[] = TlsUtils.calculateVerifyData(state.serverContext, "server finished", handshake.getCurrentHash());
        handshake.sendMessage((short)20, serverVerifyData);
        handshake.finish();
        state.server.notifyHandshakeComplete();
        return new DTLSTransport(recordLayer);
    }

    protected byte[] generateCertificateRequest(ServerHandshakeState state, CertificateRequest certificateRequest)
        throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        certificateRequest.encode(buf);
        return buf.toByteArray();
    }

    protected byte[] generateNewSessionTicket(ServerHandshakeState state, NewSessionTicket newSessionTicket)
        throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        newSessionTicket.encode(buf);
        return buf.toByteArray();
    }

    protected byte[] generateServerHello(ServerHandshakeState state)
        throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ProtocolVersion server_version = state.server.getServerVersion();
        if(!server_version.isEqualOrEarlierVersionOf(state.serverContext.getClientVersion()))
            throw new TlsFatalAlert((short)80);
        state.serverContext.setServerVersion(server_version);
        TlsUtils.writeVersion(state.serverContext.getServerVersion(), buf);
        buf.write(state.serverContext.getSecurityParameters().serverRandom);
        TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, buf);
        state.selectedCipherSuite = state.server.getSelectedCipherSuite();
        if(!TlsProtocol.arrayContains(state.offeredCipherSuites, state.selectedCipherSuite) || state.selectedCipherSuite == 0 || state.selectedCipherSuite == 255)
            throw new TlsFatalAlert((short)80);
        validateSelectedCipherSuite(state.selectedCipherSuite, (short)80);
        state.selectedCompressionMethod = state.server.getSelectedCompressionMethod();
        if(!TlsProtocol.arrayContains(state.offeredCompressionMethods, state.selectedCompressionMethod))
            throw new TlsFatalAlert((short)80);
        TlsUtils.writeUint16(state.selectedCipherSuite, buf);
        TlsUtils.writeUint8(state.selectedCompressionMethod, buf);
        state.serverExtensions = state.server.getServerExtensions();
        if(state.secure_renegotiation)
        {
            boolean noRenegExt = state.serverExtensions == null || !state.serverExtensions.containsKey(TlsProtocol.EXT_RenegotiationInfo);
            if(noRenegExt)
            {
                if(state.serverExtensions == null)
                    state.serverExtensions = new Hashtable();
                state.serverExtensions.put(TlsProtocol.EXT_RenegotiationInfo, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES));
            }
        }
        if(state.serverExtensions != null)
        {
            state.expectSessionTicket = state.serverExtensions.containsKey(TlsProtocol.EXT_SessionTicket);
            TlsProtocol.writeExtensions(buf, state.serverExtensions);
        }
        return buf.toByteArray();
    }

    protected void notifyClientCertificate(ServerHandshakeState state, co.org.bouncy.crypto.tls.Certificate clientCertificate)
        throws IOException
    {
        if(state.certificateRequest == null)
            throw new IllegalStateException();
        if(state.clientCertificate != null)
            throw new TlsFatalAlert((short)10);
        state.clientCertificate = clientCertificate;
        if(clientCertificate.isEmpty())
        {
            state.keyExchange.skipClientCredentials();
        } else
        {
            state.clientCertificateType = TlsUtils.getClientCertificateType(clientCertificate, state.serverCredentials.getCertificate());
            state.keyExchange.processClientCertificate(clientCertificate);
        }
        state.server.notifyClientCertificate(clientCertificate);
    }

    protected void processClientCertificate(ServerHandshakeState state, byte body[])
        throws IOException
    {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        co.org.bouncy.crypto.tls.Certificate clientCertificate = Certificate.parse(buf);
        TlsProtocol.assertEmpty(buf);
        notifyClientCertificate(state, clientCertificate);
    }

    protected void processCertificateVerify(ServerHandshakeState state, byte body[], byte certificateVerifyHash[])
        throws IOException
    {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        byte clientCertificateSignature[] = TlsUtils.readOpaque16(buf);
        TlsProtocol.assertEmpty(buf);
        try
        {
            TlsSigner tlsSigner = TlsUtils.createTlsSigner(state.clientCertificateType);
            tlsSigner.init(state.serverContext);
            Certificate x509Cert = state.clientCertificate.getCertificateAt(0);
            co.org.bouncy.asn1.x509.SubjectPublicKeyInfo keyInfo = x509Cert.getSubjectPublicKeyInfo();
            co.org.bouncy.crypto.params.AsymmetricKeyParameter publicKey = PublicKeyFactory.createKey(keyInfo);
            tlsSigner.verifyRawSignature(clientCertificateSignature, publicKey, certificateVerifyHash);
        }
        catch(Exception e)
        {
            throw new TlsFatalAlert((short)51);
        }
    }

    protected void processClientHello(ServerHandshakeState state, byte body[])
        throws IOException
    {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        ProtocolVersion client_version = TlsUtils.readVersion(buf);
        if(!client_version.isDTLS())
            throw new TlsFatalAlert((short)47);
        byte client_random[] = TlsUtils.readFully(32, buf);
        byte sessionID[] = TlsUtils.readOpaque8(buf);
        if(sessionID.length > 32)
            throw new TlsFatalAlert((short)47);
        byte cookie[] = TlsUtils.readOpaque8(buf);
        int cipher_suites_length = TlsUtils.readUint16(buf);
        if(cipher_suites_length < 2 || (cipher_suites_length & 1) != 0)
            throw new TlsFatalAlert((short)50);
        state.offeredCipherSuites = TlsUtils.readUint16Array(cipher_suites_length / 2, buf);
        int compression_methods_length = TlsUtils.readUint8(buf);
        if(compression_methods_length < 1)
            throw new TlsFatalAlert((short)47);
        state.offeredCompressionMethods = TlsUtils.readUint8Array(compression_methods_length, buf);
        state.clientExtensions = TlsProtocol.readExtensions(buf);
        state.serverContext.setClientVersion(client_version);
        state.server.notifyClientVersion(client_version);
        state.serverContext.getSecurityParameters().clientRandom = client_random;
        state.server.notifyOfferedCipherSuites(state.offeredCipherSuites);
        state.server.notifyOfferedCompressionMethods(state.offeredCompressionMethods);
        if(TlsProtocol.arrayContains(state.offeredCipherSuites, 255))
            state.secure_renegotiation = true;
        if(state.clientExtensions != null)
        {
            byte renegExtValue[] = (byte[])(byte[])state.clientExtensions.get(TlsProtocol.EXT_RenegotiationInfo);
            if(renegExtValue != null)
            {
                state.secure_renegotiation = true;
                if(!Arrays.constantTimeAreEqual(renegExtValue, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES)))
                    throw new TlsFatalAlert((short)40);
            }
        }
        state.server.notifySecureRenegotiation(state.secure_renegotiation);
        if(state.clientExtensions != null)
            state.server.processClientExtensions(state.clientExtensions);
    }

    protected void processClientKeyExchange(ServerHandshakeState state, byte body[])
        throws IOException
    {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        state.keyExchange.processClientKeyExchange(buf);
        TlsProtocol.assertEmpty(buf);
        TlsProtocol.establishMasterSecret(state.serverContext, state.keyExchange);
    }

    protected void processClientSupplementalData(ServerHandshakeState state, byte body[])
        throws IOException
    {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        Vector clientSupplementalData = TlsProtocol.readSupplementalDataMessage(buf);
        state.server.processClientSupplementalData(clientSupplementalData);
    }

    protected boolean expectCertificateVerifyMessage(ServerHandshakeState state)
    {
        return state.clientCertificateType >= 0 && TlsUtils.hasSigningCapability(state.clientCertificateType);
    }

    protected boolean verifyRequests;
}
