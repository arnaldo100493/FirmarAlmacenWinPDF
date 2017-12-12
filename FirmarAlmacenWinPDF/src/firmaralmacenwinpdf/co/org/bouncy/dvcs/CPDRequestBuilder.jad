// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CPDRequestBuilder.java

package co.org.bouncy.dvcs;

import co.org.bouncy.asn1.dvcs.*;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.dvcs:
//            DVCSRequestBuilder, DVCSException, DVCSRequest

public class CPDRequestBuilder extends DVCSRequestBuilder
{

    public CPDRequestBuilder()
    {
        super(new DVCSRequestInformationBuilder(ServiceType.CPD));
    }

    public DVCSRequest build(byte messageBytes[])
        throws DVCSException, IOException
    {
        Data data = new Data(messageBytes);
        return createDVCRequest(data);
    }
}
