// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultTlsCipherFactory.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.digests.*;
import co.org.bouncy.crypto.engines.*;
import co.org.bouncy.crypto.modes.*;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            AbstractTlsCipherFactory, TlsFatalAlert, TlsBlockCipher, TlsAEADCipher, 
//            TlsNullCipher, TlsStreamCipher, TlsContext, TlsCipher

public class DefaultTlsCipherFactory extends AbstractTlsCipherFactory
{

    public DefaultTlsCipherFactory()
    {
    }

    public TlsCipher createCipher(TlsContext context, int encryptionAlgorithm, int macAlgorithm)
        throws IOException
    {
        switch(encryptionAlgorithm)
        {
        case 7: // '\007'
            return createDESedeCipher(context, macAlgorithm);

        case 8: // '\b'
            return createAESCipher(context, 16, macAlgorithm);

        case 10: // '\n'
            return createCipher_AES_GCM(context, 16, 16);

        case 9: // '\t'
            return createAESCipher(context, 32, macAlgorithm);

        case 11: // '\013'
            return createCipher_AES_GCM(context, 32, 16);

        case 12: // '\f'
            return createCamelliaCipher(context, 16, macAlgorithm);

        case 13: // '\r'
            return createCamelliaCipher(context, 32, macAlgorithm);

        case 0: // '\0'
            return createNullCipher(context, macAlgorithm);

        case 2: // '\002'
            return createRC4Cipher(context, 16, macAlgorithm);

        case 14: // '\016'
            return createSEEDCipher(context, macAlgorithm);

        case 1: // '\001'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        default:
            throw new TlsFatalAlert((short)80);
        }
    }

    protected TlsBlockCipher createAESCipher(TlsContext context, int cipherKeySize, int macAlgorithm)
        throws IOException
    {
        return new TlsBlockCipher(context, createAESBlockCipher(), createAESBlockCipher(), createHMACDigest(macAlgorithm), createHMACDigest(macAlgorithm), cipherKeySize);
    }

    protected TlsAEADCipher createCipher_AES_GCM(TlsContext context, int cipherKeySize, int macSize)
        throws IOException
    {
        return new TlsAEADCipher(context, createAEADBlockCipher_AES_GCM(), createAEADBlockCipher_AES_GCM(), cipherKeySize, macSize);
    }

    protected TlsBlockCipher createCamelliaCipher(TlsContext context, int cipherKeySize, int macAlgorithm)
        throws IOException
    {
        return new TlsBlockCipher(context, createCamelliaBlockCipher(), createCamelliaBlockCipher(), createHMACDigest(macAlgorithm), createHMACDigest(macAlgorithm), cipherKeySize);
    }

    protected TlsNullCipher createNullCipher(TlsContext context, int macAlgorithm)
        throws IOException
    {
        return new TlsNullCipher(context, createHMACDigest(macAlgorithm), createHMACDigest(macAlgorithm));
    }

    protected TlsStreamCipher createRC4Cipher(TlsContext context, int cipherKeySize, int macAlgorithm)
        throws IOException
    {
        return new TlsStreamCipher(context, createRC4StreamCipher(), createRC4StreamCipher(), createHMACDigest(macAlgorithm), createHMACDigest(macAlgorithm), cipherKeySize);
    }

    protected TlsBlockCipher createDESedeCipher(TlsContext context, int macAlgorithm)
        throws IOException
    {
        return new TlsBlockCipher(context, createDESedeBlockCipher(), createDESedeBlockCipher(), createHMACDigest(macAlgorithm), createHMACDigest(macAlgorithm), 24);
    }

    protected TlsBlockCipher createSEEDCipher(TlsContext context, int macAlgorithm)
        throws IOException
    {
        return new TlsBlockCipher(context, createSEEDBlockCipher(), createSEEDBlockCipher(), createHMACDigest(macAlgorithm), createHMACDigest(macAlgorithm), 16);
    }

    protected StreamCipher createRC4StreamCipher()
    {
        return new RC4Engine();
    }

    protected BlockCipher createAESBlockCipher()
    {
        return new CBCBlockCipher(new AESFastEngine());
    }

    protected AEADBlockCipher createAEADBlockCipher_AES_GCM()
    {
        return new GCMBlockCipher(new AESFastEngine());
    }

    protected BlockCipher createCamelliaBlockCipher()
    {
        return new CBCBlockCipher(new CamelliaEngine());
    }

    protected BlockCipher createDESedeBlockCipher()
    {
        return new CBCBlockCipher(new DESedeEngine());
    }

    protected BlockCipher createSEEDBlockCipher()
    {
        return new CBCBlockCipher(new SEEDEngine());
    }

    protected Digest createHMACDigest(int macAlgorithm)
        throws IOException
    {
        switch(macAlgorithm)
        {
        case 0: // '\0'
            return null;

        case 1: // '\001'
            return new MD5Digest();

        case 2: // '\002'
            return new SHA1Digest();

        case 3: // '\003'
            return new SHA256Digest();

        case 4: // '\004'
            return new SHA384Digest();

        case 5: // '\005'
            return new SHA512Digest();
        }
        throw new TlsFatalAlert((short)80);
    }
}
