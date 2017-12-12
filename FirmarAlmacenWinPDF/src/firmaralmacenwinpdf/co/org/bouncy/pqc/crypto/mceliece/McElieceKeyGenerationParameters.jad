// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceKeyGenerationParameters.java

package co.org.bouncy.pqc.crypto.mceliece;

import co.org.bouncy.crypto.KeyGenerationParameters;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.pqc.crypto.mceliece:
//            McElieceParameters

public class McElieceKeyGenerationParameters extends KeyGenerationParameters
{

    public McElieceKeyGenerationParameters(SecureRandom random, McElieceParameters params)
    {
        super(random, 256);
        this.params = params;
    }

    public McElieceParameters getParameters()
    {
        return params;
    }

    private McElieceParameters params;
}
