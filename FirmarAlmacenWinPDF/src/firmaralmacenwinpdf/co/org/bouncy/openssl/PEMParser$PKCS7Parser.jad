// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMParser.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.cms.ContentInfo;
import co.org.bouncy.util.io.pem.PemObject;
import co.org.bouncy.util.io.pem.PemObjectParser;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMParser

private class PEMParser$PKCS7Parser
    implements PemObjectParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        try
        {
            ASN1InputStream aIn = new ASN1InputStream(obj.getContent());
            return ContentInfo.getInstance(aIn.readObject());
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("problem parsing PKCS7 object: ").append(e.toString()).toString(), e);
        }
    }

    final PEMParser this$0;

    private PEMParser$PKCS7Parser()
    {
        this$0 = PEMParser.this;
        super();
    }

    PEMParser$PKCS7Parser(PEMParser._cls1 x1)
    {
        this();
    }
}
