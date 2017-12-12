// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcImplProvider.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.digests.*;
import co.org.bouncy.crypto.encodings.PKCS1Encoding;
import co.org.bouncy.crypto.engines.*;
import co.org.bouncy.crypto.signers.*;
import co.org.bouncy.openpgp.PGPException;

class BcImplProvider
{

    BcImplProvider()
    {
    }

    static Digest createDigest(int algorithm)
        throws PGPException
    {
        switch(algorithm)
        {
        case 2: // '\002'
            return new SHA1Digest();

        case 11: // '\013'
            return new SHA224Digest();

        case 8: // '\b'
            return new SHA256Digest();

        case 9: // '\t'
            return new SHA384Digest();

        case 10: // '\n'
            return new SHA512Digest();

        case 5: // '\005'
            return new MD2Digest();

        case 1: // '\001'
            return new MD5Digest();

        case 3: // '\003'
            return new RIPEMD160Digest();

        case 6: // '\006'
            return new TigerDigest();

        case 4: // '\004'
        case 7: // '\007'
        default:
            throw new PGPException("cannot recognise digest");
        }
    }

    static Signer createSigner(int keyAlgorithm, int hashAlgorithm)
        throws PGPException
    {
        switch(keyAlgorithm)
        {
        case 1: // '\001'
        case 3: // '\003'
            return new RSADigestSigner(createDigest(hashAlgorithm));

        case 17: // '\021'
            return new DSADigestSigner(new DSASigner(), createDigest(hashAlgorithm));
        }
        throw new PGPException("cannot recognise keyAlgorithm");
    }

    static BlockCipher createBlockCipher(int encAlgorithm)
        throws PGPException
    {
        BlockCipher engine;
        switch(encAlgorithm)
        {
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
            engine = new AESEngine();
            break;

        case 4: // '\004'
            engine = new BlowfishEngine();
            break;

        case 3: // '\003'
            engine = new CAST5Engine();
            break;

        case 6: // '\006'
            engine = new DESEngine();
            break;

        case 10: // '\n'
            engine = new TwofishEngine();
            break;

        case 2: // '\002'
            engine = new DESedeEngine();
            break;

        case 5: // '\005'
        default:
            throw new PGPException("cannot recognise cipher");
        }
        return engine;
    }

    static AsymmetricBlockCipher createPublicKeyCipher(int encAlgorithm)
        throws PGPException
    {
        AsymmetricBlockCipher c;
        switch(encAlgorithm)
        {
        case 1: // '\001'
        case 2: // '\002'
            c = new PKCS1Encoding(new RSABlindedEngine());
            break;

        case 16: // '\020'
        case 20: // '\024'
            c = new PKCS1Encoding(new ElGamalEngine());
            break;

        case 17: // '\021'
            throw new PGPException("Can't use DSA for encryption.");

        case 19: // '\023'
            throw new PGPException("Can't use ECDSA for encryption.");

        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 18: // '\022'
        default:
            throw new PGPException((new StringBuilder()).append("unknown asymmetric algorithm: ").append(encAlgorithm).toString());
        }
        return c;
    }
}
