// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ISOSignatureSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.crypto.AsymmetricBlockCipher;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.*;
import co.org.bouncy.crypto.engines.RSABlindedEngine;
import co.org.bouncy.crypto.signers.ISO9796d2Signer;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.rsa:
//            RSAUtil

public class ISOSignatureSpi extends SignatureSpi
{
    public static class RIPEMD160WithRSAEncryption extends ISOSignatureSpi
    {

        public RIPEMD160WithRSAEncryption()
        {
            super(new RIPEMD160Digest(), new RSABlindedEngine());
        }
    }

    public static class MD5WithRSAEncryption extends ISOSignatureSpi
    {

        public MD5WithRSAEncryption()
        {
            super(new MD5Digest(), new RSABlindedEngine());
        }
    }

    public static class SHA1WithRSAEncryption extends ISOSignatureSpi
    {

        public SHA1WithRSAEncryption()
        {
            super(new SHA1Digest(), new RSABlindedEngine());
        }
    }


    protected ISOSignatureSpi(Digest digest, AsymmetricBlockCipher cipher)
    {
        signer = new ISO9796d2Signer(cipher, digest, true);
    }

    protected void engineInitVerify(PublicKey publicKey)
        throws InvalidKeyException
    {
        co.org.bouncy.crypto.CipherParameters param = RSAUtil.generatePublicKeyParameter((RSAPublicKey)publicKey);
        signer.init(false, param);
    }

    protected void engineInitSign(PrivateKey privateKey)
        throws InvalidKeyException
    {
        co.org.bouncy.crypto.CipherParameters param = RSAUtil.generatePrivateKeyParameter((RSAPrivateKey)privateKey);
        signer.init(true, param);
    }

    protected void engineUpdate(byte b)
        throws SignatureException
    {
        signer.update(b);
    }

    protected void engineUpdate(byte b[], int off, int len)
        throws SignatureException
    {
        signer.update(b, off, len);
    }

    protected byte[] engineSign()
        throws SignatureException
    {
        try
        {
            byte sig[] = signer.generateSignature();
            return sig;
        }
        catch(Exception e)
        {
            throw new SignatureException(e.toString());
        }
    }

    protected boolean engineVerify(byte sigBytes[])
        throws SignatureException
    {
        boolean yes = signer.verifySignature(sigBytes);
        return yes;
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
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    private ISO9796d2Signer signer;
}
