// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseCipherSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.util;

import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.InvalidCipherTextException;
import co.org.bouncy.crypto.Wrapper;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public abstract class BaseCipherSpi extends CipherSpi
{

    protected BaseCipherSpi()
    {
        engineParams = null;
        wrapEngine = null;
    }

    protected int engineGetBlockSize()
    {
        return 0;
    }

    protected byte[] engineGetIV()
    {
        return null;
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
        throws InvalidKeyException
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
        catch(NoSuchAlgorithmException e)
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
    protected AlgorithmParameters engineParams;
    protected Wrapper wrapEngine;
    private int ivSize;
    private byte iv[];
}
