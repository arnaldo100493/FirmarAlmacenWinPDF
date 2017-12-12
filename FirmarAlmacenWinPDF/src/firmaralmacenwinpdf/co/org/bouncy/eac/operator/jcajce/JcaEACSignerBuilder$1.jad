// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaEACSignerBuilder.java

package co.org.bouncy.eac.operator.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.eac.EACObjectIdentifiers;
import co.org.bouncy.eac.operator.EACSigner;
import co.org.bouncy.operator.RuntimeOperatorException;
import java.io.OutputStream;
import java.security.SignatureException;

// Referenced classes of package co.org.bouncy.eac.operator.jcajce:
//            JcaEACSignerBuilder

class JcaEACSignerBuilder$1
    implements EACSigner
{

    public ASN1ObjectIdentifier getUsageIdentifier()
    {
        return val$usageOid;
    }

    public OutputStream getOutputStream()
    {
        return val$sigStream;
    }

    public byte[] getSignature()
    {
        byte signature[];
        try
        {
            signature = val$sigStream.getSignature();
            if(val$usageOid.on(EACObjectIdentifiers.id_TA_ECDSA))
                return JcaEACSignerBuilder.access$000(signature);
        }
        catch(SignatureException e)
        {
            throw new RuntimeOperatorException((new StringBuilder()).append("exception obtaining signature: ").append(e.getMessage()).toString(), e);
        }
        return signature;
    }

    final ASN1ObjectIdentifier val$usageOid;
    final gnatureOutputStream val$sigStream;
    final JcaEACSignerBuilder this$0;

    JcaEACSignerBuilder$1()
    {
        this$0 = final_jcaeacsignerbuilder;
        val$usageOid = asn1objectidentifier;
        val$sigStream = gnatureOutputStream.this;
        super();
    }
}
