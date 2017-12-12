// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignatureSpi.java

package co.org.bouncy.pqc.jcajce.provider.rainbow;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.SHA224Digest;
import co.org.bouncy.crypto.digests.SHA256Digest;
import co.org.bouncy.crypto.digests.SHA384Digest;
import co.org.bouncy.crypto.digests.SHA512Digest;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.pqc.crypto.rainbow.RainbowSigner;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.rainbow:
//            RainbowKeysToParams

public class SignatureSpi extends java.security.SignatureSpi
{
    public static class withSha512 extends SignatureSpi
    {

        public withSha512()
        {
            super(new SHA512Digest(), new RainbowSigner());
        }
    }

    public static class withSha384 extends SignatureSpi
    {

        public withSha384()
        {
            super(new SHA384Digest(), new RainbowSigner());
        }
    }

    public static class withSha256 extends SignatureSpi
    {

        public withSha256()
        {
            super(new SHA256Digest(), new RainbowSigner());
        }
    }

    public static class withSha224 extends SignatureSpi
    {

        public withSha224()
        {
            super(new SHA224Digest(), new RainbowSigner());
        }
    }


    protected SignatureSpi(Digest digest, RainbowSigner signer)
    {
        this.digest = digest;
        this.signer = signer;
    }

    protected void engineInitVerify(PublicKey publicKey)
        throws InvalidKeyException
    {
        CipherParameters param = RainbowKeysToParams.generatePublicKeyParameter(publicKey);
        digest.reset();
        signer.init(false, param);
    }

    protected void engineInitSign(PrivateKey privateKey, SecureRandom random)
        throws InvalidKeyException
    {
        this.random = random;
        engineInitSign(privateKey);
    }

    protected void engineInitSign(PrivateKey privateKey)
        throws InvalidKeyException
    {
        CipherParameters param = RainbowKeysToParams.generatePrivateKeyParameter(privateKey);
        if(random != null)
            param = new ParametersWithRandom(param, random);
        digest.reset();
        signer.init(true, param);
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
            byte sig[] = signer.generateSignature(hash);
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
        byte hash[] = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return signer.verifySignature(hash, sigBytes);
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

    private Digest digest;
    private RainbowSigner signer;
    private SecureRandom random;
}
