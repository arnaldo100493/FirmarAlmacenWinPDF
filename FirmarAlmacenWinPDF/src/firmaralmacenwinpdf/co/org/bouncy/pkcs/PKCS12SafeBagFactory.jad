// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS12SafeBagFactory.java

package co.org.bouncy.pkcs;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.pkcs.ContentInfo;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.SafeBag;
import co.org.bouncy.cms.CMSEncryptedData;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.operator.InputDecryptorProvider;

// Referenced classes of package co.org.bouncy.pkcs:
//            PKCSException, PKCS12SafeBag

public class PKCS12SafeBagFactory
{

    public PKCS12SafeBagFactory(ContentInfo info)
    {
        if(info.getContentType().equals(PKCSObjectIdentifiers.encryptedData))
        {
            throw new IllegalArgumentException("encryptedData requires constructor with decryptor.");
        } else
        {
            safeBagSeq = ASN1Sequence.getInstance(ASN1OctetString.getInstance(info.getContent()).getOctets());
            return;
        }
    }

    public PKCS12SafeBagFactory(ContentInfo info, InputDecryptorProvider inputDecryptorProvider)
        throws PKCSException
    {
        if(info.getContentType().equals(PKCSObjectIdentifiers.encryptedData))
        {
            CMSEncryptedData encData = new CMSEncryptedData(co.org.bouncy.asn1.cms.ContentInfo.getInstance(info));
            try
            {
                safeBagSeq = ASN1Sequence.getInstance(encData.getContent(inputDecryptorProvider));
            }
            catch(CMSException e)
            {
                throw new PKCSException((new StringBuilder()).append("unable to extract data: ").append(e.getMessage()).toString(), e);
            }
            return;
        } else
        {
            throw new IllegalArgumentException("encryptedData requires constructor with decryptor.");
        }
    }

    public PKCS12SafeBag[] getSafeBags()
    {
        PKCS12SafeBag safeBags[] = new PKCS12SafeBag[safeBagSeq.size()];
        for(int i = 0; i != safeBagSeq.size(); i++)
            safeBags[i] = new PKCS12SafeBag(SafeBag.getInstance(safeBagSeq.getObjectAt(i)));

        return safeBags;
    }

    private ASN1Sequence safeBagSeq;
}
