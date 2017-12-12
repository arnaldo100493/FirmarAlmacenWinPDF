// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OCSPReqGenerator.java

package co.org.bouncy.ocsp;

import co.org.bouncy.asn1.ocsp.Request;
import co.org.bouncy.asn1.x509.Extensions;
import co.org.bouncy.asn1.x509.X509Extensions;

// Referenced classes of package co.org.bouncy.ocsp:
//            CertificateID, OCSPReqGenerator

private class OCSPReqGenerator$RequestObject
{

    public Request toRequest()
        throws Exception
    {
        return new Request(certId.toASN1Object(), Extensions.getInstance(extensions));
    }

    CertificateID certId;
    X509Extensions extensions;
    final OCSPReqGenerator this$0;

    public OCSPReqGenerator$RequestObject(CertificateID certId, X509Extensions extensions)
    {
        this$0 = OCSPReqGenerator.this;
        super();
        this.certId = certId;
        this.extensions = extensions;
    }
}
