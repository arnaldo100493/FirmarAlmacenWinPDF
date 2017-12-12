// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigestSignatureSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.DigestInfo;
import co.org.bouncy.crypto.AsymmetricBlockCipher;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.*;
import co.org.bouncy.crypto.encodings.PKCS1Encoding;
import co.org.bouncy.crypto.engines.RSABlindedEngine;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.rsa:
//            RSAUtil

public class DigestSignatureSpi extends SignatureSpi
{
    public static class noneRSA extends DigestSignatureSpi
    {

        public noneRSA()
        {
            super(new NullDigest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class RIPEMD256 extends DigestSignatureSpi
    {

        public RIPEMD256()
        {
            super(TeleTrusTObjectIdentifiers.ripemd256, new RIPEMD256Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class RIPEMD128 extends DigestSignatureSpi
    {

        public RIPEMD128()
        {
            super(TeleTrusTObjectIdentifiers.ripemd128, new RIPEMD128Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class RIPEMD160 extends DigestSignatureSpi
    {

        public RIPEMD160()
        {
            super(TeleTrusTObjectIdentifiers.ripemd160, new RIPEMD160Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class MD5 extends DigestSignatureSpi
    {

        public MD5()
        {
            super(PKCSObjectIdentifiers.md5, new MD5Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class MD4 extends DigestSignatureSpi
    {

        public MD4()
        {
            super(PKCSObjectIdentifiers.md4, new MD4Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class MD2 extends DigestSignatureSpi
    {

        public MD2()
        {
            super(PKCSObjectIdentifiers.md2, new MD2Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA512 extends DigestSignatureSpi
    {

        public SHA512()
        {
            super(NISTObjectIdentifiers.id_sha512, new SHA512Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA384 extends DigestSignatureSpi
    {

        public SHA384()
        {
            super(NISTObjectIdentifiers.id_sha384, new SHA384Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA256 extends DigestSignatureSpi
    {

        public SHA256()
        {
            super(NISTObjectIdentifiers.id_sha256, new SHA256Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA224 extends DigestSignatureSpi
    {

        public SHA224()
        {
            super(NISTObjectIdentifiers.id_sha224, new SHA224Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA1 extends DigestSignatureSpi
    {

        public SHA1()
        {
            super(OIWObjectIdentifiers.idSHA1, new SHA1Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }


    protected DigestSignatureSpi(Digest digest, AsymmetricBlockCipher cipher)
    {
        this.digest = digest;
        this.cipher = cipher;
        algId = null;
    }

    protected DigestSignatureSpi(ASN1ObjectIdentifier objId, Digest digest, AsymmetricBlockCipher cipher)
    {
        this.digest = digest;
        this.cipher = cipher;
        algId = new AlgorithmIdentifier(objId, DERNull.INSTANCE);
    }

    protected void engineInitVerify(PublicKey publicKey)
        throws InvalidKeyException
    {
        if(!(publicKey instanceof RSAPublicKey))
        {
            throw new InvalidKeyException((new StringBuilder()).append("Supplied key (").append(getType(publicKey)).append(") is not a RSAPublicKey instance").toString());
        } else
        {
            co.org.bouncy.crypto.CipherParameters param = RSAUtil.generatePublicKeyParameter((RSAPublicKey)publicKey);
            digest.reset();
            cipher.init(false, param);
            return;
        }
    }

    protected void engineInitSign(PrivateKey privateKey)
        throws InvalidKeyException
    {
        if(!(privateKey instanceof RSAPrivateKey))
        {
            throw new InvalidKeyException((new StringBuilder()).append("Supplied key (").append(getType(privateKey)).append(") is not a RSAPrivateKey instance").toString());
        } else
        {
            co.org.bouncy.crypto.CipherParameters param = RSAUtil.generatePrivateKeyParameter((RSAPrivateKey)privateKey);
            digest.reset();
            cipher.init(true, param);
            return;
        }
    }

    private String getType(Object o)
    {
        if(o == null)
            return null;
        else
            return o.getClass().getName();
    }

    protected void engineUpdate(byte b)
        throws SignatureException
    {
        digest.update(b);
    }

    protected void engineUpdate(byte b[], int off, int len)
        throws SignatureException
    {
        digest.update(b, off, len);
    }

    protected byte[] engineSign()
        throws SignatureException
    {
        byte hash[] = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        try
        {
            byte bytes[] = derEncode(hash);
            return cipher.processBlock(bytes, 0, bytes.length);
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new SignatureException("key too small for signature type");
        }
        catch(Exception e)
        {
            throw new SignatureException(e.toString());
        }
    }

    protected boolean engineVerify(byte sigBytes[])
        throws SignatureException
    {
        byte hash[] = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        byte sig[];
        byte expected[];
        try
        {
            sig = cipher.processBlock(sigBytes, 0, sigBytes.length);
            expected = derEncode(hash);
        }
        catch(Exception e)
        {
            return false;
        }
        if(sig.length == expected.length)
        {
            for(int i = 0; i < sig.length; i++)
                if(sig[i] != expected[i])
                    return false;

        } else
        if(sig.length == expected.length - 2)
        {
            int sigOffset = sig.length - hash.length - 2;
            int expectedOffset = expected.length - hash.length - 2;
            expected[1] -= 2;
            expected[3] -= 2;
            for(int i = 0; i < hash.length; i++)
                if(sig[sigOffset + i] != expected[expectedOffset + i])
                    return false;

            for(int i = 0; i < sigOffset; i++)
                if(sig[i] != expected[i])
                    return false;

        } else
        {
            return false;
        }
        return true;
    }

    protected void engineSetParameter(AlgorithmParameterSpec params)
    {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    /**
     * @deprecated Method engineSetParameter is deprecated
     */

    protected void engineSetParameter(String param, Object value)
    {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    /**
     * @deprecated Method engineGetParameter is deprecated
     */

    protected Object engineGetParameter(String param)
    {
        return null;
    }

    protected AlgorithmParameters engineGetParameters()
    {
        return null;
    }

    private byte[] derEncode(byte hash[])
        throws IOException
    {
        if(algId == null)
        {
            return hash;
        } else
        {
            DigestInfo dInfo = new DigestInfo(algId, hash);
            return dInfo.getEncoded("DER");
        }
    }

    private Digest digest;
    private AsymmetricBlockCipher cipher;
    private AlgorithmIdentifier algId;
}
