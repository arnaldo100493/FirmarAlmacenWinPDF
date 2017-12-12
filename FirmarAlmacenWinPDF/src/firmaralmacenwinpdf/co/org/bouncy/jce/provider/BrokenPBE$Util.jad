// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BrokenPBE.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.PBEParametersGenerator;
import co.org.bouncy.crypto.digests.*;
import co.org.bouncy.crypto.generators.*;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;
import co.org.bouncy.jcajce.provider.symmetric.util.BCPBEKey;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.PBEParameterSpec;

// Referenced classes of package co.org.bouncy.jce.provider:
//            OldPKCS12ParametersGenerator, BrokenPBE

public static class BrokenPBE$Util
{

    private static void setOddParity(byte bytes[])
    {
        for(int i = 0; i < bytes.length; i++)
        {
            int b = bytes[i];
            bytes[i] = (byte)(b & 0xfe | b >> 1 ^ b >> 2 ^ b >> 3 ^ b >> 4 ^ b >> 5 ^ b >> 6 ^ b >> 7 ^ 1);
        }

    }

    private static PBEParametersGenerator makePBEGenerator(int type, int hash)
    {
        PBEParametersGenerator generator;
        if(type == 0)
            switch(hash)
            {
            case 0: // '\0'
                generator = new PKCS5S1ParametersGenerator(new MD5Digest());
                break;

            case 1: // '\001'
                generator = new PKCS5S1ParametersGenerator(new SHA1Digest());
                break;

            default:
                throw new IllegalStateException("PKCS5 scheme 1 only supports only MD5 and SHA1.");
            }
        else
        if(type == 1)
            generator = new PKCS5S2ParametersGenerator();
        else
        if(type == 3)
            switch(hash)
            {
            case 0: // '\0'
                generator = new OldPKCS12ParametersGenerator(new MD5Digest());
                break;

            case 1: // '\001'
                generator = new OldPKCS12ParametersGenerator(new SHA1Digest());
                break;

            case 2: // '\002'
                generator = new OldPKCS12ParametersGenerator(new RIPEMD160Digest());
                break;

            default:
                throw new IllegalStateException("unknown digest scheme for PBE encryption.");
            }
        else
            switch(hash)
            {
            case 0: // '\0'
                generator = new PKCS12ParametersGenerator(new MD5Digest());
                break;

            case 1: // '\001'
                generator = new PKCS12ParametersGenerator(new SHA1Digest());
                break;

            case 2: // '\002'
                generator = new PKCS12ParametersGenerator(new RIPEMD160Digest());
                break;

            default:
                throw new IllegalStateException("unknown digest scheme for PBE encryption.");
            }
        return generator;
    }

    static CipherParameters makePBEParameters(BCPBEKey pbeKey, AlgorithmParameterSpec spec, int type, int hash, String targetAlgorithm, int keySize, int ivSize)
    {
        if(spec == null || !(spec instanceof PBEParameterSpec))
            throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
        PBEParameterSpec pbeParam = (PBEParameterSpec)spec;
        PBEParametersGenerator generator = makePBEGenerator(type, hash);
        byte key[] = pbeKey.getEncoded();
        generator.init(key, pbeParam.getSalt(), pbeParam.getIterationCount());
        CipherParameters param;
        if(ivSize != 0)
            param = generator.generateDerivedParameters(keySize, ivSize);
        else
            param = generator.generateDerivedParameters(keySize);
        if(targetAlgorithm.startsWith("DES"))
            if(param instanceof ParametersWithIV)
            {
                KeyParameter kParam = (KeyParameter)((ParametersWithIV)param).getParameters();
                setOddParity(kParam.getKey());
            } else
            {
                KeyParameter kParam = (KeyParameter)param;
                setOddParity(kParam.getKey());
            }
        for(int i = 0; i != key.length; i++)
            key[i] = 0;

        return param;
    }

    static CipherParameters makePBEMacParameters(BCPBEKey pbeKey, AlgorithmParameterSpec spec, int type, int hash, int keySize)
    {
        if(spec == null || !(spec instanceof PBEParameterSpec))
            throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
        PBEParameterSpec pbeParam = (PBEParameterSpec)spec;
        PBEParametersGenerator generator = makePBEGenerator(type, hash);
        byte key[] = pbeKey.getEncoded();
        generator.init(key, pbeParam.getSalt(), pbeParam.getIterationCount());
        CipherParameters param = generator.generateDerivedMacParameters(keySize);
        for(int i = 0; i != key.length; i++)
            key[i] = 0;

        return param;
    }

    public BrokenPBE$Util()
    {
    }
}
