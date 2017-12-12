// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMParser.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x9.X9ECParameters;
import co.org.bouncy.util.io.pem.PemObject;
import co.org.bouncy.util.io.pem.PemObjectParser;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMParser

private class PEMParser$ECCurveParamsParser
    implements PemObjectParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        Object param;
        try
        {
            param = ASN1Primitive.fromByteArray(obj.getContent());
            if(param instanceof ASN1ObjectIdentifier)
                return ASN1Primitive.fromByteArray(obj.getContent());
        }
        catch(IOException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("exception extracting EC named curve: ").append(e.toString()).toString());
        }
        if(param instanceof ASN1Sequence)
            return X9ECParameters.getInstance(param);
        return null;
    }

    final PEMParser this$0;

    private PEMParser$ECCurveParamsParser()
    {
        this$0 = PEMParser.this;
        super();
    }

    PEMParser$ECCurveParamsParser(PEMParser._cls1 x1)
    {
        this();
    }
}
