// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TBSCertList.java

package co.org.bouncy.asn1.x509;

import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            TBSCertList

private class TBSCertList$RevokedCertificatesEnumeration
    implements Enumeration
{

    public boolean hasMoreElements()
    {
        return en.hasMoreElements();
    }

    public Object nextElement()
    {
        return TBSCertList.CRLEntry.getInstance(en.nextElement());
    }

    private final Enumeration en;
    final TBSCertList this$0;

    TBSCertList$RevokedCertificatesEnumeration(Enumeration en)
    {
        this$0 = TBSCertList.this;
        super();
        this.en = en;
    }
}
