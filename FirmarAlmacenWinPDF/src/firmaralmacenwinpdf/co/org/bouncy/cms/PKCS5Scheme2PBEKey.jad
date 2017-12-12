// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS5Scheme2PBEKey.java

package co.org.bouncy.cms;

import co.org.bouncy.crypto.PBEParametersGenerator;
import co.org.bouncy.crypto.generators.PKCS5S2ParametersGenerator;
import co.org.bouncy.crypto.params.KeyParameter;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;

// Referenced classes of package co.org.bouncy.cms:
//            CMSPBEKey, CMSEnvelopedHelper

public class PKCS5Scheme2PBEKey extends CMSPBEKey
{

    public PKCS5Scheme2PBEKey(char password[], byte salt[], int iterationCount)
    {
        super(password, salt, iterationCount);
    }

    public PKCS5Scheme2PBEKey(char password[], AlgorithmParameters pbeParams)
        throws InvalidAlgorithmParameterException
    {
        super(password, getParamSpec(pbeParams));
    }

    byte[] getEncoded(String algorithmOid)
    {
        PKCS5S2ParametersGenerator gen = new PKCS5S2ParametersGenerator();
        gen.init(PBEParametersGenerator.PKCS5PasswordToBytes(getPassword()), getSalt(), getIterationCount());
        return ((KeyParameter)gen.generateDerivedParameters(CMSEnvelopedHelper.INSTANCE.getKeySize(algorithmOid))).getKey();
    }
}
