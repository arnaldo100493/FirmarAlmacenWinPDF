// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DVCSRequest.java

package co.org.bouncy.dvcs;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.cms.ContentInfo;
import co.org.bouncy.asn1.cms.SignedData;
import co.org.bouncy.asn1.dvcs.DVCSObjectIdentifiers;
import co.org.bouncy.asn1.dvcs.ServiceType;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.cms.CMSSignedData;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.dvcs:
//            DVCSMessage, DVCSConstructionException, DVCSRequestInfo, CPDRequestData, 
//            VSDRequestData, VPKCRequestData, CCPDRequestData, DVCSRequestData

public class DVCSRequest extends DVCSMessage
{

    public DVCSRequest(CMSSignedData signedData)
        throws DVCSConstructionException
    {
        this(SignedData.getInstance(signedData.toASN1Structure().getContent()).getEncapContentInfo());
    }

    public DVCSRequest(ContentInfo contentInfo)
        throws DVCSConstructionException
    {
        super(contentInfo);
        if(!DVCSObjectIdentifiers.id_ct_DVCSRequestData.equals(contentInfo.getContentType()))
            throw new DVCSConstructionException("ContentInfo not a DVCS Request");
        try
        {
            if(contentInfo.getContent().toASN1Primitive() instanceof ASN1Sequence)
                asn1 = co.org.bouncy.asn1.dvcs.DVCSRequest.getInstance(contentInfo.getContent());
            else
                asn1 = co.org.bouncy.asn1.dvcs.DVCSRequest.getInstance(ASN1OctetString.getInstance(contentInfo.getContent()).getOctets());
        }
        catch(Exception e)
        {
            throw new DVCSConstructionException((new StringBuilder()).append("Unable to parse content: ").append(e.getMessage()).toString(), e);
        }
        reqInfo = new DVCSRequestInfo(asn1.getRequestInformation());
        int service = reqInfo.getServiceType();
        if(service == ServiceType.CPD.getValue().intValue())
            data = new CPDRequestData(asn1.getData());
        else
        if(service == ServiceType.VSD.getValue().intValue())
            data = new VSDRequestData(asn1.getData());
        else
        if(service == ServiceType.VPKC.getValue().intValue())
            data = new VPKCRequestData(asn1.getData());
        else
        if(service == ServiceType.CCPD.getValue().intValue())
            data = new CCPDRequestData(asn1.getData());
        else
            throw new DVCSConstructionException((new StringBuilder()).append("Unknown service type: ").append(service).toString());
    }

    public ASN1Encodable getContent()
    {
        return asn1;
    }

    public DVCSRequestInfo getRequestInfo()
    {
        return reqInfo;
    }

    public DVCSRequestData getData()
    {
        return data;
    }

    public GeneralName getTransactionIdentifier()
    {
        return asn1.getTransactionIdentifier();
    }

    private co.org.bouncy.asn1.dvcs.DVCSRequest asn1;
    private DVCSRequestInfo reqInfo;
    private DVCSRequestData data;
}
