// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsRSASigner.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.encodings.PKCS1Encoding;
import co.org.bouncy.crypto.engines.RSABlindedEngine;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.crypto.signers.GenericSigner;
import co.org.bouncy.crypto.signers.RSADigestSigner;
import co.org.bouncy.util.Arrays;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            AbstractTlsSigner, CombinedHash, TlsContext, ProtocolVersion

public class TlsRSASigner extends AbstractTlsSigner
{

    public TlsRSASigner()
    {
    }

    public byte[] generateRawSignature(AsymmetricKeyParameter privateKey, byte md5AndSha1[])
        throws CryptoException
    {
        AsymmetricBlockCipher engine = createRSAImpl();
        engine.init(true, new ParametersWithRandom(privateKey, context.getSecureRandom()));
        return engine.processBlock(md5AndSha1, 0, md5AndSha1.length);
    }

    public boolean verifyRawSignature(byte sigBytes[], AsymmetricKeyParameter publicKey, byte md5AndSha1[])
        throws CryptoException
    {
        AsymmetricBlockCipher engine = createRSAImpl();
        engine.init(false, publicKey);
        byte signed[] = engine.processBlock(sigBytes, 0, sigBytes.length);
        return Arrays.constantTimeAreEqual(signed, md5AndSha1);
    }

    public Signer createSigner(AsymmetricKeyParameter privateKey)
    {
        return makeSigner(new CombinedHash(), true, new ParametersWithRandom(privateKey, context.getSecureRandom()));
    }

    public Signer createVerifyer(AsymmetricKeyParameter publicKey)
    {
        return makeSigner(new CombinedHash(), false, publicKey);
    }

    public boolean isValidPublicKey(AsymmetricKeyParameter publicKey)
    {
        return (publicKey instanceof RSAKeyParameters) && !publicKey.isPrivate();
    }

    protected Signer makeSigner(Digest d, boolean forSigning, CipherParameters cp)
    {
        Signer s;
        if(ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(context.getServerVersion().getEquivalentTLSVersion()))
            s = new RSADigestSigner(d);
        else
            s = new GenericSigner(createRSAImpl(), d);
        s.init(forSigning, cp);
        return s;
    }

    protected AsymmetricBlockCipher createRSAImpl()
    {
        return new PKCS1Encoding(new RSABlindedEngine());
    }
}
