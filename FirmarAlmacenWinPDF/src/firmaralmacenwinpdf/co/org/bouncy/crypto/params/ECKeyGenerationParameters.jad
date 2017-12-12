// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECKeyGenerationParameters.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.crypto.KeyGenerationParameters;
import java.math.BigInteger;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.params:
//            ECDomainParameters

public class ECKeyGenerationParameters extends KeyGenerationParameters
{

    public ECKeyGenerationParameters(ECDomainParameters domainParams, SecureRandom random)
    {
        super(random, domainParams.getN().bitLength());
        this.domainParams = domainParams;
    }

    public ECDomainParameters getDomainParameters()
    {
        return domainParams;
    }

    private ECDomainParameters domainParams;
}
