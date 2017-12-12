// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OriginatorInformation.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.OriginatorInfo;
import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.asn1.x509.CertificateList;
import co.org.bouncy.cert.X509CRLHolder;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.util.CollectionStore;
import co.org.bouncy.util.Store;
import java.util.*;

public class OriginatorInformation
{

    OriginatorInformation(OriginatorInfo originatorInfo)
    {
        this.originatorInfo = originatorInfo;
    }

    public Store getCertificates()
    {
        ASN1Set certSet = originatorInfo.getCertificates();
        if(certSet != null)
        {
            List certList = new ArrayList(certSet.size());
            Enumeration en = certSet.getObjects();
            do
            {
                if(!en.hasMoreElements())
                    break;
                co.org.bouncy.asn1.ASN1Primitive obj = ((ASN1Encodable)en.nextElement()).toASN1Primitive();
                if(obj instanceof ASN1Sequence)
                    certList.add(new X509CertificateHolder(Certificate.getInstance(obj)));
            } while(true);
            return new CollectionStore(certList);
        } else
        {
            return new CollectionStore(new ArrayList());
        }
    }

    public Store getCRLs()
    {
        ASN1Set crlSet = originatorInfo.getCRLs();
        if(crlSet != null)
        {
            List crlList = new ArrayList(crlSet.size());
            Enumeration en = crlSet.getObjects();
            do
            {
                if(!en.hasMoreElements())
                    break;
                co.org.bouncy.asn1.ASN1Primitive obj = ((ASN1Encodable)en.nextElement()).toASN1Primitive();
                if(obj instanceof ASN1Sequence)
                    crlList.add(new X509CRLHolder(CertificateList.getInstance(obj)));
            } while(true);
            return new CollectionStore(crlList);
        } else
        {
            return new CollectionStore(new ArrayList());
        }
    }

    public OriginatorInfo toASN1Structure()
    {
        return originatorInfo;
    }

    private OriginatorInfo originatorInfo;
}
