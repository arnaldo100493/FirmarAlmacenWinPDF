// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPKCS12MacCalculatorBuilder.java

package co.org.bouncy.pkcs.bc;

import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCS12PBEParams;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.ExtendedDigest;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.operator.MacCalculator;
import co.org.bouncy.pkcs.PKCS12MacCalculatorBuilder;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.pkcs.bc:
//            PKCS12PBEUtils

public class BcPKCS12MacCalculatorBuilder
    implements PKCS12MacCalculatorBuilder
{

    public BcPKCS12MacCalculatorBuilder()
    {
        this(((ExtendedDigest) (new SHA1Digest())), new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE));
    }

    public BcPKCS12MacCalculatorBuilder(ExtendedDigest digest, AlgorithmIdentifier algorithmIdentifier)
    {
        iterationCount = 1024;
        this.digest = digest;
        this.algorithmIdentifier = algorithmIdentifier;
        saltLength = digest.getDigestSize();
    }

    public AlgorithmIdentifier getDigestAlgorithmIdentifier()
    {
        return algorithmIdentifier;
    }

    public MacCalculator build(char password[])
    {
        if(random == null)
            random = new SecureRandom();
        byte salt[] = new byte[saltLength];
        random.nextBytes(salt);
        return PKCS12PBEUtils.createMacCalculator(algorithmIdentifier.getAlgorithm(), digest, new PKCS12PBEParams(salt, iterationCount), password);
    }

    private ExtendedDigest digest;
    private AlgorithmIdentifier algorithmIdentifier;
    private SecureRandom random;
    private int saltLength;
    private int iterationCount;
}
