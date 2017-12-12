// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaSimpleSignerInfoGeneratorBuilder.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.operator.*;
import co.org.bouncy.operator.jcajce.JcaContentSignerBuilder;
import co.org.bouncy.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import java.security.PrivateKey;
import java.security.Provider;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            JcaSimpleSignerInfoGeneratorBuilder

private class JcaSimpleSignerInfoGeneratorBuilder$ProviderHelper extends JcaSimpleSignerInfoGeneratorBuilder.Helper
{

    ContentSigner createContentSigner(String algorithm, PrivateKey privateKey)
        throws OperatorCreationException
    {
        return (new JcaContentSignerBuilder(algorithm)).setProvider(provider).build(privateKey);
    }

    DigestCalculatorProvider createDigestCalculatorProvider()
        throws OperatorCreationException
    {
        return (new JcaDigestCalculatorProviderBuilder()).setProvider(provider).build();
    }

    private final Provider provider;
    final JcaSimpleSignerInfoGeneratorBuilder this$0;

    public JcaSimpleSignerInfoGeneratorBuilder$ProviderHelper(Provider provider)
    {
        this$0 = JcaSimpleSignerInfoGeneratorBuilder.this;
        super(JcaSimpleSignerInfoGeneratorBuilder.this, null);
        this.provider = provider;
    }
}
