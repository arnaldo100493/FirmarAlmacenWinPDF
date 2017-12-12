// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMParser.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.util.io.pem.PemObject;
import co.org.bouncy.util.io.pem.PemObjectParser;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMParser

private class PEMParser$PublicKeyParser
    implements PemObjectParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        return SubjectPublicKeyInfo.getInstance(obj.getContent());
    }

    final PEMParser this$0;

    public PEMParser$PublicKeyParser()
    {
        this$0 = PEMParser.this;
        super();
    }
}
