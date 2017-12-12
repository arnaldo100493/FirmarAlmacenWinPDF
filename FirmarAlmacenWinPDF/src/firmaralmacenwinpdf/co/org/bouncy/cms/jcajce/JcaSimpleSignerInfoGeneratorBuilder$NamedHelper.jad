// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaSimpleSignerInfoGeneratorBuilder.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.operator.*;
import co.org.bouncy.operator.jcajce.JcaContentSignerBuilder;
import co.org.bouncy.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import java.security.PrivateKey;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            JcaSimpleSignerInfoGeneratorBuilder

private class JcaSimpleSignerInfoGeneratorBuilder$NamedHelper extends JcaSimpleSignerInfoGeneratorBuilder.Helper
{

    ContentSigner createContentSigner(String algorithm, PrivateKey privateKey)
        throws OperatorCreationException
    {
        return (new JcaContentSignerBuilder(algorithm)).setProvider(providerName).build(privateKey);
    }

    DigestCalculatorProvider createDigestCalculatorProvider()
        throws OperatorCreationException
    {
        return (new JcaDigestCalculatorProviderBuilder()).setProvider(providerName).build();
    }

    private final String providerName;
    final JcaSimpleSignerInfoGeneratorBuilder this$0;

    public JcaSimpleSignerInfoGeneratorBuilder$NamedHelper(String providerName)
    {
        this$0 = JcaSimpleSignerInfoGeneratorBuilder.this;
        super(JcaSimpleSignerInfoGeneratorBuilder.this, null);
        this.providerName = providerName;
    }
}
