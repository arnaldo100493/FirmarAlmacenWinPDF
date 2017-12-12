// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OCSPReqBuilder.java

package co.org.bouncy.cert.ocsp;

import co.org.bouncy.asn1.ocsp.Request;
import co.org.bouncy.asn1.x509.Extensions;

// Referenced classes of package co.org.bouncy.cert.ocsp:
//            CertificateID, OCSPReqBuilder

private class OCSPReqBuilder$RequestObject
{

    public Request toRequest()
        throws Exception
    {
        return new Request(certId.toASN1Object(), extensions);
    }

    CertificateID certId;
    Extensions extensions;
    final OCSPReqBuilder this$0;

    public OCSPReqBuilder$RequestObject(CertificateID certId, Extensions extensions)
    {
        this$0 = OCSPReqBuilder.this;
        super();
        this.certId = certId;
        this.extensions = extensions;
    }
}
