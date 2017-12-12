// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CipherSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.elgamal;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.encodings.*;
import co.org.bouncy.crypto.engines.ElGamalEngine;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.jcajce.provider.asymmetric.util.BaseCipherSpi;
import co.org.bouncy.jcajce.provider.util.DigestFactory;
import co.org.bouncy.jce.interfaces.*;
import co.org.bouncy.jce.spec.ElGamalParameterSpec;
import co.org.bouncy.util.Strings;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.*;
import javax.crypto.interfaces.DHKey;
import javax.crypto.spec.*;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.elgamal:
//            ElGamalUtil

public class CipherSpi extends BaseCipherSpi
{
    public static class PKCS1v1_5Padding extends CipherSpi
    {

        public PKCS1v1_5Padding()
        {
            super(new PKCS1Encoding(new ElGamalEngine()));
        }
    }

    public static class NoPadding extends CipherSpi
    {

        public NoPadding()
        {
            super(new ElGamalEngine());
        }
    }


    public CipherSpi(AsymmetricBlockCipher engine)
    {
        cipher = new BufferedAsymmetricBlockCipher(engine);
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
            cipher = new BufferedAsymmetricBlockCipher(new OAEPEncoding(new ElGamalEngine(), digest, ((javax.crypto.spec.PSource.PSpecified)pSpec.getPSource()).getValue()));
            paramSpec = pSpec;
            return;
        }
    }

    protected int engineGetBlockSize()
    {
        return cipher.getInputBlockSize();
    }

    protected int engineGetKeySize(Key key)
    {
        if(key instanceof ElGamalKey)
        {
            ElGamalKey k = (ElGamalKey)key;
            return k.getParameters().getP().bitLength();
        }
        if(key instanceof DHKey)
        {
            DHKey k = (DHKey)key;
            return k.getParams().getP().bitLength();
        } else
        {
            throw new IllegalArgumentException("not an ElGamal key!");
        }
    }

    protected int engineGetOutputSize(int inputLen)
    {
        return cipher.getOutputBlockSize();
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
        else
            throw new NoSuchAlgorithmException((new StringBuilder()).append("can't support mode ").append(mode).toString());
    }

    protected void engineSetPadding(String padding)
        throws NoSuchPaddingException
    {
        String pad = Strings.toUpperCase(padding);
        if(pad.equals("NOPADDING"))
            cipher = new BufferedAsymmetricBlockCipher(new ElGamalEngine());
        else
        if(pad.equals("PKCS1PADDING"))
            cipher = new BufferedAsymmetricBlockCipher(new PKCS1Encoding(new ElGamalEngine()));
        else
        if(pad.equals("ISO9796-1PADDING"))
            cipher = new BufferedAsymmetricBlockCipher(new ISO9796d1Encoding(new ElGamalEngine()));
        else
        if(pad.equals("OAEPPADDING"))
            initFromSpec(OAEPParameterSpec.DEFAULT);
        else
        if(pad.equals("OAEPWITHMD5ANDMGF1PADDING"))
            initFromSpec(new OAEPParameterSpec("MD5", "MGF1", new MGF1ParameterSpec("MD5"), javax.crypto.spec.PSource.PSpecified.DEFAULT));
        else
        if(pad.equals("OAEPWITHSHA1ANDMGF1PADDING"))
            initFromSpec(OAEPParameterSpec.DEFAULT);
        else
        if(pad.equals("OAEPWITHSHA224ANDMGF1PADDING"))
            initFromSpec(new OAEPParameterSpec("SHA-224", "MGF1", new MGF1ParameterSpec("SHA-224"), javax.crypto.spec.PSource.PSpecified.DEFAULT));
        else
        if(pad.equals("OAEPWITHSHA256ANDMGF1PADDING"))
            initFromSpec(new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, javax.crypto.spec.PSource.PSpecified.DEFAULT));
        else
        if(pad.equals("OAEPWITHSHA384ANDMGF1PADDING"))
            initFromSpec(new OAEPParameterSpec("SHA-384", "MGF1", MGF1ParameterSpec.SHA384, javax.crypto.spec.PSource.PSpecified.DEFAULT));
        else
        if(pad.equals("OAEPWITHSHA512ANDMGF1PADDING"))
            initFromSpec(new OAEPParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, javax.crypto.spec.PSource.PSpecified.DEFAULT));
        else
            throw new NoSuchPaddingException((new StringBuilder()).append(padding).append(" unavailable with ElGamal.").toString());
    }

    protected void engineInit(int opmode, Key key, AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidKeyException
    {
        CipherParameters param;
        if(params == null)
        {
            if(key instanceof ElGamalPublicKey)
                param = ElGamalUtil.generatePublicKeyParameter((PublicKey)key);
            else
            if(key instanceof ElGamalPrivateKey)
                param = ElGamalUtil.generatePrivateKeyParameter((PrivateKey)key);
            else
                throw new InvalidKeyException("unknown key type passed to ElGamal");
        } else
        {
            throw new IllegalArgumentException("unknown parameter type.");
        }
        if(random != null)
            param = new ParametersWithRandom(param, random);
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
            throw new InvalidParameterException((new StringBuilder()).append("unknown opmode ").append(opmode).append(" passed to ElGamal").toString());
        }
    }

    protected void engineInit(int opmode, Key key, AlgorithmParameters params, SecureRandom random)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        throw new InvalidAlgorithmParameterException("can't handle parameters in ElGamal");
    }

    protected void engineInit(int opmode, Key key, SecureRandom random)
        throws InvalidKeyException
    {
        engineInit(opmode, key, (AlgorithmParameterSpec)null, random);
    }

    protected byte[] engineUpdate(byte input[], int inputOffset, int inputLen)
    {
        cipher.processBytes(input, inputOffset, inputLen);
        return null;
    }

    protected int engineUpdate(byte input[], int inputOffset, int inputLen, byte output[], int outputOffset)
    {
        cipher.processBytes(input, inputOffset, inputLen);
        return 0;
    }

    protected byte[] engineDoFinal(byte input[], int inputOffset, int inputLen)
        throws IllegalBlockSizeException, BadPaddingException
    {
        cipher.processBytes(input, inputOffset, inputLen);
        try
        {
            return cipher.doFinal();
        }
        catch(InvalidCipherTextException e)
        {
            throw new BadPaddingException(e.getMessage());
        }
    }

    protected int engineDoFinal(byte input[], int inputOffset, int inputLen, byte output[], int outputOffset)
        throws IllegalBlockSizeException, BadPaddingException
    {
        cipher.processBytes(input, inputOffset, inputLen);
        byte out[];
        try
        {
            out = cipher.doFinal();
        }
        catch(InvalidCipherTextException e)
        {
            throw new BadPaddingException(e.getMessage());
        }
        for(int i = 0; i != out.length; i++)
            output[outputOffset + i] = out[i];

        return out.length;
    }

    private BufferedAsymmetricBlockCipher cipher;
    private AlgorithmParameterSpec paramSpec;
    private AlgorithmParameters engineParams;
}
