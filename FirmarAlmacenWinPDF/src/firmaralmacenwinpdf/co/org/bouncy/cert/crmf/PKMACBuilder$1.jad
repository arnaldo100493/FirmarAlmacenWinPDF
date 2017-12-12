// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKMACBuilder.java

package co.org.bouncy.cert.crmf;

import co.org.bouncy.asn1.cmp.CMPObjectIdentifiers;
import co.org.bouncy.asn1.cmp.PBMParameter;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.cert.crmf:
//            CRMFException, PKMACBuilder, PKMACValuesCalculator

class PKMACBuilder$1
    implements MacCalculator
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return new AlgorithmIdentifier(CMPObjectIdentifiers.passwordBasedMac, val$params);
    }

    public GenericKey getKey()
    {
        return new GenericKey(getAlgorithmIdentifier(), val$key);
    }

    public OutputStream getOutputStream()
    {
        return bOut;
    }

    public byte[] getMac()
    {
        try
        {
            return PKMACBuilder.access$000(PKMACBuilder.this).calculateMac(val$key, bOut.toByteArray());
        }
        catch(CRMFException e)
        {
            throw new RuntimeOperatorException((new StringBuilder()).append("exception calculating mac: ").append(e.getMessage()).toString(), e);
        }
    }

    ByteArrayOutputStream bOut;
    final PBMParameter val$params;
    final byte val$key[];
    final PKMACBuilder this$0;

    PKMACBuilder$1()
    {
        this$0 = final_pkmacbuilder;
        val$params = pbmparameter;
        val$key = _5B_B.this;
        super();
        bOut = new ByteArrayOutputStream();
    }
}
