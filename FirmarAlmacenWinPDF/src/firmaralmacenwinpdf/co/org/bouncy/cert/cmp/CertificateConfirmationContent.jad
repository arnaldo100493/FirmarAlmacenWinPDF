// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateConfirmationContent.java

package co.org.bouncy.cert.cmp;

import co.org.bouncy.asn1.cmp.CertConfirmContent;
import co.org.bouncy.operator.DefaultDigestAlgorithmIdentifierFinder;
import co.org.bouncy.operator.DigestAlgorithmIdentifierFinder;

// Referenced classes of package co.org.bouncy.cert.cmp:
//            CertificateStatus

public class CertificateConfirmationContent
{

    public CertificateConfirmationContent(CertConfirmContent content)
    {
        this(content, ((DigestAlgorithmIdentifierFinder) (new DefaultDigestAlgorithmIdentifierFinder())));
    }

    public CertificateConfirmationContent(CertConfirmContent content, DigestAlgorithmIdentifierFinder digestAlgFinder)
    {
        this.digestAlgFinder = digestAlgFinder;
        this.content = content;
    }

    public CertConfirmContent toASN1Structure()
    {
        return content;
    }

    public CertificateStatus[] getStatusMessages()
    {
        co.org.bouncy.asn1.cmp.CertStatus statusArray[] = content.toCertStatusArray();
        CertificateStatus ret[] = new CertificateStatus[statusArray.length];
        for(int i = 0; i != ret.length; i++)
            ret[i] = new CertificateStatus(digestAlgFinder, statusArray[i]);

        return ret;
    }

    private DigestAlgorithmIdentifierFinder digestAlgFinder;
    private CertConfirmContent content;
}
