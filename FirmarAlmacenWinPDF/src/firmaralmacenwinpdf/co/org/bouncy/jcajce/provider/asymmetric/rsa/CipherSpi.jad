// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CipherSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.encodings.*;
import co.org.bouncy.crypto.engines.RSABlindedEngine;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.jcajce.provider.asymmetric.util.BaseCipherSpi;
import co.org.bouncy.jcajce.provider.util.DigestFactory;
import co.org.bouncy.util.Strings;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.rsa:
//            RSAUtil

public class CipherSpi extends BaseCipherSpi
{
    public static class ISO9796d1Padding extends CipherSpi
    {

        public ISO9796d1Padding()
        {
            super(new ISO9796d1Encoding(new RSABlindedEngine()));
        }
    }

    public static class OAEPPadding extends CipherSpi
    {

        public OAEPPadding()
        {
            super(OAEPParameterSpec.DEFAULT);
        }
    }

    public static class PKCS1v1_5Padding_PublicOnly extends CipherSpi
    {

        public PKCS1v1_5Padding_PublicOnly()
        {
            super(true, false, new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class PKCS1v1_5Padding_PrivateOnly extends CipherSpi
    {

        public PKCS1v1_5Padding_PrivateOnly()
        {
            super(false, true, new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class PKCS1v1_5Padding extends CipherSpi
    {

        public PKCS1v1_5Padding()
        {
            super(new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class NoPadding extends CipherSpi
    {

        public NoPadding()
        {
            super(new RSABlindedEngine());
        }
    }


    public CipherSpi(AsymmetricBlockCipher engine)
    {
        publicKeyOnly = false;
        privateKeyOnly = false;
        bOut = new ByteArrayOutputStream();
        cipher = engine;
    }

    public CipherSpi(OAEPParameterSpec pSpec)
    {
        publicKeyOnly = false;
        privateKeyOnly = false;
        bOut = new ByteArrayOutputStream();
        try
        {
            initFromSpec(pSpec);
        }
        catch(NoSuchPaddingException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public CipherSpi(boolean publicKeyOnly, boolean privateKeyOnly, AsymmetricBlockCipher engine)
    {
        this.publicKeyOnly = false;
        this.privateKeyOnly = false;
        bOut = new ByteArrayOutputStream();
        this.publicKeyOnly = publicKeyOnly;
        this.privateKeyOnly = privateKeyOnly;
        cipher = engine;
    }

    private void initFromSpec(OAEPParameterSpec pSpec)
        throws NoSuchPaddingException
    {
        MGF1ParameterSpec mgfParams = (MGF1ParameterSpec)pSpec.getMGFParameters();
        Digest digest = DigestFactory.getDigest(mgfParams.getDigestAlgorithm());
        if(digest == null)
        {
            throw new NoSuchPaddingException((new StringBuilder()).append("no match on OAEP constructor for digest algorithm: ").append(mgfParams.getDigestAlgorithm()).toString());
        } else
        {
            cipher = new OAEPEncoding(new RSABlindedEngine(), digest, ((javax.crypto.spec.PSource.PSpecified)pSpec.getPSource()).getValue());
            paramSpec = pSpec;
            return;
        }
    }

    protected int engineGetBlockSize()
    {
        try
        {
            return cipher.getInputBlockSize();
        }
        catch(NullPointerException e)
        {
            throw new IllegalStateException("RSA Cipher not initialised");
        }
    }

    protected int engineGetKeySize(Key key)
    {
        if(key instanceof RSAPrivateKey)
        {
            RSAPrivateKey k = (RSAPrivateKey)key;
            return k.getModulus().bitLength();
        }
        if(key instanceof RSAPublicKey)
        {
            RSAPublicKey k = (RSAPublicKey)key;
            return k.getModulus().bitLength();
        } else
        {
            throw new IllegalArgumentException("not an RSA key!");
        }
    }

    protected int engineGetOutputSize(int inputLen)
    {
        try
        {
            return cipher.getOutputBlockSize();
        }
        catch(NullPointerException e)
        {
            throw new IllegalStateException("RSA Cipher not initialised");
        }
    }

    protected AlgorithmParameters engineGetParameters()
    {
        if(engineParams == null && paramSpec != null)
            try
            {
                engineParams = AlgorithmParameters.getInstance("OAEP", "BC");
                engineParams.init(paramSpec);
            }
            catch(Exception e)
            {
                throw new RuntimeException(e.toString());
            }
        return engineParams;
    }

    protected void engineSetMode(String mode)
        throws NoSuchAlgorithmException
    {
        String md = Strings.toUpperCase(mode);
        if(md.equals("NONE") || md.equals("ECB"))
            return;
        if(md.equals("1"))
        {
            privateKeyOnly = true;
            publicKeyOnly = false;
            return;
        }
        if(md.equals("2"))
        {
            privateKeyOnly = false;
            publicKeyOnly = true;
            return;
        } else
        {
            throw new NoSuchAlgorithmException((new StringBuilder()).append("can't support mode ").append(mode).toString());
        }
    }

    protected void engineSetPadding(String padding)
        throws NoSuchPaddingException
    {
        String pad = Strings.toUpperCase(padding);
        if(pad.equals("NOPADDING"))
            cipher = new RSABlindedEngine();
        else
        if(pad.equals("PKCS1PADDING"))
            cipher = new PKCS1Encoding(new RSABlindedEngine());
        else
        if(pad.equals("ISO9796-1PADDING"))
            cipher = new ISO9796d1Encoding(new RSABlindedEngine());
        else
        if(pad.equals("OAEPWITHMD5ANDMGF1PADDING"))
            initFromSpec(new OAEPParameterSpec("MD5", "MGF1", new MGF1ParameterSpec("MD5"), javax.crypto.spec.PSource.PSpecified.DEFAULT));
        else
        if(pad.equals("OAEPPADDING"))
            initFromSpec(OAEPParameterSpec.DEFAULT);
        else
        if(pad.equals("OAEPWITHSHA1ANDMGF1PADDING") || pad.equals("OAEPWITHSHA-1ANDMGF1PADDING"))
            initFromSpec(OAEPParameterSpec.DEFAULT);
        else
        if(pad.equals("OAEPWITHSHA224ANDMGF1PADDING") || pad.equals("OAEPWITHSHA-224ANDMGF1PADDING"))
            initFromSpec(new OAEPParameterSpec("SHA-224", "MGF1", new MGF1ParameterSpec("SHA-224"), javax.crypto.spec.PSource.PSpecified.DEFAULT));
        else
        if(pad.equals("OAEPWITHSHA256ANDMGF1PADDING") || pad.equals("OAEPWITHSHA-256ANDMGF1PADDING"))
            initFromSpec(new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, javax.crypto.spec.PSource.PSpecified.DEFAULT));
        else
        if(pad.equals("OAEPWITHSHA384ANDMGF1PADDING") || pad.equals("OAEPWITHSHA-384ANDMGF1PADDING"))
            initFromSpec(new OAEPParameterSpec("SHA-384", "MGF1", MGF1ParameterSpec.SHA384, javax.crypto.spec.PSource.PSpecified.DEFAULT));
        else
        if(pad.equals("OAEPWITHSHA512ANDMGF1PADDING") || pad.equals("OAEPWITHSHA-512ANDMGF1PADDING"))
            initFromSpec(new OAEPParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, javax.crypto.spec.PSource.PSpecified.DEFAULT));
        else
            throw new NoSuchPaddingException((new StringBuilder()).append(padding).append(" unavailable with RSA.").toString());
    }

    protected void engineInit(int opmode, Key key, AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        CipherParameters param;
        if(params == null || (params instanceof OAEPParameterSpec))
        {
            if(key instanceof RSAPublicKey)
            {
                if(privateKeyOnly && opmode == 1)
                    throw new InvalidKeyException("mode 1 requires RSAPrivateKey");
                param = RSAUtil.generatePublicKeyParameter((RSAPublicKey)key);
            } else
            if(key instanceof RSAPrivateKey)
            {
                if(publicKeyOnly && opmode == 1)
                    throw new InvalidKeyException("mode 2 requires RSAPublicKey");
                param = RSAUtil.generatePrivateKeyParameter((RSAPrivateKey)key);
            } else
            {
                throw new InvalidKeyException("unknown key type passed to RSA");
            }
            if(params != null)
            {
                OAEPParameterSpec spec = (OAEPParameterSpec)params;
                paramSpec = params;
                if(!spec.getMGFAlgorithm().equalsIgnoreCase("MGF1") && !spec.getMGFAlgorithm().equals(PKCSObjectIdentifiers.id_mgf1.getId()))
                    throw new InvalidAlgorithmParameterException("unknown mask generation function specified");
                if(!(spec.getMGFParameters() instanceof MGF1ParameterSpec))
                    throw new InvalidAlgorithmParameterException("unkown MGF parameters");
                Digest digest = DigestFactory.getDigest(spec.getDigestAlgorithm());
                if(digest == null)
                    throw new InvalidAlgorithmParameterException((new StringBuilder()).append("no match on digest algorithm: ").append(spec.getDigestAlgorithm()).toString());
                MGF1ParameterSpec mgfParams = (MGF1ParameterSpec)spec.getMGFParameters();
                Digest mgfDigest = DigestFactory.getDigest(mgfParams.getDigestAlgorithm());
                if(mgfDigest == null)
                    throw new InvalidAlgorithmParameterException((new StringBuilder()).append("no match on MGF digest algorithm: ").append(mgfParams.getDigestAlgorithm()).toString());
                cipher = new OAEPEncoding(new RSABlindedEngine(), digest, mgfDigest, ((javax.crypto.spec.PSource.PSpecified)spec.getPSource()).getValue());
            }
        } else
        {
            throw new IllegalArgumentException("unknown parameter type.");
        }
        if(!(cipher instanceof RSABlindedEngine))
            if(random != null)
                param = new ParametersWithRandom(param, random);
            else
                param = new ParametersWithRandom(param, new SecureRandom());
        bOut.reset();
        switch(opmode)
        {
        case 1: // '\001'
        case 3: // '\003'
            cipher.init(true, param);
            break;

        case 2: // '\002'
        case 4: // '\004'
            cipher.init(false, param);
            break;

        default:
            throw new InvalidParameterException((new StringBuilder()).append("unknown opmode ").append(opmode).append(" passed to RSA").toString());
        }
    }

    protected void engineInit(int opmode, Key key, AlgorithmParameters params, SecureRandom random)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        AlgorithmParameterSpec paramSpec = null;
        if(params != null)
            try
            {
                paramSpec = params.getParameterSpec(javax/crypto/spec/OAEPParameterSpec);
            }
            catch(InvalidParameterSpecException e)
            {
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("cannot recognise parameters: ").append(e.toString()).toString(), e);
            }
        engineParams = params;
        engineInit(opmode, key, paramSpec, random);
    }

    protected void engineInit(int opmode, Key key, SecureRandom random)
        throws InvalidKeyException
    {
        try
        {
            engineInit(opmode, key, (AlgorithmParameterSpec)null, random);
        }
        catch(InvalidAlgorithmParameterException e)
        {
            throw new InvalidKeyException((new StringBuilder()).append("Eeeek! ").append(e.toString()).toString(), e);
        }
    }

    protected byte[] engineUpdate(byte input[], int inputOffset, int inputLen)
    {
        bOut.write(input, inputOffset, inputLen);
        if(cipher instanceof RSABlindedEngine)
        {
            if(bOut.size() > cipher.getInputBlockSize() + 1)
                throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        } else
        if(bOut.size() > cipher.getInputBlockSize())
            throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        return null;
    }

    protected int engineUpdate(byte input[], int inputOffset, int inputLen, byte output[], int outputOffset)
    {
        bOut.write(input, inputOffset, inputLen);
        if(cipher instanceof RSABlindedEngine)
        {
            if(bOut.size() > cipher.getInputBlockSize() + 1)
                throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        } else
        if(bOut.size() > cipher.getInputBlockSize())
            throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        return 0;
    }

    protected byte[] engineDoFinal(byte input[], int inputOffset, int inputLen)
        throws IllegalBlockSizeException, BadPaddingException
    {
        if(input != null)
            bOut.write(input, inputOffset, inputLen);
        if(cipher instanceof RSABlindedEngine)
        {
            if(bOut.size() > cipher.getInputBlockSize() + 1)
                throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        } else
        if(bOut.size() > cipher.getInputBlockSize())
            throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        try
        {
            byte bytes[] = bOut.toByteArray();
            bOut.reset();
            return cipher.processBlock(bytes, 0, bytes.length);
        }
        catch(InvalidCipherTextException e)
        {
            throw new BadPaddingException(e.getMessage());
        }
    }

    protected int engineDoFinal(byte input[], int inputOffset, int inputLen, byte output[], int outputOffset)
        throws IllegalBlockSizeException, BadPaddingException
    {
        if(input != null)
            bOut.write(input, inputOffset, inputLen);
        if(cipher instanceof RSABlindedEngine)
        {
            if(bOut.size() > cipher.getInputBlockSize() + 1)
                throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        } else
        if(bOut.size() > cipher.getInputBlockSize())
            throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        byte out[];
        try
        {
            byte bytes[] = bOut.toByteArray();
            out = cipher.processBlock(bytes, 0, bytes.length);
        }
        catch(InvalidCipherTextException e)
        {
            throw new BadPaddingException(e.getMessage());
        }
        bOut.reset();
        break MISSING_BLOCK_LABEL_147;
        Exception exception;
        exception;
        bOut.reset();
        throw exception;
        for(int i = 0; i != out.length; i++)
            output[outputOffset + i] = out[i];

        return out.length;
    }

    private AsymmetricBlockCipher cipher;
    private AlgorithmParameterSpec paramSpec;
    private AlgorithmParameters engineParams;
    private boolean publicKeyOnly;
    private boolean privateKeyOnly;
    private ByteArrayOutputStream bOut;
}
