// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsAEADCipher.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.modes.AEADBlockCipher;
import co.org.bouncy.crypto.params.AEADParameters;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.util.Arrays;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsFatalAlert, TlsCipher, TlsContext, ProtocolVersion, 
//            TlsUtils

public class TlsAEADCipher
    implements TlsCipher
{

    public TlsAEADCipher(TlsContext context, AEADBlockCipher clientWriteCipher, AEADBlockCipher serverWriteCipher, int cipherKeySize, int macSize)
        throws IOException
    {
        if(!ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(context.getServerVersion().getEquivalentTLSVersion()))
            throw new TlsFatalAlert((short)80);
        this.context = context;
        this.macSize = macSize;
        nonce_explicit_length = 8;
        int fixed_iv_length = 4;
        int key_block_size = 2 * cipherKeySize + 2 * fixed_iv_length;
        byte key_block[] = TlsUtils.calculateKeyBlock(context, key_block_size);
        int offset = 0;
        KeyParameter client_write_key = new KeyParameter(key_block, offset, cipherKeySize);
        offset += cipherKeySize;
        KeyParameter server_write_key = new KeyParameter(key_block, offset, cipherKeySize);
        offset += cipherKeySize;
        byte client_write_IV[] = Arrays.copyOfRange(key_block, offset, offset + fixed_iv_length);
        offset += fixed_iv_length;
        byte server_write_IV[] = Arrays.copyOfRange(key_block, offset, offset + fixed_iv_length);
        offset += fixed_iv_length;
        if(offset != key_block_size)
            throw new TlsFatalAlert((short)80);
        KeyParameter encryptKey;
        KeyParameter decryptKey;
        if(context.isServer())
        {
            encryptCipher = serverWriteCipher;
            decryptCipher = clientWriteCipher;
            encryptImplicitNonce = server_write_IV;
            decryptImplicitNonce = client_write_IV;
            encryptKey = server_write_key;
            decryptKey = client_write_key;
        } else
        {
            encryptCipher = clientWriteCipher;
            decryptCipher = serverWriteCipher;
            encryptImplicitNonce = client_write_IV;
            decryptImplicitNonce = server_write_IV;
            encryptKey = client_write_key;
            decryptKey = server_write_key;
        }
        byte dummyNonce[] = new byte[fixed_iv_length + nonce_explicit_length];
        encryptCipher.init(true, new AEADParameters(encryptKey, 8 * macSize, dummyNonce));
        decryptCipher.init(false, new AEADParameters(decryptKey, 8 * macSize, dummyNonce));
    }

    public int getPlaintextLimit(int ciphertextLimit)
    {
        return ciphertextLimit - macSize - nonce_explicit_length;
    }

    public byte[] encodePlaintext(long seqNo, short type, byte plaintext[], int offset, int len)
        throws IOException
    {
        byte nonce[] = new byte[encryptImplicitNonce.length + nonce_explicit_length];
        System.arraycopy(encryptImplicitNonce, 0, nonce, 0, encryptImplicitNonce.length);
        TlsUtils.writeUint64(seqNo, nonce, encryptImplicitNonce.length);
        int plaintextOffset = offset;
        int plaintextLength = len;
        int ciphertextLength = encryptCipher.getOutputSize(plaintextLength);
        byte output[] = new byte[nonce_explicit_length + ciphertextLength];
        System.arraycopy(nonce, encryptImplicitNonce.length, output, 0, nonce_explicit_length);
        int outputPos = nonce_explicit_length;
        encryptCipher.init(true, new AEADParameters(null, 8 * macSize, nonce, getAdditionalData(seqNo, type, plaintextLength)));
        outputPos += encryptCipher.processBytes(plaintext, plaintextOffset, plaintextLength, output, outputPos);
        try
        {
            outputPos += encryptCipher.doFinal(output, outputPos);
        }
        catch(Exception e)
        {
            throw new TlsFatalAlert((short)80);
        }
        if(outputPos != output.length)
            throw new TlsFatalAlert((short)80);
        else
            return output;
    }

    public byte[] decodeCiphertext(long seqNo, short type, byte ciphertext[], int offset, int len)
        throws IOException
    {
        if(getPlaintextLimit(len) < 0)
            throw new TlsFatalAlert((short)50);
        byte nonce[] = new byte[decryptImplicitNonce.length + nonce_explicit_length];
        System.arraycopy(decryptImplicitNonce, 0, nonce, 0, decryptImplicitNonce.length);
        System.arraycopy(ciphertext, offset, nonce, decryptImplicitNonce.length, nonce_explicit_length);
        int ciphertextOffset = offset + nonce_explicit_length;
        int ciphertextLength = len - nonce_explicit_length;
        int plaintextLength = decryptCipher.getOutputSize(ciphertextLength);
        byte output[] = new byte[plaintextLength];
        int outputPos = 0;
        decryptCipher.init(false, new AEADParameters(null, 8 * macSize, nonce, getAdditionalData(seqNo, type, plaintextLength)));
        outputPos += decryptCipher.processBytes(ciphertext, ciphertextOffset, ciphertextLength, output, outputPos);
        try
        {
            outputPos += decryptCipher.doFinal(output, outputPos);
        }
        catch(Exception e)
        {
            throw new TlsFatalAlert((short)20);
        }
        if(outputPos != output.length)
            throw new TlsFatalAlert((short)80);
        else
            return output;
    }

    protected byte[] getAdditionalData(long seqNo, short type, int len)
        throws IOException
    {
        byte additional_data[] = new byte[13];
        TlsUtils.writeUint64(seqNo, additional_data, 0);
        TlsUtils.writeUint8(type, additional_data, 8);
        TlsUtils.writeVersion(context.getServerVersion(), additional_data, 9);
        TlsUtils.writeUint16(len, additional_data, 11);
        return additional_data;
    }

    protected TlsContext context;
    protected int macSize;
    protected int nonce_explicit_length;
    protected AEADBlockCipher encryptCipher;
    protected AEADBlockCipher decryptCipher;
    protected byte encryptImplicitNonce[];
    protected byte decryptImplicitNonce[];
}
