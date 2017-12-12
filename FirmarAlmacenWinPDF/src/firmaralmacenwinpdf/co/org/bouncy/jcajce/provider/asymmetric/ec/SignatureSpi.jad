// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignatureSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.ec;

import co.org.bouncy.asn1.*;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.digests.*;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.crypto.signers.ECDSASigner;
import co.org.bouncy.crypto.signers.ECNRSigner;
import co.org.bouncy.jcajce.provider.asymmetric.util.*;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;

public class SignatureSpi extends DSABase
{
    private static class CVCDSAEncoder
        implements DSAEncoder
    {

        public byte[] encode(BigInteger r, BigInteger s)
            throws IOException
        {
            byte first[] = makeUnsigned(r);
            byte second[] = makeUnsigned(s);
            byte res[];
            if(first.length > second.length)
                res = new byte[first.length * 2];
            else
                res = new byte[second.length * 2];
            System.arraycopy(first, 0, res, res.length / 2 - first.length, first.length);
            System.arraycopy(second, 0, res, res.length - second.length, second.length);
            return res;
        }

        private byte[] makeUnsigned(BigInteger val)
        {
            byte res[] = val.toByteArray();
            if(res[0] == 0)
            {
                byte tmp[] = new byte[res.length - 1];
                System.arraycopy(res, 1, tmp, 0, tmp.length);
                return tmp;
            } else
            {
                return res;
            }
        }

        public BigInteger[] decode(byte encoding[])
            throws IOException
        {
            BigInteger sig[] = new BigInteger[2];
            byte first[] = new byte[encoding.length / 2];
            byte second[] = new byte[encoding.length / 2];
            System.arraycopy(encoding, 0, first, 0, first.length);
            System.arraycopy(encoding, first.length, second, 0, second.length);
            sig[0] = new BigInteger(1, first);
            sig[1] = new BigInteger(1, second);
            return sig;
        }

        private CVCDSAEncoder()
        {
        }

    }

    private static class StdDSAEncoder
        implements DSAEncoder
    {

        public byte[] encode(BigInteger r, BigInteger s)
            throws IOException
        {
            ASN1EncodableVector v = new ASN1EncodableVector();
            v.add(new ASN1Integer(r));
            v.add(new ASN1Integer(s));
            return (new DERSequence(v)).getEncoded("DER");
        }

        public BigInteger[] decode(byte encoding[])
            throws IOException
        {
            ASN1Sequence s = (ASN1Sequence)ASN1Primitive.fromByteArray(encoding);
            BigInteger sig[] = new BigInteger[2];
            sig[0] = ASN1Integer.getInstance(s.getObjectAt(0)).getValue();
            sig[1] = ASN1Integer.getInstance(s.getObjectAt(1)).getValue();
            return sig;
        }

        private StdDSAEncoder()
        {
        }

    }

    public static class ecCVCDSA512 extends SignatureSpi
    {

        public ecCVCDSA512()
        {
            super(new SHA512Digest(), new ECDSASigner(), new CVCDSAEncoder());
        }
    }

    public static class ecCVCDSA384 extends SignatureSpi
    {

        public ecCVCDSA384()
        {
            super(new SHA384Digest(), new ECDSASigner(), new CVCDSAEncoder());
        }
    }

    public static class ecCVCDSA256 extends SignatureSpi
    {

        public ecCVCDSA256()
        {
            super(new SHA256Digest(), new ECDSASigner(), new CVCDSAEncoder());
        }
    }

    public static class ecCVCDSA224 extends SignatureSpi
    {

        public ecCVCDSA224()
        {
            super(new SHA224Digest(), new ECDSASigner(), new CVCDSAEncoder());
        }
    }

    public static class ecCVCDSA extends SignatureSpi
    {

        public ecCVCDSA()
        {
            super(new SHA1Digest(), new ECDSASigner(), new CVCDSAEncoder());
        }
    }

    public static class ecNR512 extends SignatureSpi
    {

        public ecNR512()
        {
            super(new SHA512Digest(), new ECNRSigner(), new StdDSAEncoder());
        }
    }

    public static class ecNR384 extends SignatureSpi
    {

        public ecNR384()
        {
            super(new SHA384Digest(), new ECNRSigner(), new StdDSAEncoder());
        }
    }

    public static class ecNR256 extends SignatureSpi
    {

        public ecNR256()
        {
            super(new SHA256Digest(), new ECNRSigner(), new StdDSAEncoder());
        }
    }

    public static class ecNR224 extends SignatureSpi
    {

        public ecNR224()
        {
            super(new SHA224Digest(), new ECNRSigner(), new StdDSAEncoder());
        }
    }

    public static class ecNR extends SignatureSpi
    {

        public ecNR()
        {
            super(new SHA1Digest(), new ECNRSigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSARipeMD160 extends SignatureSpi
    {

        public ecDSARipeMD160()
        {
            super(new RIPEMD160Digest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSA512 extends SignatureSpi
    {

        public ecDSA512()
        {
            super(new SHA512Digest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSA384 extends SignatureSpi
    {

        public ecDSA384()
        {
            super(new SHA384Digest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSA256 extends SignatureSpi
    {

        public ecDSA256()
        {
            super(new SHA256Digest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSA224 extends SignatureSpi
    {

        public ecDSA224()
        {
            super(new SHA224Digest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSAnone extends SignatureSpi
    {

        public ecDSAnone()
        {
            super(new NullDigest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSA extends SignatureSpi
    {

        public ecDSA()
        {
            super(new SHA1Digest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }


    SignatureSpi(Digest digest, DSA signer, DSAEncoder encoder)
    {
        super(digest, signer, encoder);
    }

    protected void engineInitVerify(PublicKey publicKey)
        throws InvalidKeyException
    {
        CipherParameters param = ECUtil.generatePublicKeyParameter(publicKey);
        digest.reset();
        signer.init(false, param);
    }

    protected void engineInitSign(PrivateKey privateKey)
        throws InvalidKeyException
    {
        CipherParameters param = ECUtil.generatePrivateKeyParameter(privateKey);
        digest.reset();
        if(appRandom != null)
            signer.init(true, new ParametersWithRandom(param, appRandom));
        else
            signer.init(true, param);
    }
}
