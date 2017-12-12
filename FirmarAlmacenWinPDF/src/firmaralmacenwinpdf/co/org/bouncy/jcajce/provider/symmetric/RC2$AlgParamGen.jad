// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RC2.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            RC2

public static class RC2$AlgParamGen extends BaseAlgorithmParameterGenerator
{

    protected void engineInit(AlgorithmParameterSpec genParamSpec, SecureRandom random)
        throws InvalidAlgorithmParameterException
    {
        if(genParamSpec instanceof RC2ParameterSpec)
        {
            spec = (RC2ParameterSpec)genParamSpec;
            return;
        } else
        {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for RC2 parameter generation.");
        }
    }

    protected AlgorithmParameters engineGenerateParameters()
    {
        AlgorithmParameters params;
        if(spec == null)
        {
            byte iv[] = new byte[8];
            if(random == null)
                random = new SecureRandom();
            random.nextBytes(iv);
            try
            {
                params = AlgorithmParameters.getInstance("RC2", "BC");
                params.init(new IvParameterSpec(iv));
            }
            catch(Exception e)
            {
                throw new RuntimeException(e.getMessage());
            }
        } else
        {
            try
            {
                params = AlgorithmParameters.getInstance("RC2", "BC");
                params.init(spec);
            }
            catch(Exception e)
            {
                throw new RuntimeException(e.getMessage());
            }
        }
        return params;
    }

    RC2ParameterSpec spec;

    public RC2$AlgParamGen()
    {
        spec = null;
    }
}
