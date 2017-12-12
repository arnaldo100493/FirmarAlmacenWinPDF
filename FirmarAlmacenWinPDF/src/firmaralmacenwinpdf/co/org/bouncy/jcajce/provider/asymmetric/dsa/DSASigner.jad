// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSASigner.java

package co.org.bouncy.jcajce.provider.asymmetric.dsa;

import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.DERSequence;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x509.X509ObjectIdentifiers;
import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.DSA;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.NullDigest;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.digests.SHA224Digest;
import co.org.bouncy.crypto.digests.SHA256Digest;
import co.org.bouncy.crypto.digests.SHA384Digest;
import co.org.bouncy.crypto.digests.SHA512Digest;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.interfaces.DSAKey;
import java.security.spec.AlgorithmParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.dsa:
//            BCDSAPublicKey, DSAUtil

public class DSASigner extends SignatureSpi
    implements PKCSObjectIdentifiers, X509ObjectIdentifiers
{
    public static class noneDSA extends DSASigner
    {

        public noneDSA()
        {
            super(new NullDigest(), new co.org.bouncy.crypto.signers.DSASigner());
        }
    }

    public static class dsa512 extends DSASigner
    {

        public dsa512()
        {
            super(new SHA512Digest(), new co.org.bouncy.crypto.signers.DSASigner());
        }
    }

    public static class dsa384 extends DSASigner
    {

        public dsa384()
        {
            super(new SHA384Digest(), new co.org.bouncy.crypto.signers.DSASigner());
        }
    }

    public static class dsa256 extends DSASigner
    {

        public dsa256()
        {
            super(new SHA256Digest(), new co.org.bouncy.crypto.signers.DSASigner());
        }
    }

    public static class dsa224 extends DSASigner
    {

        public dsa224()
        {
            super(new SHA224Digest(), new co.org.bouncy.crypto.signers.DSASigner());
        }
    }

    public static class stdDSA extends DSASigner
    {

        public stdDSA()
        {
            super(new SHA1Digest(), new co.org.bouncy.crypto.signers.DSASigner());
        }
    }


    protected DSASigner(Digest digest, DSA signer)
    {
        this.digest = digest;
        this.signer = signer;
    }

    protected void engineInitVerify(PublicKey publicKey)
        throws InvalidKeyException
    {
        CipherParameters param;
        if(publicKey instanceof DSAKey)
            param = DSAUtil.generatePublicKeyParameter(publicKey);
        else
            try
            {
                byte bytes[] = publicKey.getEncoded();
                publicKey = new BCDSAPublicKey(SubjectPublicKeyInfo.getInstance(bytes));
                if(publicKey instanceof DSAKey)
                    param = DSAUtil.generatePublicKeyParameter(publicKey);
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

    protected void engineInitSign(PrivateKey privateKey, SecureRandom random)
        throws InvalidKeyException
    {
        this.random = random;
        engineInitSign(privateKey);
    }

    protected void engineInitSign(PrivateKey privateKey)
        throws InvalidKeyException
    {
        CipherParameters param = DSAUtil.generatePrivateKeyParameter(privateKey);
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
            BigInteger sig[] = signer.generateSignature(hash);
            return derEncode(sig[0], sig[1]);
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
            sig = derDecode(sigBytes);
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

    private byte[] derEncode(BigInteger r, BigInteger s)
        throws IOException
    {
        ASN1Integer rs[] = {
            new ASN1Integer(r), new ASN1Integer(s)
        };
        return (new DERSequence(rs)).getEncoded("DER");
    }

    private BigInteger[] derDecode(byte encoding[])
        throws IOException
    {
        ASN1Sequence s = (ASN1Sequence)ASN1Primitive.fromByteArray(encoding);
        return (new BigInteger[] {
            ((ASN1Integer)s.getObjectAt(0)).getValue(), ((ASN1Integer)s.getObjectAt(1)).getValue()
        });
    }

    private Digest digest;
    private DSA signer;
    private SecureRandom random;
}
