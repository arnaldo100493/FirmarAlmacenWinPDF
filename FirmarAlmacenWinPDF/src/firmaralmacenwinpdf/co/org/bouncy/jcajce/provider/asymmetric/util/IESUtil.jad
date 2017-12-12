// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IESUtil.java

package co.org.bouncy.jcajce.provider.asymmetric.util;

import co.org.bouncy.crypto.BlockCipher;
import co.org.bouncy.crypto.BufferedBlockCipher;
import co.org.bouncy.crypto.engines.IESEngine;
import co.org.bouncy.jce.spec.IESParameterSpec;

public class IESUtil
{

    public IESUtil()
    {
    }

    public static IESParameterSpec guessParameterSpec(IESEngine engine)
    {
        if(engine.getCipher() == null)
            return new IESParameterSpec(null, null, 128);
        if(engine.getCipher().getUnderlyingCipher().getAlgorithmName().equals("DES") || engine.getCipher().getUnderlyingCipher().getAlgorithmName().equals("RC2") || engine.getCipher().getUnderlyingCipher().getAlgorithmName().equals("RC5-32") || engine.getCipher().getUnderlyingCipher().getAlgorithmName().equals("RC5-64"))
            return new IESParameterSpec(null, null, 64, 64);
        if(engine.getCipher().getUnderlyingCipher().getAlgorithmName().equals("SKIPJACK"))
            return new IESParameterSpec(null, null, 80, 80);
        if(engine.getCipher().getUnderlyingCipher().getAlgorithmName().equals("GOST28147"))
            return new IESParameterSpec(null, null, 256, 256);
        else
            return new IESParameterSpec(null, null, 128, 128);
    }
}
