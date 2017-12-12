// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PBEPBKDF2.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            PBEPBKDF2

public static class PBEPBKDF2$Mappings extends AlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("AlgorithmParameters.PBKDF2", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(PKCSObjectIdentifiers.id_PBKDF2).toString(), "PBKDF2");
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/PBEPBKDF2.getName();


    public PBEPBKDF2$Mappings()
    {
    }
}
