// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RainbowKeyGenerationParameters.java

package co.org.bouncy.pqc.crypto.rainbow;

import co.org.bouncy.crypto.KeyGenerationParameters;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.pqc.crypto.rainbow:
//            RainbowParameters

public class RainbowKeyGenerationParameters extends KeyGenerationParameters
{

    public RainbowKeyGenerationParameters(SecureRandom random, RainbowParameters params)
    {
        super(random, params.getVi()[params.getVi().length - 1] - params.getVi()[0]);
        this.params = params;
    }

    public RainbowParameters getParameters()
    {
        return params;
    }

    private RainbowParameters params;
}
