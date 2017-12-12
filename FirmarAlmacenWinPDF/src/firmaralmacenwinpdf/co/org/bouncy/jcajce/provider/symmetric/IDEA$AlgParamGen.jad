// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IDEA.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            IDEA

public static class IDEA$AlgParamGen extends BaseAlgorithmParameterGenerator
{

    protected void engineInit(AlgorithmParameterSpec genParamSpec, SecureRandom random)
        throws InvalidAlgorithmParameterException
    {
        throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for IDEA parameter generation.");
    }

    protected AlgorithmParameters engineGenerateParameters()
    {
        byte iv[] = new byte[8];
        if(random == null)
            random = new SecureRandom();
        random.nextBytes(iv);
        AlgorithmParameters params;
        try
        {
            params = AlgorithmParameters.getInstance("IDEA", "BC");
            params.init(new IvParameterSpec(iv));
        }
        catch(Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
        return params;
    }

    public IDEA$AlgParamGen()
    {
    }
}
