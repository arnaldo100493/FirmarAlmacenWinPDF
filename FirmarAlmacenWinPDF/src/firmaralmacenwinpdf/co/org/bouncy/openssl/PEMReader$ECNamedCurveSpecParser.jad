// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMReader.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.DERObjectIdentifier;
import co.org.bouncy.jce.ECNamedCurveTable;
import co.org.bouncy.util.io.pem.PemObject;
import co.org.bouncy.util.io.pem.PemObjectParser;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMReader

private class PEMReader$ECNamedCurveSpecParser
    implements PemObjectParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        try
        {
            DERObjectIdentifier oid = (DERObjectIdentifier)ASN1Primitive.fromByteArray(obj.getContent());
            Object params = ECNamedCurveTable.getParameterSpec(oid.getId());
            if(params == null)
                throw new IOException("object ID not found in EC curve table");
            else
                return params;
        }
        catch(IOException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("exception extracting EC named curve: ").append(e.toString()).toString());
        }
    }

    final PEMReader this$0;

    private PEMReader$ECNamedCurveSpecParser()
    {
        this$0 = PEMReader.this;
        super();
    }

    PEMReader$ECNamedCurveSpecParser(PEMReader._cls1 x1)
    {
        this();
    }
}
