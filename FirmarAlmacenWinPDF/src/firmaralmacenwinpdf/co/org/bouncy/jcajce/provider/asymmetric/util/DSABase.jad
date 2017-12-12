// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSABase.java

package co.org.bouncy.jcajce.provider.asymmetric.util;

import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.X509ObjectIdentifiers;
import co.org.bouncy.crypto.DSA;
import co.org.bouncy.crypto.Digest;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.spec.AlgorithmParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.util:
//            DSAEncoder

public abstract class DSABase extends SignatureSpi
    implements PKCSObjectIdentifiers, X509ObjectIdentifiers
{

    protected DSABase(Digest digest, DSA signer, DSAEncoder encoder)
    {
        this.digest = digest;
        this.signer = signer;
        this.encoder = encoder;
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
            java.math.BigInteger sig[] = signer.generateSignature(hash);
            return encoder.encode(sig[0], sig[1]);
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
        java.math.BigInteger sig[];
        try
        {
            sig = encoder.decode(sigBytes);
        }
        catch(Exception e)
        {
            throw new SignatureException("error decoding signature bytes.");
        }
        return signer.verifySignature(hash, sig[0], sig[1]);
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

    protected Digest digest;
    protected DSA signer;
    protected DSAEncoder encoder;
}
