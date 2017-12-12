// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VPKCRequestData.java

package co.org.bouncy.dvcs;

import co.org.bouncy.asn1.dvcs.Data;
import java.util.*;

// Referenced classes of package co.org.bouncy.dvcs:
//            DVCSRequestData, DVCSConstructionException, TargetChain

public class VPKCRequestData extends DVCSRequestData
{

    VPKCRequestData(Data data)
        throws DVCSConstructionException
    {
        super(data);
        co.org.bouncy.asn1.dvcs.TargetEtcChain certs[] = data.getCerts();
        if(certs == null)
            throw new DVCSConstructionException("DVCSRequest.data.certs should be specified for VPKC service");
        chains = new ArrayList(certs.length);
        for(int i = 0; i != certs.length; i++)
            chains.add(new TargetChain(certs[i]));

    }

    public List getCerts()
    {
        return Collections.unmodifiableList(chains);
    }

    private List chains;
}
