// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaEACSignatureVerifierBuilder.java

package co.org.bouncy.eac.operator.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.eac.EACObjectIdentifiers;
import co.org.bouncy.eac.operator.EACSignatureVerifier;
import co.org.bouncy.operator.RuntimeOperatorException;
import java.io.OutputStream;
import java.security.SignatureException;

// Referenced classes of package co.org.bouncy.eac.operator.jcajce:
//            JcaEACSignatureVerifierBuilder

class JcaEACSignatureVerifierBuilder$1
    implements EACSignatureVerifier
{

    public ASN1ObjectIdentifier getUsageIdentifier()
    {
        return val$usageOid;
    }

    public OutputStream getOutputStream()
    {
        return val$sigStream;
    }

    public boolean verify(byte expected[])
    {
        if(val$usageOid.on(EACObjectIdentifiers.id_TA_ECDSA))
            try
            {
                byte reencoded[] = JcaEACSignatureVerifierBuilder.access$000(expected);
                return val$sigStream.verify(reencoded);
            }
            catch(Exception e)
            {
                return false;
            }
        try
        {
            return val$sigStream.verify(expected);
        }
        catch(SignatureException e)
        {
            throw new RuntimeOperatorException((new StringBuilder()).append("exception obtaining signature: ").append(e.getMessage()).toString(), e);
        }
    }

    final ASN1ObjectIdentifier val$usageOid;
    final gnatureOutputStream val$sigStream;
    final JcaEACSignatureVerifierBuilder this$0;

    JcaEACSignatureVerifierBuilder$1()
    {
        this$0 = final_jcaeacsignatureverifierbuilder;
        val$usageOid = asn1objectidentifier;
        val$sigStream = gnatureOutputStream.this;
        super();
    }
}
