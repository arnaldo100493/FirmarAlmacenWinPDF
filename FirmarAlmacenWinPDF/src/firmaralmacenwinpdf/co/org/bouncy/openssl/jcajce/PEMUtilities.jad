// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMUtilities.java

package co.org.bouncy.openssl.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.DERObjectIdentifier;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.crypto.PBEParametersGenerator;
import co.org.bouncy.crypto.generators.OpenSSLPBEParametersGenerator;
import co.org.bouncy.crypto.generators.PKCS5S2ParametersGenerator;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.jcajce.JcaJceHelper;
import co.org.bouncy.openssl.EncryptionException;
import co.org.bouncy.openssl.PEMException;
import co.org.bouncy.util.Integers;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.*;

class PEMUtilities
{

    PEMUtilities()
    {
    }

    static int getKeySize(String algorithm)
    {
        if(!KEYSIZES.containsKey(algorithm))
            throw new IllegalStateException((new StringBuilder()).append("no key size for algorithm: ").append(algorithm).toString());
        else
            return ((Integer)KEYSIZES.get(algorithm)).intValue();
    }

    static boolean isPKCS5Scheme1(DERObjectIdentifier algOid)
    {
        return PKCS5_SCHEME_1.contains(algOid);
    }

    static boolean isPKCS5Scheme2(ASN1ObjectIdentifier algOid)
    {
        return PKCS5_SCHEME_2.contains(algOid);
    }

    public static boolean isPKCS12(DERObjectIdentifier algOid)
    {
        return algOid.getId().startsWith(PKCSObjectIdentifiers.pkcs_12PbeIds.getId());
    }

    public static SecretKey generateSecretKeyForPKCS5Scheme2(String algorithm, char password[], byte salt[], int iterationCount)
    {
        PBEParametersGenerator generator = new PKCS5S2ParametersGenerator();
        generator.init(PBEParametersGenerator.PKCS5PasswordToBytes(password), salt, iterationCount);
        return new SecretKeySpec(((KeyParameter)generator.generateDerivedParameters(getKeySize(algorithm))).getKey(), algorithm);
    }

    static byte[] crypt(boolean encrypt, JcaJceHelper helper, byte bytes[], char password[], String dekAlgName, byte iv[])
        throws PEMException
    {
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        String blockMode = "CBC";
        String padding = "PKCS5Padding";
        if(dekAlgName.endsWith("-CFB"))
        {
            blockMode = "CFB";
            padding = "NoPadding";
        }
        if(dekAlgName.endsWith("-ECB") || "DES-EDE".equals(dekAlgName) || "DES-EDE3".equals(dekAlgName))
        {
            blockMode = "ECB";
            paramSpec = null;
        }
        if(dekAlgName.endsWith("-OFB"))
        {
            blockMode = "OFB";
            padding = "NoPadding";
        }
        String alg;
        Key sKey;
        if(dekAlgName.startsWith("DES-EDE"))
        {
            alg = "DESede";
            boolean des2 = !dekAlgName.startsWith("DES-EDE3");
            sKey = getKey(password, alg, 24, iv, des2);
        } else
        if(dekAlgName.startsWith("DES-"))
        {
            alg = "DES";
            sKey = getKey(password, alg, 8, iv);
        } else
        if(dekAlgName.startsWith("BF-"))
        {
            alg = "Blowfish";
            sKey = getKey(password, alg, 16, iv);
        } else
        if(dekAlgName.startsWith("RC2-"))
        {
            alg = "RC2";
            int keyBits = 128;
            if(dekAlgName.startsWith("RC2-40-"))
                keyBits = 40;
            else
            if(dekAlgName.startsWith("RC2-64-"))
                keyBits = 64;
            sKey = getKey(password, alg, keyBits / 8, iv);
            if(paramSpec == null)
                paramSpec = new RC2ParameterSpec(keyBits);
            else
                paramSpec = new RC2ParameterSpec(keyBits, iv);
        } else
        if(dekAlgName.startsWith("AES-"))
        {
            alg = "AES";
            byte salt[] = iv;
            if(salt.length > 8)
            {
                salt = new byte[8];
                System.arraycopy(iv, 0, salt, 0, 8);
            }
            int keyBits;
            if(dekAlgName.startsWith("AES-128-"))
                keyBits = 128;
            else
            if(dekAlgName.startsWith("AES-192-"))
                keyBits = 192;
            else
            if(dekAlgName.startsWith("AES-256-"))
                keyBits = 256;
            else
                throw new EncryptionException("unknown AES encryption with private key");
            sKey = getKey(password, "AES", keyBits / 8, salt);
        } else
        {
            throw new EncryptionException("unknown encryption with private key");
        }
        String transformation = (new StringBuilder()).append(alg).append("/").append(blockMode).append("/").append(padding).toString();
        try
        {
            Cipher c = helper.createCipher(transformation);
            int mode = encrypt ? 1 : 2;
            if(paramSpec == null)
                c.init(mode, sKey);
            else
                c.init(mode, sKey, paramSpec);
            return c.doFinal(bytes);
        }
        catch(Exception e)
        {
            throw new EncryptionException("exception using cipher - please check password and data.", e);
        }
    }

    private static SecretKey getKey(char password[], String algorithm, int keyLength, byte salt[])
    {
        return getKey(password, algorithm, keyLength, salt, false);
    }

    private static SecretKey getKey(char password[], String algorithm, int keyLength, byte salt[], boolean des2)
    {
        OpenSSLPBEParametersGenerator pGen = new OpenSSLPBEParametersGenerator();
        pGen.init(PBEParametersGenerator.PKCS5PasswordToBytes(password), salt);
        KeyParameter keyParam = (KeyParameter)pGen.generateDerivedParameters(keyLength * 8);
        byte key[] = keyParam.getKey();
        if(des2 && key.length >= 24)
            System.arraycopy(key, 0, key, 16, 8);
        return new SecretKeySpec(key, algorithm);
    }

    private static final Map KEYSIZES;
    private static final Set PKCS5_SCHEME_1;
    private static final Set PKCS5_SCHEME_2;

    static 
    {
        KEYSIZES = new HashMap();
        PKCS5_SCHEME_1 = new HashSet();
        PKCS5_SCHEME_2 = new HashSet();
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC);
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithMD2AndRC2_CBC);
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC);
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC);
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC);
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC);
        PKCS5_SCHEME_2.add(PKCSObjectIdentifiers.id_PBES2);
        PKCS5_SCHEME_2.add(PKCSObjectIdentifiers.des_EDE3_CBC);
        PKCS5_SCHEME_2.add(NISTObjectIdentifiers.id_aes128_CBC);
        PKCS5_SCHEME_2.add(NISTObjectIdentifiers.id_aes192_CBC);
        PKCS5_SCHEME_2.add(NISTObjectIdentifiers.id_aes256_CBC);
        KEYSIZES.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), Integers.valueOf(192));
        KEYSIZES.put(NISTObjectIdentifiers.id_aes128_CBC.getId(), Integers.valueOf(128));
        KEYSIZES.put(NISTObjectIdentifiers.id_aes192_CBC.getId(), Integers.valueOf(192));
        KEYSIZES.put(NISTObjectIdentifiers.id_aes256_CBC.getId(), Integers.valueOf(256));
    }
}
