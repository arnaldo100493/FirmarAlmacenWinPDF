// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKMACValueGenerator.java

package co.org.bouncy.cert.crmf;

import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.crmf.PKMACValue;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.operator.MacCalculator;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.cert.crmf:
//            CRMFException, PKMACBuilder

class PKMACValueGenerator
{

    public PKMACValueGenerator(PKMACBuilder builder)
    {
        this.builder = builder;
    }

    public PKMACValue generate(char password[], SubjectPublicKeyInfo keyInfo)
        throws CRMFException
    {
        MacCalculator calculator = builder.build(password);
        OutputStream macOut = calculator.getOutputStream();
        try
        {
            macOut.write(keyInfo.getEncoded("DER"));
            macOut.close();
        }
        catch(IOException e)
        {
            throw new CRMFException((new StringBuilder()).append("exception encoding mac input: ").append(e.getMessage()).toString(), e);
        }
        return new PKMACValue(calculator.getAlgorithmIdentifier(), new DERBitString(calculator.getMac()));
    }

    private PKMACBuilder builder;
}
