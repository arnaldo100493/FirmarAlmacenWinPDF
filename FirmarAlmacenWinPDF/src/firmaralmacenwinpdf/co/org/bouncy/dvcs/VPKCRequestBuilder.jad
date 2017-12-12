// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VPKCRequestBuilder.java

package co.org.bouncy.dvcs;

import co.org.bouncy.asn1.dvcs.*;
import co.org.bouncy.asn1.x509.Extension;
import co.org.bouncy.cert.X509CertificateHolder;
import java.util.*;

// Referenced classes of package co.org.bouncy.dvcs:
//            DVCSRequestBuilder, DVCSException, TargetChain, DVCSRequest

public class VPKCRequestBuilder extends DVCSRequestBuilder
{

    public VPKCRequestBuilder()
    {
        super(new DVCSRequestInformationBuilder(ServiceType.VPKC));
        chains = new ArrayList();
    }

    public void addTargetChain(X509CertificateHolder cert)
    {
        chains.add(new TargetEtcChain(new CertEtcToken(0, cert.toASN1Structure())));
    }

    public void addTargetChain(Extension extension)
    {
        chains.add(new TargetEtcChain(new CertEtcToken(extension)));
    }

    public void addTargetChain(TargetChain targetChain)
    {
        chains.add(targetChain.toASN1Structure());
    }

    public void setRequestTime(Date requestTime)
    {
        requestInformationBuilder.setRequestTime(new DVCSTime(requestTime));
    }

    public DVCSRequest build()
        throws DVCSException
    {
        Data data = new Data((TargetEtcChain[])(TargetEtcChain[])chains.toArray(new TargetEtcChain[chains.size()]));
        return createDVCRequest(data);
    }

    private List chains;
}
