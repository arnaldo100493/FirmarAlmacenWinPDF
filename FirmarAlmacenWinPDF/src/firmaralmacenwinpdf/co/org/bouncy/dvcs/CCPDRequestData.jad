// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CCPDRequestData.java

package co.org.bouncy.dvcs;

import co.org.bouncy.asn1.dvcs.Data;

// Referenced classes of package co.org.bouncy.dvcs:
//            DVCSRequestData, DVCSConstructionException, MessageImprint

public class CCPDRequestData extends DVCSRequestData
{

    CCPDRequestData(Data data)
        throws DVCSConstructionException
    {
        super(data);
        initDigest();
    }

    private void initDigest()
        throws DVCSConstructionException
    {
        if(data.getMessageImprint() == null)
            throw new DVCSConstructionException("DVCSRequest.data.messageImprint should be specified for CCPD service");
        else
            return;
    }

    public MessageImprint getMessageImprint()
    {
        return new MessageImprint(data.getMessageImprint());
    }
}
