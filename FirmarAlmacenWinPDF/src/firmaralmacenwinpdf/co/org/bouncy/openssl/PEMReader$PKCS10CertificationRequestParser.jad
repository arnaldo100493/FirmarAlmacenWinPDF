// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMReader.java

package co.org.bouncy.openssl;

import co.org.bouncy.jce.PKCS10CertificationRequest;
import co.org.bouncy.util.io.pem.PemObject;
import co.org.bouncy.util.io.pem.PemObjectParser;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMReader

private class PEMReader$PKCS10CertificationRequestParser
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

    final PEMReader this$0;

    private PEMReader$PKCS10CertificationRequestParser()
    {
        this$0 = PEMReader.this;
        super();
    }

    PEMReader$PKCS10CertificationRequestParser(PEMReader._cls1 x1)
    {
        this();
    }
}
