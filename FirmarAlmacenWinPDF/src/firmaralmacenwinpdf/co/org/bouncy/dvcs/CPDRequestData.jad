// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CPDRequestData.java

package co.org.bouncy.dvcs;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.dvcs.Data;

// Referenced classes of package co.org.bouncy.dvcs:
//            DVCSRequestData, DVCSConstructionException

public class CPDRequestData extends DVCSRequestData
{

    CPDRequestData(Data data)
        throws DVCSConstructionException
    {
        super(data);
        initMessage();
    }

    private void initMessage()
        throws DVCSConstructionException
    {
        if(data.getMessage() == null)
            throw new DVCSConstructionException("DVCSRequest.data.message should be specified for CPD service");
        else
            return;
    }

    public byte[] getMessage()
    {
        return data.getMessage().getOctets();
    }
}
