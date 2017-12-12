// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PSSSignatureSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.engines.RSABlindedEngine;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.crypto.signers.PSSSigner;
import co.org.bouncy.jcajce.provider.util.DigestFactory;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.rsa:
//            RSAUtil

public class PSSSignatureSpi extends SignatureSpi
{
    private class NullPssDigest
        implements Digest
    {

        public String getAlgorithmName()
        {
            return "NULL";
        }

        public int getDigestSize()
        {
            return baseDigest.getDigestSize();
        }

        public void update(byte in)
        {
            bOut.write(in);
        }

        public void update(byte in[], int inOff, int len)
        {
            bOut.write(in, inOff, len);
        }

        public int doFinal(byte out[], int outOff)
        {
            byte res[] = bOut.toByteArray();
            if(oddTime)
            {
                System.arraycopy(res, 0, out, outOff, res.length);
            } else
            {
                baseDigest.update(res, 0, res.length);
                baseDigest.doFinal(out, outOff);
            }
            reset();
            oddTime = !oddTime;
            return res.length;
        }

        public void reset()
        {
            bOut.reset();
            baseDigest.reset();
        }

        public int getByteLength()
        {
            return 0;
        }

        private ByteArrayOutputStream bOut;
        private Digest baseDigest;
        private boolean oddTime;
        final PSSSignatureSpi this$0;

        public NullPssDigest(Digest mgfDigest)
        {
            this$0 = PSSSignatureSpi.this;
            super();
            bOut = new ByteArrayOutputStream();
            oddTime = true;
            baseDigest = mgfDigest;
        }
    }

    public static class SHA512withRSA extends PSSSignatureSpi
    {

        public SHA512withRSA()
        {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA-512", "MGF1", new MGF1ParameterSpec("SHA-512"), 64, 1));
        }
    }

    public static class SHA384withRSA extends PSSSignatureSpi
    {

        public SHA384withRSA()
        {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA-384", "MGF1", new MGF1ParameterSpec("SHA-384"), 48, 1));
        }
    }

    public static class SHA256withRSA extends PSSSignatureSpi
    {

        public SHA256withRSA()
        {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-256"), 32, 1));
        }
    }

    public static class SHA224withRSA extends PSSSignatureSpi
    {

        public SHA224withRSA()
        {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA-224", "MGF1", new MGF1ParameterSpec("SHA-224"), 28, 1));
        }
    }

    public static class SHA1withRSA extends PSSSignatureSpi
    {

        public SHA1withRSA()
        {
            super(new RSABlindedEngine(), PSSParameterSpec.DEFAULT);
        }
    }

    public static class PSSwithRSA extends PSSSignatureSpi
    {

        public PSSwithRSA()
        {
            super(new RSABlindedEngine(), null);
        }
    }

    public static class nonePSS extends PSSSignatureSpi
    {

        public nonePSS()
        {
            super(new RSABlindedEngine(), null, true);
        }
    }


    private byte getTrailer(int trailerField)
    {
        if(trailerField == 1)
            return -68;
        else
            throw new IllegalArgumentException("unknown trailer field");
    }

    private void setupContentDigest()
    {
        if(isRaw)
            contentDigest = new NullPssDigest(mgfDigest);
        else
            contentDigest = mgfDigest;
    }

    protected PSSSignatureSpi(AsymmetricBlockCipher signer, PSSParameterSpec paramSpecArg)
    {
        this(signer, paramSpecArg, false);
    }

    protected PSSSignatureSpi(AsymmetricBlockCipher signer, PSSParameterSpec baseParamSpec, boolean isRaw)
    {
        this.signer = signer;
        originalSpec = baseParamSpec;
        if(baseParamSpec == null)
            paramSpec = PSSParameterSpec.DEFAULT;
        else
            paramSpec = baseParamSpec;
        mgfDigest = DigestFactory.getDigest(paramSpec.getDigestAlgorithm());
        saltLength = paramSpec.getSaltLength();
        trailer = getTrailer(paramSpec.getTrailerField());
        this.isRaw = isRaw;
        setupContentDigest();
    }

    protected void engineInitVerify(PublicKey publicKey)
        throws InvalidKeyException
    {
        if(!(publicKey instanceof RSAPublicKey))
        {
            throw new InvalidKeyException("Supplied key is not a RSAPublicKey instance");
        } else
        {
            pss = new PSSSigner(signer, contentDigest, mgfDigest, saltLength, trailer);
            pss.init(false, RSAUtil.generatePublicKeyParameter((RSAPublicKey)publicKey));
            return;
        }
    }

    protected void engineInitSign(PrivateKey privateKey, SecureRandom random)
        throws InvalidKeyException
    {
        if(!(privateKey instanceof RSAPrivateKey))
        {
            throw new InvalidKeyException("Supplied key is not a RSAPrivateKey instance");
        } else
        {
            pss = new PSSSigner(signer, contentDigest, mgfDigest, saltLength, trailer);
            pss.init(true, new ParametersWithRandom(RSAUtil.generatePrivateKeyParameter((RSAPrivateKey)privateKey), random));
            return;
        }
    }

    protected void engineInitSign(PrivateKey privateKey)
        throws InvalidKeyException
    {
        if(!(privateKey instanceof RSAPrivateKey))
        {
            throw new InvalidKeyException("Supplied key is not a RSAPrivateKey instance");
        } else
        {
            pss = new PSSSigner(signer, contentDigest, mgfDigest, saltLength, trailer);
            pss.init(true, RSAUtil.generatePrivateKeyParameter((RSAPrivateKey)privateKey));
            return;
        }
    }

    protected void engineUpdate(byte b)
        throws SignatureException
    {
        pss.update(b);
    }

    protected void engineUpdate(byte b[], int off, int len)
        throws SignatureException
    {
        pss.update(b, off, len);
    }

    protected byte[] engineSign()
        throws SignatureException
    {
        try
        {
            return pss.generateSignature();
        }
        catch(CryptoException e)
        {
            throw new SignatureException(e.getMessage());
        }
    }

    protected boolean engineVerify(byte sigBytes[])
        throws SignatureException
    {
        return pss.verifySignature(sigBytes);
    }

    protected void engineSetParameter(AlgorithmParameterSpec params)
        throws InvalidParameterException
    {
        if(params instanceof PSSParameterSpec)
        {
            PSSParameterSpec newParamSpec = (PSSParameterSpec)params;
            if(originalSpec != null && !DigestFactory.isSameDigest(originalSpec.getDigestAlgorithm(), newParamSpec.getDigestAlgorithm()))
                throw new InvalidParameterException((new StringBuilder()).append("parameter must be using ").append(originalSpec.getDigestAlgorithm()).toString());
            if(!newParamSpec.getMGFAlgorithm().equalsIgnoreCase("MGF1") && !newParamSpec.getMGFAlgorithm().equals(PKCSObjectIdentifiers.id_mgf1.getId()))
                throw new InvalidParameterException("unknown mask generation function specified");
            if(!(newParamSpec.getMGFParameters() instanceof MGF1ParameterSpec))
                throw new InvalidParameterException("unkown MGF parameters");
            MGF1ParameterSpec mgfParams = (MGF1ParameterSpec)newParamSpec.getMGFParameters();
            if(!DigestFactory.isSameDigest(mgfParams.getDigestAlgorithm(), newParamSpec.getDigestAlgorithm()))
                throw new InvalidParameterException("digest algorithm for MGF should be the same as for PSS parameters.");
            Digest newDigest = DigestFactory.getDigest(mgfParams.getDigestAlgorithm());
            if(newDigest == null)
                throw new InvalidParameterException((new StringBuilder()).append("no match on MGF digest algorithm: ").append(mgfParams.getDigestAlgorithm()).toString());
            engineParams = null;
            paramSpec = newParamSpec;
            mgfDigest = newDigest;
            saltLength = paramSpec.getSaltLength();
            trailer = getTrailer(paramSpec.getTrailerField());
            setupContentDigest();
        } else
        {
            throw new InvalidParameterException("Only PSSParameterSpec supported");
        }
    }

    protected AlgorithmParameters engineGetParameters()
    {
        if(engineParams == null && paramSpec != null)
            try
            {
                engineParams = AlgorithmParameters.getInstance("PSS", "BC");
                engineParams.init(paramSpec);
            }
            catch(Exception e)
            {
                throw new RuntimeException(e.toString());
            }
        return engineParams;
    }

    /**
     * @deprecated Method engineSetParameter is deprecated
     */

    protected void engineSetParameter(String param, Object value)
    {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    protected Object engineGetParameter(String param)
    {
        throw new UnsupportedOperationException("engineGetParameter unsupported");
    }

    private AlgorithmParameters engineParams;
    private PSSParameterSpec paramSpec;
    private PSSParameterSpec originalSpec;
    private AsymmetricBlockCipher signer;
    private Digest contentDigest;
    private Digest mgfDigest;
    private int saltLength;
    private byte trailer;
    private boolean isRaw;
    private PSSSigner pss;
}
