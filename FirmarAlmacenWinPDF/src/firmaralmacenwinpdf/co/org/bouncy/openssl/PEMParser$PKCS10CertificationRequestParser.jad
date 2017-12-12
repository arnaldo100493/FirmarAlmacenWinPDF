// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMParser.java

package co.org.bouncy.openssl;

import co.org.bouncy.pkcs.PKCS10CertificationRequest;
import co.org.bouncy.util.io.pem.PemObject;
import co.org.bouncy.util.io.pem.PemObjectParser;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMParser

private class PEMParser$PKCS10CertificationRequestParser
    implements PemObjectParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        try
        {
            return new PKCS10CertificationRequest(obj.getContent());
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("problem parsing certrequest: ").append(e.toString()).toString(), e);
        }
    }

    final PEMParser this$0;

    private PEMParser$PKCS10CertificationRequestParser()
    {
        this$0 = PEMParser.this;
        super();
    }

    PEMParser$PKCS10CertificationRequestParser(PEMParser._cls1 x1)
    {
        this();
    }
}
