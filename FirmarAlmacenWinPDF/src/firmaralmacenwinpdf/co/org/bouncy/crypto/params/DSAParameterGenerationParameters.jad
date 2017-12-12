// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSAParameterGenerationParameters.java

package co.org.bouncy.crypto.params;

import java.security.SecureRandom;

public class DSAParameterGenerationParameters
{

    public DSAParameterGenerationParameters(int L, int N, int certainty, SecureRandom random)
    {
        this(L, N, certainty, random, -1);
    }

    public DSAParameterGenerationParameters(int L, int N, int certainty, SecureRandom random, int usageIndex)
    {
        l = L;
        n = N;
        this.certainty = certainty;
        this.usageIndex = usageIndex;
        this.random = random;
    }

    public int getL()
    {
        return l;
    }

    public int getN()
    {
        return n;
    }

    public int getCertainty()
    {
        return certainty;
    }

    public SecureRandom getRandom()
    {
        return random;
    }

    public int getUsageIndex()
    {
        return usageIndex;
    }

    public static final int DIGITAL_SIGNATURE_USAGE = 1;
    public static final int KEY_ESTABLISHMENT_USAGE = 2;
    private final int l;
    private final int n;
    private final int usageIndex;
    private final int certainty;
    private final SecureRandom random;
}
