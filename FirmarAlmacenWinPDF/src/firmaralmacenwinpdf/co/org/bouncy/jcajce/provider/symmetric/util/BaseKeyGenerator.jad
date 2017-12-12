// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseKeyGenerator.java

package co.org.bouncy.jcajce.provider.symmetric.util;

import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.KeyGenerationParameters;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.KeyGeneratorSpi;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class BaseKeyGenerator extends KeyGeneratorSpi
{

    protected BaseKeyGenerator(String algName, int defaultKeySize, CipherKeyGenerator engine)
    {
        uninitialised = true;
        this.algName = algName;
        keySize = this.defaultKeySize = defaultKeySize;
        this.engine = engine;
    }

    protected void engineInit(AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidAlgorithmParameterException
    {
        throw new InvalidAlgorithmParameterException("Not Implemented");
    }

    protected void engineInit(SecureRandom random)
    {
        if(random != null)
        {
            engine.init(new KeyGenerationParameters(random, defaultKeySize));
            uninitialised = false;
        }
    }

    protected void engineInit(int keySize, SecureRandom random)
    {
        try
        {
            if(random == null)
                random = new SecureRandom();
            engine.init(new KeyGenerationParameters(random, keySize));
            uninitialised = false;
        }
        catch(IllegalArgumentException e)
        {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    protected SecretKey engineGenerateKey()
    {
        if(uninitialised)
        {
            engine.init(new KeyGenerationParameters(new SecureRandom(), defaultKeySize));
            uninitialised = false;
        }
        return new SecretKeySpec(engine.generateKey(), algName);
    }

    protected String algName;
    protected int keySize;
    protected int defaultKeySize;
    protected CipherKeyGenerator engine;
    protected boolean uninitialised;
}
