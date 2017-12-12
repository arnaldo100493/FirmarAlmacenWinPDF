// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenericSigner.java

package co.org.bouncy.crypto.signers;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.util.Arrays;

public class GenericSigner
    implements Signer
{

    public GenericSigner(AsymmetricBlockCipher engine, Digest digest)
    {
        this.engine = engine;
        this.digest = digest;
    }

    public void init(boolean forSigning, CipherParameters parameters)
    {
        this.forSigning = forSigning;
        AsymmetricKeyParameter k;
        if(parameters instanceof ParametersWithRandom)
            k = (AsymmetricKeyParameter)((ParametersWithRandom)parameters).getParameters();
        else
            k = (AsymmetricKeyParameter)parameters;
        if(forSigning && !k.isPrivate())
            throw new IllegalArgumentException("signing requires private key");
        if(!forSigning && k.isPrivate())
        {
            throw new IllegalArgumentException("verification requires public key");
        } else
        {
            reset();
            engine.init(forSigning, parameters);
            return;
        }
    }

    public void update(byte input)
    {
        digest.update(input);
    }

    public void update(byte input[], int inOff, int length)
    {
        digest.update(input, inOff, length);
    }

    public byte[] generateSignature()
        throws CryptoException, DataLengthException
    {
        if(!forSigning)
        {
            throw new IllegalStateException("GenericSigner not initialised for signature generation.");
        } else
        {
            byte hash[] = new byte[digest.getDigestSize()];
            digest.doFinal(hash, 0);
            return engine.processBlock(hash, 0, hash.length);
        }
    }

    public boolean verifySignature(byte signature[])
    {
        if(forSigning)
            throw new IllegalStateException("GenericSigner not initialised for verification");
        byte hash[] = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        try
        {
            byte sig[] = engine.processBlock(signature, 0, signature.length);
            return Arrays.constantTimeAreEqual(sig, hash);
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public void reset()
    {
        digest.reset();
    }

    private final AsymmetricBlockCipher engine;
    private final Digest digest;
    private boolean forSigning;
}
