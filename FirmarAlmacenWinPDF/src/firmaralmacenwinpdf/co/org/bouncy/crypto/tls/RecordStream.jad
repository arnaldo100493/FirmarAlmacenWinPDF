// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RecordStream.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.Digest;
import java.io.*;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsNullCompression, TlsNullCipher, DeferredHash, TlsFatalAlert, 
//            ProtocolVersion, TlsHandshakeHash, TlsUtils, TlsProtocol, 
//            TlsCipher, TlsCompression, TlsContext

class RecordStream
{

    RecordStream(TlsProtocol handler, InputStream input, OutputStream output)
    {
        pendingCompression = null;
        readCompression = null;
        writeCompression = null;
        pendingCipher = null;
        readCipher = null;
        writeCipher = null;
        readSeqNo = 0L;
        writeSeqNo = 0L;
        buffer = new ByteArrayOutputStream();
        context = null;
        hash = null;
        readVersion = null;
        writeVersion = null;
        restrictReadVersion = true;
        this.handler = handler;
        this.input = input;
        this.output = output;
        readCompression = new TlsNullCompression();
        writeCompression = readCompression;
        readCipher = new TlsNullCipher(context);
        writeCipher = readCipher;
    }

    void init(TlsContext context)
    {
        this.context = context;
        hash = new DeferredHash();
        hash.init(context);
    }

    ProtocolVersion getReadVersion()
    {
        return readVersion;
    }

    void setReadVersion(ProtocolVersion readVersion)
    {
        this.readVersion = readVersion;
    }

    void setWriteVersion(ProtocolVersion writeVersion)
    {
        this.writeVersion = writeVersion;
    }

    void setRestrictReadVersion(boolean enabled)
    {
        restrictReadVersion = enabled;
    }

    void notifyHelloComplete()
    {
        hash = hash.commit();
    }

    void setPendingConnectionState(TlsCompression tlsCompression, TlsCipher tlsCipher)
    {
        pendingCompression = tlsCompression;
        pendingCipher = tlsCipher;
    }

    void sentWriteCipherSpec()
        throws IOException
    {
        if(pendingCompression == null || pendingCipher == null)
        {
            throw new TlsFatalAlert((short)40);
        } else
        {
            writeCompression = pendingCompression;
            writeCipher = pendingCipher;
            writeSeqNo = 0L;
            return;
        }
    }

    void receivedReadCipherSpec()
        throws IOException
    {
        if(pendingCompression == null || pendingCipher == null)
        {
            throw new TlsFatalAlert((short)40);
        } else
        {
            readCompression = pendingCompression;
            readCipher = pendingCipher;
            readSeqNo = 0L;
            return;
        }
    }

    void finaliseHandshake()
        throws IOException
    {
        if(readCompression != pendingCompression || writeCompression != pendingCompression || readCipher != pendingCipher || writeCipher != pendingCipher)
        {
            throw new TlsFatalAlert((short)40);
        } else
        {
            pendingCompression = null;
            pendingCipher = null;
            return;
        }
    }

    public void readRecord()
        throws IOException
    {
        short type = TlsUtils.readUint8(input);
        checkType(type, (short)10);
        if(!restrictReadVersion)
        {
            int version = TlsUtils.readVersionRaw(input);
            if((version & 0xffffff00) != 768)
                throw new TlsFatalAlert((short)47);
        } else
        {
            ProtocolVersion version = TlsUtils.readVersion(input);
            if(readVersion == null)
                readVersion = version;
            else
            if(!version.equals(readVersion))
                throw new TlsFatalAlert((short)47);
        }
        int length = TlsUtils.readUint16(input);
        byte plaintext[] = decodeAndVerify(type, input, length);
        handler.processRecord(type, plaintext, 0, plaintext.length);
    }

    protected byte[] decodeAndVerify(short type, InputStream input, int len)
        throws IOException
    {
        checkLength(len, CIPHERTEXT_LIMIT, (short)22);
        byte buf[] = TlsUtils.readFully(len, input);
        byte decoded[] = readCipher.decodeCiphertext(readSeqNo++, type, buf, 0, buf.length);
        checkLength(decoded.length, COMPRESSED_LIMIT, (short)22);
        OutputStream cOut = readCompression.decompress(buffer);
        if(cOut != buffer)
        {
            cOut.write(decoded, 0, decoded.length);
            cOut.flush();
            decoded = getBufferContents();
        }
        checkLength(decoded.length, PLAINTEXT_LIMIT, (short)30);
        return decoded;
    }

    protected void writeRecord(short type, byte plaintext[], int plaintextOffset, int plaintextLength)
        throws IOException
    {
        checkType(type, (short)80);
        checkLength(plaintextLength, PLAINTEXT_LIMIT, (short)80);
        if(plaintextLength < 1 && type != 23)
            throw new TlsFatalAlert((short)80);
        if(type == 22)
            updateHandshakeData(plaintext, plaintextOffset, plaintextLength);
        OutputStream cOut = writeCompression.compress(buffer);
        byte ciphertext[];
        if(cOut == buffer)
        {
            ciphertext = writeCipher.encodePlaintext(writeSeqNo++, type, plaintext, plaintextOffset, plaintextLength);
        } else
        {
            cOut.write(plaintext, plaintextOffset, plaintextLength);
            cOut.flush();
            byte compressed[] = getBufferContents();
            checkLength(compressed.length, plaintextLength + 1024, (short)80);
            ciphertext = writeCipher.encodePlaintext(writeSeqNo++, type, compressed, 0, compressed.length);
        }
        checkLength(ciphertext.length, CIPHERTEXT_LIMIT, (short)80);
        byte record[] = new byte[ciphertext.length + 5];
        TlsUtils.writeUint8(type, record, 0);
        TlsUtils.writeVersion(writeVersion, record, 1);
        TlsUtils.writeUint16(ciphertext.length, record, 3);
        System.arraycopy(ciphertext, 0, record, 5, ciphertext.length);
        output.write(record);
        output.flush();
    }

    void updateHandshakeData(byte message[], int offset, int len)
    {
        hash.update(message, offset, len);
    }

    byte[] getCurrentHash(byte sender[])
    {
        TlsHandshakeHash d = hash.fork();
        if(context.getServerVersion().isSSL() && sender != null)
            d.update(sender, 0, sender.length);
        return doFinal(d);
    }

    protected void close()
        throws IOException
    {
        IOException e = null;
        try
        {
            input.close();
        }
        catch(IOException ex)
        {
            e = ex;
        }
        try
        {
            output.close();
        }
        catch(IOException ex)
        {
            e = ex;
        }
        if(e != null)
            throw e;
        else
            return;
    }

    protected void flush()
        throws IOException
    {
        output.flush();
    }

    private byte[] getBufferContents()
    {
        byte contents[] = buffer.toByteArray();
        buffer.reset();
        return contents;
    }

    private static byte[] doFinal(Digest d)
    {
        byte bs[] = new byte[d.getDigestSize()];
        d.doFinal(bs, 0);
        return bs;
    }

    private static void checkType(short type, short alertDescription)
        throws IOException
    {
        switch(type)
        {
        default:
            throw new TlsFatalAlert(alertDescription);

        case 20: // '\024'
        case 21: // '\025'
        case 22: // '\026'
        case 23: // '\027'
            return;
        }
    }

    private static void checkLength(int length, int limit, short alertDescription)
        throws IOException
    {
        if(length > limit)
            throw new TlsFatalAlert(alertDescription);
        else
            return;
    }

    private static int PLAINTEXT_LIMIT;
    private static int COMPRESSED_LIMIT;
    private static int CIPHERTEXT_LIMIT;
    private TlsProtocol handler;
    private InputStream input;
    private OutputStream output;
    private TlsCompression pendingCompression;
    private TlsCompression readCompression;
    private TlsCompression writeCompression;
    private TlsCipher pendingCipher;
    private TlsCipher readCipher;
    private TlsCipher writeCipher;
    private long readSeqNo;
    private long writeSeqNo;
    private ByteArrayOutputStream buffer;
    private TlsContext context;
    private TlsHandshakeHash hash;
    private ProtocolVersion readVersion;
    private ProtocolVersion writeVersion;
    private boolean restrictReadVersion;

    static 
    {
        PLAINTEXT_LIMIT = 16384;
        COMPRESSED_LIMIT = PLAINTEXT_LIMIT + 1024;
        CIPHERTEXT_LIMIT = COMPRESSED_LIMIT + 1024;
    }
}
