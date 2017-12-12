// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseWrapCipher.java

package co.org.bouncy.jcajce.provider.symmetric.util;

import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import java.io.PrintStream;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric.util:
//            BCPBEKey, PBE

public abstract class BaseWrapCipher extends CipherSpi
    implements PBE
{

    protected BaseWrapCipher()
    {
        pbeType = 2;
        pbeHash = 1;
        engineParams = null;
        wrapEngine = null;
    }

    protected BaseWrapCipher(Wrapper wrapEngine)
    {
        this(wrapEngine, 0);
    }

    protected BaseWrapCipher(Wrapper wrapEngine, int ivSize)
    {
        pbeType = 2;
        pbeHash = 1;
        engineParams = null;
        this.wrapEngine = null;
        this.wrapEngine = wrapEngine;
        this.ivSize = ivSize;
    }

    protected int engineGetBlockSize()
    {
        return 0;
    }

    protected byte[] engineGetIV()
    {
        return (byte[])(byte[])iv.clone();
    }

    protected int engineGetKeySize(Key key)
    {
        return key.getEncoded().length;
    }

    protected int engineGetOutputSize(int inputLen)
    {
        return -1;
    }

    protected AlgorithmParameters engineGetParameters()
    {
        return null;
    }

    protected void engineSetMode(String mode)
        throws NoSuchAlgorithmException
    {
        throw new NoSuchAlgorithmException((new StringBuilder()).append("can't support mode ").append(mode).toString());
    }

    protected void engineSetPadding(String padding)
        throws NoSuchPaddingException
    {
        throw new NoSuchPaddingException((new StringBuilder()).append("Padding ").append(padding).append(" unknown.").toString());
    }

    protected void engineInit(int opmode, Key key, AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        CipherParameters param;
        if(key instanceof BCPBEKey)
        {
            BCPBEKey k = (BCPBEKey)key;
            if(params instanceof PBEParameterSpec)
                param = PBE.Util.makePBEParameters(k, params, wrapEngine.getAlgorithmName());
            else
            if(k.getParam() != null)
                param = k.getParam();
            else
                throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
        } else
        {
            param = new KeyParameter(key.getEncoded());
        }
        if(params instanceof IvParameterSpec)
        {
            IvParameterSpec iv = (IvParameterSpec)params;
            param = new ParametersWithIV(param, iv.getIV());
        }
        if((param instanceof KeyParameter) && ivSize != 0)
        {
            this.iv = new byte[ivSize];
            random.nextBytes(this.iv);
            param = new ParametersWithIV(param, this.iv);
        }
        switch(opmode)
        {
        case 3: // '\003'
            wrapEngine.init(true, param);
            break;

        case 4: // '\004'
            wrapEngine.init(false, param);
            break;

        case 1: // '\001'
        case 2: // '\002'
            throw new IllegalArgumentException("engine only valid for wrapping");

        default:
            System.out.println("eeek!");
            break;
        }
    }

    protected void engineInit(int opmode, Key key, AlgorithmParameters params, SecureRandom random)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        AlgorithmParameterSpec paramSpec = null;
        if(params != null)
        {
            int i = 0;
            do
            {
                if(i == availableSpecs.length)
                    break;
                try
                {
                    paramSpec = params.getParameterSpec(availableSpecs[i]);
                    break;
                }
                catch(Exception e)
                {
                    i++;
                }
            } while(true);
            if(paramSpec == null)
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("can't handle parameter ").append(params.toString()).toString());
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
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    protected byte[] engineUpdate(byte input[], int inputOffset, int inputLen)
    {
        throw new RuntimeException("not supported for wrapping");
    }

    protected int engineUpdate(byte input[], int inputOffset, int inputLen, byte output[], int outputOffset)
        throws ShortBufferException
    {
        throw new RuntimeException("not supported for wrapping");
    }

    protected byte[] engineDoFinal(byte input[], int inputOffset, int inputLen)
        throws IllegalBlockSizeException, BadPaddingException
    {
        return null;
    }

    protected int engineDoFinal(byte input[], int inputOffset, int inputLen, byte abyte0[], int i)
        throws IllegalBlockSizeException, BadPaddingException, ShortBufferException
    {
        return 0;
    }

    protected byte[] engineWrap(Key key)
        throws IllegalBlockSizeException, InvalidKeyException
    {
        byte encoded[] = key.getEncoded();
        if(encoded == null)
            throw new InvalidKeyException("Cannot wrap key, null encoding.");
        try
        {
            if(wrapEngine == null)
                return engineDoFinal(encoded, 0, encoded.length);
        }
        catch(BadPaddingException e)
        {
            throw new IllegalBlockSizeException(e.getMessage());
        }
        return wrapEngine.wrap(encoded, 0, encoded.length);
    }

    protected Key engineUnwrap(byte wrappedKey[], String wrappedKeyAlgorithm, int wrappedKeyType)
        throws InvalidKeyException, NoSuchAlgorithmException
    {
        byte encoded[];
        try
        {
            if(wrapEngine == null)
                encoded = engineDoFinal(wrappedKey, 0, wrappedKey.length);
            else
                encoded = wrapEngine.unwrap(wrappedKey, 0, wrappedKey.length);
        }
        catch(InvalidCipherTextException e)
        {
            throw new InvalidKeyException(e.getMessage());
        }
        catch(BadPaddingException e)
        {
            throw new InvalidKeyException(e.getMessage());
        }
        catch(IllegalBlockSizeException e2)
        {
            throw new InvalidKeyException(e2.getMessage());
        }
        if(wrappedKeyType == 3)
            return new SecretKeySpec(encoded, wrappedKeyAlgorithm);
        if(!wrappedKeyAlgorithm.equals("") || wrappedKeyType != 2)
            break MISSING_BLOCK_LABEL_186;
        PrivateKeyInfo in;
        try
        {
            in = PrivateKeyInfo.getInstance(encoded);
            PrivateKey privKey = BouncyCastleProvider.getPrivateKey(in);
            if(privKey != null)
                return privKey;
        }
        catch(Exception e)
        {
            throw new InvalidKeyException("Invalid key encoding.");
        }
        throw new InvalidKeyException((new StringBuilder()).append("algorithm ").append(in.getPrivateKeyAlgorithm().getAlgorithm()).append(" not supported").toString());
        KeyFactory kf;
        try
        {
            kf = KeyFactory.getInstance(wrappedKeyAlgorithm, "BC");
            if(wrappedKeyType == 1)
                return kf.generatePublic(new X509EncodedKeySpec(encoded));
        }
        catch(NoSuchProviderException e)
        {
            throw new InvalidKeyException((new StringBuilder()).append("Unknown key type ").append(e.getMessage()).toString());
        }
        catch(InvalidKeySpecException e2)
        {
            throw new InvalidKeyException((new StringBuilder()).append("Unknown key type ").append(e2.getMessage()).toString());
        }
        if(wrappedKeyType == 2)
            return kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));
        throw new InvalidKeyException((new StringBuilder()).append("Unknown key type ").append(wrappedKeyType).toString());
    }

    private Class availableSpecs[] = {
        javax/crypto/spec/IvParameterSpec, javax/crypto/spec/PBEParameterSpec, javax/crypto/spec/RC2ParameterSpec, javax/crypto/spec/RC5ParameterSpec
    };
    protected int pbeType;
    protected int pbeHash;
    protected int pbeKeySize;
    protected int pbeIvSize;
    protected AlgorithmParameters engineParams;
    protected Wrapper wrapEngine;
    private int ivSize;
    private byte iv[];
}
