// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMParser.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.util.io.pem.PemObject;
import co.org.bouncy.util.io.pem.PemObjectParser;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMParser

private class PEMParser$PrivateKeyParser
    implements PemObjectParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        try
        {
            return PrivateKeyInfo.getInstance(obj.getContent());
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("problem parsing PRIVATE KEY: ").append(e.toString()).toString(), e);
        }
    }

    final PEMParser this$0;

    public PEMParser$PrivateKeyParser()
    {
        this$0 = PEMParser.this;
        super();
    }
}
