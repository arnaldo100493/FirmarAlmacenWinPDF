// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMParser.java

package co.org.bouncy.openssl;

import co.org.bouncy.cert.X509CRLHolder;
import co.org.bouncy.util.io.pem.PemObject;
import co.org.bouncy.util.io.pem.PemObjectParser;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMParser

private class PEMParser$X509CRLParser
    implements PemObjectParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        try
        {
            return new X509CRLHolder(obj.getContent());
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("problem parsing cert: ").append(e.toString()).toString(), e);
        }
    }

    final PEMParser this$0;

    private PEMParser$X509CRLParser()
    {
        this$0 = PEMParser.this;
        super();
    }

    PEMParser$X509CRLParser(PEMParser._cls1 x1)
    {
        this();
    }
}
