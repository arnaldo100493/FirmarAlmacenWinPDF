// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsClientProtocol.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.prng.ThreadedSeedGenerator;
import co.org.bouncy.util.Arrays;
import java.io.*;
import java.security.SecureRandom;
import java.util.*;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsProtocol, SecurityParameters, TlsClientContextImpl, TlsSignerCredentials, 
//            TlsCredentials, ProtocolVersion, TlsClient, RecordStream, 
//            Certificate, TlsKeyExchange, TlsAuthentication, CertificateRequest, 
//            NewSessionTicket, TlsUtils, AbstractTlsContext, TlsPeer

public class TlsClientProtocol extends TlsProtocol
{

    private static SecureRandom createSecureRandom()
    {
        ThreadedSeedGenerator tsg = new ThreadedSeedGenerator();
        SecureRandom random = new SecureRandom();
        random.setSeed(tsg.generateSeed(20, true));
        return random;
    }

    public TlsClientProtocol(InputStream input, OutputStream output)
    {
        this(input, output, createSecureRandom());
    }

    public TlsClientProtocol(InputStream input, OutputStream output, SecureRandom secureRandom)
    {
        super(input, output, secureRandom);
        tlsClient = null;
        tlsClientContext = null;
        offeredCipherSuites = null;
        offeredCompressionMethods = null;
        clientExtensions = null;
        keyExchange = null;
        authentication = null;
        certificateRequest = null;
    }

    public void connect(TlsClient tlsClient)
        throws IOException
    {
        if(tlsClient == null)
            throw new IllegalArgumentException("'tlsClient' cannot be null");
        if(this.tlsClient != null)
        {
            throw new IllegalStateException("connect can only be called once");
        } else
        {
            this.tlsClient = tlsClient;
            securityParameters = new SecurityParameters();
            securityParameters.entity = 1;
            securityParameters.clientRandom = createRandomBlock(secureRandom);
            tlsClientContext = new TlsClientContextImpl(secureRandom, securityParameters);
            this.tlsClient.init(tlsClientContext);
            recordStream.init(tlsClientContext);
            sendClientHelloMessage();
            connection_state = 1;
            completeHandshake();
            this.tlsClient.notifyHandshakeComplete();
            return;
        }
    }

    protected AbstractTlsContext getContext()
    {
        return tlsClientContext;
    }

    protected TlsPeer getPeer()
    {
        return tlsClient;
    }

    protected void handleChangeCipherSpecMessage()
        throws IOException
    {
        switch(connection_state)
        {
        case 13: // '\r'
            if(expectSessionTicket)
                failWithError((short)2, (short)40);
            // fall through

        case 14: // '\016'
            connection_state = 15;
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
        case 11: // '\013'
            switch(connection_state)
            {
            case 2: // '\002'
                handleSupplementalData(null);
                // fall through

            case 3: // '\003'
                Certificate serverCertificate = Certificate.parse(buf);
                assertEmpty(buf);
                keyExchange.processServerCertificate(serverCertificate);
                authentication = tlsClient.getAuthentication();
                authentication.notifyServerCertificate(serverCertificate);
                break;

            default:
                failWithError((short)2, (short)10);
                break;
            }
            connection_state = 4;
            break;

        case 20: // '\024'
            switch(connection_state)
            {
            case 15: // '\017'
                processFinishedMessage(buf);
                connection_state = 16;
                break;

            default:
                failWithError((short)2, (short)10);
                break;
            }
            break;

        case 2: // '\002'
            switch(connection_state)
            {
            case 1: // '\001'
                receiveServerHelloMessage(buf);
                connection_state = 2;
                securityParameters.prfAlgorithm = getPRFAlgorithm(selectedCipherSuite);
                securityParameters.compressionAlgorithm = selectedCompressionMethod;
                securityParameters.verifyDataLength = 12;
                recordStream.notifyHelloComplete();
                break;

            default:
                failWithError((short)2, (short)10);
                break;
            }
            break;

        case 23: // '\027'
            switch(connection_state)
            {
            case 2: // '\002'
                handleSupplementalData(readSupplementalDataMessage(buf));
                break;

            default:
                failWithError((short)2, (short)10);
                break;
            }
            break;

        case 14: // '\016'
            switch(connection_state)
            {
            case 2: // '\002'
                handleSupplementalData(null);
                // fall through

            case 3: // '\003'
                keyExchange.skipServerCredentials();
                authentication = null;
                // fall through

            case 4: // '\004'
                keyExchange.skipServerKeyExchange();
                // fall through

            case 5: // '\005'
            case 6: // '\006'
                assertEmpty(buf);
                connection_state = 7;
                Vector clientSupplementalData = tlsClient.getClientSupplementalData();
                if(clientSupplementalData != null)
                    sendSupplementalDataMessage(clientSupplementalData);
                connection_state = 8;
                TlsCredentials clientCreds = null;
                if(certificateRequest == null)
                {
                    keyExchange.skipClientCredentials();
                } else
                {
                    clientCreds = authentication.getClientCredentials(certificateRequest);
                    if(clientCreds == null)
                    {
                        keyExchange.skipClientCredentials();
                        sendCertificateMessage(Certificate.EMPTY_CHAIN);
                    } else
                    {
                        keyExchange.processClientCredentials(clientCreds);
                        sendCertificateMessage(clientCreds.getCertificate());
                    }
                }
                connection_state = 9;
                sendClientKeyExchangeMessage();
                establishMasterSecret(getContext(), keyExchange);
                recordStream.setPendingConnectionState(tlsClient.getCompression(), tlsClient.getCipher());
                connection_state = 10;
                if(clientCreds != null && (clientCreds instanceof TlsSignerCredentials))
                {
                    TlsSignerCredentials signerCreds = (TlsSignerCredentials)clientCreds;
                    byte md5andsha1[] = recordStream.getCurrentHash(null);
                    byte clientCertificateSignature[] = signerCreds.generateCertificateSignature(md5andsha1);
                    sendCertificateVerifyMessage(clientCertificateSignature);
                    connection_state = 11;
                }
                sendChangeCipherSpecMessage();
                connection_state = 12;
                sendFinishedMessage();
                connection_state = 13;
                break;

            default:
                failWithError((short)2, (short)40);
                break;
            }
            break;

        case 12: // '\f'
            switch(connection_state)
            {
            case 2: // '\002'
                handleSupplementalData(null);
                // fall through

            case 3: // '\003'
                keyExchange.skipServerCredentials();
                authentication = null;
                // fall through

            case 4: // '\004'
                keyExchange.processServerKeyExchange(buf);
                assertEmpty(buf);
                break;

            default:
                failWithError((short)2, (short)10);
                break;
            }
            connection_state = 5;
            break;

        case 13: // '\r'
            switch(connection_state)
            {
            case 4: // '\004'
                keyExchange.skipServerKeyExchange();
                // fall through

            case 5: // '\005'
                if(authentication == null)
                    failWithError((short)2, (short)40);
                certificateRequest = CertificateRequest.parse(buf);
                assertEmpty(buf);
                keyExchange.validateCertificateRequest(certificateRequest);
                break;

            default:
                failWithError((short)2, (short)10);
                break;
            }
            connection_state = 6;
            break;

        case 4: // '\004'
            switch(connection_state)
            {
            case 13: // '\r'
                if(!expectSessionTicket)
                    failWithError((short)2, (short)10);
                receiveNewSessionTicketMessage(buf);
                connection_state = 14;
                break;

            default:
                failWithError((short)2, (short)10);
                break;
            }
            // fall through

        case 0: // '\0'
            assertEmpty(buf);
            if(connection_state == 16)
            {
                String message = "Renegotiation not supported";
                raiseWarning((short)100, message);
            }
            break;

        case 1: // '\001'
        case 3: // '\003'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 15: // '\017'
        case 16: // '\020'
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

    protected void handleSupplementalData(Vector serverSupplementalData)
        throws IOException
    {
        tlsClient.processServerSupplementalData(serverSupplementalData);
        connection_state = 3;
        keyExchange = tlsClient.getKeyExchange();
        keyExchange.init(getContext());
    }

    protected void receiveNewSessionTicketMessage(ByteArrayInputStream buf)
        throws IOException
    {
        NewSessionTicket newSessionTicket = NewSessionTicket.parse(buf);
        TlsProtocol.assertEmpty(buf);
        tlsClient.notifyNewSessionTicket(newSessionTicket);
    }

    protected void receiveServerHelloMessage(ByteArrayInputStream buf)
        throws IOException
    {
        ProtocolVersion server_version = TlsUtils.readVersion(buf);
        if(server_version.isDTLS())
            failWithError((short)2, (short)47);
        if(!server_version.equals(recordStream.getReadVersion()))
            failWithError((short)2, (short)47);
        ProtocolVersion client_version = getContext().getClientVersion();
        if(!server_version.isEqualOrEarlierVersionOf(client_version))
            failWithError((short)2, (short)47);
        recordStream.setWriteVersion(server_version);
        getContext().setServerVersion(server_version);
        tlsClient.notifyServerVersion(server_version);
        securityParameters.serverRandom = TlsUtils.readFully(32, buf);
        byte sessionID[] = TlsUtils.readOpaque8(buf);
        if(sessionID.length > 32)
            failWithError((short)2, (short)47);
        tlsClient.notifySessionID(sessionID);
        selectedCipherSuite = TlsUtils.readUint16(buf);
        if(!arrayContains(offeredCipherSuites, selectedCipherSuite) || selectedCipherSuite == 0 || selectedCipherSuite == 255)
            failWithError((short)2, (short)47);
        tlsClient.notifySelectedCipherSuite(selectedCipherSuite);
        short selectedCompressionMethod = TlsUtils.readUint8(buf);
        if(!arrayContains(offeredCompressionMethods, selectedCompressionMethod))
            failWithError((short)2, (short)47);
        tlsClient.notifySelectedCompressionMethod(selectedCompressionMethod);
        Hashtable serverExtensions = readExtensions(buf);
        if(serverExtensions != null)
        {
            Enumeration e = serverExtensions.keys();
            do
            {
                if(!e.hasMoreElements())
                    break;
                Integer extType = (Integer)e.nextElement();
                if(!extType.equals(EXT_RenegotiationInfo) && (clientExtensions == null || clientExtensions.get(extType) == null))
                    failWithError((short)2, (short)110);
            } while(true);
            byte renegExtValue[] = (byte[])(byte[])serverExtensions.get(EXT_RenegotiationInfo);
            if(renegExtValue != null)
            {
                secure_renegotiation = true;
                if(!Arrays.constantTimeAreEqual(renegExtValue, createRenegotiationInfo(TlsUtils.EMPTY_BYTES)))
                    failWithError((short)2, (short)40);
            }
            expectSessionTicket = serverExtensions.containsKey(EXT_SessionTicket);
        }
        tlsClient.notifySecureRenegotiation(secure_renegotiation);
        if(clientExtensions != null)
            tlsClient.processServerExtensions(serverExtensions);
    }

    protected void sendCertificateVerifyMessage(byte data[])
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        TlsUtils.writeUint8((short)15, bos);
        TlsUtils.writeUint24(data.length + 2, bos);
        TlsUtils.writeOpaque16(data, bos);
        byte message[] = bos.toByteArray();
        safeWriteRecord((short)22, message, 0, message.length);
    }

    protected void sendClientHelloMessage()
        throws IOException
    {
        recordStream.setWriteVersion(tlsClient.getClientHelloRecordLayerVersion());
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        TlsUtils.writeUint8((short)1, buf);
        TlsUtils.writeUint24(0, buf);
        ProtocolVersion client_version = tlsClient.getClientVersion();
        if(client_version.isDTLS())
            failWithError((short)2, (short)80);
        getContext().setClientVersion(client_version);
        TlsUtils.writeVersion(client_version, buf);
        buf.write(securityParameters.clientRandom);
        TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, buf);
        offeredCipherSuites = tlsClient.getCipherSuites();
        clientExtensions = tlsClient.getClientExtensions();
        boolean noRenegExt = clientExtensions == null || clientExtensions.get(EXT_RenegotiationInfo) == null;
        int count = offeredCipherSuites.length;
        if(noRenegExt)
            count++;
        TlsUtils.writeUint16(2 * count, buf);
        TlsUtils.writeUint16Array(offeredCipherSuites, buf);
        if(noRenegExt)
            TlsUtils.writeUint16(255, buf);
        offeredCompressionMethods = tlsClient.getCompressionMethods();
        TlsUtils.writeUint8((short)offeredCompressionMethods.length, buf);
        TlsUtils.writeUint8Array(offeredCompressionMethods, buf);
        if(clientExtensions != null)
            writeExtensions(buf, clientExtensions);
        byte message[] = buf.toByteArray();
        TlsUtils.writeUint24(message.length - 4, message, 1);
        safeWriteRecord((short)22, message, 0, message.length);
    }

    protected void sendClientKeyExchangeMessage()
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        TlsUtils.writeUint8((short)16, bos);
        TlsUtils.writeUint24(0, bos);
        keyExchange.generateClientKeyExchange(bos);
        byte message[] = bos.toByteArray();
        TlsUtils.writeUint24(message.length - 4, message, 1);
        safeWriteRecord((short)22, message, 0, message.length);
    }

    protected TlsClient tlsClient;
    protected TlsClientContextImpl tlsClientContext;
    protected int offeredCipherSuites[];
    protected short offeredCompressionMethods[];
    protected Hashtable clientExtensions;
    protected int selectedCipherSuite;
    protected short selectedCompressionMethod;
    protected TlsKeyExchange keyExchange;
    protected TlsAuthentication authentication;
    protected CertificateRequest certificateRequest;
}
