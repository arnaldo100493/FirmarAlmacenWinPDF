// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHKeyGenerationParameters.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.crypto.KeyGenerationParameters;
import java.math.BigInteger;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.params:
//            DHParameters

public class DHKeyGenerationParameters extends KeyGenerationParameters
{

    public DHKeyGenerationParameters(SecureRandom random, DHParameters params)
    {
        super(random, getStrength(params));
        this.params = params;
    }

    public DHParameters getParameters()
    {
        return params;
    }

    static int getStrength(DHParameters params)
    {
        return params.getL() == 0 ? params.getP().bitLength() : params.getL();
    }

    private DHParameters params;
}
