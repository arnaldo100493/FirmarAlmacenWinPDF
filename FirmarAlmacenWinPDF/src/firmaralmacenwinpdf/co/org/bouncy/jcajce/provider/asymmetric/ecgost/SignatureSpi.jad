// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignatureSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.ecgost;

import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x509.X509ObjectIdentifiers;
import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.DSA;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.GOST3411Digest;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.crypto.signers.ECGOST3410Signer;
import co.org.bouncy.jcajce.provider.asymmetric.util.ECUtil;
import co.org.bouncy.jcajce.provider.asymmetric.util.GOST3410Util;
import co.org.bouncy.jce.interfaces.ECKey;
import co.org.bouncy.jce.interfaces.ECPublicKey;
import co.org.bouncy.jce.interfaces.GOST3410Key;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;

public class SignatureSpi extends java.security.SignatureSpi
    implements PKCSObjectIdentifiers, X509ObjectIdentifiers
{

    public SignatureSpi()
    {
        digest = new GOST3411Digest();
        signer = new ECGOST3410Signer();
    }

    protected void engineInitVerify(PublicKey publicKey)
        throws InvalidKeyException
    {
        CipherParameters param;
        if(publicKey instanceof ECPublicKey)
            param = ECUtil.generatePublicKeyParameter(publicKey);
        else
        if(publicKey instanceof GOST3410Key)
            param = GOST3410Util.generatePublicKeyParameter(publicKey);
        else
            try
            {
                byte bytes[] = publicKey.getEncoded();
                publicKey = BouncyCastleProvider.getPublicKey(SubjectPublicKeyInfo.getInstance(bytes));
                if(publicKey instanceof ECPublicKey)
                    param = ECUtil.generatePublicKeyParameter(publicKey);
                else
                    throw new InvalidKeyException("can't recognise key type in DSA based signer");
            }
            catch(Exception e)
            {
                throw new InvalidKeyException("can't recognise key type in DSA based signer");
            }
        digest.reset();
        signer.init(false, param);
    }

    protected void engineInitSign(PrivateKey privateKey)
        throws InvalidKeyException
    {
        CipherParameters param;
        if(privateKey instanceof ECKey)
            param = ECUtil.generatePrivateKeyParameter(privateKey);
        else
            param = GOST3410Util.generatePrivateKeyParameter(privateKey);
        digest.reset();
        if(appRandom != null)
            signer.init(true, new ParametersWithRandom(param, appRandom));
        else
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
            byte sigBytes[] = new byte[64];
            BigInteger sig[] = signer.generateSignature(hash);
            byte r[] = sig[0].toByteArray();
            byte s[] = sig[1].toByteArray();
            if(s[0] != 0)
                System.arraycopy(s, 0, sigBytes, 32 - s.length, s.length);
            else
                System.arraycopy(s, 1, sigBytes, 32 - (s.length - 1), s.length - 1);
            if(r[0] != 0)
                System.arraycopy(r, 0, sigBytes, 64 - r.length, r.length);
            else
                System.arraycopy(r, 1, sigBytes, 64 - (r.length - 1), r.length - 1);
            return sigBytes;
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
        BigInteger sig[];
        try
        {
            byte r[] = new byte[32];
            byte s[] = new byte[32];
            System.arraycopy(sigBytes, 0, s, 0, 32);
            System.arraycopy(sigBytes, 32, r, 0, 32);
            sig = new BigInteger[2];
            sig[0] = new BigInteger(1, r);
            sig[1] = new BigInteger(1, s);
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

    private Digest digest;
    private DSA signer;
}
