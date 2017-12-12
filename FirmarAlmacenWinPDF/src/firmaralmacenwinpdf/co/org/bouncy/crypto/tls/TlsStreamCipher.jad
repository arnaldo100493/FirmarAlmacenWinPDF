// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsStreamCipher.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.util.Arrays;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsMac, TlsFatalAlert, TlsCipher, TlsContext, 
//            TlsUtils

public class TlsStreamCipher
    implements TlsCipher
{

    public TlsStreamCipher(TlsContext context, StreamCipher clientWriteCipher, StreamCipher serverWriteCipher, Digest clientWriteDigest, Digest serverWriteDigest, int cipherKeySize)
        throws IOException
    {
        boolean isServer = context.isServer();
        this.context = context;
        encryptCipher = clientWriteCipher;
        decryptCipher = serverWriteCipher;
        int key_block_size = 2 * cipherKeySize + clientWriteDigest.getDigestSize() + serverWriteDigest.getDigestSize();
        byte key_block[] = TlsUtils.calculateKeyBlock(context, key_block_size);
        int offset = 0;
        TlsMac clientWriteMac = new TlsMac(context, clientWriteDigest, key_block, offset, clientWriteDigest.getDigestSize());
        offset += clientWriteDigest.getDigestSize();
        TlsMac serverWriteMac = new TlsMac(context, serverWriteDigest, key_block, offset, serverWriteDigest.getDigestSize());
        offset += serverWriteDigest.getDigestSize();
        KeyParameter clientWriteKey = new KeyParameter(key_block, offset, cipherKeySize);
        offset += cipherKeySize;
        KeyParameter serverWriteKey = new KeyParameter(key_block, offset, cipherKeySize);
        offset += cipherKeySize;
        if(offset != key_block_size)
            throw new TlsFatalAlert((short)80);
        CipherParameters encryptParams;
        CipherParameters decryptParams;
        if(isServer)
        {
            writeMac = serverWriteMac;
            readMac = clientWriteMac;
            encryptCipher = serverWriteCipher;
            decryptCipher = clientWriteCipher;
            encryptParams = serverWriteKey;
            decryptParams = clientWriteKey;
        } else
        {
            writeMac = clientWriteMac;
            readMac = serverWriteMac;
            encryptCipher = clientWriteCipher;
            decryptCipher = serverWriteCipher;
            encryptParams = clientWriteKey;
            decryptParams = serverWriteKey;
        }
        encryptCipher.init(true, encryptParams);
        decryptCipher.init(false, decryptParams);
    }

    public int getPlaintextLimit(int ciphertextLimit)
    {
        return ciphertextLimit - writeMac.getSize();
    }

    public byte[] encodePlaintext(long seqNo, short type, byte plaintext[], int offset, int len)
    {
        byte mac[] = writeMac.calculateMac(seqNo, type, plaintext, offset, len);
        byte outbuf[] = new byte[len + mac.length];
        encryptCipher.processBytes(plaintext, offset, len, outbuf, 0);
        encryptCipher.processBytes(mac, 0, mac.length, outbuf, len);
        return outbuf;
    }

    public byte[] decodeCiphertext(long seqNo, short type, byte ciphertext[], int offset, int len)
        throws IOException
    {
        int macSize = readMac.getSize();
        if(len < macSize)
            throw new TlsFatalAlert((short)50);
        byte deciphered[] = new byte[len];
        decryptCipher.processBytes(ciphertext, offset, len, deciphered, 0);
        int macInputLen = len - macSize;
        byte receivedMac[] = Arrays.copyOfRange(deciphered, macInputLen, len);
        byte computedMac[] = readMac.calculateMac(seqNo, type, deciphered, 0, macInputLen);
        if(!Arrays.constantTimeAreEqual(receivedMac, computedMac))
            throw new TlsFatalAlert((short)20);
        else
            return Arrays.copyOfRange(deciphered, 0, macInputLen);
    }

    protected TlsContext context;
    protected StreamCipher encryptCipher;
    protected StreamCipher decryptCipher;
    protected TlsMac writeMac;
    protected TlsMac readMac;
}
