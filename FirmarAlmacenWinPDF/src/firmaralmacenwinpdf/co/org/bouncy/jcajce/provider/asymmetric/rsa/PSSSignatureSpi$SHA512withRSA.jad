// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PSSSignatureSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.crypto.engines.RSABlindedEngine;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.rsa:
//            PSSSignatureSpi

public static class PSSSignatureSpi$SHA512withRSA extends PSSSignatureSpi
{

    public PSSSignatureSpi$SHA512withRSA()
    {
        super(new RSABlindedEngine(), new PSSParameterSpec("SHA-512", "MGF1", new MGF1ParameterSpec("SHA-512"), 64, 1));
    }
}
