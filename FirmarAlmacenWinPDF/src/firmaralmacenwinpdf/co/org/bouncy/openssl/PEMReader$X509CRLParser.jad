// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMReader.java

package co.org.bouncy.openssl;

import co.org.bouncy.util.io.pem.PemObject;
import co.org.bouncy.util.io.pem.PemObjectParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CertificateFactory;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMReader

private class PEMReader$X509CRLParser
    implements PemObjectParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        ByteArrayInputStream bIn = new ByteArrayInputStream(obj.getContent());
        try
        {
            CertificateFactory certFact = CertificateFactory.getInstance("X.509", provider);
            return certFact.generateCRL(bIn);
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("problem parsing cert: ").append(e.toString()).toString(), e);
        }
    }

    private String provider;
    final PEMReader this$0;

    public PEMReader$X509CRLParser(String provider)
    {
        this$0 = PEMReader.this;
        super();
        this.provider = provider;
    }
}
