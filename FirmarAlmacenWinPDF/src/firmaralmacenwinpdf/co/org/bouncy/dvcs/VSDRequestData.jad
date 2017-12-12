// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VSDRequestData.java

package co.org.bouncy.dvcs;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.dvcs.Data;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.CMSSignedData;

// Referenced classes of package co.org.bouncy.dvcs:
//            DVCSRequestData, DVCSConstructionException

public class VSDRequestData extends DVCSRequestData
{

    VSDRequestData(Data data)
        throws DVCSConstructionException
    {
        super(data);
        initDocument();
    }

    private void initDocument()
        throws DVCSConstructionException
    {
        if(doc == null)
        {
            if(data.getMessage() == null)
                throw new DVCSConstructionException("DVCSRequest.data.message should be specified for VSD service");
            try
            {
                doc = new CMSSignedData(data.getMessage().getOctets());
            }
            catch(CMSException e)
            {
                throw new DVCSConstructionException("Can't read CMS SignedData from input", e);
            }
        }
    }

    public byte[] getMessage()
    {
        return data.getMessage().getOctets();
    }

    public CMSSignedData getParsedMessage()
    {
        return doc;
    }

    private CMSSignedData doc;
}
