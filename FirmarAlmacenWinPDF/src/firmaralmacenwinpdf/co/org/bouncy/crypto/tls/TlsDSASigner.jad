// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsDSASigner.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.digests.NullDigest;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.crypto.signers.DSADigestSigner;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            AbstractTlsSigner, TlsContext

public abstract class TlsDSASigner extends AbstractTlsSigner
{

    public TlsDSASigner()
    {
    }

    public byte[] generateRawSignature(AsymmetricKeyParameter privateKey, byte md5AndSha1[])
        throws CryptoException
    {
        Signer signer = makeSigner(new NullDigest(), true, new ParametersWithRandom(privateKey, context.getSecureRandom()));
        signer.update(md5AndSha1, 16, 20);
        return signer.generateSignature();
    }

    public boolean verifyRawSignature(byte sigBytes[], AsymmetricKeyParameter publicKey, byte md5AndSha1[])
        throws CryptoException
    {
        Signer signer = makeSigner(new NullDigest(), false, publicKey);
        signer.update(md5AndSha1, 16, 20);
        return signer.verifySignature(sigBytes);
    }

    public Signer createSigner(AsymmetricKeyParameter privateKey)
    {
        return makeSigner(new SHA1Digest(), true, new ParametersWithRandom(privateKey, context.getSecureRandom()));
    }

    public Signer createVerifyer(AsymmetricKeyParameter publicKey)
    {
        return makeSigner(new SHA1Digest(), false, publicKey);
    }

    protected Signer makeSigner(Digest d, boolean forSigning, CipherParameters cp)
    {
        Signer s = new DSADigestSigner(createDSAImpl(), d);
        s.init(forSigning, cp);
        return s;
    }

    protected abstract DSA createDSAImpl();
}
