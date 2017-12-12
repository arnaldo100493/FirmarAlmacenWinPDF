// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CCPDRequestBuilder.java

package co.org.bouncy.dvcs;

import co.org.bouncy.asn1.dvcs.*;

// Referenced classes of package co.org.bouncy.dvcs:
//            DVCSRequestBuilder, DVCSException, MessageImprint, DVCSRequest

public class CCPDRequestBuilder extends DVCSRequestBuilder
{

    public CCPDRequestBuilder()
    {
        super(new DVCSRequestInformationBuilder(ServiceType.CCPD));
    }

    public DVCSRequest build(MessageImprint messageImprint)
        throws DVCSException
    {
        Data data = new Data(messageImprint.toASN1Structure());
        return createDVCRequest(data);
    }
}
