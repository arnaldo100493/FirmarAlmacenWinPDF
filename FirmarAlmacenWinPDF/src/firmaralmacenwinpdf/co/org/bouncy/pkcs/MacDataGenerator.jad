// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MacDataGenerator.java

package co.org.bouncy.pkcs;

import co.org.bouncy.asn1.pkcs.MacData;
import co.org.bouncy.asn1.pkcs.PKCS12PBEParams;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.DigestInfo;
import co.org.bouncy.operator.MacCalculator;
import java.io.OutputStream;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.pkcs:
//            PKCSException, PKCS12MacCalculatorBuilder

class MacDataGenerator
{

    MacDataGenerator(PKCS12MacCalculatorBuilder builder)
    {
        this.builder = builder;
    }

    public MacData build(char password[], byte data[])
        throws PKCSException
    {
        MacCalculator macCalculator;
        try
        {
            macCalculator = builder.build(password);
            OutputStream out = macCalculator.getOutputStream();
            out.write(data);
            out.close();
        }
        catch(Exception e)
        {
            throw new PKCSException((new StringBuilder()).append("unable to process data: ").append(e.getMessage()).toString(), e);
        }
        AlgorithmIdentifier algId = macCalculator.getAlgorithmIdentifier();
        DigestInfo dInfo = new DigestInfo(builder.getDigestAlgorithmIdentifier(), macCalculator.getMac());
        PKCS12PBEParams params = PKCS12PBEParams.getInstance(algId.getParameters());
        return new MacData(dInfo, params.getIV(), params.getIterations().intValue());
    }

    private PKCS12MacCalculatorBuilder builder;
}
