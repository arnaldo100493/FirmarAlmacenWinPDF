// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParametersWithRandom.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.crypto.CipherParameters;
import java.security.SecureRandom;

public class ParametersWithRandom
    implements CipherParameters
{

    public ParametersWithRandom(CipherParameters parameters, SecureRandom random)
    {
        this.random = random;
        this.parameters = parameters;
    }

    public ParametersWithRandom(CipherParameters parameters)
    {
        this(parameters, new SecureRandom());
    }

    public SecureRandom getRandom()
    {
        return random;
    }

    public CipherParameters getParameters()
    {
        return parameters;
    }

    private SecureRandom random;
    private CipherParameters parameters;
}
