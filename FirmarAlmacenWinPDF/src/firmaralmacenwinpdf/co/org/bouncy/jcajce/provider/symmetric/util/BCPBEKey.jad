// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCPBEKey.java

package co.org.bouncy.jcajce.provider.symmetric.util;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.PBEParametersGenerator;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;

public class BCPBEKey
    implements PBEKey
{

    public BCPBEKey(String algorithm, ASN1ObjectIdentifier oid, int type, int digest, int keySize, int ivSize, PBEKeySpec pbeKeySpec, 
            CipherParameters param)
    {
        tryWrong = false;
        this.algorithm = algorithm;
        this.oid = oid;
        this.type = type;
        this.digest = digest;
        this.keySize = keySize;
        this.ivSize = ivSize;
        this.pbeKeySpec = pbeKeySpec;
        this.param = param;
    }

    public String getAlgorithm()
    {
        return algorithm;
    }

    public String getFormat()
    {
        return "RAW";
    }

    public byte[] getEncoded()
    {
        if(param != null)
        {
            KeyParameter kParam;
            if(param instanceof ParametersWithIV)
                kParam = (KeyParameter)((ParametersWithIV)param).getParameters();
            else
                kParam = (KeyParameter)param;
            return kParam.getKey();
        }
        if(type == 2)
            return PBEParametersGenerator.PKCS12PasswordToBytes(pbeKeySpec.getPassword());
        if(type == 5)
            return PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(pbeKeySpec.getPassword());
        else
            return PBEParametersGenerator.PKCS5PasswordToBytes(pbeKeySpec.getPassword());
    }

    int getType()
    {
        return type;
    }

    int getDigest()
    {
        return digest;
    }

    int getKeySize()
    {
        return keySize;
    }

    public int getIvSize()
    {
        return ivSize;
    }

    public CipherParameters getParam()
    {
        return param;
    }

    public char[] getPassword()
    {
        return pbeKeySpec.getPassword();
    }

    public byte[] getSalt()
    {
        return pbeKeySpec.getSalt();
    }

    public int getIterationCount()
    {
        return pbeKeySpec.getIterationCount();
    }

    public ASN1ObjectIdentifier getOID()
    {
        return oid;
    }

    public void setTryWrongPKCS12Zero(boolean tryWrong)
    {
        this.tryWrong = tryWrong;
    }

    boolean shouldTryWrongPKCS12()
    {
        return tryWrong;
    }

    String algorithm;
    ASN1ObjectIdentifier oid;
    int type;
    int digest;
    int keySize;
    int ivSize;
    CipherParameters param;
    PBEKeySpec pbeKeySpec;
    boolean tryWrong;
}
