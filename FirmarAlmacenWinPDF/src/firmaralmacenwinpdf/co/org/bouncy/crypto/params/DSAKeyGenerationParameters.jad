// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSAKeyGenerationParameters.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.crypto.KeyGenerationParameters;
import java.math.BigInteger;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.params:
//            DSAParameters

public class DSAKeyGenerationParameters extends KeyGenerationParameters
{

    public DSAKeyGenerationParameters(SecureRandom random, DSAParameters params)
    {
        super(random, params.getP().bitLength() - 1);
        this.params = params;
    }

    public DSAParameters getParameters()
    {
        return params;
    }

    private DSAParameters params;
}
