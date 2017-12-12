// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTLSRecordLayer.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            ByteQueue, DTLSEpoch, TlsNullCipher, TlsFatalAlert, 
//            DatagramTransport, ProtocolVersion, TlsCipher, TlsUtils, 
//            DTLSReplayWindow, TlsPeer, DTLSHandshakeRetransmit, TlsContext

class DTLSRecordLayer
    implements DatagramTransport
{

    DTLSRecordLayer(DatagramTransport transport, TlsContext context, TlsPeer peer, short contentType)
    {
        closed = false;
        failed = false;
        discoveredPeerVersion = null;
        retransmit = null;
        retransmitEpoch = null;
        retransmitExpiry = 0L;
        this.transport = transport;
        this.context = context;
        this.peer = peer;
        inHandshake = true;
        currentEpoch = new DTLSEpoch(0, new TlsNullCipher(context));
        pendingEpoch = null;
        readEpoch = currentEpoch;
        writeEpoch = currentEpoch;
    }

    ProtocolVersion getDiscoveredPeerVersion()
    {
        return discoveredPeerVersion;
    }

    void initPendingEpoch(TlsCipher pendingCipher)
    {
        if(pendingEpoch != null)
        {
            throw new IllegalStateException();
        } else
        {
            pendingEpoch = new DTLSEpoch(writeEpoch.getEpoch() + 1, pendingCipher);
            return;
        }
    }

    void handshakeSuccessful(DTLSHandshakeRetransmit retransmit)
    {
        if(readEpoch == currentEpoch || writeEpoch == currentEpoch)
            throw new IllegalStateException();
        if(retransmit != null)
        {
            this.retransmit = retransmit;
            retransmitEpoch = currentEpoch;
            retransmitExpiry = System.currentTimeMillis() + 0x3a980L;
        }
        inHandshake = false;
        currentEpoch = pendingEpoch;
        pendingEpoch = null;
    }

    void resetWriteEpoch()
    {
        if(retransmitEpoch != null)
            writeEpoch = retransmitEpoch;
        else
            writeEpoch = currentEpoch;
    }

    public int getReceiveLimit()
        throws IOException
    {
        return Math.min(16384, readEpoch.getCipher().getPlaintextLimit(transport.getReceiveLimit() - 13));
    }

    public int getSendLimit()
        throws IOException
    {
        return Math.min(16384, writeEpoch.getCipher().getPlaintextLimit(transport.getSendLimit() - 13));
    }

    public int receive(byte buf[], int off, int len, int waitMillis)
        throws IOException
    {
        byte record[] = null;
_L1:
        int receiveLimit = Math.min(len, getReceiveLimit()) + 13;
        if(record == null || record.length < receiveLimit)
            record = new byte[receiveLimit];
        int received;
        int length;
        short type;
        int epoch;
        DTLSEpoch recordEpoch;
        long seq;
        ProtocolVersion version;
        byte plaintext[];
        short alertLevel;
        short alertDescription;
        try
        {
            if(retransmit != null && System.currentTimeMillis() > retransmitExpiry)
            {
                retransmit = null;
                retransmitEpoch = null;
            }
            received = receiveRecord(record, 0, receiveLimit, waitMillis);
            if(received < 0)
                return received;
        }
        catch(IOException e)
        {
            throw e;
        }
        if(received >= 13) goto _L2; else goto _L1
_L2:
        length = TlsUtils.readUint16(record, 11);
        if(received == length + 13) goto _L3; else goto _L1
_L3:
        type = TlsUtils.readUint8(record, 0);
        type;
        JVM INSTR tableswitch 20 23: default 159
    //                   20 156
    //                   21 156
    //                   22 156
    //                   23 156;
           goto _L1 _L4 _L4 _L4 _L4
_L4:
        epoch = TlsUtils.readUint16(record, 3);
        recordEpoch = null;
        if(epoch == readEpoch.getEpoch())
            recordEpoch = readEpoch;
        else
        if(type == 22 && retransmitEpoch != null && epoch == retransmitEpoch.getEpoch())
            recordEpoch = retransmitEpoch;
        if(recordEpoch != null) goto _L5; else goto _L1
_L5:
        seq = TlsUtils.readUint48(record, 5);
        if(!recordEpoch.getReplayWindow().shouldDiscard(seq)) goto _L6; else goto _L1
_L6:
        version = TlsUtils.readVersion(record, 1);
        if(discoveredPeerVersion == null || discoveredPeerVersion.equals(version)) goto _L7; else goto _L1
_L7:
        plaintext = recordEpoch.getCipher().decodeCiphertext(getMacSequenceNumber(recordEpoch.getEpoch(), seq), type, record, 13, received - 13);
        recordEpoch.getReplayWindow().reportAuthenticated(seq);
        if(discoveredPeerVersion == null)
            discoveredPeerVersion = version;
        type;
        JVM INSTR tableswitch 20 23: default 524
    //                   20 454
    //                   21 376
    //                   22 490
    //                   23 444;
           goto _L8 _L9 _L10 _L11 _L12
_L8:
        break MISSING_BLOCK_LABEL_524;
_L10:
        if(plaintext.length == 2)
        {
            alertLevel = plaintext[0];
            alertDescription = plaintext[1];
            peer.notifyAlertReceived(alertLevel, alertDescription);
            if(alertLevel == 2)
            {
                fail(alertDescription);
                throw new TlsFatalAlert(alertDescription);
            }
            if(alertDescription == 0)
                closeTransport();
        }
          goto _L1
_L12:
        if(!inHandshake) goto _L13; else goto _L1
_L13:
        break MISSING_BLOCK_LABEL_524;
_L9:
        if(plaintext.length == 1 && plaintext[0] == 1) goto _L14; else goto _L1
_L14:
        if(pendingEpoch == null) goto _L1; else goto _L15
_L15:
        readEpoch = pendingEpoch;
          goto _L1
_L11:
        if(inHandshake)
            break MISSING_BLOCK_LABEL_524;
        if(retransmit != null)
            retransmit.receivedHandshakeRecord(epoch, plaintext, 0, plaintext.length);
          goto _L1
        if(!inHandshake && retransmit != null)
        {
            retransmit = null;
            retransmitEpoch = null;
        }
        System.arraycopy(plaintext, 0, buf, off, plaintext.length);
        return plaintext.length;
    }

    public void send(byte buf[], int off, int len)
        throws IOException
    {
        short contentType = 23;
        if(inHandshake || writeEpoch == retransmitEpoch)
        {
            contentType = 22;
            short handshakeType = TlsUtils.readUint8(buf, off);
            if(handshakeType == 20)
            {
                DTLSEpoch nextEpoch = null;
                if(inHandshake)
                    nextEpoch = pendingEpoch;
                else
                if(writeEpoch == retransmitEpoch)
                    nextEpoch = currentEpoch;
                if(nextEpoch == null)
                    throw new IllegalStateException();
                byte data[] = {
                    1
                };
                sendRecord((short)20, data, 0, data.length);
                writeEpoch = nextEpoch;
            }
        }
        sendRecord(contentType, buf, off, len);
    }

    public void close()
        throws IOException
    {
        if(!closed)
        {
            if(inHandshake)
                warn((short)90, "User canceled handshake");
            closeTransport();
        }
    }

    void fail(short alertDescription)
    {
        if(!closed)
        {
            try
            {
                raiseAlert((short)2, alertDescription, null, null);
            }
            catch(Exception e) { }
            failed = true;
            closeTransport();
        }
    }

    void warn(short alertDescription, String message)
        throws IOException
    {
        raiseAlert((short)1, alertDescription, message, null);
    }

    private void closeTransport()
    {
        if(!closed)
        {
            try
            {
                if(!failed)
                    warn((short)0, null);
                transport.close();
            }
            catch(Exception e) { }
            closed = true;
        }
    }

    private void raiseAlert(short alertLevel, short alertDescription, String message, Exception cause)
        throws IOException
    {
        peer.notifyAlertRaised(alertLevel, alertDescription, message, cause);
        byte error[] = new byte[2];
        error[0] = (byte)alertLevel;
        error[1] = (byte)alertDescription;
        sendRecord((short)21, error, 0, 2);
    }

    private int receiveRecord(byte buf[], int off, int len, int waitMillis)
        throws IOException
    {
        if(recordQueue.size() > 0)
        {
            int length = 0;
            if(recordQueue.size() >= 13)
            {
                byte lengthBytes[] = new byte[2];
                recordQueue.read(lengthBytes, 0, 2, 11);
                length = TlsUtils.readUint16(lengthBytes, 0);
            }
            int received = Math.min(recordQueue.size(), 13 + length);
            recordQueue.read(buf, off, received, 0);
            recordQueue.removeData(received);
            return received;
        }
        int received = transport.receive(buf, off, len, waitMillis);
        if(received >= 13)
        {
            int fragmentLength = TlsUtils.readUint16(buf, off + 11);
            int recordLength = 13 + fragmentLength;
            if(received > recordLength)
            {
                recordQueue.addData(buf, off + recordLength, received - recordLength);
                received = recordLength;
            }
        }
        return received;
    }

    private void sendRecord(short contentType, byte buf[], int off, int len)
        throws IOException
    {
        if(len < 1 && contentType != 23)
            throw new TlsFatalAlert((short)80);
        int recordEpoch = writeEpoch.getEpoch();
        long recordSequenceNumber = writeEpoch.allocateSequenceNumber();
        byte ciphertext[] = writeEpoch.getCipher().encodePlaintext(getMacSequenceNumber(recordEpoch, recordSequenceNumber), contentType, buf, off, len);
        if(ciphertext.length > 16384)
        {
            throw new TlsFatalAlert((short)80);
        } else
        {
            byte record[] = new byte[ciphertext.length + 13];
            TlsUtils.writeUint8(contentType, record, 0);
            ProtocolVersion version = discoveredPeerVersion == null ? context.getClientVersion() : discoveredPeerVersion;
            TlsUtils.writeVersion(version, record, 1);
            TlsUtils.writeUint16(recordEpoch, record, 3);
            TlsUtils.writeUint48(recordSequenceNumber, record, 5);
            TlsUtils.writeUint16(ciphertext.length, record, 11);
            System.arraycopy(ciphertext, 0, record, 13, ciphertext.length);
            transport.send(record, 0, record.length);
            return;
        }
    }

    private static long getMacSequenceNumber(int epoch, long sequence_number)
    {
        return (long)epoch << 48 | sequence_number;
    }

    private static final int RECORD_HEADER_LENGTH = 13;
    private static final int MAX_FRAGMENT_LENGTH = 16384;
    private static final long TCP_MSL = 0x1d4c0L;
    private static final long RETRANSMIT_TIMEOUT = 0x3a980L;
    private final DatagramTransport transport;
    private final TlsContext context;
    private final TlsPeer peer;
    private final ByteQueue recordQueue = new ByteQueue();
    private volatile boolean closed;
    private volatile boolean failed;
    private volatile ProtocolVersion discoveredPeerVersion;
    private volatile boolean inHandshake;
    private DTLSEpoch currentEpoch;
    private DTLSEpoch pendingEpoch;
    private DTLSEpoch readEpoch;
    private DTLSEpoch writeEpoch;
    private DTLSHandshakeRetransmit retransmit;
    private DTLSEpoch retransmitEpoch;
    private long retransmitExpiry;
}
