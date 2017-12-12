// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VSDRequestBuilder.java

package co.org.bouncy.dvcs;

import co.org.bouncy.asn1.dvcs.*;
import co.org.bouncy.cms.CMSSignedData;
import java.io.IOException;
import java.util.Date;

// Referenced classes of package co.org.bouncy.dvcs:
//            DVCSRequestBuilder, DVCSException, DVCSRequest

public class VSDRequestBuilder extends DVCSRequestBuilder
{

    public VSDRequestBuilder()
    {
        super(new DVCSRequestInformationBuilder(ServiceType.VSD));
    }

    public void setRequestTime(Date requestTime)
    {
        requestInformationBuilder.setRequestTime(new DVCSTime(requestTime));
    }

    public DVCSRequest build(CMSSignedData document)
        throws DVCSException
    {
        try
        {
            Data data = new Data(document.getEncoded());
            return createDVCRequest(data);
        }
        catch(IOException e)
        {
            throw new DVCSException("Failed to encode CMS signed data", e);
        }
    }
}
