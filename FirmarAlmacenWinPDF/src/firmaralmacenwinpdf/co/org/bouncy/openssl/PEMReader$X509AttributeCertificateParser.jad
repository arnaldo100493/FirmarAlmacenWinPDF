// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMReader.java

package co.org.bouncy.openssl;

import co.org.bouncy.util.io.pem.PemObject;
import co.org.bouncy.util.io.pem.PemObjectParser;
import co.org.bouncy.x509_.X509V2AttributeCertificate;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMReader

private class PEMReader$X509AttributeCertificateParser
    implements PemObjectParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        return new X509V2AttributeCertificate(obj.getContent());
    }

    final PEMReader this$0;

    private PEMReader$X509AttributeCertificateParser()
    {
        this$0 = PEMReader.this;
        super();
    }

    PEMReader$X509AttributeCertificateParser(PEMReader._cls1 x1)
    {
        this();
    }
}
