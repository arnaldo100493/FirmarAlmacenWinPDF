// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPKCS12MacCalculatorBuilderProvider.java

package co.org.bouncy.pkcs.bc;

import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.pkcs.PKCS12PBEParams;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.MacCalculator;
import co.org.bouncy.operator.OperatorCreationException;
import co.org.bouncy.operator.bc.BcDigestProvider;
import co.org.bouncy.pkcs.PKCS12MacCalculatorBuilder;

// Referenced classes of package co.org.bouncy.pkcs.bc:
//            BcPKCS12MacCalculatorBuilderProvider, PKCS12PBEUtils

class BcPKCS12MacCalculatorBuilderProvider$1
    implements PKCS12MacCalculatorBuilder
{

    public MacCalculator build(char password[])
        throws OperatorCreationException
    {
        PKCS12PBEParams pbeParams = PKCS12PBEParams.getInstance(val$algorithmIdentifier.getParameters());
        return PKCS12PBEUtils.createMacCalculator(val$algorithmIdentifier.getAlgorithm(), BcPKCS12MacCalculatorBuilderProvider.access$000(BcPKCS12MacCalculatorBuilderProvider.this).get(val$algorithmIdentifier), pbeParams, password);
    }

    public AlgorithmIdentifier getDigestAlgorithmIdentifier()
    {
        return new AlgorithmIdentifier(val$algorithmIdentifier.getAlgorithm(), DERNull.INSTANCE);
    }

    final AlgorithmIdentifier val$algorithmIdentifier;
    final BcPKCS12MacCalculatorBuilderProvider this$0;

    BcPKCS12MacCalculatorBuilderProvider$1()
    {
        this$0 = final_bcpkcs12maccalculatorbuilderprovider;
        val$algorithmIdentifier = AlgorithmIdentifier.this;
        super();
    }
}
