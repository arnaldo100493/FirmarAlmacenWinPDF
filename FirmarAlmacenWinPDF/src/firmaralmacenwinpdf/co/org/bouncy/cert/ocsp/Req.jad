// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Req.java

package co.org.bouncy.cert.ocsp;

import co.org.bouncy.asn1.ocsp.Request;
import co.org.bouncy.asn1.x509.Extensions;

// Referenced classes of package co.org.bouncy.cert.ocsp:
//            CertificateID

public class Req
{

    public Req(Request req)
    {
        this.req = req;
    }

    public CertificateID getCertID()
    {
        return new CertificateID(req.getReqCert());
    }

    public Extensions getSingleRequestExtensions()
    {
        return req.getSingleRequestExtensions();
    }

    private Request req;
}
