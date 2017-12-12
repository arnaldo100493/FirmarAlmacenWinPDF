// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsProtocol.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Integers;
import java.io.*;
import java.security.SecureRandom;
import java.util.*;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            ByteQueue, RecordStream, TlsInputStream, TlsOutputStream, 
//            TlsFatalAlert, SupplementalDataEntry, ProtocolVersion, TlsContext, 
//            TlsKeyExchange, AbstractTlsContext, TlsUtils, TlsPeer, 
//            Certificate, SecurityParameters

public abstract class TlsProtocol
{

    public TlsProtocol(InputStream input, OutputStream output, SecureRandom secureRandom)
    {
        applicationDataQueue = new ByteQueue();
        changeCipherSpecQueue = new ByteQueue();
        alertQueue = new ByteQueue();
        handshakeQueue = new ByteQueue();
        tlsInputStream = null;
        tlsOutputStream = null;
        closed = false;
        failedWithError = false;
        appDataReady = false;
        writeExtraEmptyRecords = true;
        expected_verify_data = null;
        securityParameters = null;
        connection_state = 0;
        secure_renegotiation = false;
        expectSessionTicket = false;
        recordStream = new RecordStream(this, input, output);
        this.secureRandom = secureRandom;
    }

    protected abstract AbstractTlsContext getContext();

    protected abstract TlsPeer getPeer();

    protected abstract void handleChangeCipherSpecMessage()
        throws IOException;

    protected abstract void handleHandshakeMessage(short word0, byte abyte0[])
        throws IOException;

    protected void handleWarningMessage(short word0)
        throws IOException
    {
    }

    protected void completeHandshake()
        throws IOException
    {
        expected_verify_data = null;
        while(connection_state != 16) 
            safeReadRecord();
        recordStream.finaliseHandshake();
        ProtocolVersion version = getContext().getServerVersion();
        writeExtraEmptyRecords = version.isEqualOrEarlierVersionOf(ProtocolVersion.TLSv10);
        if(!appDataReady)
        {
            appDataReady = true;
            tlsInputStream = new TlsInputStream(this);
            tlsOutputStream = new TlsOutputStream(this);
        }
    }

    protected void processRecord(short protocol, byte buf[], int offset, int len)
        throws IOException
    {
        switch(protocol)
        {
        default:
            break;

        case 20: // '\024'
            changeCipherSpecQueue.addData(buf, offset, len);
            processChangeCipherSpec();
            break;

        case 21: // '\025'
            alertQueue.addData(buf, offset, len);
            processAlert();
            break;

        case 22: // '\026'
            handshakeQueue.addData(buf, offset, len);
            processHandshake();
            break;

        case 23: // '\027'
            if(!appDataReady)
                failWithError((short)2, (short)10);
            applicationDataQueue.addData(buf, offset, len);
            processApplicationData();
            break;
        }
    }

    private void processHandshake()
        throws IOException
    {
        boolean read;
        do
        {
            short type;
            byte buf[];
label0:
            {
                read = false;
                if(handshakeQueue.size() < 4)
                    continue;
                byte beginning[] = new byte[4];
                handshakeQueue.read(beginning, 0, 4, 0);
                ByteArrayInputStream bis = new ByteArrayInputStream(beginning);
                type = TlsUtils.readUint8(bis);
                int len = TlsUtils.readUint24(bis);
                if(handshakeQueue.size() < len + 4)
                    continue;
                buf = new byte[len];
                handshakeQueue.read(buf, 0, len, 4);
                handshakeQueue.removeData(len + 4);
                switch(type)
                {
                default:
                    break;

                case 0: // '\0'
                    break label0;

                case 20: // '\024'
                    if(expected_verify_data == null)
                        expected_verify_data = createVerifyData(!getContext().isServer());
                    break;
                }
                recordStream.updateHandshakeData(beginning, 0, 4);
                recordStream.updateHandshakeData(buf, 0, len);
            }
            handleHandshakeMessage(type, buf);
            read = true;
        } while(read);
    }

    private void processApplicationData()
    {
    }

    private void processAlert()
        throws IOException
    {
        short description;
        for(; alertQueue.size() >= 2; handleWarningMessage(description))
        {
            byte tmp[] = new byte[2];
            alertQueue.read(tmp, 0, 2, 0);
            alertQueue.removeData(2);
            short level = tmp[0];
            description = tmp[1];
            getPeer().notifyAlertReceived(level, description);
            if(level == 2)
            {
                failedWithError = true;
                closed = true;
                try
                {
                    recordStream.close();
                }
                catch(Exception e) { }
                throw new IOException("Internal TLS error, this could be an attack");
            }
            if(description == 0)
                handleClose(false);
        }

    }

    private void processChangeCipherSpec()
        throws IOException
    {
        for(; changeCipherSpecQueue.size() > 0; handleChangeCipherSpecMessage())
        {
            byte b[] = new byte[1];
            changeCipherSpecQueue.read(b, 0, 1, 0);
            changeCipherSpecQueue.removeData(1);
            if(b[0] != 1)
                failWithError((short)2, (short)10);
            recordStream.receivedReadCipherSpec();
        }

    }

    protected int readApplicationData(byte buf[], int offset, int len)
        throws IOException
    {
        if(len < 1)
            return 0;
        for(; applicationDataQueue.size() == 0; safeReadRecord())
            if(closed)
                if(failedWithError)
                    throw new IOException("Internal TLS error, this could be an attack");
                else
                    return -1;

        len = Math.min(len, applicationDataQueue.size());
        applicationDataQueue.read(buf, offset, len, 0);
        applicationDataQueue.removeData(len);
        return len;
    }

    protected void safeReadRecord()
        throws IOException
    {
        try
        {
            recordStream.readRecord();
        }
        catch(TlsFatalAlert e)
        {
            if(!closed)
                failWithError((short)2, e.getAlertDescription());
            throw e;
        }
        catch(IOException e)
        {
            if(!closed)
                failWithError((short)2, (short)80);
            throw e;
        }
        catch(RuntimeException e)
        {
            if(!closed)
                failWithError((short)2, (short)80);
            throw e;
        }
    }

    protected void safeWriteRecord(short type, byte buf[], int offset, int len)
        throws IOException
    {
        try
        {
            recordStream.writeRecord(type, buf, offset, len);
        }
        catch(TlsFatalAlert e)
        {
            if(!closed)
                failWithError((short)2, e.getAlertDescription());
            throw e;
        }
        catch(IOException e)
        {
            if(!closed)
                failWithError((short)2, (short)80);
            throw e;
        }
        catch(RuntimeException e)
        {
            if(!closed)
                failWithError((short)2, (short)80);
            throw e;
        }
    }

    protected void writeData(byte buf[], int offset, int len)
        throws IOException
    {
        if(closed)
            if(failedWithError)
                throw new IOException("Internal TLS error, this could be an attack");
            else
                throw new IOException("Sorry, connection has been closed, you cannot write more data");
        int toWrite;
        for(; len > 0; len -= toWrite)
        {
            if(writeExtraEmptyRecords)
                safeWriteRecord((short)23, TlsUtils.EMPTY_BYTES, 0, 0);
            toWrite = Math.min(len, 16384);
            safeWriteRecord((short)23, buf, offset, toWrite);
            offset += toWrite;
        }

    }

    public OutputStream getOutputStream()
    {
        return tlsOutputStream;
    }

    public InputStream getInputStream()
    {
        return tlsInputStream;
    }

    protected void failWithError(short alertLevel, short alertDescription)
        throws IOException
    {
        if(!closed)
        {
            closed = true;
            if(alertLevel == 2)
                failedWithError = true;
            raiseAlert(alertLevel, alertDescription, null, null);
            recordStream.close();
            if(alertLevel == 2)
                throw new IOException("Internal TLS error, this could be an attack");
            else
                return;
        } else
        {
            throw new IOException("Internal TLS error, this could be an attack");
        }
    }

    protected void processFinishedMessage(ByteArrayInputStream buf)
        throws IOException
    {
        byte verify_data[] = TlsUtils.readFully(expected_verify_data.length, buf);
        assertEmpty(buf);
        if(!Arrays.constantTimeAreEqual(expected_verify_data, verify_data))
            failWithError((short)2, (short)51);
    }

    protected void raiseAlert(short alertLevel, short alertDescription, String message, Exception cause)
        throws IOException
    {
        getPeer().notifyAlertRaised(alertLevel, alertDescription, message, cause);
        byte error[] = new byte[2];
        error[0] = (byte)alertLevel;
        error[1] = (byte)alertDescription;
        safeWriteRecord((short)21, error, 0, 2);
    }

    protected void raiseWarning(short alertDescription, String message)
        throws IOException
    {
        raiseAlert((short)1, alertDescription, message, null);
    }

    protected void sendCertificateMessage(Certificate certificate)
        throws IOException
    {
        if(certificate == null)
            certificate = Certificate.EMPTY_CHAIN;
        if(certificate.getLength() == 0)
        {
            TlsContext context = getContext();
            if(!context.isServer())
            {
                ProtocolVersion serverVersion = getContext().getServerVersion();
                if(serverVersion.isSSL())
                {
                    String message = (new StringBuilder()).append(serverVersion.toString()).append(" client didn't provide credentials").toString();
                    raiseWarning((short)41, message);
                    return;
                }
            }
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        TlsUtils.writeUint8((short)11, bos);
        TlsUtils.writeUint24(0, bos);
        certificate.encode(bos);
        byte message[] = bos.toByteArray();
        TlsUtils.writeUint24(message.length - 4, message, 1);
        safeWriteRecord((short)22, message, 0, message.length);
    }

    protected void sendChangeCipherSpecMessage()
        throws IOException
    {
        byte message[] = {
            1
        };
        safeWriteRecord((short)20, message, 0, message.length);
        recordStream.sentWriteCipherSpec();
    }

    protected void sendFinishedMessage()
        throws IOException
    {
        byte verify_data[] = createVerifyData(getContext().isServer());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        TlsUtils.writeUint8((short)20, bos);
        TlsUtils.writeUint24(verify_data.length, bos);
        bos.write(verify_data);
        byte message[] = bos.toByteArray();
        safeWriteRecord((short)22, message, 0, message.length);
    }

    protected void sendSupplementalDataMessage(Vector supplementalData)
        throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        TlsUtils.writeUint8((short)23, buf);
        TlsUtils.writeUint24(0, buf);
        writeSupplementalData(buf, supplementalData);
        byte message[] = buf.toByteArray();
        TlsUtils.writeUint24(message.length - 4, message, 1);
        safeWriteRecord((short)22, message, 0, message.length);
    }

    protected byte[] createVerifyData(boolean isServer)
    {
        TlsContext context = getContext();
        if(isServer)
            return TlsUtils.calculateVerifyData(context, "server finished", recordStream.getCurrentHash(TlsUtils.SSL_SERVER));
        else
            return TlsUtils.calculateVerifyData(context, "client finished", recordStream.getCurrentHash(TlsUtils.SSL_CLIENT));
    }

    public void close()
        throws IOException
    {
        handleClose(true);
    }

    protected void handleClose(boolean user_canceled)
        throws IOException
    {
        if(!closed)
        {
            if(user_canceled && !appDataReady)
                raiseWarning((short)90, "User canceled handshake");
            failWithError((short)1, (short)0);
        }
    }

    protected void flush()
        throws IOException
    {
        recordStream.flush();
    }

    protected static boolean arrayContains(short a[], short n)
    {
        for(int i = 0; i < a.length; i++)
            if(a[i] == n)
                return true;

        return false;
    }

    protected static boolean arrayContains(int a[], int n)
    {
        for(int i = 0; i < a.length; i++)
            if(a[i] == n)
                return true;

        return false;
    }

    protected static void assertEmpty(ByteArrayInputStream buf)
        throws IOException
    {
        if(buf.available() > 0)
            throw new TlsFatalAlert((short)50);
        else
            return;
    }

    protected static byte[] createRandomBlock(SecureRandom random)
    {
        byte result[] = new byte[32];
        random.nextBytes(result);
        TlsUtils.writeGMTUnixTime(result, 0);
        return result;
    }

    protected static byte[] createRenegotiationInfo(byte renegotiated_connection[])
        throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        TlsUtils.writeOpaque8(renegotiated_connection, buf);
        return buf.toByteArray();
    }

    protected static void establishMasterSecret(TlsContext context, TlsKeyExchange keyExchange)
        throws IOException
    {
        byte pre_master_secret[] = keyExchange.generatePremasterSecret();
        context.getSecurityParameters().masterSecret = TlsUtils.calculateMasterSecret(context, pre_master_secret);
        if(pre_master_secret != null)
            Arrays.fill(pre_master_secret, (byte)0);
        break MISSING_BLOCK_LABEL_45;
        Exception exception;
        exception;
        if(pre_master_secret != null)
            Arrays.fill(pre_master_secret, (byte)0);
        throw exception;
    }

    protected static Hashtable readExtensions(ByteArrayInputStream input)
        throws IOException
    {
        if(input.available() < 1)
            return null;
        byte extBytes[] = TlsUtils.readOpaque16(input);
        assertEmpty(input);
        ByteArrayInputStream buf = new ByteArrayInputStream(extBytes);
        Hashtable extensions = new Hashtable();
        while(buf.available() > 0) 
        {
            Integer extType = Integers.valueOf(TlsUtils.readUint16(buf));
            byte extValue[] = TlsUtils.readOpaque16(buf);
            if(null != extensions.put(extType, extValue))
                throw new TlsFatalAlert((short)47);
        }
        return extensions;
    }

    protected static Vector readSupplementalDataMessage(ByteArrayInputStream input)
        throws IOException
    {
        byte supp_data[] = TlsUtils.readOpaque24(input);
        assertEmpty(input);
        ByteArrayInputStream buf = new ByteArrayInputStream(supp_data);
        Vector supplementalData = new Vector();
        int supp_data_type;
        byte data[];
        for(; buf.available() > 0; supplementalData.addElement(new SupplementalDataEntry(supp_data_type, data)))
        {
            supp_data_type = TlsUtils.readUint16(buf);
            data = TlsUtils.readOpaque16(buf);
        }

        return supplementalData;
    }

    protected static void writeExtensions(OutputStream output, Hashtable extensions)
        throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        byte extValue[];
        for(Enumeration keys = extensions.keys(); keys.hasMoreElements(); TlsUtils.writeOpaque16(extValue, buf))
        {
            Integer extType = (Integer)keys.nextElement();
            extValue = (byte[])(byte[])extensions.get(extType);
            TlsUtils.writeUint16(extType.intValue(), buf);
        }

        byte extBytes[] = buf.toByteArray();
        TlsUtils.writeOpaque16(extBytes, output);
    }

    protected static void writeSupplementalData(OutputStream output, Vector supplementalData)
        throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        for(int i = 0; i < supplementalData.size(); i++)
        {
            SupplementalDataEntry entry = (SupplementalDataEntry)supplementalData.elementAt(i);
            TlsUtils.writeUint16(entry.getDataType(), buf);
            TlsUtils.writeOpaque16(entry.getData(), buf);
        }

        byte supp_data[] = buf.toByteArray();
        TlsUtils.writeOpaque24(supp_data, output);
    }

    protected static int getPRFAlgorithm(int ciphersuite)
    {
        switch(ciphersuite)
        {
        case 59: // ';'
        case 60: // '<'
        case 61: // '='
        case 62: // '>'
        case 63: // '?'
        case 64: // '@'
        case 103: // 'g'
        case 104: // 'h'
        case 105: // 'i'
        case 106: // 'j'
        case 107: // 'k'
        case 156: 
        case 158: 
        case 160: 
        case 162: 
        case 164: 
        case 49187: 
        case 49189: 
        case 49191: 
        case 49193: 
        case 49195: 
        case 49197: 
        case 49199: 
        case 49201: 
            return 1;

        case 157: 
        case 159: 
        case 161: 
        case 163: 
        case 165: 
        case 49188: 
        case 49190: 
        case 49192: 
        case 49194: 
        case 49196: 
        case 49198: 
        case 49200: 
        case 49202: 
            return 2;
        }
        return 0;
    }

    protected static final Integer EXT_RenegotiationInfo = Integers.valueOf(65281);
    protected static final Integer EXT_SessionTicket = Integers.valueOf(35);
    private static final String TLS_ERROR_MESSAGE = "Internal TLS error, this could be an attack";
    protected static final short CS_START = 0;
    protected static final short CS_CLIENT_HELLO = 1;
    protected static final short CS_SERVER_HELLO = 2;
    protected static final short CS_SERVER_SUPPLEMENTAL_DATA = 3;
    protected static final short CS_SERVER_CERTIFICATE = 4;
    protected static final short CS_SERVER_KEY_EXCHANGE = 5;
    protected static final short CS_CERTIFICATE_REQUEST = 6;
    protected static final short CS_SERVER_HELLO_DONE = 7;
    protected static final short CS_CLIENT_SUPPLEMENTAL_DATA = 8;
    protected static final short CS_CLIENT_CERTIFICATE = 9;
    protected static final short CS_CLIENT_KEY_EXCHANGE = 10;
    protected static final short CS_CERTIFICATE_VERIFY = 11;
    protected static final short CS_CLIENT_CHANGE_CIPHER_SPEC = 12;
    protected static final short CS_CLIENT_FINISHED = 13;
    protected static final short CS_SERVER_SESSION_TICKET = 14;
    protected static final short CS_SERVER_CHANGE_CIPHER_SPEC = 15;
    protected static final short CS_SERVER_FINISHED = 16;
    private ByteQueue applicationDataQueue;
    private ByteQueue changeCipherSpecQueue;
    private ByteQueue alertQueue;
    private ByteQueue handshakeQueue;
    protected RecordStream recordStream;
    protected SecureRandom secureRandom;
    private TlsInputStream tlsInputStream;
    private TlsOutputStream tlsOutputStream;
    private volatile boolean closed;
    private volatile boolean failedWithError;
    private volatile boolean appDataReady;
    private volatile boolean writeExtraEmptyRecords;
    private byte expected_verify_data[];
    protected SecurityParameters securityParameters;
    protected short connection_state;
    protected boolean secure_renegotiation;
    protected boolean expectSessionTicket;

}
