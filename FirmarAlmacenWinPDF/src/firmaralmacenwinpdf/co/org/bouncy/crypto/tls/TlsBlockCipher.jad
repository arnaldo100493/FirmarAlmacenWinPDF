// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsBlockCipher.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;
import co.org.bouncy.util.Arrays;
import java.io.IOException;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsMac, TlsFatalAlert, TlsCipher, TlsContext, 
//            ProtocolVersion, TlsUtils

public class TlsBlockCipher
    implements TlsCipher
{

    public TlsMac getWriteMac()
    {
        return writeMac;
    }

    public TlsMac getReadMac()
    {
        return readMac;
    }

    public TlsBlockCipher(TlsContext context, BlockCipher clientWriteCipher, BlockCipher serverWriteCipher, Digest clientWriteDigest, Digest serverWriteDigest, int cipherKeySize)
        throws IOException
    {
        this.context = context;
        randomData = new byte[256];
        context.getSecureRandom().nextBytes(randomData);
        useExplicitIV = ProtocolVersion.TLSv11.isEqualOrEarlierVersionOf(context.getServerVersion().getEquivalentTLSVersion());
        int key_block_size = 2 * cipherKeySize + clientWriteDigest.getDigestSize() + serverWriteDigest.getDigestSize();
        if(!useExplicitIV)
            key_block_size += clientWriteCipher.getBlockSize() + serverWriteCipher.getBlockSize();
        byte key_block[] = TlsUtils.calculateKeyBlock(context, key_block_size);
        int offset = 0;
        TlsMac clientWriteMac = new TlsMac(context, clientWriteDigest, key_block, offset, clientWriteDigest.getDigestSize());
        offset += clientWriteDigest.getDigestSize();
        TlsMac serverWriteMac = new TlsMac(context, serverWriteDigest, key_block, offset, serverWriteDigest.getDigestSize());
        offset += serverWriteDigest.getDigestSize();
        KeyParameter client_write_key = new KeyParameter(key_block, offset, cipherKeySize);
        offset += cipherKeySize;
        KeyParameter server_write_key = new KeyParameter(key_block, offset, cipherKeySize);
        offset += cipherKeySize;
        byte client_write_IV[];
        byte server_write_IV[];
        if(useExplicitIV)
        {
            client_write_IV = new byte[clientWriteCipher.getBlockSize()];
            server_write_IV = new byte[serverWriteCipher.getBlockSize()];
        } else
        {
            client_write_IV = Arrays.copyOfRange(key_block, offset, offset + clientWriteCipher.getBlockSize());
            offset += clientWriteCipher.getBlockSize();
            server_write_IV = Arrays.copyOfRange(key_block, offset, offset + serverWriteCipher.getBlockSize());
            offset += serverWriteCipher.getBlockSize();
        }
        if(offset != key_block_size)
            throw new TlsFatalAlert((short)80);
        CipherParameters encryptParams;
        CipherParameters decryptParams;
        if(context.isServer())
        {
            writeMac = serverWriteMac;
            readMac = clientWriteMac;
            encryptCipher = serverWriteCipher;
            decryptCipher = clientWriteCipher;
            encryptParams = new ParametersWithIV(server_write_key, server_write_IV);
            decryptParams = new ParametersWithIV(client_write_key, client_write_IV);
        } else
        {
            writeMac = clientWriteMac;
            readMac = serverWriteMac;
            encryptCipher = clientWriteCipher;
            decryptCipher = serverWriteCipher;
            encryptParams = new ParametersWithIV(client_write_key, client_write_IV);
            decryptParams = new ParametersWithIV(server_write_key, server_write_IV);
        }
        encryptCipher.init(true, encryptParams);
        decryptCipher.init(false, decryptParams);
    }

    public int getPlaintextLimit(int ciphertextLimit)
    {
        int blockSize = encryptCipher.getBlockSize();
        int macSize = writeMac.getSize();
        int result = ciphertextLimit - ciphertextLimit % blockSize - macSize - 1;
        if(useExplicitIV)
            result -= blockSize;
        return result;
    }

    public byte[] encodePlaintext(long seqNo, short type, byte plaintext[], int offset, int len)
    {
        int blockSize = encryptCipher.getBlockSize();
        int macSize = writeMac.getSize();
        ProtocolVersion version = context.getServerVersion();
        int padding_length = blockSize - 1 - (len + macSize) % blockSize;
        if(!version.isDTLS() && !version.isSSL())
        {
            int maxExtraPadBlocks = (255 - padding_length) / blockSize;
            int actualExtraPadBlocks = chooseExtraPadBlocks(context.getSecureRandom(), maxExtraPadBlocks);
            padding_length += actualExtraPadBlocks * blockSize;
        }
        int totalSize = len + macSize + padding_length + 1;
        if(useExplicitIV)
            totalSize += blockSize;
        byte outbuf[] = new byte[totalSize];
        int outOff = 0;
        if(useExplicitIV)
        {
            byte explicitIV[] = new byte[blockSize];
            context.getSecureRandom().nextBytes(explicitIV);
            encryptCipher.init(true, new ParametersWithIV(null, explicitIV));
            System.arraycopy(explicitIV, 0, outbuf, outOff, blockSize);
            outOff += blockSize;
        }
        byte mac[] = writeMac.calculateMac(seqNo, type, plaintext, offset, len);
        System.arraycopy(plaintext, offset, outbuf, outOff, len);
        System.arraycopy(mac, 0, outbuf, outOff + len, mac.length);
        int padOffset = outOff + len + mac.length;
        for(int i = 0; i <= padding_length; i++)
            outbuf[i + padOffset] = (byte)padding_length;

        for(int i = outOff; i < totalSize; i += blockSize)
            encryptCipher.processBlock(outbuf, i, outbuf, i);

        return outbuf;
    }

    public byte[] decodeCiphertext(long seqNo, short type, byte ciphertext[], int offset, int len)
        throws IOException
    {
        int blockSize = decryptCipher.getBlockSize();
        int macSize = readMac.getSize();
        int minLen = Math.max(blockSize, macSize + 1);
        if(useExplicitIV)
            minLen += blockSize;
        if(len < minLen)
            throw new TlsFatalAlert((short)50);
        if(len % blockSize != 0)
            throw new TlsFatalAlert((short)21);
        if(useExplicitIV)
        {
            decryptCipher.init(false, new ParametersWithIV(null, ciphertext, offset, blockSize));
            offset += blockSize;
            len -= blockSize;
        }
        for(int i = 0; i < len; i += blockSize)
            decryptCipher.processBlock(ciphertext, offset + i, ciphertext, offset + i);

        int totalPad = checkPaddingConstantTime(ciphertext, offset, len, blockSize, macSize);
        int macInputLen = len - totalPad - macSize;
        byte decryptedMac[] = Arrays.copyOfRange(ciphertext, offset + macInputLen, offset + macInputLen + macSize);
        byte calculatedMac[] = readMac.calculateMacConstantTime(seqNo, type, ciphertext, offset, macInputLen, len - macSize, randomData);
        boolean badMac = !Arrays.constantTimeAreEqual(calculatedMac, decryptedMac);
        if(badMac || totalPad == 0)
            throw new TlsFatalAlert((short)20);
        else
            return Arrays.copyOfRange(ciphertext, offset, offset + macInputLen);
    }

    protected int checkPaddingConstantTime(byte buf[], int off, int len, int blockSize, int macSize)
    {
        int end = off + len;
        byte lastByte = buf[end - 1];
        int padlen = lastByte & 0xff;
        int totalPad = padlen + 1;
        int dummyIndex = 0;
        byte padDiff = 0;
        if(context.getServerVersion().isSSL() && totalPad > blockSize || macSize + totalPad > len)
        {
            totalPad = 0;
        } else
        {
            int padPos = end - totalPad;
            do
                padDiff |= buf[padPos++] ^ lastByte;
            while(padPos < end);
            dummyIndex = totalPad;
            if(padDiff != 0)
                totalPad = 0;
        }
        byte dummyPad[] = randomData;
        while(dummyIndex < 256) 
            padDiff |= dummyPad[dummyIndex++] ^ lastByte;
        dummyPad[0] ^= padDiff;
        return totalPad;
    }

    protected int chooseExtraPadBlocks(SecureRandom r, int max)
    {
        int x = r.nextInt();
        int n = lowestBitSet(x);
        return Math.min(n, max);
    }

    protected int lowestBitSet(int x)
    {
        if(x == 0)
            return 32;
        int n = 0;
        for(; (x & 1) == 0; x >>= 1)
            n++;

        return n;
    }

    protected TlsContext context;
    protected byte randomData[];
    protected boolean useExplicitIV;
    protected BlockCipher encryptCipher;
    protected BlockCipher decryptCipher;
    protected TlsMac writeMac;
    protected TlsMac readMac;
}
