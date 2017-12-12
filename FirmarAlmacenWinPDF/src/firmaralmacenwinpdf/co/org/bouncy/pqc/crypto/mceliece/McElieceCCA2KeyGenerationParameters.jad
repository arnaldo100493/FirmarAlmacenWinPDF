// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceCCA2KeyGenerationParameters.java

package co.org.bouncy.pqc.crypto.mceliece;

import co.org.bouncy.crypto.KeyGenerationParameters;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.pqc.crypto.mceliece:
//            McElieceCCA2Parameters

public class McElieceCCA2KeyGenerationParameters extends KeyGenerationParameters
{

    public McElieceCCA2KeyGenerationParameters(SecureRandom random, McElieceCCA2Parameters params)
    {
        super(random, 128);
        this.params = params;
    }

    public McElieceCCA2Parameters getParameters()
    {
        return params;
    }

    private McElieceCCA2Parameters params;
}
