// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSADigestSigner.java

package co.org.bouncy.crypto.signers;

import co.org.bouncy.asn1.*;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import java.io.IOException;
import java.math.BigInteger;

public class DSADigestSigner
    implements Signer
{

    public DSADigestSigner(DSA signer, Digest digest)
    {
        this.digest = digest;
        dsaSigner = signer;
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
            throw new IllegalArgumentException("Signing Requires Private Key.");
        if(!forSigning && k.isPrivate())
        {
            throw new IllegalArgumentException("Verification Requires Public Key.");
        } else
        {
            reset();
            dsaSigner.init(forSigning, parameters);
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
    {
        if(!forSigning)
            throw new IllegalStateException("DSADigestSigner not initialised for signature generation.");
        byte hash[] = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        BigInteger sig[] = dsaSigner.generateSignature(hash);
        try
        {
            return derEncode(sig[0], sig[1]);
        }
        catch(IOException e)
        {
            throw new IllegalStateException("unable to encode signature");
        }
    }

    public boolean verifySignature(byte signature[])
    {
        if(forSigning)
            throw new IllegalStateException("DSADigestSigner not initialised for verification");
        byte hash[] = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        try
        {
            BigInteger sig[] = derDecode(signature);
            return dsaSigner.verifySignature(hash, sig[0], sig[1]);
        }
        catch(IOException e)
        {
            return false;
        }
    }

    public void reset()
    {
        digest.reset();
    }

    private byte[] derEncode(BigInteger r, BigInteger s)
        throws IOException
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new DERInteger(r));
        v.add(new DERInteger(s));
        return (new DERSequence(v)).getEncoded("DER");
    }

    private BigInteger[] derDecode(byte encoding[])
        throws IOException
    {
        ASN1Sequence s = (ASN1Sequence)ASN1Primitive.fromByteArray(encoding);
        return (new BigInteger[] {
            ((DERInteger)s.getObjectAt(0)).getValue(), ((DERInteger)s.getObjectAt(1)).getValue()
        });
    }

    private final Digest digest;
    private final DSA dsaSigner;
    private boolean forSigning;
}
