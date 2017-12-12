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
import co.org.bouncy.pkcs.PKCS12MacCalculatorBuilderProvider;

// Referenced classes of package co.org.bouncy.pkcs.bc:
//            PKCS12PBEUtils

public class BcPKCS12MacCalculatorBuilderProvider
    implements PKCS12MacCalculatorBuilderProvider
{

    public BcPKCS12MacCalculatorBuilderProvider(BcDigestProvider digestProvider)
    {
        this.digestProvider = digestProvider;
    }

    public PKCS12MacCalculatorBuilder get(final AlgorithmIdentifier algorithmIdentifier)
    {
        return new PKCS12MacCalculatorBuilder() {

            public MacCalculator build(char password[])
                throws OperatorCreationException
            {
                PKCS12PBEParams pbeParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                return PKCS12PBEUtils.createMacCalculator(algorithmIdentifier.getAlgorithm(), digestProvider.get(algorithmIdentifier), pbeParams, password);
            }

            public AlgorithmIdentifier getDigestAlgorithmIdentifier()
            {
                return new AlgorithmIdentifier(algorithmIdentifier.getAlgorithm(), DERNull.INSTANCE);
            }

            final AlgorithmIdentifier val$algorithmIdentifier;
            final BcPKCS12MacCalculatorBuilderProvider this$0;

            
            {
                this$0 = BcPKCS12MacCalculatorBuilderProvider.this;
                algorithmIdentifier = algorithmidentifier;
                super();
            }
        }
;
    }

    private BcDigestProvider digestProvider;

}
