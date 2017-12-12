// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTLSClientProtocol.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.util.Arrays;
import java.io.*;
import java.security.SecureRandom;
import java.util.*;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            DTLSProtocol, SecurityParameters, TlsClientContextImpl, DTLSRecordLayer, 
//            TlsFatalAlert, DTLSReliableHandshake, TlsSignerCredentials, DTLSTransport, 
//            TlsClient, DatagramTransport, ProtocolVersion, Certificate, 
//            TlsProtocol, TlsKeyExchange, TlsAuthentication, TlsCredentials, 
//            TlsUtils, CertificateRequest, NewSessionTicket, TlsContext

public class DTLSClientProtocol extends DTLSProtocol
{
    protected static class ClientHandshakeState
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

        protected ClientHandshakeState()
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


    public DTLSClientProtocol(SecureRandom secureRandom)
    {
        super(secureRandom);
    }

    public DTLSTransport connect(TlsClient client, DatagramTransport transport)
        throws IOException
    {
        if(client == null)
            throw new IllegalArgumentException("'client' cannot be null");
        if(transport == null)
            throw new IllegalArgumentException("'transport' cannot be null");
        SecurityParameters securityParameters = new SecurityParameters();
        securityParameters.entity = 1;
        securityParameters.clientRandom = TlsProtocol.createRandomBlock(secureRandom);
        ClientHandshakeState state = new ClientHandshakeState();
        state.client = client;
        state.clientContext = new TlsClientContextImpl(secureRandom, securityParameters);
        client.init(state.clientContext);
        DTLSRecordLayer recordLayer = new DTLSRecordLayer(transport, state.clientContext, client, (short)22);
        try
        {
            return clientHandshake(state, recordLayer);
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

    protected DTLSTransport clientHandshake(ClientHandshakeState state, DTLSRecordLayer recordLayer)
        throws IOException
    {
        SecurityParameters securityParameters = state.clientContext.getSecurityParameters();
        DTLSReliableHandshake handshake = new DTLSReliableHandshake(state.clientContext, recordLayer);
        byte clientHelloBody[] = generateClientHello(state, state.client);
        handshake.sendMessage((short)1, clientHelloBody);
        DTLSReliableHandshake.Message serverMessage = handshake.receiveMessage();
        ProtocolVersion server_version = recordLayer.getDiscoveredPeerVersion();
        ProtocolVersion client_version = state.clientContext.getClientVersion();
        if(!server_version.isEqualOrEarlierVersionOf(client_version))
            throw new TlsFatalAlert((short)47);
        state.clientContext.setServerVersion(server_version);
        state.client.notifyServerVersion(server_version);
        for(; serverMessage.getType() == 3; serverMessage = handshake.receiveMessage())
        {
            byte cookie[] = parseHelloVerifyRequest(state.clientContext, serverMessage.getBody());
            byte patched[] = patchClientHelloWithCookie(clientHelloBody, cookie);
            handshake.resetHandshakeMessagesDigest();
            handshake.sendMessage((short)1, patched);
        }

        if(serverMessage.getType() == 2)
        {
            processServerHello(state, serverMessage.getBody());
            serverMessage = handshake.receiveMessage();
        } else
        {
            throw new TlsFatalAlert((short)10);
        }
        securityParameters.prfAlgorithm = TlsProtocol.getPRFAlgorithm(state.selectedCipherSuite);
        securityParameters.compressionAlgorithm = state.selectedCompressionMethod;
        securityParameters.verifyDataLength = 12;
        handshake.notifyHelloComplete();
        if(serverMessage.getType() == 23)
        {
            processServerSupplementalData(state, serverMessage.getBody());
            serverMessage = handshake.receiveMessage();
        } else
        {
            state.client.processServerSupplementalData(null);
        }
        state.keyExchange = state.client.getKeyExchange();
        state.keyExchange.init(state.clientContext);
        if(serverMessage.getType() == 11)
        {
            processServerCertificate(state, serverMessage.getBody());
            serverMessage = handshake.receiveMessage();
        } else
        {
            state.keyExchange.skipServerCredentials();
        }
        if(serverMessage.getType() == 12)
        {
            processServerKeyExchange(state, serverMessage.getBody());
            serverMessage = handshake.receiveMessage();
        } else
        {
            state.keyExchange.skipServerKeyExchange();
        }
        if(serverMessage.getType() == 13)
        {
            processCertificateRequest(state, serverMessage.getBody());
            serverMessage = handshake.receiveMessage();
        }
        if(serverMessage.getType() == 14)
        {
            if(serverMessage.getBody().length != 0)
                throw new TlsFatalAlert((short)50);
        } else
        {
            throw new TlsFatalAlert((short)10);
        }
        Vector clientSupplementalData = state.client.getClientSupplementalData();
        if(clientSupplementalData != null)
        {
            byte supplementalDataBody[] = generateSupplementalData(clientSupplementalData);
            handshake.sendMessage((short)23, supplementalDataBody);
        }
        if(state.certificateRequest != null)
        {
            state.clientCredentials = state.authentication.getClientCredentials(state.certificateRequest);
            Certificate clientCertificate = null;
            if(state.clientCredentials != null)
                clientCertificate = state.clientCredentials.getCertificate();
            if(clientCertificate == null)
                clientCertificate = Certificate.EMPTY_CHAIN;
            byte certificateBody[] = generateCertificate(clientCertificate);
            handshake.sendMessage((short)11, certificateBody);
        }
        if(state.clientCredentials != null)
            state.keyExchange.processClientCredentials(state.clientCredentials);
        else
            state.keyExchange.skipClientCredentials();
        byte clientKeyExchangeBody[] = generateClientKeyExchange(state);
        handshake.sendMessage((short)16, clientKeyExchangeBody);
        TlsProtocol.establishMasterSecret(state.clientContext, state.keyExchange);
        if(state.clientCredentials instanceof TlsSignerCredentials)
        {
            TlsSignerCredentials signerCredentials = (TlsSignerCredentials)state.clientCredentials;
            byte md5andsha1[] = handshake.getCurrentHash();
            byte signature[] = signerCredentials.generateCertificateSignature(md5andsha1);
            byte certificateVerifyBody[] = generateCertificateVerify(state, signature);
            handshake.sendMessage((short)15, certificateVerifyBody);
        }
        recordLayer.initPendingEpoch(state.client.getCipher());
        byte clientVerifyData[] = TlsUtils.calculateVerifyData(state.clientContext, "client finished", handshake.getCurrentHash());
        handshake.sendMessage((short)20, clientVerifyData);
        if(state.expectSessionTicket)
        {
            serverMessage = handshake.receiveMessage();
            if(serverMessage.getType() == 4)
                processNewSessionTicket(state, serverMessage.getBody());
            else
                throw new TlsFatalAlert((short)10);
        }
        byte expectedServerVerifyData[] = TlsUtils.calculateVerifyData(state.clientContext, "server finished", handshake.getCurrentHash());
        serverMessage = handshake.receiveMessage();
        if(serverMessage.getType() == 20)
            processFinished(serverMessage.getBody(), expectedServerVerifyData);
        else
            throw new TlsFatalAlert((short)10);
        handshake.finish();
        state.client.notifyHandshakeComplete();
        return new DTLSTransport(recordLayer);
    }

    protected byte[] generateCertificateVerify(ClientHandshakeState state, byte signature[])
        throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        TlsUtils.writeOpaque16(signature, buf);
        return buf.toByteArray();
    }

    protected byte[] generateClientHello(ClientHandshakeState state, TlsClient client)
        throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ProtocolVersion client_version = client.getClientVersion();
        if(!client_version.isDTLS())
            throw new TlsFatalAlert((short)80);
        state.clientContext.setClientVersion(client_version);
        TlsUtils.writeVersion(client_version, buf);
        buf.write(state.clientContext.getSecurityParameters().getClientRandom());
        TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, buf);
        TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, buf);
        state.offeredCipherSuites = client.getCipherSuites();
        state.clientExtensions = client.getClientExtensions();
        boolean noRenegExt = state.clientExtensions == null || state.clientExtensions.get(TlsProtocol.EXT_RenegotiationInfo) == null;
        int count = state.offeredCipherSuites.length;
        if(noRenegExt)
            count++;
        TlsUtils.writeUint16(2 * count, buf);
        TlsUtils.writeUint16Array(state.offeredCipherSuites, buf);
        if(noRenegExt)
            TlsUtils.writeUint16(255, buf);
        state.offeredCompressionMethods = (new short[] {
            0
        });
        TlsUtils.writeUint8((short)state.offeredCompressionMethods.length, buf);
        TlsUtils.writeUint8Array(state.offeredCompressionMethods, buf);
        if(state.clientExtensions != null)
            TlsProtocol.writeExtensions(buf, state.clientExtensions);
        return buf.toByteArray();
    }

    protected byte[] generateClientKeyExchange(ClientHandshakeState state)
        throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        state.keyExchange.generateClientKeyExchange(buf);
        return buf.toByteArray();
    }

    protected void processCertificateRequest(ClientHandshakeState state, byte body[])
        throws IOException
    {
        if(state.authentication == null)
        {
            throw new TlsFatalAlert((short)40);
        } else
        {
            ByteArrayInputStream buf = new ByteArrayInputStream(body);
            state.certificateRequest = CertificateRequest.parse(buf);
            TlsProtocol.assertEmpty(buf);
            state.keyExchange.validateCertificateRequest(state.certificateRequest);
            return;
        }
    }

    protected void processNewSessionTicket(ClientHandshakeState state, byte body[])
        throws IOException
    {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        NewSessionTicket newSessionTicket = NewSessionTicket.parse(buf);
        TlsProtocol.assertEmpty(buf);
        state.client.notifyNewSessionTicket(newSessionTicket);
    }

    protected void processServerCertificate(ClientHandshakeState state, byte body[])
        throws IOException
    {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        Certificate serverCertificate = Certificate.parse(buf);
        TlsProtocol.assertEmpty(buf);
        state.keyExchange.processServerCertificate(serverCertificate);
        state.authentication = state.client.getAuthentication();
        state.authentication.notifyServerCertificate(serverCertificate);
    }

    protected void processServerHello(ClientHandshakeState state, byte body[])
        throws IOException
    {
        SecurityParameters securityParameters = state.clientContext.getSecurityParameters();
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        ProtocolVersion server_version = TlsUtils.readVersion(buf);
        if(!server_version.equals(state.clientContext.getServerVersion()))
            throw new TlsFatalAlert((short)47);
        securityParameters.serverRandom = TlsUtils.readFully(32, buf);
        byte sessionID[] = TlsUtils.readOpaque8(buf);
        if(sessionID.length > 32)
            throw new TlsFatalAlert((short)47);
        state.client.notifySessionID(sessionID);
        state.selectedCipherSuite = TlsUtils.readUint16(buf);
        if(!TlsProtocol.arrayContains(state.offeredCipherSuites, state.selectedCipherSuite) || state.selectedCipherSuite == 0 || state.selectedCipherSuite == 255)
            throw new TlsFatalAlert((short)47);
        validateSelectedCipherSuite(state.selectedCipherSuite, (short)47);
        state.client.notifySelectedCipherSuite(state.selectedCipherSuite);
        state.selectedCompressionMethod = TlsUtils.readUint8(buf);
        if(!TlsProtocol.arrayContains(state.offeredCompressionMethods, state.selectedCompressionMethod))
            throw new TlsFatalAlert((short)47);
        state.client.notifySelectedCompressionMethod(state.selectedCompressionMethod);
        Hashtable serverExtensions = TlsProtocol.readExtensions(buf);
        if(serverExtensions != null)
        {
            for(Enumeration e = serverExtensions.keys(); e.hasMoreElements();)
            {
                Integer extType = (Integer)e.nextElement();
                if(!extType.equals(TlsProtocol.EXT_RenegotiationInfo) && (state.clientExtensions == null || state.clientExtensions.get(extType) == null))
                    throw new TlsFatalAlert((short)110);
            }

            byte renegExtValue[] = (byte[])(byte[])serverExtensions.get(TlsProtocol.EXT_RenegotiationInfo);
            if(renegExtValue != null)
            {
                state.secure_renegotiation = true;
                if(!Arrays.constantTimeAreEqual(renegExtValue, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES)))
                    throw new TlsFatalAlert((short)40);
            }
            state.expectSessionTicket = serverExtensions.containsKey(TlsProtocol.EXT_SessionTicket);
        }
        state.client.notifySecureRenegotiation(state.secure_renegotiation);
        if(state.clientExtensions != null)
            state.client.processServerExtensions(serverExtensions);
    }

    protected void processServerKeyExchange(ClientHandshakeState state, byte body[])
        throws IOException
    {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        state.keyExchange.processServerKeyExchange(buf);
        TlsProtocol.assertEmpty(buf);
    }

    protected void processServerSupplementalData(ClientHandshakeState state, byte body[])
        throws IOException
    {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        Vector serverSupplementalData = TlsProtocol.readSupplementalDataMessage(buf);
        state.client.processServerSupplementalData(serverSupplementalData);
    }

    protected static byte[] parseHelloVerifyRequest(TlsContext context, byte body[])
        throws IOException
    {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        ProtocolVersion server_version = TlsUtils.readVersion(buf);
        if(!server_version.equals(context.getServerVersion()))
        {
            throw new TlsFatalAlert((short)47);
        } else
        {
            byte cookie[] = TlsUtils.readOpaque8(buf);
            TlsProtocol.assertEmpty(buf);
            return cookie;
        }
    }

    protected static byte[] patchClientHelloWithCookie(byte clientHelloBody[], byte cookie[])
        throws IOException
    {
        int sessionIDPos = 34;
        int sessionIDLength = TlsUtils.readUint8(clientHelloBody, sessionIDPos);
        int cookieLengthPos = sessionIDPos + 1 + sessionIDLength;
        int cookiePos = cookieLengthPos + 1;
        byte patched[] = new byte[clientHelloBody.length + cookie.length];
        System.arraycopy(clientHelloBody, 0, patched, 0, cookieLengthPos);
        TlsUtils.writeUint8((short)cookie.length, patched, cookieLengthPos);
        System.arraycopy(cookie, 0, patched, cookiePos, cookie.length);
        System.arraycopy(clientHelloBody, cookiePos, patched, cookiePos + cookie.length, clientHelloBody.length - cookiePos);
        return patched;
    }
}
