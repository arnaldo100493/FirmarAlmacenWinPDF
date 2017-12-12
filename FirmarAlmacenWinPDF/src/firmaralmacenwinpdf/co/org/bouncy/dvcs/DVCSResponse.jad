// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DVCSResponse.java

package co.org.bouncy.dvcs;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.cms.ContentInfo;
import co.org.bouncy.asn1.cms.SignedData;
import co.org.bouncy.asn1.dvcs.DVCSObjectIdentifiers;
import co.org.bouncy.cms.CMSSignedData;

// Referenced classes of package co.org.bouncy.dvcs:
//            DVCSMessage, DVCSConstructionException

public class DVCSResponse extends DVCSMessage
{

    public DVCSResponse(CMSSignedData signedData)
        throws DVCSConstructionException
    {
        this(SignedData.getInstance(signedData.toASN1Structure().getContent()).getEncapContentInfo());
    }

    public DVCSResponse(ContentInfo contentInfo)
        throws DVCSConstructionException
    {
        super(contentInfo);
        if(!DVCSObjectIdentifiers.id_ct_DVCSResponseData.equals(contentInfo.getContentType()))
            throw new DVCSConstructionException("ContentInfo not a DVCS Request");
        try
        {
            if(contentInfo.getContent().toASN1Primitive() instanceof ASN1Sequence)
                asn1 = co.org.bouncy.asn1.dvcs.DVCSResponse.getInstance(contentInfo.getContent());
            else
                asn1 = co.org.bouncy.asn1.dvcs.DVCSResponse.getInstance(ASN1OctetString.getInstance(contentInfo.getContent()).getOctets());
        }
        catch(Exception e)
        {
            throw new DVCSConstructionException((new StringBuilder()).append("Unable to parse content: ").append(e.getMessage()).toString(), e);
        }
    }

    public ASN1Encodable getContent()
    {
        return asn1;
    }

    private co.org.bouncy.asn1.dvcs.DVCSResponse asn1;
}
