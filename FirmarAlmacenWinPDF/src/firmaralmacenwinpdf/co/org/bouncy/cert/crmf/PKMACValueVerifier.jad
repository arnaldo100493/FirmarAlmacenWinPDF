// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKMACValueVerifier.java

package co.org.bouncy.cert.crmf;

import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.cmp.PBMParameter;
import co.org.bouncy.asn1.crmf.PKMACValue;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.operator.MacCalculator;
import co.org.bouncy.util.Arrays;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.cert.crmf:
//            CRMFException, PKMACBuilder

class PKMACValueVerifier
{

    public PKMACValueVerifier(PKMACBuilder builder)
    {
        this.builder = builder;
    }

    public boolean isValid(PKMACValue value, char password[], SubjectPublicKeyInfo keyInfo)
        throws CRMFException
    {
        builder.setParameters(PBMParameter.getInstance(value.getAlgId().getParameters()));
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
        return Arrays.areEqual(calculator.getMac(), value.getValue().getBytes());
    }

    private final PKMACBuilder builder;
}
