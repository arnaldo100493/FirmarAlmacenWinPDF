// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigestingMessageSigner.java

package co.org.bouncy.pqc.crypto;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.params.ParametersWithRandom;

// Referenced classes of package co.org.bouncy.pqc.crypto:
//            MessageSigner

public class DigestingMessageSigner
    implements Signer
{

    public DigestingMessageSigner(MessageSigner messSigner, Digest messDigest)
    {
        this.messSigner = messSigner;
        this.messDigest = messDigest;
    }

    public void init(boolean forSigning, CipherParameters param)
    {
        this.forSigning = forSigning;
        AsymmetricKeyParameter k;
        if(param instanceof ParametersWithRandom)
            k = (AsymmetricKeyParameter)((ParametersWithRandom)param).getParameters();
        else
            k = (AsymmetricKeyParameter)param;
        if(forSigning && !k.isPrivate())
            throw new IllegalArgumentException("Signing Requires Private Key.");
        if(!forSigning && k.isPrivate())
        {
            throw new IllegalArgumentException("Verification Requires Public Key.");
        } else
        {
            reset();
            messSigner.init(forSigning, param);
            return;
        }
    }

    public byte[] generateSignature()
    {
        if(!forSigning)
        {
            throw new IllegalStateException("RainbowDigestSigner not initialised for signature generation.");
        } else
        {
            byte hash[] = new byte[messDigest.getDigestSize()];
            messDigest.doFinal(hash, 0);
            return messSigner.generateSignature(hash);
        }
    }

    public boolean verify(byte signature[])
    {
        if(forSigning)
        {
            throw new IllegalStateException("RainbowDigestSigner not initialised for verification");
        } else
        {
            byte hash[] = new byte[messDigest.getDigestSize()];
            messDigest.doFinal(hash, 0);
            return messSigner.verifySignature(hash, signature);
        }
    }

    public void update(byte b)
    {
        messDigest.update(b);
    }

    public void update(byte in[], int off, int len)
    {
        messDigest.update(in, off, len);
    }

    public void reset()
    {
        messDigest.reset();
    }

    public boolean verifySignature(byte signature[])
    {
        return verify(signature);
    }

    private final Digest messDigest;
    private final MessageSigner messSigner;
    private boolean forSigning;
}
