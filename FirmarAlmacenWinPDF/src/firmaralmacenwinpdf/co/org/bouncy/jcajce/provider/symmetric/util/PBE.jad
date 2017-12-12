// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PBE.java

package co.org.bouncy.jcajce.provider.symmetric.util;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.PBEParametersGenerator;
import co.org.bouncy.crypto.digests.*;
import co.org.bouncy.crypto.generators.*;
import co.org.bouncy.crypto.params.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric.util:
//            BCPBEKey

public interface PBE
{
    public static class Util
    {

        private static PBEParametersGenerator makePBEGenerator(int type, int hash)
        {
            PBEParametersGenerator generator;
            if(type == 0 || type == 4)
                switch(hash)
                {
                case 5: // '\005'
                    generator = new PKCS5S1ParametersGenerator(new MD2Digest());
                    break;

                case 0: // '\0'
                    generator = new PKCS5S1ParametersGenerator(new MD5Digest());
                    break;

                case 1: // '\001'
                    generator = new PKCS5S1ParametersGenerator(new SHA1Digest());
                    break;

                default:
                    throw new IllegalStateException("PKCS5 scheme 1 only supports MD2, MD5 and SHA1.");
                }
            else
            if(type == 1 || type == 5)
                generator = new PKCS5S2ParametersGenerator();
            else
            if(type == 2)
                switch(hash)
                {
                case 5: // '\005'
                    generator = new PKCS12ParametersGenerator(new MD2Digest());
                    break;

                case 0: // '\0'
                    generator = new PKCS12ParametersGenerator(new MD5Digest());
                    break;

                case 1: // '\001'
                    generator = new PKCS12ParametersGenerator(new SHA1Digest());
                    break;

                case 2: // '\002'
                    generator = new PKCS12ParametersGenerator(new RIPEMD160Digest());
                    break;

                case 3: // '\003'
                    generator = new PKCS12ParametersGenerator(new TigerDigest());
                    break;

                case 4: // '\004'
                    generator = new PKCS12ParametersGenerator(new SHA256Digest());
                    break;

                case 6: // '\006'
                    generator = new PKCS12ParametersGenerator(new GOST3411Digest());
                    break;

                default:
                    throw new IllegalStateException("unknown digest scheme for PBE encryption.");
                }
            else
                generator = new OpenSSLPBEParametersGenerator();
            return generator;
        }

        public static CipherParameters makePBEParameters(BCPBEKey pbeKey, AlgorithmParameterSpec spec, String targetAlgorithm)
        {
            if(spec == null || !(spec instanceof PBEParameterSpec))
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            PBEParameterSpec pbeParam = (PBEParameterSpec)spec;
            PBEParametersGenerator generator = makePBEGenerator(pbeKey.getType(), pbeKey.getDigest());
            byte key[] = pbeKey.getEncoded();
            if(pbeKey.shouldTryWrongPKCS12())
                key = new byte[2];
            generator.init(key, pbeParam.getSalt(), pbeParam.getIterationCount());
            CipherParameters param;
            if(pbeKey.getIvSize() != 0)
                param = generator.generateDerivedParameters(pbeKey.getKeySize(), pbeKey.getIvSize());
            else
                param = generator.generateDerivedParameters(pbeKey.getKeySize());
            if(targetAlgorithm.startsWith("DES"))
                if(param instanceof ParametersWithIV)
                {
                    KeyParameter kParam = (KeyParameter)((ParametersWithIV)param).getParameters();
                    DESParameters.setOddParity(kParam.getKey());
                } else
                {
                    KeyParameter kParam = (KeyParameter)param;
                    DESParameters.setOddParity(kParam.getKey());
                }
            for(int i = 0; i != key.length; i++)
                key[i] = 0;

            return param;
        }

        public static CipherParameters makePBEMacParameters(BCPBEKey pbeKey, AlgorithmParameterSpec spec)
        {
            if(spec == null || !(spec instanceof PBEParameterSpec))
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            PBEParameterSpec pbeParam = (PBEParameterSpec)spec;
            PBEParametersGenerator generator = makePBEGenerator(pbeKey.getType(), pbeKey.getDigest());
            byte key[] = pbeKey.getEncoded();
            if(pbeKey.shouldTryWrongPKCS12())
                key = new byte[2];
            generator.init(key, pbeParam.getSalt(), pbeParam.getIterationCount());
            CipherParameters param = generator.generateDerivedMacParameters(pbeKey.getKeySize());
            for(int i = 0; i != key.length; i++)
                key[i] = 0;

            return param;
        }

        public static CipherParameters makePBEParameters(PBEKeySpec keySpec, int type, int hash, int keySize, int ivSize)
        {
            PBEParametersGenerator generator = makePBEGenerator(type, hash);
            byte key[] = convertPassword(type, keySpec);
            generator.init(key, keySpec.getSalt(), keySpec.getIterationCount());
            CipherParameters param;
            if(ivSize != 0)
                param = generator.generateDerivedParameters(keySize, ivSize);
            else
                param = generator.generateDerivedParameters(keySize);
            for(int i = 0; i != key.length; i++)
                key[i] = 0;

            return param;
        }

        public static CipherParameters makePBEMacParameters(PBEKeySpec keySpec, int type, int hash, int keySize)
        {
            PBEParametersGenerator generator = makePBEGenerator(type, hash);
            byte key[] = convertPassword(type, keySpec);
            generator.init(key, keySpec.getSalt(), keySpec.getIterationCount());
            CipherParameters param = generator.generateDerivedMacParameters(keySize);
            for(int i = 0; i != key.length; i++)
                key[i] = 0;

            return param;
        }

        private static byte[] convertPassword(int type, PBEKeySpec keySpec)
        {
            byte key[];
            if(type == 2)
                key = PBEParametersGenerator.PKCS12PasswordToBytes(keySpec.getPassword());
            else
            if(type == 5 || type == 4)
                key = PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(keySpec.getPassword());
            else
                key = PBEParametersGenerator.PKCS5PasswordToBytes(keySpec.getPassword());
            return key;
        }

        public Util()
        {
        }
    }


    public static final int MD5 = 0;
    public static final int SHA1 = 1;
    public static final int RIPEMD160 = 2;
    public static final int TIGER = 3;
    public static final int SHA256 = 4;
    public static final int MD2 = 5;
    public static final int GOST3411 = 6;
    public static final int PKCS5S1 = 0;
    public static final int PKCS5S2 = 1;
    public static final int PKCS12 = 2;
    public static final int OPENSSL = 3;
    public static final int PKCS5S1_UTF8 = 4;
    public static final int PKCS5S2_UTF8 = 5;
}
